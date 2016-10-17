package service;
//TODO: Class and methods needs comments and documentation.

<<<<<<< HEAD
import com.sun.org.apache.xpath.internal.operations.String;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
=======

//TODO: Cleanup unused imports.
>>>>>>> cd9a3d8c4a84a9a6177730b8a137db254d9d0e1e
import shared.CourseDTO;
import shared.LectureDTO;
import shared.ReviewDTO;
import shared.UserDTO;

import java.util.ArrayList;


public interface Service {

    public UserDTO login(String username, String password);
    public boolean addReview(Integer rating, String comment);
    public CourseDTO insertCourses(CourseDTO courses);
    public ArrayList<CourseDTO> getCourses(UserDTO user);
    public ArrayList<ReviewDTO> getReviews(LectureDTO lecture);
    public boolean deleteReview(ReviewDTO review);
    public boolean deleteReviewComment(ReviewDTO review);
    public ArrayList<LectureDTO> getLectures(UserDTO users);

}
