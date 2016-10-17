package logic;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.util.Iterator;

public abstract class ConfigLoader {

    public static String DB_TYPE;
    public static String DB_HOST;
    public static String DB_PORT;
    public static String DB_NAME;
    public static String DB_USER;
    public static String DB_PASS;
    public static String CBS_API_LINK;
    public static String COURSES_JSON;
    public static String HASH_SALT;
    public static String ENCRYPT_KEY;
    public static int SERVER_PORT;
    public static String DEBUG;

    public static void main(String args[]){

        parseConfig();

    }

    /**
     * Køres når server starter op. Parser configurationsfilen op i static variabler.
     */
    public static void parseConfig() {

        JsonParser jparser = new JsonParser();
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader("config.dist.json"));
            JsonObject jsonObject = jparser.parse(jsonReader).getAsJsonObject();



            for(Iterator iterator = jsonObject.entrySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();

                ConfigLoader.class.getField(key).set(key, jsonObject.get(key));

            }


          //  CBS_API_link = object.get("CBS_API_link").getAsString();
           // port = object.get("port").getAsInt();
        } catch (Exception e){

        }
    }
}

