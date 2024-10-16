package practice.api.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.api.course.model.CourseEntity;
import practice.api.majors.model.MajorsEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_student")
public class StudentEntity {

    @Id
    @Column
    private String id;

    @Column(name = "nim")
    private String nim;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "majors_id", insertable = false, updatable = false)
    private String majorsId;

    @JoinColumn(name = "majors_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MajorsEntity majors;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courseList = new ArrayList<>();

    public void addCourse(CourseEntity course) {
        this.courseList.add(course);
        course.setStudent(this);
    }

    public StudentEntity(String id, String nim, String name, String gender) {
        this.id = id;
        this.nim = nim;
        this.name = name;
        this.gender = gender;
    }
}
