package logic;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;

public abstract class ConfigLoader {

    public static String CBS_API_link;
    public static int port;

    public static void main(String args[]){

        parseConfig();

    }

    /**
     * Køres når server starter op. Parser configurationsfilen op i static variabler.
     */
    public static void parseConfig() {

        JsonParser jparser = new JsonParser();
        JsonReader jsonReader;
        Gson gson;

        try {
            jsonReader = new JsonReader(new FileReader("config.dist.json"));
            JsonObject object = jparser.parse(jsonReader).getAsJsonObject();


            CBS_API_link = object.get("CBS_API_link").getAsString();
            port = object.get("port").getAsInt();
        } catch (Exception e){

        }
    }
}

