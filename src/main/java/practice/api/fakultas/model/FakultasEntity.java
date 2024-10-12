package practice.api.fakultas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.api.majors.model.MajorsEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_fakultas")
public class FakultasEntity {

    @Id
    @Column
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fakultas",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MajorsEntity> majorsList = new ArrayList<>();

    public void addMajor(MajorsEntity major) {
        this.majorsList.add(major);
        major.setFakultas(this);
    }

    public FakultasEntity(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
