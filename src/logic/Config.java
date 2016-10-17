package logic;


import com.google.gson.Gson;
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
        System.out.println("dsf");


        try {
            jsonReader = new JsonReader(new FileReader("config.dist.json"));
            gson = new Gson();
            System.out.println("sdagdf");
            CBS_API_link = gson.fromJson(jsonReader, Config.CBS_API_link.getClass());

            System.out.println("2");
            System.out.println(CBS_API_link);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return CBS_API_link;


    }
}

