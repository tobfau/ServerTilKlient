package logic;

import dal.MYSQLDriver;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import service.DBWrapper;
import service.Service;
import shared.*;
import view.TUIAdminMenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Admin klassen er logikken bag administratorens funktioner, denne klasse hører sammen med TUIAdminMenu,
 * som er UI siden af klassen.
 **/
public class AdminController extends UserController {

    private AdminDTO currentAdmin;
    private LectureDTO lectureDTO;
    private CourseDTO courseDTO;
    private ReviewDTO reviewDTO;
    private TUIAdminMenu tuiAdminMenu;

    /**
     * AdminControlleren har nedarvet fra UserControlleren dvs. den har adgang til de samme metoder.
     **/
    public AdminController() {
        super();
    }

    /**
     * Kaldes i MainControlleren som den admin, der logges ind og anvender adminCtrl.
     * @param currentAdmin
     */
    public void loadAdmin(AdminDTO currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    /**
     * deleteComment metoden sørger for at slette en kommentar udfra et indtastet id af admin brugeren (indtastes i TUI)
     **/
    public void deleteReview() {

/**
 * Dette er en foreach løkke som printer alle lectures ud med tilhørende id
 **/
        for (CourseDTO courseDTO : getCourses()) {
            System.out.println(courseDTO.getId() + "id: " + courseDTO);
        }
/**
 * Her kaldes tuiAdminMenuen, som spørger admin efter et id på den Course admin ønsker og se tilhørende lectures til
 **/
        int idCourseChoice = 0;
        tuiAdminMenu.TUIChooseCourseId(idCourseChoice);

/**
 * Dette er en foreach løkke som printer alle courses ud med deres id
 **/
        for (LectureDTO lectureDTO : getLectures(idCourseChoice)) {
            System.out.println(lectureDTO.getId() + "id: " + lectureDTO);

        }

        int idLectureChoice = 0;
        tuiAdminMenu.TUIChooseLectureId(idLectureChoice);
        /**
         * Dette er en foreach løkke som printer alle reviews ud på baggrund af den givne id, som admin har skrevet
         * ind i tuiAdminMenu.
         */
        for (ReviewDTO reviewDTO : getReviews(idLectureChoice)) {
            System.out.println(reviewDTO.getId() + "id: " + reviewDTO);

        }


        int idReviewChoice = 0;
        tuiAdminMenu.TUIChooseReviewId(idReviewChoice);

        /**
         * Nu har vi fået et id på et review fra TUIChooseReviewId, den indtastede id findes i review database-tabelen
         * og her ændres værdien "is_deleted" til 1 dvs. den "soft deletes"
         */
        try {

            Map<String, String> id = new HashMap<String, String>();

            id.put("id", String.valueOf(idReviewChoice));

            Map<String, String> commentDelete = new HashMap<String, String>();

            commentDelete.put("is_deleted", "1");

            DBWrapper.updateRecords("review", id, commentDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tuiAdminMenu.Menu(currentAdmin);
        ;
    }

    /**
     *Her ses en else som træder i kraft ved indtasting af invalid id
     **/
 /*
        else{
            System.out.println("Invalid id");
            deleteComment();
            tuiAdminMenu.Menu(currentAdmin);
        }
  */

    /**
     * Denne metode er til at slette en bruger, hvor alle brugerne listes op i terminalen (med id)
     * og derefter kan der tastes et id ind af den bruger som skal slettes.
     **/
    public void deleteUser(int userId) {

        // ShowAllUsers

        for (UserDTO user : getUsers(userId)) {
            System.out.println(user.getId() + "id: " + user);
        }


        int idUserChoice = 0;
        tuiAdminMenu.TUIDeleteUser(idUserChoice);

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede bruger der skal slettes: ");

        int idUserChoiceDelete = input.nextInt();

        //useridFromDb hentes
        try {
            Map<String, String> userIdDb = new HashMap<String, String>();

            userIdDb.put("id", String.valueOf(idUserChoiceDelete));

            DBWrapper.getRecords("user", null, userIdDb, null, 0);

            tuiAdminMenu.TUIDeleteUserMenu(idUserChoiceDelete);
        }

        /**
         * Hvis der ikke findes en værdi der passer med det indtastede id i Databasen,
         * vil denne catch kaste admin videre til tuiAdminMenuen, hvor man kan få muligheden for og prøve igen.
         * hvis der ingen ens værdi findes med det indtastede id og id  DB vil denne catch kaste brugeren videre til tuiAdminMenuen, hvor man kan få muligheden for og prøve igen osv.
         */ catch (SQLException e) {
            e.printStackTrace();
            tuiAdminMenu.TUIDeleteUserValidate();
        }

        /**
         * Her slettes brugen fra databasen ved hjælp af den id som der er blevet skrevet ind af admin.
         */
        try {

            Map<String, String> id = new HashMap<String, String>();

            id.put("id", String.valueOf(idUserChoiceDelete));

            DBWrapper.deleteRecords("user", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Denne metode er til at lave en ny bruger, admin skal indtaste de tilhørende parametre: mail, password og type
     * hvorefter brugeren bliver oprettet i databasen.
     **/
    public void createUser() {
        String mail = "";
        String password = "";
        String type = "";

        tuiAdminMenu.TUICreateUser(mail, password, type);

        //tjekker passwordet for tal og bogstaver, om det opfylder et normalt krav til et password
        if (password.matches(".*\\d+.*") && (password.matches(".*[a-zA-Z]+.*"))) {

            try {
                Map<String, String> userMail = new HashMap<String, String>();

                userMail.put("cbs_mail", String.valueOf(mail));
                userMail.put("password", String.valueOf(password));

                userMail.put("type", String.valueOf(type));
                DBWrapper.insertIntoRecords("user", userMail);
                System.out.println("Brugeren " + mail + " er nu oprettet. ");
                tuiAdminMenu.Menu(currentAdmin);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Forkert værdi i password. Prøv igen ");
            createUser();
        }
    }

    public ArrayList<UserDTO> getUsers(int userId) {

        ArrayList<UserDTO> users = new ArrayList<UserDTO>();

        try {
            Map<String, String> params = new HashMap();
            params.put("id", String.valueOf(userId));
            String[] attributes = {"id", "cbs_mail", "type"};

            ResultSet rs = DBWrapper.getRecords("user", attributes, params, null, 0);

            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("id"));
                user.setCbsMail(rs.getString("cbs_mail"));
                user.setType(rs.getString("type"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}