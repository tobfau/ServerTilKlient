package logic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import shared.CourseDTO;
import shared.LectureDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by emilstepanian on 12/10/2016
 */
public class CbsConnector {
   // public static void main(String []args) {
     //   CbsConnector sc = new CbsConnector();
       // sc.start();

   // }
    public void start() {
        try {


            Gson gson = new Gson();

            ArrayList<LectureDTO> arrayListLectureDTOs = new ArrayList<LectureDTO>();

            LectureDTO lectureDTO = new LectureDTO();
            for (String string : getLectureDTOArray()) {

                lectureDTO = gson.fromJson(string, lectureDTO.getClass());
                System.out.println(lectureDTO.toString());

                arrayListLectureDTOs.add(lectureDTO);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }

    public static ArrayList<String> getLectureDTOArray() throws Exception {

        ArrayList<String> lectureDTOArray = new ArrayList<String>();
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://calendar.cbs.dk/events.php?format=json&groups=BINTO1067U_LA_E16,BINTO2056U_LA_E16,BINTO1063U_LA_E16,BINTO1064U_LA_E16");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");


        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        String lineCommaRemoved;
        while ((line = rd.readLine()) != null) {

            //System.out.println(line);
            result.append(line);
            lineCommaRemoved = line.substring(0, line.length()-1);
            lectureDTOArray.add(lineCommaRemoved);
        }
        lectureDTOArray.remove(0);
        rd.close();
        return lectureDTOArray;
    }

}