package logic;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;

public class Config {

    public static String CBS_API_link;

    public static void main(String args[]){
        System.out.println(getCBSPrefix());
    }


    public static String getCBSPrefix() {

        JsonReader jsonReader;
        Gson gson;


        try {
            jsonReader = new JsonReader(new FileReader("config.dist.json"));
            gson = new Gson();

            JsonObject obj = gson.fromJson(jsonReader, JsonObject.class);

            JsonElement element = obj.get("CBS_API_link");

            System.out.println(element.getass);



            CBS_API_link = element.getAsString();

     //       JsonObject obj = gson.fromJson(jsonReader, JsonObject.class);


            //CBS_API_link = gson.fromJson(jsonReader, (String) CBS_API_link);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return CBS_API_link;


    }
}

