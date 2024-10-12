package practice.api.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.api.student.model.StudentEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_course")
public class CourseEntity {

    @Id
    @Column
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "dosen_name")
    private String dosenName;

    @Column(name = "grade")
    private String grade;

    @Column(name = "student_id", insertable = false, updatable = false)
    private String studentId;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private StudentEntity student;

    public CourseEntity(String id, String code, String name, String dosenName, String grade) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dosenName = dosenName;
        this.grade = grade;
    }
}
