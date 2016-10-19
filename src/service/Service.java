package service;
//TODO: Class and methods needs comments and documentation.

//TODO: Cleanup unused imports.
import com.sun.org.apache.xpath.internal.operations.String;
import shared.CourseDTO;
import shared.LectureDTO;
import shared.ReviewDTO;
import shared.UserDTO;

import java.util.ArrayList;

public interface Service {

    public UserDTO login(String username, String password);
    public boolean addReview(ReviewDTO review, Integer rating, String comment);
    public boolean insertCourse(CourseDTO course);
    public ArrayList<CourseDTO> getCourses(UserDTO user);
    public ArrayList<ReviewDTO> getReviews(LectureDTO lecture);
    public boolean deleteReview(ReviewDTO review);
    public boolean deleteReviewComment(ReviewDTO review);
    public ArrayList<LectureDTO> getLectures(CourseDTO course);
}
