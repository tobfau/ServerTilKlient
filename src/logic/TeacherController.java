package logic;

import shared.LectureDTO;
import shared.ReviewDTO;
import shared.TeacherDTO;

public class TeacherController extends UserController {

    private TeacherDTO currentAdmin;

    public TeacherController() {
    }

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

        double average = sumOfRatings / numberOfReviews;


        return average;
    }

    public double calculateAverageRatingOnCourse(int courseId) {

        int lectureId = 0;
        double sumOfRatings = 0;
        double numberOfReviews = 0;

        // for (LectureDTO lecture : getLectures1(courseId)) {
        for (LectureDTO lecture : getLectures(courseId)) {

            lectureId = lecture.getId();
        }

        //for (ReviewDTO review : getReviews1(lectureId)) {
        for (ReviewDTO review : getReviews(lectureId)) {
            sumOfRatings = sumOfRatings + review.getRating();
        }

        //numberOfReviews = getReviews1(lectureId).size();
        numberOfReviews = getReviews(lectureId).size();

        double average = sumOfRatings / numberOfReviews;

        return average;
    }
}