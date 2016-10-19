package logic;
//TODO: Class and methods needs documentation!

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import dal.MYSQLDriver;
import service.DBWrapper;
import shared.CourseDTO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
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
        parseCoursesToArray();
        parseLectures();
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
        parseStudiesToDatabase();
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

                if(!duplicatesCheck.contains(obj.get("study-name").toString())){
                    studyValues.put("shortname",obj.get("shortname").toString());
                    studyValues.put("name",obj.get("study-name").toString());
                    duplicatesCheck.add(obj.get("study-name").toString());
                    DBWrapper.insertIntoRecords("study", studyValues);
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void parseLectures(){
        try{
            String urlPrefix = ConfigLoader.CBS_API_LINK;
            URL url;
            HttpURLConnection conn;
            BufferedReader br;


            //Læs Json fra calendar.cbs.dk for hvert kursus og fyld lektioner ind i kursets arrayliste.
            for(CourseDTO course : courseArray) {
                url = new URL(urlPrefix + course.getId());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //Tomt kursus-object nødvendigt for at indlæse JSON
                CourseDTO tempCourse = gson.fromJson(br, CourseDTO.class);
                course.setEvents(tempCourse.getEvents());

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
       /** Map<String, String> courseValues = new HashMap<String, String>();

        //Parser studyNames til database

        Set<Map.Entry<String, String>> studyEntries = studyValues.entrySet();

        Map<String, String> tmpMap = new HashMap<String, String>();

        for(Map.Entry<String, String> entry : studyEntries){

            tmpMap.put(entry.getKey(),entry.getValue());
            DBWrapper.insertIntoRecords("study",tmpMap);

        }

       /** Iterator iterator = studyValues.entrySet().iterator();

        while(iterator.hasNext()){



            tmpMap.put((Map.Entry<String,String>)iterator.next().ge

            Map.Entry entry = (Map.Entry)iterator.next();
            String name = entry.getKey()

            DBWrapper.insertIntoRecords("study", );
        }
*/








/*


        for(Iterator iterator = studyValues.entrySet().iterator(); iterator.hasNext();) {

            Map<String, String> values = new HashMap<String, String>();

            values.put((Map.Entry<String, String>) iterator.)

            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();



            DBWrapper.insertIntoRecords("study", entry);
        }

*/
        //Tjek om studiet er blevet oprettet. Hvis ikke, da opret i database og tilføj til Set for at markere, det er oprettet.
           /* studyName = course.getId().substring(0,5);
            if(!studyNames.contains(studyName)){
                studyNames.add(studyName);

                //Opret studie i databasen
                studyValues.put("name",studyName);
                DBWrapper.insertIntoRecords("study",studyValues);
            }
            */



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
