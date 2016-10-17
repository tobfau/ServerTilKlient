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
    private ServiceImpl serviceImpl;
    private Digester digester;
    private AdminController adminCtrl;
    private StudentController studentCtrl;
    private TeacherController teacherCtrl;

    public void logIn (String mail, String password) throws SQLException {

        //hashing
        String passwor = Digester.hashWithSalt(password);

        //Kommunikation med databasen, servicelmpl klassen bliver kaldt og metoden loginstudent køres.
        serviceImpl.loginStudent(mail, password);

        String mailFromDb = serviceImpl.loginStudent(mail, password).getCbsMail();
        String passwordFromDb = serviceImpl.loginStudent(mail, password).getPassword();

        //Validering med mail og password gennem databasen
        if(mail.equals(mailFromDb) && password.equals(passwordFromDb) ){
            if (serviceImpl.loginStudent(mail, password).getType() == "admin"){
                adminCtrl = new AdminController();
            }
            if (serviceImpl.loginStudent(mail, password).getType() == "teacher" ){
                teacherCtrl = new TeacherController();
            }
            if (serviceImpl.loginStudent(mail, password).getType() == "student"){
                studentCtrl = new StudentController();
            }
        }
        //else træder i kraft ved forkert mail eller password, der er her mulighed for et output til klienten om fejl ved login.
        else {
            System.out.println("Forkert log in!!!!!!!!!!!!!!!!!");
            //DataOutputStream OutToClient = new DataOutputStream();
        }
    }
}
