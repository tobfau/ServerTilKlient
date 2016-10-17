package shared;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class ReviewDTO {
    //penis
    private int id;
    private int rating;
    private String comment;
    private int lectureId;
    private String cbsMail;

    public ReviewDTO(){
    }

    public ReviewDTO(int id, int rating, String comment, int lectureId, String cbsMail) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.lectureId = lectureId;
        this.cbsMail = cbsMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCbsMail() {
        return cbsMail;
    }

    public void setCbsMail(String cbsMail) {
        this.cbsMail = cbsMail;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", lectureId=" + lectureId +
                ", cbsMail='" + cbsMail + '\'' +
                '}';
    }
}