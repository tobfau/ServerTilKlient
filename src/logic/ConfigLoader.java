package logic;
//TODO: Dokumentation needed for this class!

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.sun.deploy.config.Config;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ConfigLoader {

    /* Static variable indlæses alle som String. ServerPort skal egentlig være en int, men det må løses med en
       Integer.parseInt() metode, hvor den skal bruges.
     */

    public static String DB_TYPE;
    public static String DB_HOST;
    public static String DB_PORT;
    public static String DB_NAME;
    public static String DB_USER;
    public static String DB_PASS;
    public static String CBS_API_LINK;
    public static String COURSES_JSON;
    public static String COURSES_WITH_DATA_JSON;
    public static String HASH_SALT;
    public static String ENCRYPT_KEY;
    public static String SERVER_ADDRESS;
    public static String SERVER_PORT;
    public static String DEBUG;

    private static final ConfigLoader SINGLETON = new ConfigLoader();

    public ConfigLoader getInstance(){
        return SINGLETON;
    }

    private ConfigLoader(){
        parseConfig();
    }

    /*
    public static void main(String args[]){

    }
    */

    public static void parseConfig() {

        JsonParser jparser = new JsonParser();
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader("config.json"));
            JsonObject jsonObject = jparser.parse(jsonReader).getAsJsonObject();


            Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();//will return members of your object

            for (Map.Entry<String, JsonElement> entry: entries) {
                try {
                    ConfigLoader.class.getDeclaredField(entry.getKey()).set(SINGLETON, entry.getValue().getAsString());

                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

