package logic;

import dal.ServiceImpl;
import security.Digester;
import shared.UserDTO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class MainController {

    private UserDTO user;
    private ServiceImpl servicelmpl;
    private Digester digester;
    private AdminController adminController;
    private StudentController studentController;
    private TeacherController teacherController;

    public void logIn (String mail, String password) throws SQLException {

        //hashing
        String passwor = Digester.hashWithSalt(password);

        //Komminukation med databasen, servicelmpl klassen bliver kaldt og metoden loginstudent køres.
        servicelmpl.loginStudent(mail, password);

        String mail1 = servicelmpl.loginStudent(mail, password).getCbsMail();
        String password1 = servicelmpl.loginStudent(mail, password).getPassword();

        //Validering med mail og password gennem databasen
        if(mail.equals(mail1) && password.equals(password1) ){
            if (servicelmpl.loginStudent(mail, password).getType() == "student"){
                studentController = new StudentController();
            }
            if (servicelmpl.loginStudent(mail, password).getType() == "teacher" ){
                teacherController = new TeacherController();
            }
            if (servicelmpl.loginStudent(mail, password).getType() == "admin"){
                adminController = new AdminController();
            }
        }
        //else træder i kraft ved forkert mail eller password, der er her mulighed for et output til klienten om fejl ved login.
        else{
            System.out.println("Forkert log in!!!!!!!!!!!!!!!!!");
            //DataOutputStream OutToClient = new DataOutputStream();
        }
    }
}
