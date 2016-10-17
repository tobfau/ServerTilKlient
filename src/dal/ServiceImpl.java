/**package dal;


import service.Service;
import shared.*;

import java.sql.*;
import java.util.ArrayList;


//Created by emilstepanian on 12/10/2016.

public class ServiceImpl implements Service {

    private static Connection dbConnection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "repsej";


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


    //Forberedt main-metode, til test af diverse metoder.


/*    public static void main(String[] args) {
        ServiceImpl d = new ServiceImpl();
        ArrayList<StudentDTO> arr = d.getStudents();
        for (StudentDTO studentDTO : arr) {
            System.out.println(studentDTO.getUsername());


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


    public ArrayList<UserDTO> getUsers(UserDTO users) throws SQLException {
        ResultSet resultSet = null;
        ArrayList<UserDTO> usersArr = new ArrayList();

        try {
            PreparedStatement getcourses =
                    dbConnection.prepareStatement("SELECT (id,cbs_mail,type)  FROM user");

            resultSet = getcourses.executeQuery();

            while (resultSet.next()) {

                UserDTO allUsers = new UserDTO();
                allUsers.setCbsMail(resultSet.getString("cbs_mail"));
                allUsers.setType(resultSet.getString("type"));
                allUsers.setId(resultSet.getInt("id"));
                usersArr.add(allUsers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return usersArr;
    }




    public UserDTO createUser(UserDTO user) throws SQLException {

        StudentDTO userStudent = null;
        try {

            PreparedStatement createStudentStatement = dbConnection.prepareStatement("INSERT INTO user (cbs_mail, password, type)" +
                    "VALUES (?, ?, ?)");


            createStudentStatement.setString(1, userStudent.getCbsMail());
            createStudentStatement.setString(2, userStudent.getPassword());

            createStudentStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
        }

    public UserDTO deleteUser(UserDTO user) throws SQLException {

        StudentDTO userStudent = null;

        try {

            PreparedStatement deleteStudentStatement = dbConnection.prepareStatement("DELETE FROM user WHERE id = ?");

            deleteStudentStatement.setInt(1, userStudent.getId());


            deleteStudentStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }





    public boolean addReview(int rating, String comment) throws IllegalArgumentException {

        try {
            //Prepared statement der bruges i databasen
            PreparedStatement addReview = dbConnection.prepareStatement(
                    "INSERT INTO review (rating, comment) VALUES (?, ?)");

            addReview.setInt(1, rating);
            addReview.setString(2, comment);

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

        try {
            //Laver to preparedstatements, som først skal indsætte courses og efterfølgende hente dem ned.
            PreparedStatement insertCourses =
                    dbConnection.prepareStatement("INSERT INTO course (study_id, name) VALUES (?,?)");


            insertCourses.setString(1, courses.getId());
            insertCourses.setString(2, courses.getName());
            //insertCourses.setArray(4, courses.getEvents());

            //Events køres.
            insertCourses.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return courses;
    }

    public ArrayList<CourseDTO> getCourses(UserDTO user) throws SQLException {
        ResultSet resultSet = null;
        ArrayList<CourseDTO> courses = new ArrayList();

        try {
            PreparedStatement getcourses =
                    dbConnection.prepareStatement("SELECT * FROM course c\n" +
                            "JOIN course_attendant ca\n" +
                            "ON ca.user_id =" + user.getId());

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

    public ArrayList<ReviewDTO> getReviews(LectureDTO lecture) throws SQLException {
        ResultSet resultSet = null;
        ArrayList<ReviewDTO> reviews = new ArrayList();

        try {
            PreparedStatement getReviews =
                    dbConnection.prepareStatement(
                            "SELECT * FROM review r" +
                                    "JOIN lecture l" +
                                    "ON r.lecture_id = l.id" +
                                    "JOIN course c" +
                                    "ON l.course_id = c.id" +
                                    "JOIN course_attendant ca" +
                                    "ON ca.course_id = c.id" +
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




    public boolean deleteReview(ReviewDTO delete) throws SQLException {

        try {
            PreparedStatement deleteReview =
                    dbConnection.prepareStatement("DELETE FROM review WHERE id = ?");

            deleteReview.setInt(1, delete.getId());

            deleteReview.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return true;
    }

    public boolean deleteReviewComment(ReviewDTO deletecomment) throws SQLException {

        try {
            PreparedStatement deleteReview =
                    dbConnection.prepareStatement("UPDATE review SET comment_is_deleted = 1 WHERE id = ?");

            deleteReview.executeUpdate();

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


        try {
            //Laver to preparedstatements, som først skal indsætte reviews og efterfølgende hente dem ned.
            PreparedStatement insertReview =
                    dbConnection.prepareStatement("INSERT INTO review (rating, comment, lectureid) VALUES (?,?,?)");

            //Sætter PS, så vi akn indsætte nye reviews.
            insertReview.setInt(1, rating);
            insertReview.setString(2, comment);
            insertReview.setInt(3, lectureid);

            //Events køres.
            insertReview.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return true;
    }







}*/