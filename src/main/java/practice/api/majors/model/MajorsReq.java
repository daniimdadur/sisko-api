package practice.api.majors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MajorsReq {
    private String id;
    private String code;
    private String name;
    private String fakultasId;
}
