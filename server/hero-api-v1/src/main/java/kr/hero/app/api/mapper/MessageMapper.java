package kr.hero.app.api.mapper;

import kr.hero.app.api.config.db.annotation.HeroDB;
import kr.hero.app.api.model.MessageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@HeroDB
public interface MessageMapper {

    Integer insertMessage(MessageDTO messageDTO);
}
