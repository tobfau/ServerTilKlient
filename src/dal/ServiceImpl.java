package dal;


import service.Service;
import shared.*;

import java.sql.*;
import java.util.ArrayList;


//Created by emilstepanian on 12/10/2016.

public class ServiceImpl implements Service{

    private static Connection dbConnection =null;
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "repsej";


    /*Vi opretter DriveManager, som opretter forbindelse til SQL serveren.*/
    public ServiceImpl() {
        try {
            dbConnection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void close(){
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //Forberedt main-metode, til test af diverse metoder.

/*
    public static void main(String[] args) {
        ServiceImpl d = new ServiceImpl();
        ArrayList<StudentDTO> arr = d.getStudents();
        for (StudentDTO studentDTO : arr) {
            System.out.println(studentDTO.getUsername());


        }
    }


*/



    public UserDTO loginStudent(String username, String password) throws SQLException {
        ResultSet resultSet = null;

        StudentDTO user = null;
        try{

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
    }catch(SQLException e)

    {
        e.printStackTrace();
    } finally {
            try {
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
                close();
            }

        }
return user;

    }

    public boolean addReview (ReviewDTO review) throws IllegalArgumentException {

        try {
            //Prepared statement der bruges i databasen
            PreparedStatement addReview = dbConnection.prepareStatement(
                    "INSERT INTO review (rating, comment) VALUES (?, ?)");

            addReview.setInt(1, review.getRating());
            addReview.setString(2, review.getComment());

            int rowsAffected = addReview.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CourseDTO insertCourses (CourseDTO courses) throws SQLException {

        try{
            //Laver to preparedstatements, som først skal indsætte courses og efterfølgende hente dem ned.
            PreparedStatement insertCourses =
                    dbConnection.prepareStatement("INSERT INTO course (id, name) VALUES (?,?)");



            insertCourses.setString(1, courses.getId());
            insertCourses.setString(2, courses.getName());
            //insertCourses.setArray(4, courses.getEvents());

            //Events køres.
            insertCourses.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return courses;
    }

    public ArrayList<CourseDTO> getCourses (CourseDTO course) throws SQLException {
        ResultSet resultSet = null;
        ArrayList<CourseDTO> courses = new ArrayList();

        try{
            PreparedStatement getcourses =
                    dbConnection.prepareStatement("SELECT * FROM courses");

            resultSet = getcourses.executeQuery();

            while (resultSet.next()){
                CourseDTO allCourses = new CourseDTO();
                allCourses.setName(resultSet.getString("name"));
                allCourses.setId(resultSet.getString("id"));
                courses.add(allCourses);
            }
        }catch (SQLException e){
            e.printStackTrace();
            close();
        }
        return courses;
    }

    public void insertReview (ReviewDTO review) throws SQLException {


        try {
            //Laver to preparedstatements, som først skal indsætte reviews og efterfølgende hente dem ned.
            PreparedStatement insertReview =
                    dbConnection.prepareStatement("INSERT INTO review (rating, comment, cbsmail, lectureid) VALUES (?,?,?,?)");

            //Sætter PS, så vi akn indsætte nye reviews.
            insertReview.setInt(1, review.getRating());
            insertReview.setString(2, review.getComment());
            insertReview.setString(3, review.getCbsMail());
            insertReview.setInt(4, review.getLectureId());

            //Events køres.
            insertReview.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return;
    }


    public ArrayList<ReviewDTO> getReviews (ReviewDTO review) throws SQLException {
        ResultSet resultSet = null;
        ArrayList<ReviewDTO> reviews = new ArrayList();

        try{
            PreparedStatement getReviews =
                    dbConnection.prepareStatement("SELECT * FROM review");

            resultSet = getReviews.executeQuery();
            while (resultSet.next()){
                ReviewDTO allreview = new ReviewDTO();
                allreview.setLectureId(resultSet.getInt("lectureid"));
                allreview.setCbsMail(resultSet.getString("cbsmail"));
                allreview.setComment(resultSet.getString("comment"));
                allreview.setRating(resultSet.getInt("rating"));

                reviews.add(allreview);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return reviews;
    }


}
