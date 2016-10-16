package shared;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseDTO {

    @SerializedName("events")
    @Expose
    //private List<LectureDTO> lectures = new ArrayList<LectureDTO>();
    private LectureDTO[] events;

    private String id;
    private String displaytext;

    public CourseDTO() {
    }

    public CourseDTO(String id, String displaytext) {
        this.id = id;
        this.displaytext = displaytext;
    }

    /*public List<LectureDTO> getLectures() {
        return lectures;
    }
    */

    /*public void setLectures(List<LectureDTO> lectures) {
        this.lectures = lectures;
    }
    */

    public LectureDTO[] getEvents() {
        return events;
    }

    public void setEvents(LectureDTO[] events) {
        this.events = events;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*
    public String getBint() {
        return id;
    }

    public void setBint(String id) {
        this.id = id;
    }
    */

    public String getName() {
        return displaytext;
    }

    public void setName(String displaytext) {
        this.displaytext = displaytext;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "lectures=" + events +
                ", id=" + id +
                ", displaytext='" + displaytext + '\'' +
                '}';
    }
}