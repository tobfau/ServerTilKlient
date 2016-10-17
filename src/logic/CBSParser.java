package logic;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import shared.CourseDTO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kasper on 15/10/2016.
 */
public class CBSParser {
    private CourseDTO[] courseArray;
    private Gson gson;

    public CBSParser() {
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

    //TODO: Brugt til testing - udkommenteret men får lige lov at stå her lidt.
    /*
    public static void main(String args[]) {
        CBSParser conTest = new CBSParser();
        conTest.parseCoursesToArray();
        conTest.parseLectures();

    }
    */
}
