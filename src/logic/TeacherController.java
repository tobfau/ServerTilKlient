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
        //double average = Double.valueOf(df.format(sumOfRatings/numberOfReviews));
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

   /* private ArrayList<LectureDTO> getLectures1(int courseId) {
        ArrayList<LectureDTO> lectures = new ArrayList<LectureDTO>();

        List<String> start1 = new ArrayList<String>();
        start1.add(0, "14.");
        start1.add(1, "december");
        start1.add(2, "2016");
        start1.add(3, "kl.");
        start1.add(4, "18:00");

        List<String> end1 = new ArrayList<String>();
        end1.add(0, "14.");
        end1.add(1, "december");
        end1.add(2, "2016");
        end1.add(3, "kl.");
        end1.add(4, "19.30");

        List<String> start2 = new ArrayList<String>();
        start2.add(0, "16.");
        start2.add(1, "december");
        start2.add(2, "2016");
        start2.add(3, "kl.");
        start2.add(4, "18:00");

        List<String> end2 = new ArrayList<String>();
        end2.add(0, "16.");
        end2.add(1, "december");
        end2.add(2, "2016");
        end2.add(3, "kl.");
        end2.add(4, "19.30");

        List<String> start3 = new ArrayList<String>();
        start3.add(0, "20.");
        start3.add(1, "december");
        start3.add(2, "2016");
        start3.add(3, "kl.");
        start3.add(4, "18:00");

        List<String> end3 = new ArrayList<String>();
        end3.add(0, "20.");
        end3.add(1, "december");
        end3.add(2, "2016");
        end3.add(3, "kl.");
        end3.add(4, "19.30");

        List<String> start4 = new ArrayList<String>();
        start3.add(0, "20.");
        start3.add(1, "december");
        start3.add(2, "2016");
        start3.add(3, "kl.");
        start3.add(4, "18:00");

        List<String> end4 = new ArrayList<String>();
        end3.add(0, "20.");
        end3.add(1, "december");
        end3.add(2, "2016");
        end3.add(3, "kl.");
        end3.add(4, "19.30");

        List<String> start5 = new ArrayList<String>();
        start3.add(0, "20.");
        start3.add(1, "december");
        start3.add(2, "2016");
        start3.add(3, "kl.");
        start3.add(4, "18:00");

        List<String> end5 = new ArrayList<String>();
        end3.add(0, "20.");
        end3.add(1, "december");
        end3.add(2, "2016");
        end3.add(3, "kl.");
        end3.add(4, "19.30");

        LectureDTO lecture1 = new LectureDTO(1, courseId, "Lecture", "Forelæsning1", start1, end1, "SP01");
        LectureDTO lecture2 = new LectureDTO(2, courseId, "Exercise", "Øvelser1", start2, end3, "SP05");
        LectureDTO lecture3 = new LectureDTO(3, courseId, "Lecture", "Forelæsning2", start3, end3, "SP10");
        LectureDTO lecture4 = new LectureDTO(4, courseId, "Lecture", "Forelæsning2", start3, end3, "SP12");
        LectureDTO lecture5 = new LectureDTO(5, courseId, "Lecture", "Forelæsning2", start3, end3, "SP15");

        lectures.add(lecture1);
        lectures.add(lecture2);
        lectures.add(lecture3);
        lectures.add(lecture4);
        lectures.add(lecture5);

        return lectures;
    }

    private ArrayList<ReviewDTO> getReviews1(int lectureId) {
        ArrayList<ReviewDTO> reviews = new ArrayList<ReviewDTO>();

        ReviewDTO review1 = new ReviewDTO(1, 1, 4, "Fedt man.", 1, false);
        ReviewDTO review2 = new ReviewDTO(2, 2, 3, "Nice", 2, false);
        ReviewDTO review3 = new ReviewDTO(3, 3, 4, "", 3, true);
        ReviewDTO review4 = new ReviewDTO(4, 4, 2, "", 4, true);
        ReviewDTO review5 = new ReviewDTO(5, 5, 0, "", 5, true);

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);

        return reviews;
    }

    public static void main(String[] args) {
        /**TeacherController teacherCtrl = new TeacherController();
        System.out.println(teacherCtrl.calculateAverageRatingOnCourse(1));**/
}