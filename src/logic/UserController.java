package logic;

import dal.MYSQLDriver;
import service.DBWrapper;
import shared.CourseDTO;
import shared.LectureDTO;
import shared.ReviewDTO;
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

    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.getCourses(1);
    }

    public UserController(){
    }

    public ArrayList<ReviewDTO> getReviews(int lectureId) {

        ArrayList<ReviewDTO> reviews = new ArrayList<ReviewDTO>();

        try {
        Map<String, String> params = new HashMap();
        params.put("id", String.valueOf(lectureId));
        String[] attributes = {"id", "user_id", "lecture_id", "rating", "commment", "comment_is_deleted"};

        ResultSet rs = dbWrapper.getRecords("review", attributes, params, null, 0);

        while (rs.next()) {
            ReviewDTO review = new ReviewDTO();
            review.setId(rs.getInt("id"));
            review.setUserId(rs.getInt("user_id"));
            review.setLectureId(rs.getInt("lecture_id"));
            review.setRating(rs.getInt("rating"));
            review.setComment(rs.getString("comment"));
            review.setCommentIsDeleted(rs.getBoolean("comment_is_deleted"));

            reviews.add(review);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public ArrayList<LectureDTO> getLectures(int courseId){

        ArrayList<LectureDTO> lectures = new ArrayList<LectureDTO>();

        try {
            Map<String, String> params = new HashMap();

            params.put("id", String.valueOf(courseId));


            ResultSet rs = dbWrapper.getRecords("lecture", null, params, null, 0);


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

    public ArrayList<CourseDTO> getCourses(int userId){

        ArrayList<CourseDTO> courses = new ArrayList<CourseDTO>();

        try {
            Map<String, String> params = new HashMap();
            Map<String, String> joins = new HashMap();

            params.put("id", String.valueOf(userId));
            joins.put("table","course_attendant");

            String[] attributes = new String[]{"name", "code"};
            ResultSet rs = dbWrapper.getRecords("course", attributes, params, null, 0);


            while (rs.next()){
                CourseDTO course = new CourseDTO();

                course.setName(rs.getString("name"));
                course.setId(rs.getString("code"));
                courses.add(course);
            }


        } catch (SQLException e){

        }
        return courses;
    }
}
