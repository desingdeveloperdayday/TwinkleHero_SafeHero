package kr.hero.app.api.service;

import kr.hero.app.api.mapper.CertMapper;
import kr.hero.app.api.model.CertificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CertService {
    @Autowired
    CertMapper certMapper;

    public CertificationDTO selectCertByMemPhone(String certMemPhone){
        return certMapper.selectCertByMemPhone(certMemPhone);
    }

    public Integer insertCert(Map<String, Object> certInfoMap){
        return certMapper.insertCert(certInfoMap);
    }

    public Integer deleteCertByMemPhone(String certMemPhone){
        return certMapper.deleteCertByMemPhone(certMemPhone);
    }



}
