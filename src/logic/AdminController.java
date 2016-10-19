package logic;

import dal.MYSQLDriver;
import dal.ServiceImpl;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import service.DBWrapper;
import service.Service;
import shared.*;
import view.TUIAdminMenu;

import java.sql.SQLException;
import java.util.*;

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


        try {

            Map<String, String> id = new HashMap<>();

            id.put("id", String.valueOf(idReviewChoice));

            Map<String, String> commentDelete = new HashMap<>();

            commentDelete.put("is_deleted", "1");

            DBWrapper.updateRecords("review", id, commentDelete);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        tuiAdminMenu.Menu(adminDTO);
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
        for (userDTO : super.getUsers()) {
            System.out.println(userDTO.getId() + "id: " + userDTO);
        }

        int idUserChoice = 0;
        tuiAdminMenu.TUIDeleteUser(idUserChoice);

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede bruger der skal slettes: ");

        int idUserChoiceDelete = input.nextInt();

        //useridFromDb hentes
        try {
            Map<String, String> userId = new HashMap<>();

            userId.put("id", String.valueOf(idUserChoiceDelete));

            DBWrapper.getRecords("user", null, userId, null, 0);

                tuiAdminMenu.TUIDeleteUserMenu(idUserChoiceDelete);
        }

        //hvis der ingen ens værdi findes med det indtastede id og id i DB vil denne catch kaste brugeren videre til tuiAdminMenuen, hvor man kan få muligheden for og prøve igen osv.
        catch (SQLException e){
            e.printStackTrace();
            tuiAdminMenu.TUIDeleteUserValidate();
        }

        //sletning af bruger.
        try {

            Map<String, String> id = new HashMap<>();

            id.put("id", String.valueOf(idUserChoiceDelete));

            DBWrapper.deleteRecords("user", id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

  /**
  *Denne metode er til og lave en ny bruger, admin skal indtaste mail, password og type af bruger.
  * hvorefter brugeren bliver oprettet i databasen.
  **/
    public void createUser(){
        String mail= "";
        String password = "";
        String type = "";

       tuiAdminMenu.TUICreateUser(mail, password, type);

        //tjekker passwordet for tal og bogstaver, om det opfylder et normalt krav til et password
        if (password.matches(".*\\d+.*") && (password.matches(".*[a-zA-Z]+.*"))) {

            try {
            Map<String, String> userMail = new HashMap<>();

            userMail.put("cbs_mail", String.valueOf(mail));

            userMail.put("password", String.valueOf(password));

            userMail.put("type", String.valueOf(type));
                DBWrapper.insertIntoRecords("user",userMail);
                System.out.println("Brugeren " + mail + " er nu oprettet. ");
                tuiAdminMenu.Menu(adminDTO);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Forkert værdi i password. Prøv igen ");
            createUser();
        }
    }
}
