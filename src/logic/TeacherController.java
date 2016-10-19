package logic;

import shared.ReviewDTO;
import shared.TeacherDTO;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TeacherController extends UserController {

    private TeacherDTO currentAdmin;

    public void loadAdmin(TeacherDTO currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    public double calculateAverageRatingOnLecture(int lectureId) {
        //DecimalFormat df = new DecimalFormat("#.00");

        getReviews(lectureId);

        int numberOfReviews = getReviews(lectureId).size();
        int sumOfRatings = 0;

        for (ReviewDTO review : getReviews(lectureId)) {
            sumOfRatings = sumOfRatings + review.getRating();
        }
        //double average = Double.valueOf(df.format(sumOfRatings/numberOfReviews));
        double average = sumOfRatings/numberOfReviews;

        return average;
    }

    public double calculateAverageRatingOnCourse() {

    }

}