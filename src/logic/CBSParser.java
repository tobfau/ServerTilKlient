package logic;
//TODO: Class and methods needs documentation!

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import service.DBWrapper;
import shared.CourseDTO;
import shared.LectureDTO;

import javax.sql.rowset.CachedRowSet;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Kasper on 15/10/2016.
 */
public class CBSParser {
    private static CourseDTO[] courseArray;
    private static Gson gson = new Gson();



    public static void parseCBSData() throws SQLException {
        ConfigLoader.parseConfig();
        parseCoursesToArray();
        parseStudiesToDatabase();
        parseCoursesToDatabase();
        parseLecturesToDatabase();
    }

    private static void parseCoursesToArray() {
        try {
            //Læs Json filen og opret array med CourseDTO objekter
            JsonReader reader = new JsonReader(new FileReader(ConfigLoader.COURSES_JSON));
            courseArray = gson.fromJson(reader, CourseDTO[].class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void parseStudiesToDatabase() {
        JsonReader jsonReader;
        JsonParser jparser = new JsonParser();

        Set<String> duplicatesCheck = new HashSet<String>();

        try {
            jsonReader = new JsonReader(new FileReader(ConfigLoader.STUDY_DATA_JSON));
            JsonArray jArray = jparser.parse(jsonReader).getAsJsonArray();

            Iterator iterator = jArray.iterator();


            while(iterator.hasNext()){

                JsonObject obj = (JsonObject) iterator.next();
                Map<String, String> studyValues = new HashMap<String, String>();

                if(!duplicatesCheck.contains(obj.get("shortname").toString().replace("\"", "").substring(0,5))){

                    studyValues.put("shortname",obj.get("shortname").toString().replace("\"", "").substring(0,5));
                    studyValues.put("name",obj.get("study-name").toString().replace("\"", ""));
                    duplicatesCheck.add(obj.get("shortname").toString().replace("\"", "").substring(0,5));
                    DBWrapper.insertIntoRecords("study", studyValues);
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void parseCoursesToDatabase() throws SQLException {
        Map<String, String> studyAttributes = new HashMap<String, String>();
        Map<String, String> courseMap = new HashMap<String, String>();

        /*
            RET TIL CACHEDROWSET I HELE PROGRAMMET HVOR DER BRUGES RESULTSET
         */
        CachedRowSet rs = DBWrapper.getRecords("study", new String[]{"id","shortname"}, null, null, 0);


        //Overvej at lave denne til en metode for sig selv da den også bruges i parseLecturesToDatabase();
        while(rs.next()){


            String substring = rs.getString("shortname").substring(0,5);

            studyAttributes.put(substring, rs.getString("id"));
        }



        for (CourseDTO course : courseArray){

            String substring = course.getId().substring(0,5);

            if(studyAttributes.containsKey(substring)){

                courseMap.put("code", course.getName());
                //courseMap.put("name", course.getId());
                courseMap.put("study_id", studyAttributes.get(substring));

                DBWrapper.insertIntoRecords("course", courseMap);
            }
        }
    }

    private static void parseLecturesToDatabase() throws SQLException{

        String urlPrefix = ConfigLoader.CBS_API_LINK;
        URL url;
        HttpURLConnection conn;
        BufferedReader br;
        Map<String, String> lectureMap;

        CachedRowSet rs = DBWrapper.getRecords("course", new String[]{"id", "name"}, null, null, 0);

        try{

            while(rs.next()){

                /*
                For hvert kursus i databasen: ind tilsvarende kursus i courseArray baseret på name
                tilskriv tilsvarende kursus-navn fra kurset i databasen
                */

                String name = rs.getString("name");


                for (CourseDTO course : courseArray){

                    if(course.getId().equals(name)){

                        url = new URL(urlPrefix + course.getId());
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        //Tomt kursus-object nødvendigt for at indlæse JSON
                        CourseDTO tempCourse = gson.fromJson(br, CourseDTO.class);
                        course.setEvents(tempCourse.getEvents());

                        //Lav lecture record i databasen for hver lectureobjekt i hvert enkelt kursus' events
                        for (LectureDTO lecture : course.getEvents()){
                            lectureMap = new HashMap<String, String>();

                            lectureMap.put("course_id", course.getId());
                            lectureMap.put("type", lecture.getType());
                            lectureMap.put("description", lecture.getDescription());

                            lectureMap.put("start", convertToDateTime(lecture.getStart()));
                            lectureMap.put("end", convertToDateTime(lecture.getEnd()));
                            lectureMap.put("location", lecture.getLocation());

                            DBWrapper.insertIntoRecords("lecture", lectureMap);

                        }
                    }
                }
            }
        }
        catch(MalformedURLException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }



    private static String convertToDateTime(List<String> dateData ){
        StringBuilder dateBuilder = new StringBuilder();

        //Sorter arraylisten, så vi tilføjer et 0 foran stringen, i det tilfælde hvor der kun står et enkelt tal
        for (int i = 0; i < dateData.size(); i++){
            if (dateData.get(i).length() < 2){
                dateData.set(i, "0" + dateData.get(i));
            }
        }

        //Byg Stringen så den matcher med formatet på et DateTime objekt som vi bruger i MySQL databasen
        dateBuilder.append(dateData.get(0));
        dateBuilder.append("-");
        //Plusser 1 måned til månedsværdien, da dataen hos CBS åbenbart er fucked og er én måned bagud.
        int month = Integer.parseInt(dateData.get(1))+1;
        dateBuilder.append(String.valueOf(month));
        dateBuilder.append("-");
        dateBuilder.append(dateData.get(2));
        dateBuilder.append(" ");
        dateBuilder.append(dateData.get(3));
        dateBuilder.append(":");
        dateBuilder.append(dateData.get(4));
        dateBuilder.append(":");
        dateBuilder.append("00");

        return dateBuilder.toString();

    }

}
