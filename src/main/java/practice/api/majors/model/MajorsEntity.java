package practice.api.majors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.api.fakultas.model.FakultasEntity;
import practice.api.student.model.StudentEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_majors")
public class MajorsEntity {

    @Id
    @Column
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "fakultas_id", insertable = false, updatable = false)
    private String fakultasId;

    @JoinColumn(name = "fakultas_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FakultasEntity fakultas;

    @OneToMany(mappedBy = "majors", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentEntity> studentList = new ArrayList<>();

    public void addStudent(StudentEntity student) {
        this.studentList.add(student);
        student.setMajors(this);
    }

    public MajorsEntity(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
