
/*package logic;
=======
package logic;

>>>>>>> 9c81f1e0bcb3da58e0820a023a4fa0643f5dca6e

import security.Digester;
import service.Service;
import shared.AdminDTO;
import shared.StudentDTO;
import shared.TeacherDTO;
import shared.UserDTO;
import view.TUIAdminMenu;
import view.TUIMainMenu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

<<<<<<< HEAD
=======
/**
 * MainControlleren er den første controlleren der bliver kørt.
 */
/*

public class MainController {

    private UserDTO user;
    private Service service;
    private Digester digester;
    private AdminController adminCtrl;
    private TeacherController teacherCtrl;
    private StudentController studentCtrl;
    private TUIMainMenu tuiMainMenu;
    private TUIAdminMenu tuiAdminMenu;

    public MainController(Service service) {
        this.service = service;
        adminCtrl = new AdminController();
        teacherCtrl = new TeacherController();
        studentCtrl = new StudentController();
    }*/

  /**
  * Dette er login metoden som er baseret på variablerne mail og password, som er indtastet af den studerende eller læreren.
  **//*
    public void login (String mail, String password) {

  /**
  * Her hashes passwordet (med salt), som så derefter er et sikkret password, (det er her anden gang det hashes)
  **//*
        String securedPassword = Digester.hashWithSalt(password);

        try {
/**
 * Databasen bliver kaldt, for og validere om passwordet og mailen er en eksisterende bruger.
 **//*
            user = serviceImpl.loginStudent(mail, securedPassword);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }

 /**
 * Der tjekkes her i en if, om brugeren der logger ind er en teacher eller student. Disse to brugere er dem som kan logge ind i klienten, hvor admin kun er tilhørende i TUI
 **//*
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
      /**
       * else træder i kraft ved forkert mail eller password, der er her mulighed for et output til klienten om fejl ved login.
        else {
            System.out.println("Forkert log in!");
<<<<<<< HEAD
            DataOutputStream OutToClient = new DataOutputStream();
        }**/
/*
      public void loginAdmin(){

          String mail = "";
          String password = "";
          tuiMainMenu.TUILogIn(mail, password);
 /**
 * I dette tilfælde (hvor der logges ind gennem terminalen) er der ikke hashet første gang (hvor passwordet sendes fra klient til server)
 * Derfor hashes der to gange her for og få den rigtige sikkerhed.
 **//*
          String securedPassword = Digester.hashWithSalt(password);

          String securedPassword1 = Digester.hashWithSalt(securedPassword);

          try {
 /**
 *Kommunikation med databasen, serviceImpl klassen bliver kaldt og metoden loginStudent køres.
 **//*
          user = serviceImpl.loginStudent(mail, securedPassword1);
          }
          catch (SQLException e) {
          System.out.print(e.getMessage());
          }

 /**
 * En if statement der validere om brugeren der logger in er af typen admin eller kan der ikke logges ind i TUI.
 **//*
          if (user.getType().equals("admin")) {
              adminCtrl = new AdminController();
              //adminCtrl.loadAdmin(user);

              AdminDTO adminDTO = new AdminDTO();
              adminDTO.setCbsMail(mail);
              adminDTO.setPassword(securedPassword1);

              tuiAdminMenu.Menu(adminDTO);
          }

 /**
 * En else statement der træder i kraft hvis der er indtastet et forkert mail eller password.
 **//*
          else {
              System.out.println("Du indtastede en forkert vaerdi, proev igen. \n");
              tuiMainMenu.TUILogIn(mail, password);
          }


      }

}
<<<<<<< HEAD
*/

