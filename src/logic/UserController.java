package logic;

import service.Service;
import shared.LectureDTO;
import shared.ReviewDTO;

import javax.security.auth.callback.Callback;
import java.util.ArrayList;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public abstract class UserController implements Service {

    private ArrayList<LectureDTO> lecture = new ArrayList<LectureDTO>();
    private ArrayList<ReviewDTO> review = new ArrayList<ReviewDTO>();
    private Service service;


    public UserController(Service service){

        lecture = new ArrayList<>();
        review = new ArrayList<>();

        this.service = service;
    }


    //Metode der henter alle lectures
    public void loadLectures(){

        lecture = service.getLectures();
    }


    //Metode der henter alle reviews
    public void loadReviews(){

        review = service.getReview();

    }

}