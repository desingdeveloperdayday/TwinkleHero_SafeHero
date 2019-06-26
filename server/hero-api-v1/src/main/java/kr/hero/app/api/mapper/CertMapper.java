package kr.hero.app.api.mapper;

import kr.hero.app.api.config.db.annotation.HeroDB;
import kr.hero.app.api.model.CertificationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
@HeroDB
public interface CertMapper {

    CertificationDTO selectCertByMemPhone(String certMemPhone);
    Integer insertCert(Map<String, Object> certInfoMap);
    Integer deleteCertByMemPhone(String certMemPhone);


}
