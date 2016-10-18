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
import java.util.*;

/**
 * Created by Kasper on 15/10/2016.
 */
public class CBSParser {
    private static CourseDTO[] courseArray;
    private static Gson gson = new Gson();

    private static MYSQLDriver mysqlDriver = new MYSQLDriver();
    private static DBWrapper dbWrapper = new DBWrapper(mysqlDriver);

    public CBSParser() {
    }

    public static void parseCBSData() {
        parseCoursesToArray();
        parseLectures();
        parseToDatabase();
    }

    private static void parseCoursesToArray() {
        try {
            //Læs Json filen og opret array med CourseDTO objekter
            JsonReader reader = new JsonReader(new FileReader("resources/courses.json"));
            courseArray = gson.fromJson(reader, CourseDTO[].class);
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

    private static void parseToDatabase() {
        Set<String> studyNames = new HashSet<String>();
        Map<String, String> studyValues = new HashMap<String, String>();
        Map<String, String> courseValues = new HashMap<String, String>();
        String studyName;

        for (CourseDTO course : courseArray){

            int counter = 0;
            counter++;
            if(counter == 50){
            }

            //Tjek om studiet er blevet oprettet. Hvis ikke, da opret i database og tilføj til Set for at markere, det er oprettet.
            studyName = course.getId().substring(0,5);
            if(!studyNames.contains(studyName)){
                studyNames.add(studyName);

                //Opret studie i databasen
                studyValues.put("name",studyName);
                dbWrapper.insertIntoRecords("study",studyValues);
            }

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
                        dbWrapper.insertIntoRecords("course", courseValues);
                    }
                }

            } catch (Exception e ){

            }


            //Opret kursus i database og tilknyt det fagkoden

        }
    }

    //TODO: Brugt til testing - udkommenteret men får lige lov at stå her lidt.


    public static void main(String args[]) {
        CBSParser.parseCBSData();


    }

}
