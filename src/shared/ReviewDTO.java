package shared;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class ReviewDTO {
    //penis
    private int id;
    private int userId;
    private int rating;
    private String comment;
    private int lectureId;
    private boolean commentIsDeleted;

    public ReviewDTO(){
    }

    public ReviewDTO(int id, int userId, int rating, String comment, int lectureId, boolean commentIsDeleted) {
        this.id = id;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.lectureId = lectureId;
        this.commentIsDeleted = commentIsDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public boolean getCommentIsDeleted() {
        return commentIsDeleted;
    }

    public void setCommentIsDeleted(boolean commentIsDeleted) {
        this.commentIsDeleted = commentIsDeleted;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", lectureId=" + lectureId +
                '}';
    }
}