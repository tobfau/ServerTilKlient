package logic;

import dal.ServiceImpl;
import service.Service;
import shared.AdminDTO;
import shared.StudentDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class AdminController extends UserController {

    //currentUser

    public AdminController () {
        super(service);
    }

    public void Menu(AdminDTO adminDTO){
        Scanner input = new Scanner(System.in);
        try{

            System.out.println("Velkommen til Undervisningsevaluering for CBS studerende og professorer!");
            System.out.println("Du er logget ind som Admin" /* brugernavn?*/);
            System.out.println("Tast 0 for at stoppe programmet og log ud.");
            System.out.println("Tast 1 for at oprette en ny bruger. ");
            System.out.println("Tast 2 for slet en bruger. ");
            System.out.println("Tast 3 for slet en kommentar");

            int choice = input.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Programmet er stoppet og du er logget ud.");
                    break;

                case 1:
                    createUser();
                    break;

                case 2:
                    deleteUser();
                    break;

                case 3:
                    deleteComment();
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


    public void deleteComment() {

       for (int i : i==loadLectures(); : i++){
            //int idLectures =
            System.out.println(idLectures + loadLectures());
        }
    }

    public void deleteUser(StudentDTO student) {
        // ShowAllUsers

        // ServiceImpl.
        //int userIdFromDb = ServiceImpl.

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id på bruger der skal slettes: ");

        String userId = input.nextLine();
        //det gemte brugernavn skal sendes til Service

        if (userId.equals(userIdFromDb)) {
            System.out.println("Brugeren " + ServiceImpl. + " er nu fjernet. \n");
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

                    break;
                case 2:

                    break;
                default:
                    System.out.println("Du indtastede en forkert vaerdi, proev igen.\n");
            }
        }
    }

    public void createUser(){

    }
}
