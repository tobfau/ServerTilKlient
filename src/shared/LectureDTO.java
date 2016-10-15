
package shared;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LectureDTO {

    private String type;

    private String description;

    private List<String> start = new ArrayList<String>();

    private List<String> end = new ArrayList<String>();

    private String location;


    public LectureDTO() {
    }


    public LectureDTO(String activityid, String eventid, String type, String title, String description, List<String> start, List<String> end, String location) {
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.location = location;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
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
                ",\n type='" + type + '\'' +
                ",\n description='" + description + '\'' +
                ",\n start=" + start +
                ",\n end=" + end +
                ",\n location='" + location + '\'' +
                '}';
    }
}