package logic;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import shared.CourseDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Kasper on 15/10/2016.
 */
public class CbsConTest {



    public static void main(String args[]) {
        try {

            Gson gson = new Gson();
            System.out.println("sdf");
            JsonReader reader = new JsonReader(new FileReader("resources/courses.json"));

            System.out.println("hej");


            CourseDTO[] courses = gson.fromJson(reader, CourseDTO[].class);

            System.out.println(courses[0]);


            reader.beginObject();

            System.out.println(reader.nextString().toString());



            String name;


            while (reader.hasNext()){
                name = reader.nextName();
                System.out.println(name);

                if (name.equals("id")) {
                    System.out.println(reader.nextString());
                } else if (name.equals("displaytext")) {
                    System.out.println(reader.nextString());
                } else
                    reader.skipValue();
            }

            reader.endObject();
            reader.close();

        } catch (Exception e){

        }

    }






}
