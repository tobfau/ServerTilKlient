/**package dal;

import service.Service;
import shared.LectureDTO;
import shared.StudentDTO;
import shared.TeacherDTO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by emilstepanian on 12/10/2016.


public class ServiceImpl implements Service {

    private static Connection dbConnection =null;
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "repsej";

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

    public static void main(String[] args) {
        ServiceImpl d = new ServiceImpl();
        ArrayList<StudentDTO> arr = d.getStudents();
        for (StudentDTO studentDTO : arr) {
            System.out.println(studentDTO.getUsername());


        }
    }





    public StudentDTO loginStudent(String username, String password) throws SQLException {
        ResultSet resultSet = null;

        StudentDTO user = null;
        try{

        PreparedStatement approveUser = dbConnection.prepareStatement("SELECT * FROM user WHERE cbs_mail = ? AND password = ?");

        approveUser.setString(1, username);
        approveUser.setString(2, password);

        resultSet = approveUser.executeQuery();

        while (resultSet.next()) {
            user = new StudentDTO();
            user.setuser(resultSet.getString("cbs_mail"));
            user.setpassword(resultSet.getString("password"));
            user.settype(resultSet.getString("type"));
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

    public LectureDTO getLecture(String placeholder, String placeholder2) throws SQLException{
        ResultSet resultSet = null;
        LectureDTO lecture = null;

        try {
            PreparedStatement Lecture = dbConnection.prepareStatement("SELECT * FROM placeholder where placeholder = ?");

            Lecture.setString(1, placeholder);
            Lecture.setString(2, placeholder2);

            resultSet = Lecture.executeQuery();

            while (resultSet.next()) {
                lecture = new LectureDTO();
                lecture.setplaceholder(resultSet.getString("placeholder"));
                lecture.setplaceholder2(resultSet.getString("placeholder2"));


            }
        }

    return lecture;
    }




    public boolean addReview (StudentDTO user) throws IllegalArgumentException {

        try {
            //Prepared statement der bruges i databasen
            PreparedStatement addReview = connection.prepareStatement(
                    "INSERT INTO review (rating, comment) VALUES (?, ?)");

            addReview.setString(1, user.getRating());
            addReview.setString(2, user.getComment());

            int rowsAffected = addReview.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addReview (StudentDTO user) throws IllegalArgumentException {

        try {
            //Prepared statement der bruges i databasen
            PreparedStatement addReview = connection.prepareStatement(
                    "INSERT INTO review (rating, comment) VALUES (?, ?)");

            addReview.setString(1, user.getRating());
            addReview.setString(2, user.getComment());

            int rowsAffected = addReview.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
**/