package logic;

import shared.LectureDTO;
import shared.ReviewDTO;
import java.util.ArrayList;
import service.DBWrapper;
import shared.CourseDTO;
import shared.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.getCourses(1);
    }

    public UserController() {
    }

    public ArrayList<ReviewDTO> getReviews(int lectureId) {

        ArrayList<ReviewDTO> reviews = new ArrayList<ReviewDTO>();

        try {
            Map<String, String> params = new HashMap();
            params.put("id", String.valueOf(lectureId));
            String[] attributes = {"id", "user_id", "lecture_id", "rating", "comment", "is_deleted"};

            ResultSet rs = DBWrapper.getRecords("review", attributes, params, null, 0);

            while (rs.next()) {
                ReviewDTO review = new ReviewDTO();
                review.setId(rs.getInt("id"));
                review.setUserId(rs.getInt("user_id"));
                review.setLectureId(rs.getInt("lecture_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setDeleted(rs.getBoolean("is_deleted"));

                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public ArrayList<LectureDTO> getLectures(int courseId) {

        ArrayList<LectureDTO> lectures = new ArrayList<LectureDTO>();

        try {
            Map<String, String> params = new HashMap();

            params.put("id", String.valueOf(courseId));

            ResultSet rs = DBWrapper.getRecords("lecture", null, params, null, 0);

            while (rs.next()) {
                LectureDTO lecture = new LectureDTO();

                lecture.setType(rs.getString("type"));
                lecture.setDescription(rs.getString("description"));
                lectures.add(lecture);
            }


        } catch (SQLException e) {

        }
        return lectures;
    }

    //Metode der softdeleter et review fra databasen
    public void softDeleteReview(ReviewDTO review) {

        try {
            Map<String, String> isDeleted = new HashMap();

            isDeleted.put("is_deleted", String.valueOf(review.isDeleted()));

            Map<String, String> id = new HashMap();
            id.put("id", String.valueOf(review.getId()));

            DBWrapper.updateRecords("review", isDeleted, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CourseDTO> getCourses(int userId) {

        ArrayList<CourseDTO> courses = new ArrayList<CourseDTO>();

        try {
            Map<String, String> params = new HashMap();
            Map<String, String> joins = new HashMap();

            params.put("course_attendant.user_id", String.valueOf(userId));
            joins.put("course_attendant", "course_id");

            String[] attributes = new String[]{"name", "code", "course.id"};
            ResultSet rs = DBWrapper.getRecords("course", attributes, params, joins, 0);


            while (rs.next()) {
                CourseDTO course = new CourseDTO();

                course.setName(rs.getString("name"));
                course.setId(rs.getInt("id"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


    public UserDTO login(String cbs_email, String password) {

        UserDTO user = new UserDTO();

        try {
            Map<String, String> params = new HashMap();
            params.put("cbs_mail", String.valueOf(cbs_email));
            params.put("password", String.valueOf(password));

            String[] attributes = {"id"};
            ResultSet rs = DBWrapper.getRecords("user", attributes, params, null, 0);

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                System.out.print("User found");
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("User not found");
        return null;
    }
}