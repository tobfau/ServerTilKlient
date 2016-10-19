package logic;

import shared.LectureDTO;
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

    public double calculateAverageRatingOnCourse(int courseId) {

        int lectureId = 0;
        double sumOfRatingsOnCourse = 0;
        double numberOfReviews = 0;

        for (LectureDTO lecture : getLectures(courseId)) {
            lectureId = lecture.getId();

            for (ReviewDTO review : getReviews(lectureId)) {
                sumOfRatingsOnCourse = sumOfRatingsOnCourse + review.getRating();
            }
        }



        numberOfReviews = getReviews(lectureId).size();



        double average = sumOfRatingsOnCourse/numberOfLectures;

        return average;

    }
}