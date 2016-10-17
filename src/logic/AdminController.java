package logic;

import dal.ServiceImpl;
import service.Service;
import shared.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class AdminController extends UserController {

    private Service service;
    private UserDTO userDTO;
    private LectureDTO lectureDTO;
    private CourseDTO courseDTO;
    private ReviewDTO reviewDTO;
    private AdminDTO adminDTO;


    public AdminController () {
        super();
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
                    Menu(adminDTO);
            }

	/*Her er en catch som tr�der frem, hvis brugeren taster en forkert vaerdi. */
        }catch(InputMismatchException e){
            System.out.printf("Systemet fandt fejlen: %s \n",e);
            System.out.println("Du indtastede ikke et heltal, menuen kører forfra");
            input.nextLine();
        }
    }


    public void deleteComment() {

        for (LectureDTO lectures : service.getLectures(userDTO)) {

            ArrayList<LectureDTO> idLectures = service.getLectures(userDTO);
            System.out.println("id: " + idLectures + " " + lectures);

        }

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for ønskede forelæsning: ");

        int idChoice = input.nextInt();


        if (idChoice == /*lecture id*/ ){

            for (CourseDTO courseDTO: service.getCourses(userDTO) ) {

                ArrayList<CourseDTO> idCourse = service.getCourses(userDTO);
                System.out.println("id: " + idCourse + " " + courseDTO);

            }

            Scanner input1 = new Scanner(System.in);
            System.out.println("Indtast id for ønskede kommentar der skal slettes: ");

            int idChoice1 = input1.nextInt();

            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(idChoice1);

            service.deleteReviewComment(reviewDTO);
        }
        else{
            System.out.println("Invalid id");
            deleteComment();
            Menu(adminDTO);
        }
    }

    public void deleteUser(StudentDTO studentDTO) {

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
                    deleteUser(studentDTO);
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

    public void createUser(){
        Scanner mail_input = new Scanner(System.in);
        System.out.println("Indtast CBS mail: ");

        Scanner password_input = new Scanner(System.in);
        System.out.println("Indtast password: ");

        Scanner type_input = new Scanner(System.in);
        System.out.println("Indtast type (Student, Teacher, Admin): ");

        String mail = mail_input.nextLine();
        String password = password_input.nextLine();
        String type = type_input.nextLine();

        UserDTO userDTO = new UserDTO();

        //tjekker passwordet for tal og bogstaver, om det opfylder et normalt krav til et password
        if (password.matches(".*\\d+.*") && (password.matches(".*[a-zA-Z]+.*"))) {

            userDTO.setCbsMail(mail);
            userDTO.setType(type);
            userDTO.setPassword(password);
        }
        else{
            System.out.println("Forkert værdi i password. Prøv igen ");
            createUser();
        }

        //mangler metode i Service
        //Service.createUser(userDTO);
        System.out.println("Brugeren " + /* mail*/ + " er nu oprettet. ");
        Menu(adminDTO);
    }
}
