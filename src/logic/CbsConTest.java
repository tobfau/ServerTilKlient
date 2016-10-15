package logic;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import shared.CourseDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Kasper on 15/10/2016.
 */
public class CbsConTest {
    private final String coursePrefix = "BINTO";


    public CbsConTest(){
    }


    public void parseCourses(){
        try{
            //Læs Json filen og opret array med CourseDTO objekter
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("resources/courses.json"));
            CourseDTO[] courseArray = gson.fromJson(reader, CourseDTO[].class);

            //Sorterer Ha(it.) fag
            for(CourseDTO course : courseArray){
                if(course.getId().contains(coursePrefix))
                    System.out.println(course.getId());
                //getLecturesFromCourse(course.getId());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




    public static void main(String args[]) {

        CbsConTest conTest = new CbsConTest();
        conTest.parseCourses();
    }






}
