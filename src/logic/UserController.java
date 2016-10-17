package logic;

import dal.MYSQLDriver;
import service.DBWrapper;
import shared.LectureDTO;
import shared.UserDTO;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class UserController {

    MYSQLDriver driver = new MYSQLDriver();
    DBWrapper dbWrapper = new DBWrapper(driver);

    public UserController(){
    }


    public ArrayList<LectureDTO> getLectures(int courseId){

        ArrayList<LectureDTO> lectures = new ArrayList<LectureDTO>();

        try {
            Map<String, String> params = new HashMap();

            params.put("id", String.valueOf(courseId));


            ResultSet rs = dbWrapper.getRecords("lecture", params, 0);


            while (rs.next()){
                LectureDTO lecture = new LectureDTO();

                lecture.setType(rs.getString("type"));
                lecture.setDescription(rs.getString("description"));
                lectures.add(lecture);
            }


            } catch (SQLException e){

            }
        return lectures;
    }
}
