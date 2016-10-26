package shared;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class LectureDTO {

    private int id;
    private int courseId;
    private String type;
    private String description;
    private Date start;
    private Date end;
    private String location;

    public LectureDTO() {
    }

<<<<<<< HEAD
    public LectureDTO(int courseId, String type, String description, List<String> start, List<String> end, String location, int lectureId) {
=======
    public LectureDTO(int courseId, String type, String description, Date start, Date end, String location, int id) {
>>>>>>> 099af45aee126a1dafe80ca42e2adf4978e5c7c1
        this.courseId = courseId;
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.location = location;
        this.id = id;
    }

    public int getId() {
        return id;
    }

<<<<<<< HEAD
    public int getLectureId() {
        return id;
    }

    public void setLectureId(int id) {
=======
    public void setId(int id) {
>>>>>>> 099af45aee126a1dafe80ca42e2adf4978e5c7c1
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId() {
        this.courseId = courseId;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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