package dal;


import shared.*;

import java.sql.*;


//Created by emilstepanian on 12/10/2016.

public class ServiceImpl {

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
            user.setMail(resultSet.getString("cbs_mail"));
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
    

}

