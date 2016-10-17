package shared;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseDTO {

    @SerializedName("events")
    @Expose

    /*
        Events er vores samling af LectureDTO objekter. Det havde givet mere mening at kalde denne variabel
        for "lectures", men "events" var nødvendigt for at kunne parse Json dataen ind i CBS parser
     */
    private LectureDTO[] events;

    private String id;
    private String displaytext;

    public CourseDTO() {
    }

    public CourseDTO(String id, String displaytext) {
        this.id = id;
        this.displaytext = displaytext;
    }

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