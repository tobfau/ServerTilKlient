
package shared;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LectureDTO {

    @SerializedName("activityid")
    @Expose
    private String activityid;
    @SerializedName("eventid")
    @Expose
    private String eventid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("start")
    @Expose
    private List<String> start = new ArrayList<String>();
    @SerializedName("end")
    @Expose
    private List<String> end = new ArrayList<String>();
    @SerializedName("location")
    @Expose
    private String location;


    public LectureDTO() {
    }


    public LectureDTO(String activityid, String eventid, String type, String title, String description, List<String> start, List<String> end, String location) {
        this.activityid = activityid;
        this.eventid = eventid;
        this.type = type;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.location = location;
    }


    public String getActivityid() {
        return activityid;
    }


    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }


    public String getEventid() {
        return eventid;
    }


    public void setid(String eventid) {
        this.eventid = eventid;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<String> getStart() {
        return start;
    }


    public void setStart(List<String> start) {
        this.start = start;
    }


    public List<String> getEnd() {
        return end;
    }


    public void setEnd(List<String> end) {
        this.end = end;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "\nLectureDTO{" +
                "\nactivityid='" + activityid + '\'' +
                ",\n eventid='" + eventid + '\'' +
                ",\n type='" + type + '\'' +
                ",\n title='" + title + '\'' +
                ",\n description='" + description + '\'' +
                ",\n start=" + start +
                ",\n end=" + end +
                ",\n location='" + location + '\'' +
                '}';
    }
}