package dal;

import service.Service;
import shared.*;
import java.sql.*;
import java.util.ArrayList;


public class ServiceImpl {

    private static Connection dbConnection = null;
    private static final String URL = "jdbc:mysql://localhost:8889/undervisning";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    //Vi opretter DriveManager, som opretter forbindelse til SQL serveren.
    public ServiceImpl() {
        try {
            dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDTO loginStudent(String username, String password) throws SQLException {
        ResultSet resultSet = null;

        StudentDTO user = null;
        try {

            PreparedStatement approveUser = dbConnection.prepareStatement("SELECT * FROM user WHERE cbs_mail = ? AND password = ?");

            approveUser.setString(1, username);
            approveUser.setString(2, password);

            resultSet = approveUser.executeQuery();

            while (resultSet.next()) {
                user = new StudentDTO();
                user.setCbsMail(resultSet.getString("cbs_mail"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getString("type"));
            }
        } catch (SQLException e)

        {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                close();
            }

        }
        return user;

    }

    //HER
    public boolean addReview(ReviewDTO review, UserDTO user, int rating, String comment) throws IllegalArgumentException {

        try {
            //Prepared statement der bruges i databasen
            PreparedStatement addReview = dbConnection.prepareStatement(
                    "INSERT INTO review (lecture_id, rating, comment) VALUES (?, ?, ?)");

            addReview.setInt(1, review.getId());
            addReview.setInt(2, rating);
            addReview.setString(3, comment);

            int rowsAffected = addReview.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserDTO login(String username, String password) {
        return null;
    }

    public boolean addReview(ReviewDTO review) {
        return false;
    }

    public CourseDTO insertCourses(CourseDTO courses) throws SQLException {
        return courses;
    }

    public boolean insertCourse(CourseDTO course) throws SQLException {

        try {
            //Laver to preparedstatements, som først skal indsætte courses og efterfølgende hente dem ned.
            PreparedStatement insertCourses =
                    dbConnection.prepareStatement("INSERT INTO course (study_id, name) VALUES (?,?)");


            insertCourses.setString(1, course.getId());
            insertCourses.setString(2, course.getName());

            int rowsAffected = insertCourses.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return false;
    }

    public ArrayList<CourseDTO> getCourses(UserDTO user) throws SQLException {


        ResultSet resultSet = null;
        ArrayList<CourseDTO> courses = new ArrayList();

        try {
            PreparedStatement getcourses =
                    dbConnection.prepareStatement("SELECT c.name,c.id FROM course c" +
                            "JOIN course_attendant ca" +
                            "ON c.id = ca.course_id" +
                            "WHERE ca.user_id = ?");

            getcourses.setInt(1,user.getId());
            resultSet = getcourses.executeQuery();

            while (resultSet.next()) {
                CourseDTO allCourses = new CourseDTO();
                allCourses.setName(resultSet.getString("name"));
                allCourses.setId(resultSet.getString("id"));
                courses.add(allCourses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return courses;
    }

    public boolean insertReview(ReviewDTO review) {
        return false;
    }

    public ArrayList<LectureDTO> getLectures(CourseDTO course) throws SQLException {
        ResultSet resultSet = null;
        ArrayList<LectureDTO> lectures = new ArrayList();

        try {
            PreparedStatement getLectures =
                    dbConnection.prepareStatement("SELECT l.description, l.location, l.start, l.end FROM lecture l WHERE course_id = ?");

            getLectures.setString(1,course.getId());
            resultSet = getLectures.executeQuery();

            while (resultSet.next()) {
                LectureDTO lecture = new LectureDTO();
                lecture.setDescription(resultSet.getString("description"));
                lecture.setLocation(resultSet.getString("location"));
                lectures.add(lecture);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }

        return lectures;
    }

    public ArrayList<ReviewDTO> getReviews(LectureDTO lecture) throws SQLException {
        ResultSet resultSet = null;
        ArrayList<ReviewDTO> reviews = new ArrayList();

        try {
            PreparedStatement getReviews =
                    dbConnection.prepareStatement("SELECT * FROM review r " +
                                    "JOIN lecture l " +
                                    "ON r.lecture_id = l.id " +
                                    "JOIN course c " +
                                    "ON l.course_id = c.id " +
                                    "JOIN course_attendant ca " +
                                    "ON ca.course_id = c.id " +
                                    "WHERE l.id = ?");

            getReviews.setInt(1,lecture.getId());

            resultSet = getReviews.executeQuery();
            while (resultSet.next()) {
                ReviewDTO allreview = new ReviewDTO();
                allreview.setLectureId(resultSet.getInt("lectureid"));
                allreview.setComment(resultSet.getString("comment"));
                allreview.setRating(resultSet.getInt("rating"));
                allreview.setId(resultSet.getInt("study_id"));

                reviews.add(allreview);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return reviews;
    }

    public boolean deleteReview(ReviewDTO review) throws SQLException {

        try {
            PreparedStatement deleteReview =
                    dbConnection.prepareStatement("DELETE FROM review WHERE id = ?");

            deleteReview.setInt(1, review.getId());

            int rowsAffected = deleteReview.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return true;
    }

    public ArrayList<LectureDTO> getLectures(UserDTO users) {
        return null;
    }

    public boolean insertLectures(int rating, String comment, int lectureid) throws SQLException {
        return false;
    }
}