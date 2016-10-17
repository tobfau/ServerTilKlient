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
    private TeacherController teacherCtrl;
    private StudentController studentCtrl;

    public MainController(ServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
        adminCtrl = new AdminController();
        teacherCtrl = new TeacherController();
        studentCtrl = new StudentController();
    }

    public void login () {

        String mail = "as";
        String password = "123456";

        //hashing
        String securedPassword = Digester.hashWithSalt(password);

        try {
            //Kommunikation med databasen, serviceImpl klassen bliver kaldt og metoden loginStudent køres.
            user = serviceImpl.loginStudent(mail, securedPassword);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }
            if (user.getType().equals("admin")) {
                adminCtrl = new AdminController();
                //adminCtrl.loadAdmin(user);
            }
            if (user.getType().equals("teacher")) {
                teacherCtrl = new TeacherController();
                //teacherCtrl.loadTeacher(user);
            }
            if (user.getType().equals("student")) {
                studentCtrl = new StudentController();
                //studentCtrl.loadStudent(user);
            }
    }
      /*  //else træder i kraft ved forkert mail eller password, der er her mulighed for et output til klienten om fejl ved login.
        else {
            System.out.println("Forkert log in!");
            //DataOutputStream OutToClient = new DataOutputStream();
        }*/
}