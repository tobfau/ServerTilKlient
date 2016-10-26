package logic;

import security.Digester;
import service.DBWrapper;
import shared.AdminDTO;
import shared.StudentDTO;
import shared.TeacherDTO;
import shared.UserDTO;
import view.TUIAdminMenu;
import view.TUIMainMenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * MainControlleren er den første controlleren der bliver kørt.
 */

public class MainController {

    private AdminDTO admin;
    private TeacherDTO teacher;
    private StudentDTO student;
    private Digester digester;
    private AdminController adminCtrl;
    private TeacherController teacherCtrl;
    private StudentController studentCtrl;
    private TUIMainMenu tuiMainMenu;
    private TUIAdminMenu tuiAdminMenu;

    public MainController() {
        adminCtrl = new AdminController();
        teacherCtrl = new TeacherController();
        studentCtrl = new StudentController();
    }

    /**
     * Dette er login metoden som er baseret på variablerne mail og password, som er indtastet af den studerende eller læreren.
     **/

    public void login (String mail, String password) {

        /**
         * Her hashes passwordet (med salt), som så derefter er et sikkret password, (det er her anden gang det hashes)
         **/
        String securedPassword = Digester.hashWithSalt(password);

        try {

            Map<String, String> loginMail = new HashMap<String, String>();

            loginMail.put("cbs_mail", String.valueOf(mail));
            loginMail.put("password", String.valueOf(password));

            ResultSet result = DBWrapper.getRecords("user", null, loginMail, null, 0);

            UserDTO type = new UserDTO();
            type.setType(result.getString("type"));

            if (type.equals("teacher")) {
                teacherCtrl = new TeacherController();
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setCbsMail(mail);
                teacherDTO.setPassword(securedPassword);
                teacherCtrl.loadTeacher(teacher);

            }
            if (type.equals("student")) {
                studentCtrl = new StudentController();
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setCbsMail(mail);
                studentDTO.setPassword(securedPassword);
                studentCtrl.loadStudent(student);
            }
        }

        //hvis der ingen ens værdi findes med det indtastede id og id i DB vil denne catch kaste brugeren videre til tuiAdminMenuen, hvor man kan få muligheden for og prøve igen osv.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("invalid login.");
        }
    }

    public void loginAdmin() {

        String mail = "";
        String password = "";
        tuiMainMenu.TUILogIn(mail, password);
        /**
         * I dette tilfælde (hvor der logges ind gennem terminalen) er der ikke hashet første gang (hvor passwordet sendes fra klient til server)
         * Derfor hashes der to gange her for og få den rigtige sikkerhed.
         **/
        String securedPassword = Digester.hashWithSalt(password);

        String securedPassword1 = Digester.hashWithSalt(securedPassword);

        /**
         *Kommunikation med databasen, serviceImpl klassen bliver kaldt og metoden loginStudent køres.
         **/
        try {
            Map<String, String> loginMail = new HashMap<String, String>();

            loginMail.put("cbs_mail", String.valueOf(mail));
            loginMail.put("password", String.valueOf(password));

            DBWrapper.getRecords("user", null, loginMail, null, 0);

            ResultSet result = DBWrapper.getRecords("user", null, loginMail, null, 0);

            String type = result.getString("type");

            /**
             * En if statement der validere om brugeren der logger in er af typen admin eller kan der ikke logges ind i TUI.
             **/
            if (type.equals("admin")) {
                adminCtrl = new AdminController();
                //adminCtrl.loadAdmin(user);

                AdminDTO adminDTO = new AdminDTO();
                adminDTO.setCbsMail(mail);
                adminDTO.setPassword(securedPassword1);

                tuiAdminMenu.Menu(adminDTO);
            }

        }

        catch (SQLException e) {
            System.out.print(e.getMessage());
            System.out.println("Du indtastede en forkert vaerdi, proev igen. \n");
            tuiMainMenu.TUILogIn(mail, password);
        }

    }

}