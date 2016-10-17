/**package logic;

import dal.ServiceImpl;
import security.Digester;
import shared.UserDTO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by emilstepanian on 12/10/2016.

public class MainController {

    private UserDTO user;
    private ServiceImpl serviceImpl;
    private Digester digester;
    private AdminController adminCtrl;
    private StudentController studentCtrl;
    private TeacherController teacherCtrl;

    public void login (String mail, String password) throws SQLException {

        //hashing
        String securedPassword = Digester.hashWithSalt(password);

        //Kommunikation med databasen, serviceImpl klassen bliver kaldt og metoden loginStudent køres.
        serviceImpl.loginStudent(mail, securedPassword);

        String mailFromDb = serviceImpl.loginStudent(mail, securedPassword).getCbsMail();
        String passwordFromDb = serviceImpl.loginStudent(mail, securedPassword).getPassword();

        //Validering med mail og password gennem databasen
        if(mail.equals(mailFromDb) && securedPassword.equals(passwordFromDb) ){
            if (serviceImpl.loginStudent(mail, securedPassword).getType() == "admin"){
                adminCtrl = new AdminController();
            }
            if (serviceImpl.loginStudent(mail, securedPassword).getType() == "teacher" ){
                teacherCtrl = new TeacherController();
            }
            if (serviceImpl.loginStudent(mail, securedPassword).getType() == "student"){
                studentCtrl = new StudentController();
            }
        }
        //else træder i kraft ved forkert mail eller password, der er her mulighed for et output til klienten om fejl ved login.
        else {
            System.out.println("Forkert log in!");
            //DataOutputStream OutToClient = new DataOutputStream();
        }
    }
}*/