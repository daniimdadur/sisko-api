package practice.api.fakultas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakultasRes {
    private String id;
    private String code;
    private String name;

    public FakultasRes(FakultasEntity fakultasEntity) {
        BeanUtils.copyProperties(fakultasEntity, this);
    }
}
