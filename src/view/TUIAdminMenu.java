package view;

import logic.AdminController;
import shared.AdminDTO;
import shared.StudentDTO;
import shared.UserDTO;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Her ses TUIAdminMenuen, som er admin's text-baseret user interface menu.
 **/
public class TUIAdminMenu {

    private AdminController adminController;
    private StudentDTO studentDTO;
    private AdminDTO adminDTO;
    private UserDTO userDTO;

    public void Menu(AdminDTO adminDTO) {
        adminController = new AdminController();
        Scanner input = new Scanner(System.in);
        try {

            /**
             * Her ses de muligheder som admin har i en menu.
             **/

            System.out.println("Velkommen til Undervisningsevaluering for CBS studerende og professorer!");
            System.out.println("Du er logget ind som Admin" /* brugernavn?*/);
            System.out.println("Tast 0 for at stoppe programmet og log ud.");
            System.out.println("Tast 1 for at oprette en ny bruger. ");
            System.out.println("Tast 2 for slet en bruger. ");
            System.out.println("Tast 3 for slet en kommentar");

            /**
             * Her modtager vi admins respons til ovenstående menu, og sender dem i gennem en switch som sender
             * admin videre til den valgte metode. fx. 1 starter adminController.createUser();
             **/

            int choice = input.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Programmet er stoppet og du er logget ud.");
                    System.exit(0);
                    break;

                case 1:
                    String mail = "";
                    String password = "";
                    String type = "";

                    TUICreateUser(adminDTO);
                    break;

                case 2:
                    adminController.deleteUser(userDTO.getId());
                    break;

                case 3:
                    adminController.deleteReview();
                    break;

                default:
                    System.out.println("Du indtastede en forkert vaerdi, proev igen.\n");
                    Menu(adminDTO);
            }

	/**
     * Her er en catch som træder i kraft, hvis brugeren taster en invalid vaerdi.
     **/
        } catch (InputMismatchException e) {
            System.out.printf("Systemet fandt fejlen: %s \n", e);
            System.out.println("Du indtastede ikke et heltal, menuen kører forfra");
            input.nextLine();
        }
    }

    /**
     * TUIChooseLectureId er til admin når der skal vælges en lecture hvorfor admin ønsker at slette en kommentar.
     * Logikken bag denne metode ses i AdminControlleren.
     **/
    public void TUIChooseLectureId(int idLectureChoice) {
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede forelæsning: ");

        idLectureChoice = input.nextInt();
    }

    /**
     * TUIChooseCourseId er bruges som "søgekriterie" til at finde frem til det review der ønskes slettet.
     * Logikken bag denne metode ses i AdminControlleren
     **/
    public void TUIChooseCourseId(int idCourseChoice) {

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede kursus: ");

        idCourseChoice = input.nextInt();
        }

    /**
     * Efter at have indsnævret antallet af reviews ved først at søge ud fra courses og derefter lectures,
     * kan admin nu indtaste id på det review der ønskes slettet
     * @param idReviewChoice er id på det review som slettes fra databasen, logikken der fører den videre findes i
     * adminController
     */
    public void TUIChooseReviewId(int idReviewChoice) {

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede review: ");

        idReviewChoice = input.nextInt();
    }

    /**
     * TUICreateUser tager admins input som den nye brugers parametre: CBS mail, Password og Type
     * og sender variablerne videre til logik laget (AdminController)
     **/
    public Object TUICreateUser(AdminDTO adminDTO){

        String mail = "";
        String password = "";
        String type = "";

        Scanner mail_input = new Scanner(System.in);
        System.out.println("Indtast CBS mail: ");
        mail = mail_input.nextLine();

        Scanner password_input = new Scanner(System.in);
        System.out.println("Indtast password: ");
        password = password_input.nextLine();

        Scanner type_input = new Scanner(System.in);
        System.out.println("Indtast type (Student, Teacher, Admin): ");
        type = type_input.nextLine();


        adminDTO.setCbsMail(mail);
        adminDTO.setPassword(password);
        adminDTO.setType(type);

        AdminController adminController = new AdminController();
        adminController.createUser(adminDTO);
        return adminDTO;
    }

    /**
     * TUIDeleteUser er til sletning af en bruger. Logikken (Admincontroller) lister alle brugerne med tilhørende id
     * hvorefter admin kan slette en bruger i systemet ved at indtaste det pågældende id.
     **/
    public void TUIDeleteUser(int idUserChoice) {
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id på bruger der skal slettes: ");

        String userId = input.nextLine();
        //det gemte brugernavn skal sendes til Service

    }
    public void TUIDeleteUserMenu(int idUserChoiceDelete){

         System.out.println("Brugeren " + idUserChoiceDelete + " er nu fjernet. \n");

    }

    /**
     * TUIDeleteUserValidate træder i kraft hvis en invalid værdi er blevet givet af admin.
     */
    public void TUIDeleteUserValidate(){
        System.out.println("Forkerte værdier er indtastet.");
        System.out.println("Tast 0 for at stoppe programmet.");
        System.out.println("Tast 1 for at prøve igen.");
        System.out.println("Tast 2 for at gå tilbage til menuen.");
        Scanner input = new Scanner(System.in);

        int choice = input.nextInt();

        switch (choice) {
            case 0:
                System.out.println("Programmet er stoppet.");
                break;
            case 1:
                adminController.deleteUser(studentDTO.getId());
                break;
            case 2:
                Menu(adminDTO);
                break;
            default:
                System.out.println("Du indtastede en forkert vaerdi, proev igen.\n");
                Menu(adminDTO);
        }

    }
}

