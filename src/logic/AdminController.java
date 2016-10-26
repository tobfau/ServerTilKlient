package logic;

import security.Digester;
import service.DBWrapper;
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

    private AdminDTO adminDTO;
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
     * deleteComment metoden sørger for at slette en kommentar udfra et indtastet id af admin brugeren (indtastes i TUI)
     **/
    public void deleteReview() {

        /**
         * Dette er en foreach løkke som printer alle lectures ud med tilhørende id
         **/
        for (CourseDTO courseDTO : getCourses()) {
            System.out.println(courseDTO.getId() + ": " + courseDTO);
        }
        /**
         * Her kaldes tuiAdminMenuen, som spørger admin efter et id på den Course admin ønsker og se tilhørende lectures til
         */
        String idCourseChoice = "";
        //tuiAdminMenu.TUIChooseCourseId(idCourseChoice);
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede kursus f.eks. BINT2020: ");

        idCourseChoice = input.nextLine();

        /**
         * Dette er en foreach løkke som printer alle lectures ud med deres id
         **/
        for (LectureDTO lecture : getLectures(idCourseChoice)) {
            System.out.println(lecture.getId() + "id: " + lecture);

        }

        System.out.println("Indtast id for ønskede lecture: ");
        Scanner input1 = new Scanner(System.in);
        int idLectureChoice = input1.nextInt();

        /**
         * Dette er en foreach løkke som printer alle reviews ud på baggrund af den givne id, som admin har skrevet
         * ind i tuiAdminMenu.
         */
        for (ReviewDTO reviewDTO : getReviews(idLectureChoice)) {
            System.out.println(reviewDTO.getId() + " id: " + reviewDTO);

        }


        System.out.println("Indtast id for ønskede review der skal slettes: ");
        Scanner input2 = new Scanner(System.in);
        int idReviewChoice = input2.nextInt();

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
            Logging.log(e, 2, "Det indtastede id matcher ikke med nogle Review id i databsen");
        }
        tuiAdminMenu.Menu(adminDTO);

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
    public void deleteUser(AdminDTO adminDTO) {

        /**
         * Viser alle brugere i databasen.
         */
        for (UserDTO user : getUsers())
        {
            System.out.println("Id: " + user.getId() +  " Type: " + user.getType() + "  CBS mail: " + user.getCbsMail()) ;
        }

        //if bruger id ikke findes - else: tuiAdminMenu.TUIDeleteUserValidate();
        int userIdDelete = 0;

        //spørg efter id der skal slettes
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id på bruger der skal slettes: ");

        userIdDelete = input.nextInt();



        try {
            Map<String, String> userExsists = new HashMap<String, String>();
            userExsists.put("cbs_mail", String.valueOf(userIdDelete));
            ResultSet result = DBWrapper.getRecords("user", null, userExsists, null, 0);

            // if result ikke kunne returnere en værdi så findes brugeren ikke
           /* if (result.next() != ) {
                System.out.println("Brugeren findes ikke i Databasen");
                TUIAdminMenu tuiAdminMenu = new TUIAdminMenu();
                tuiAdminMenu.Menu(adminDTO);
            }*/

        } catch(SQLException e)
    {
        e.printStackTrace();
        Logging.log(e, 1, "Fejlede at finde brugeren i DB ved deleteUser" + "\n");
    }


    /**
     * Her slettes brugeren fra databasen ved hjælp af den id som der er blevet skrevet ind af admin.
     */

    Map<String, String> id = new HashMap<String, String>();

            id.put("id",String.valueOf(userIdDelete));

        try

    {
        DBWrapper.deleteRecords("user", id);
    } catch(SQLException e1) {
            e1.printStackTrace();
            Logging.log(e1, 1, "Brugeren kunne ikke slettes" + "\n");
    }
    /**
     * Hvis der ikke findes en værdi der passer med det indtastede id i Databasen,
     * vil denne catch kaste admin videre til tuiAdminMenuen, hvor man kan få muligheden for og prøve igen.
     * hvis der ingen ens værdi findes med det indtastede id og id  DB vil denne catch kaste brugeren videre til tuiAdminMenuen, hvor man kan få muligheden for og prøve igen osv.
     */
    System.out.println("Brugeren er slettet" + "\n");
        TUIAdminMenu tuiAdminMenu = new TUIAdminMenu();
        tuiAdminMenu.Menu(adminDTO);
}


    /**
     * Denne metode er til at lave en ny bruger, admin skal indtaste de tilhørende parametre: mail, password og type
     * hvorefter brugeren bliver oprettet i databasen.
     **/
    public void createUser(AdminDTO adminDTO) {

        String mail = adminDTO.getCbsMail();
        String password = adminDTO.getPassword();
        String type = adminDTO.getType();

        //Hash password ved opret bruger.
        String securePw = Digester.hashWithSalt(password);
        String securePw2 = Digester.hashWithSalt((securePw));


        AdminController adminController = new AdminController();
        adminController.getUsers();

        /**
         * For løkke der tjekker om brugeren findes i forvejen
         */
        for (AdminDTO user : getUsers()) {
            if (mail.equals(user)) {
                System.out.println("brugeren findes allerede" + "\n");
                tuiAdminMenu = new TUIAdminMenu();
                tuiAdminMenu.Menu(adminDTO);

            }
        }

                //tjekker passwordet for tal og bogstaver, om det opfylder et normalt krav til et password
                if ((password.matches(".*[a-zA-Z]+.*"))) {
                    try {
                        //oprettelse af bruger i DB
                        Map<String, String> userCreate = new HashMap<String, String>();

                        userCreate.put("cbs_mail", String.valueOf(mail));
                        userCreate.put("password", String.valueOf(securePw2));
                        userCreate.put("type", String.valueOf(type));

                        DBWrapper.insertIntoRecords("user", userCreate);
                        System.out.println("Brugeren " + mail + " er nu oprettet." + "\n");
                        tuiAdminMenu = new TUIAdminMenu();
                        tuiAdminMenu.Menu(adminDTO);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        Logging.log(e, 1, "Brugeren kunne ikke oprettes" + "\n");
                    }
                } else {
                System.out.println("Forkert værdi i password. Prøv igen " + "\n");
                tuiAdminMenu = new TUIAdminMenu();
                tuiAdminMenu.Menu(adminDTO);

            }

        //tuiAdminMenu = new TUIAdminMenu();
        //tuiAdminMenu.Menu(adminDTO);
    }

        public ArrayList<AdminDTO> getUsers() {

            ArrayList<AdminDTO> users = new ArrayList<AdminDTO>();
            try {
                String[] attributes = {"id", "cbs_mail", "type"};

                ResultSet rs = DBWrapper.getRecords("user", attributes, null, null, 0);

                while (rs.next()) {
                    AdminDTO user = new AdminDTO();
                    user.setId(rs.getInt("id"));
                    user.setCbsMail(rs.getString("cbs_mail"));
                    user.setType(rs.getString("type"));
                    users.add(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                Logging.log(e, 1, "Funktionen getUsers kunne ikke hente Brugerne" + "\n");
            }

            return users;

        }

}