package practice.api.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.api.course.model.CourseRes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRes {
    private String id;
    private String nim;
    private String name;
    private String majorsId;
    private String majorsName;
    private List<CourseRes> courseList = new ArrayList<>();
}
