package practice.api.fakultas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import practice.api.majors.model.MajorsRes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakultasRes {
    private String id;
    private String code;
    private String name;
    private List<MajorsRes> majorsList = new ArrayList<>();
}
