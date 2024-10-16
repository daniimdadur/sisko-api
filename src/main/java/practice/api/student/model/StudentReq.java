package practice.api.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentReq {
    private String id;
    private String nim;
    private String name;
    private String gender;
    private String majorsId;
}
