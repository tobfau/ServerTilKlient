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

    private int id;
    private String bint;
    private String name;

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
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBint() {
        return bint;
    }

    public void setBint(String bint) {
        this.bint = bint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "lectures=" + lectures +
                ", id=" + id +
                ", bint='" + bint + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
