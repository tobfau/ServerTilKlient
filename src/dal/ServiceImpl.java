package dal;

import service.Service;
import shared.StudentDTO;
import shared.TeacherDTO;

import java.sql.*;

/**
 * Created by emilstepanian on 12/10/2016.
 */
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



}
