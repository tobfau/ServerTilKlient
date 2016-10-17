package logic;

import dal.ServiceImpl;
import security.Digester;
import service.Service;
import shared.AdminDTO;
import shared.StudentDTO;
import shared.TeacherDTO;
import shared.UserDTO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class MainController {

    private UserDTO user;
    private Service service;
    private Digester digester;
    private AdminController adminCtrl;
    private TeacherController teacherCtrl;
    private StudentController studentCtrl;

    public MainController(Service service) {
        this.service = service;
        adminCtrl = new AdminController();
        teacherCtrl = new TeacherController();
        studentCtrl = new StudentController();
    }

    public void login (String mail, String password) {

        //hashing
        String securedPassword = Digester.hashWithSalt(password);

        try {

            //Kommunikation med databasen, serviceImpl klassen bliver kaldt og metoden loginStudent køres.
            user = serviceImpl.loginStudent(mail, securedPassword);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }
            if (user.getType().equals("teacher")) {
                teacherCtrl = new TeacherController();
                //teacherCtrl.loadTeacher(user);

                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setCbsMail(mail);
                teacherDTO.setPassword(securedPassword);

                //teacherCtrl.

            }
            if (user.getType().equals("student")) {
                studentCtrl = new StudentController();
                //studentCtrl.loadStudent(user);

                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setCbsMail(mail);
                studentDTO.setPassword(securedPassword);

                //studentCtrl.
            }
    }
      /*  //else træder i kraft ved forkert mail eller password, der er her mulighed for et output til klienten om fejl ved login.
        else {
            System.out.println("Forkert log in!");
            //DataOutputStream OutToClient = new DataOutputStream();
        }*/

      public void TUILogIn(){
          Scanner input = new Scanner(System.in);
          try{
          System.out.println("Velkommen til Undervisningsevaluering for CBS studerende og professorer!");
          System.out.println("Tast 0 for at stoppe programmet");
          System.out.println("Tast 1 for og Logge ind som admin");

          int choice = input.nextInt();

          switch (choice) {
              case 0:
                  System.out.println("Programmet er stoppet og du er logget ud.");
                  break;

              case 1:
                  System.out.println("Indtast mail:");
                  String mail = input.nextLine();

                  System.out.println("Indtast password:");
                  String password = input.nextLine();

                  //hashing første gang.
                  String securedPassword = Digester.hashWithSalt(password);

                  //hashing anden gang.
                  String securedPassword1 = Digester.hashWithSalt(securedPassword);

                  try {
                      //Kommunikation med databasen, serviceImpl klassen bliver kaldt og metoden loginStudent køres.
                      user = serviceImpl.loginStudent(mail, securedPassword1);
                  } catch (SQLException e){
                      System.out.print(e.getMessage());
                  }
                  if (user.getType().equals("admin")) {
                      adminCtrl = new AdminController();
                      //adminCtrl.loadAdmin(user);

                      AdminDTO adminDTO = new AdminDTO();
                      adminDTO.setCbsMail(mail);
                      adminDTO.setPassword(securedPassword1);

                      adminCtrl.Menu(adminDTO);
                  }
                  else{
                      System.out.println("Du indtastede en forkert vaerdi, proev igen. \n");
                      TUILogIn();
                  }
                  break;

              default:
                  System.out.println("Du indtastede en forkert vaerdi, proev igen.\n");
          }
          /*Her er en catch som tr�der frem, hvis brugeren taster en forkert vaerdi. */
          }catch(InputMismatchException e){
              System.out.printf("Systemet fandt fejlen: %s \n",e);
              System.out.println("Du indtastede ikke et heltal, menuen kører forfra");
              input.nextLine();
          }
    }
}