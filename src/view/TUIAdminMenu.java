package view;

import dal.ServiceImpl;
import logic.AdminController;
import shared.AdminDTO;
import shared.StudentDTO;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Her ses TUIAdminMenuen, som er admins TUI menu.
 **/
public class TUIAdminMenu {

    private AdminController adminController;
    private StudentDTO studentDTO;
    private AdminDTO adminDTO;

    public void Menu(AdminDTO adminDTO) {
        adminController = new AdminController();
        Scanner input = new Scanner(System.in);
        try {

            /**
             * Her ses menuens muligheder
             **/

            System.out.println("Velkommen til Undervisningsevaluering for CBS studerende og professorer!");
            System.out.println("Du er logget ind som Admin" /* brugernavn?*/);
            System.out.println("Tast 0 for at stoppe programmet og log ud.");
            System.out.println("Tast 1 for at oprette en ny bruger. ");
            System.out.println("Tast 2 for slet en bruger. ");
            System.out.println("Tast 3 for slet en kommentar");

            /**
             * Her bruges brugerens valg i menuen, i en switch som sender brugeren videre til den tilhørende case.
             * Den tilhørende case sender brugeren videre til den metode der er valgt.
             **/

            int choice = input.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Programmet er stoppet og du er logget ud.");
                    break;

                case 1:
                    adminController.createUser();
                    break;

                case 2:
                    adminController.deleteUser(studentDTO);
                    break;

                case 3:
                    adminController.deleteComment();
                    break;

                default:
                    System.out.println("Du indtastede en forkert vaerdi, proev igen.\n");
                    Menu(adminDTO);
            }

	/**
     * Her er en catch som tr�der frem, hvis brugeren taster en forkert vaerdi.
     **/
        } catch (InputMismatchException e) {
            System.out.printf("Systemet fandt fejlen: %s \n", e);
            System.out.println("Du indtastede ikke et heltal, menuen kører forfra");
            input.nextLine();
        }
    }

    /**
     * TUIChooseLectureId er til burgeren når der skal vælges en lecture, logikken til denne metode ses i AdminControlleren
     **/
    public void TUIChooseLectureId(int idLectureChoice) {
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede forelæsning: ");

        idLectureChoice = input.nextInt();
    }

    /**
     * TUIChooseCourseId er til burgeren når der skal vælges en Course, logikken til denne metode ses i AdminControlleren
     **/
    public void TUIChooseCourseId(int idCourseChoice) {

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede kommentar der skal slettes: ");

        idCourseChoice = input.nextInt();
        }

    /**
     * TUICreateUser tager brugerens indput om den nye brugers information og sender variablerne videre til logikken (AdminControlleren)
     **/
    public void TUICreateUser(String mail, String password, String type){
        Scanner mail_input = new Scanner(System.in);
        System.out.println("Indtast CBS mail: ");

        Scanner password_input = new Scanner(System.in);
        System.out.println("Indtast password: ");

        Scanner type_input = new Scanner(System.in);
        System.out.println("Indtast type (Student, Teacher, Admin): ");

        mail = mail_input.nextLine();
        password = password_input.nextLine();
        type = type_input.nextLine();

    }

    /**
     * TUIDeleteUser er til sletning af en bruger. i Logikken listes alle brugerne med id hvorefter admin kan slette en bruger udfra id.
     **/
    public void TUIDeleteUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id på bruger der skal slettes: ");

        String userId = input.nextLine();
        //det gemte brugernavn skal sendes til Service

        /**
         * her ses en menu, i tilfældet af at admin alligevel ikke vil slette en bruger.  
         **/
        if (userId.equals(userIdFromDb)) {
            System.out.println("Brugeren " +  + " er nu fjernet. \n");
        } else {
            System.out.println("Forkerte værdier er indtastet.");
            System.out.println("Tast 0 for at stoppe programmet.");
            System.out.println("Tast 1 for at prøve igen.");
            System.out.println("Tast 2 for at gå tilbage til menuen.");


            int choice = input.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Programmet er stoppet.");
                    break;
                case 1:
                    adminController.deleteUser(studentDTO);
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
}
