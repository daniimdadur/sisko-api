package practice.api.majors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.api.student.model.StudentRes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MajorsRes {
    private String id;
    private String code;
    private String name;
    private String fakultasId;
    private String fakultasName;
    private List<StudentRes> studentList = new ArrayList<>();
}
