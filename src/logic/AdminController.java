package logic;

import dal.MYSQLDriver;
import dal.ServiceImpl;
import service.DBWrapper;
import service.Service;
import shared.*;
import view.TUIAdminMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Admin klassen er logikken bag administratorens funktioner, denne klasse hører sammen med TUIAdminMenu,
 * som er UI siden af klassen.
 **/
public class AdminController extends UserController {

    private Service service;
    private UserDTO userDTO;
    private LectureDTO lectureDTO;
    private CourseDTO courseDTO;
    private ReviewDTO reviewDTO;
    private AdminDTO adminDTO;
    private TUIAdminMenu tuiAdminMenu;
    MYSQLDriver driver = new MYSQLDriver();
    DBWrapper dbWrapper = new DBWrapper();

/**
* AdminControlleren har nedarvet fra UserControlleren (dens metoder)
**/
    public AdminController () {

        super();
    }

/**
* deleteComent metoden sørger for at slette en kommentar udfra et indtastet id af admin brugeren (iindtastet i terminalen)
**/
    public void deleteComment() {

/**
 * Dette er en foreach løkke som printer alle lectures ud med et id først
 **/
            for (CourseDTO courseDTO : super.getCourses()) {
            System.out.println(courseDTO.getId() + "id: " + courseDTO);
            }
/**
 * Her kaldes tuiAdminMenuen, som spørger admin efter et id på det Course, admin ønsker og se tilhørende lectures til
 **/
            int idCourseChoice = 0;
            tuiAdminMenu.TUIChooseCourseId(idCourseChoice);

/**
 * Dette er en foreach løkke som printer alle courses ud med et id først
 **/
            for (LectureDTO lectureDTO : super.getLectures(idCourseChoice)) {
                System.out.println(lectureDTO.getId() + "id: " + lectureDTO);

            }

            int idLectureChoice = 0;
            tuiAdminMenu.TUIChooseLectureId(idLectureChoice);

            for (ReviewDTO reviewDTO : super.getReview(idLectureChoice)) {
            System.out.println(reviewDTO.getId() + "id: " + reviewDTO);

            }

            int idReviewChoice = 0;
            tuiAdminMenu.TUIChooseReviewId(idReviewChoice);

            DBWrapper.updateRecords("review", "id" ,idReviewChoice);
    ;}

 /**
 *Her ses en else som træder i kraft ved indtasting af invalid id
  **/
 /*
        else{
            System.out.println("Invalid id");
            deleteComment();
            tuiAdminMenu.Menu(adminDTO);
        }
  */

  /**
  *Denne metode er til og slette en bruger, hvor alle brugerne listes op i terminalen (med id)
  * og derefter kan der tastes et id af den bruger som skal slettes.
  **/
    public void deleteUser(UserDTO userDTO) {

        // ShowAllUsers

        //int userIdFromDb = ServiceImpl.

        tuiAdminMenu.TUIDeleteUser();

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede bruger der skal slettes: ");

        int idUserChoice = input.nextInt();

        //sletning af bruger.
        //service.deleteReviewComment(idUserChoice);
    }

  /**
  *Denne metode er til og lave en ny bruger, admin skal indtaste mail, password og type af bruger.
  * hvorefter brugeren bliver oprettet i databasen.
  **/
    public void createUser(){
        String mail = "";
        String password = "";
        String type = "";

       tuiAdminMenu.TUICreateUser(mail, password, type);

        //tjekker passwordet for tal og bogstaver, om det opfylder et normalt krav til et password
        if (password.matches(".*\\d+.*") && (password.matches(".*[a-zA-Z]+.*"))) {

          /*  userDTO.setCbsMail(mail);
            userDTO.setType(type);
            userDTO.setPassword(password);*/
        }
        else{
            System.out.println("Forkert værdi i password. Prøv igen ");
            createUser();
        }

        //mangler metode i Service
        //Service.createUser(userDTO);
        System.out.println("Brugeren " + /* mail*/ + " er nu oprettet. ");
        tuiAdminMenu.Menu(adminDTO);
    }
}
