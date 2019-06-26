package kr.hero.app.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDTO implements Serializable {

    private Integer certIdx;
    private String certNum;
    private String certDate;
    private String certMemPhone;
}
