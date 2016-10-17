package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kasper on 17/10/2016.
 */
public class MYSQLDriver {
    private static Connection dbConnection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "repsej";


    public MYSQLDriver(){
        try{
            dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public ResultSet executeSQL(String sql){
        try {
            return dbConnection.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
