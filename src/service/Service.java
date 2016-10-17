package service;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import shared.CourseDTO;
import shared.LectureDTO;
import shared.ReviewDTO;
import shared.UserDTO;

import java.util.ArrayList;


public interface Service {

    public UserDTO login(String username, String password);
    public boolean addReview(ReviewDTO review);
    public CourseDTO insertCourses(CourseDTO courses);
    public ArrayList<CourseDTO> getCourses(UserDTO user);
    public boolean insertReview(ReviewDTO review);
    public ArrayList<ReviewDTO> getReviews(LectureDTO lecture);
    public boolean deleteReview(ReviewDTO review);
    public boolean deleteReviewComment(ReviewDTO review);
    public ArrayList<LectureDTO> getLectures(UserDTO users);


}
