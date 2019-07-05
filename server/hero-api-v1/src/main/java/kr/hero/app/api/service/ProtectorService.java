package kr.hero.app.api.service;

import kr.hero.app.api.mapper.ProtectorMapper;
import kr.hero.app.api.model.ProtectorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProtectorService {

    @Autowired
    ProtectorMapper protectorMapper;

    public Integer insertProtector(ProtectorDTO protectorDTO){

        return protectorMapper.insertProtector(protectorDTO);
    }

    public List<ProtectorDTO> selectProtectorByMemIdx(Integer memIdx){

        return protectorMapper.selectProtectorByMemIdx(memIdx);
    }
}
