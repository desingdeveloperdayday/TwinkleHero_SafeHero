package kr.hero.app.api.mapper;


import kr.hero.app.api.config.db.annotation.HeroDB;
import kr.hero.app.api.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
@HeroDB
public interface UserMapper {

	User selectUserByMemIdx(Integer memIdx);
	//User insertUser(User paramMap);
	Integer insertUser(User user);
	Integer updateMemberProtectorNum(User user);

//	Integer updateUserProfile(Map<String, Object> param);
//	Integer insertUser(Map<String, Object> param);
}
