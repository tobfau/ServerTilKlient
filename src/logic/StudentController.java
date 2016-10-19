package logic;

import dal.MYSQLDriver;
import service.DBWrapper;
import shared.LectureDTO;
import shared.ReviewDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class StudentController {

    MYSQLDriver driver = new MYSQLDriver();
    DBWrapper dbWrapper = new DBWrapper(driver);

    public static void main(String[] args) {
        StudentController controller = new StudentController();
        controller.insertReview(1);
    }


    public StudentController(){

    }

    public void insertReview(int userId, int lectureId, int rating, String comment, String cbsMail) {

        ArrayList<ReviewDTO> review = new ArrayList<ReviewDTO>();

        try {

            Map<String, String> values = new HashMap();

            values.put("userId", String.valueOf(userId));
            values.put("lectureId", String.valueOf(lectureId));
            values.put("rating", String.valueOf(rating));
            values.put("comment", String.valueOf(comment));
            values.put("cbsMail", String.valueOf(cbsMail));


            ResultSet rs = dbWrapper.insertIntoRecords("review", values);


            ReviewDTO review1 = new ReviewDTO();

            review1.setComment(rs.getString("comment"));
            review1.setRating(rs.getInt("rating"));


            review.add(review1);


        } catch (SQLException e) {

        }

    }

}
