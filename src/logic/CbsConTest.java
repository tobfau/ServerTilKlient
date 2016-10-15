package logic;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import shared.CourseDTO;
import shared.LectureDTO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kasper on 15/10/2016.
 */
public class CbsConTest {
    //private final String coursePrefix = "BINTO";
    private CourseDTO[] courseArray;
    private ArrayList<CourseDTO> sortedCourses;
    private Gson gson;



    public CbsConTest() {
        gson = new Gson();
    }


    public void parseCoursesToArray() {
        try {
            //Læs Json filen og opret array med CourseDTO objekter
            JsonReader reader = new JsonReader(new FileReader("resources/courses.json"));
            courseArray = gson.fromJson(reader, CourseDTO[].class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void parseLectures(){
        try{
            String urlPrefix = "https://calendar.cbs.dk/events.php?format=json&groups=";
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

            }
        }
        catch(MalformedURLException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }


    public static void main(String args[]) {

        CbsConTest conTest = new CbsConTest();
        conTest.parseCoursesToArray();
        conTest.parseLectures();
    }






}
