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

    public CBSParser() {
    }

    public static void parseCBSData() throws SQLException {
        parseToDatabase();
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

    public static void main(String[] args) throws SQLException {
        ConfigLoader.parseConfig();
        parseCoursesToArray();
        parseStudiesToDatabase();
        parseCoursesToDatabase();
        parseLecturesToDatabase();
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
        // Set<String> shortnameSubstrings = new HashSet<String>();
        Map<String, String> studyAttributes = new HashMap<String, String>();
        Map<String, String> courseMap = new HashMap<String, String>();

        //Da shortnames allerede er unikke i databasen, behøves der ikke nogen duplicate checker (Set)
        /*
            RET TIL CACHEDROWSET I HELE PROGRAMMET HVOR DER BRUGES RESULTSET
         */
        CachedRowSet rs = DBWrapper.getRecords("study", new String[]{"id","shortname"}, null, null, 0);


        //Overvej at lave denne til en metode for sig selv da den også bruges i parseLecturesToDatabase();
        while(rs.next()){

            //"ABCDEFG" er blot et quickfix, ellers virker lortet ikke.
            String shortname = rs.getString("shortname") + "ABCDEFG";

            String substring = shortname.substring(0,5);

            //shortnameSubstrings.add(substring);
            studyAttributes.put(substring, rs.getString("id"));
        }

        for (CourseDTO course : courseArray){

            System.out.println(course);

            String substring = course.getId().substring(0,5);

            //if(shortnameSubstrings.contains(substring)){
            if(studyAttributes.containsKey(substring)){

                courseMap.put("code", course.getName());
                courseMap.put("name", course.getId());
                courseMap.put("study_id", studyAttributes.get(substring));

                DBWrapper.insertIntoRecords("course", courseMap);
            }
            else {

            }
        }
    }

    private static void parseLecturesToDatabase() throws SQLException{
        Map<String, String> courseAttributes = new HashMap<String, String>();
        Map<String, String> lectureMap;

        CachedRowSet rs = DBWrapper.getRecords("course", new String[]{"id",}, null, null, 0);


        while(rs.next()){

            //Assign all courses from courseArray corresponding id's from database
            for (CourseDTO course : courseArray){
                course.setId(rs.getString("id"));

            }

            //"ABCDEFG" er blot et quickfix, ellers virker lortet ikke.
            String shortname = rs.getString("name") + "ABCDEFG";

            String substring = shortname.substring(0,5);

            //shortnameSubstrings.add(substring);
            courseAttributes.put(substring, rs.getString("id"));
        }

        try{
            String urlPrefix = ConfigLoader.CBS_API_LINK;
            URL url;
            HttpURLConnection conn;
            BufferedReader br;


            //Læs Json fra calendar.cbs.dk for hvert kursus og fyld lektioner ind i kursets arrayliste.
            for(CourseDTO course : courseArray) {
                System.out.println(course.getId());
                url = new URL(urlPrefix + course.getId());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //Tomt kursus-object nødvendigt for at indlæse JSON
                CourseDTO tempCourse = gson.fromJson(br, CourseDTO.class);
                course.setEvents(tempCourse.getEvents());

                for (LectureDTO lecture : course.getEvents()){
                    lectureMap = new HashMap<String, String>();

                    lectureMap.put("course_id", course.getId());
                    lectureMap.put("type", lecture.getType());
                    lectureMap.put("description", lecture.getDescription());
                    /**
                     *
                     * VI ER NÅET HER TIL
                     *
                     *
                     *
                     *
                     *
                     */


                    // DBWrapper.insertIntoRecords("course", courseMap);



                }

                //TODO: Lav debug på anden måde end at skrive strengen ud. Så dette skal fjernes.
               /* for (LectureDTO lecture : course.getEvents()) {
                    System.out.println(lecture.toString());
                }*/

            }

        }
        catch(MalformedURLException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }


    private static void parseToDatabase() throws SQLException {
        Map<String, String> courseValues = new HashMap<String, String>();

        parseStudiesToDatabase();



        //Tjek om studiet er blevet oprettet. Hvis ikke, da opret i database og tilføj til Set for at markere, det er oprettet.
        /**   studyName = course.getId().substring(0,5);
         if(!studyNames.contains(studyName)){
         studyNames.add(studyName);

         //Opret studie i databasen
         studyValues.put("name",studyName);
         DBWrapper.insertIntoRecords("study",studyValues);
         }*/




       /* for (CourseDTO course : courseArray){


            /**
            String[] string = {};
            ResultSet rs = dbWrapper.getRecords("study",string,null,null,0);
            int i;

            try{

                while (rs.next()) {
                    if(studyName == rs.getString("name")){
                        i = rs.getInt("id");
                        courseValues.put("code", course.getId());
                        courseValues.put("name", course.getName());
                        courseValues.put("study_id", String.valueOf(i));
                        System.out.println("Inde i while");
                        dbWrapper.insertIntoRecords("course", courseValues);
                    }
                }

            } catch (Exception e ){

            }

        } */
    }

    //TODO: Brugt til testing - udkommenteret men får lige lov at stå her lidt.

/*
    public static void main(String args[]) {
        CBSParser.parseCBSData();


    }*/

}
