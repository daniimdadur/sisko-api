package practice.api.fakultas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
