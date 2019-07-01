package kr.hero.app.api.mapper;

import kr.hero.app.api.config.db.annotation.HeroDB;
import kr.hero.app.api.model.ProtectorDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
@HeroDB
public interface ProtectorMapper {

    Integer insertProtector(ProtectorDTO protectorDTO);
}
