package logic;

import service.DBWrapper;
import shared.ReviewDTO;
import shared.StudentDTO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by emilstepanian on 12/10/2016.
 */

public class StudentController extends UserController {

    private StudentDTO currentStudent;

    //Test
    public static void main(String[] args) {

        StudentController controller = new StudentController();

        controller.insertReview(new ReviewDTO(1, 1, 1, "1", true));
    }


    public StudentController() {

    }

    public void loadStudent(StudentDTO currentStudent) {
        this.currentStudent = currentStudent;
    }

        //Metode til at indsætte et review i databasen

        public void insertReview (ReviewDTO review){

            try {

                Map <String, String> values = new HashMap();

                values.put("user_id", String.valueOf(review.getUserId()));
                values.put("lecture_id", String.valueOf(review.getLectureId()));
                values.put("rating", String.valueOf(review.getRating()));
                values.put("comment", String.valueOf(review.getComment()));
                values.put("is_deleted", String.valueOf(review.isDeleted()));


                DBWrapper.insertIntoRecords("review", values);


            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

}
