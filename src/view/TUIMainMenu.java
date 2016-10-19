package view;

import logic.AdminController;
import logic.MainController;
import security.Digester;
import shared.AdminDTO;
import shared.UserDTO;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TUIMainMenu er til den første menu som admin ser inden der logges ind.
 **/
public class TUIMainMenu {

    private MainController mainController;
    private AdminController adminController;
    private UserDTO user;
    private TUIAdminMenu tuiAdminMenu;

 /**
 * TUILogin er menuen hvor Admin kan logge ind eller lukke programmet.
 **/
    public void TUILogIn(String mail, String password) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Velkommen til Undervisningsevaluering for CBS studerende og professorer!");
            System.out.println("Tast 0 for at stoppe programmet");
            System.out.println("Tast 1 for og Logge ind som admin");

            int choice = input.nextInt();
/**
 * Denne switch tager dit input (choice) og validere det til den case der skal udføres.
 **/
            switch (choice) {
                case 0:
                    System.out.println("Programmet er stoppet og du er logget ud.");
                    break;

                case 1:
                    System.out.println("Indtast mail:");
                    mail = input.nextLine();

                    System.out.println("Indtast password:");
                    password = input.nextLine();
                    mainController.loginAdmin();

                default:
                    System.out.println("Du indtastede en forkert vaerdi, proev igen.\n");
            }
  /**
  * Her er en catch som trøder frem, hvis brugeren taster en forkert vaerdi.
  **/
        } catch (InputMismatchException e) {
            System.out.printf("Systemet fandt fejlen: %s \n", e);
            System.out.println("Du indtastede ikke et heltal, menuen kører forfra");
            input.nextLine();
        }
    }
}
