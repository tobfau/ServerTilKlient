package shared;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseDTO {

    @SerializedName("events")
    @Expose
    private List<LectureDTO> lectures = new ArrayList<LectureDTO>();

    private int databaseId;
    private String id;
    private String displaytext;

    public CourseDTO() {
    }

    public CourseDTO(List<LectureDTO> lectures) {
        this.lectures = lectures;
    }


    public List<LectureDTO> getLectures() {
        return lectures;
    }

    public void setLectures(List<LectureDTO> lectures) {
        this.lectures = lectures;
    }

    public int getId() {
        return databaseId;
    }

    public void setId(int databaseId) {
        this.databaseId = databaseId;
    }

    public String getBint() {
        return id;
    }

    public void setBint(String id) {
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
                "lectures=" + lectures +
                ", databaseId=" + databaseId +
                ", id='" + id + '\'' +
                ", displaytext='" + displaytext + '\'' +
                '}';
    }
}
