package kr.hero.app.api.service;

import kr.hero.app.api.mapper.UserMapper;
import kr.hero.app.api.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class IndexService {

	// Mappers
	@Autowired
	UserMapper userMapper;

//	// 카카오 ID로 로그인
//	public UserResponseBody login(String kakaoId, String platform, String deviceId){
//
//		Map<String, Object> param = new HashMap<>();
//		param.put("snsId", kakaoId);
//
//		// 사용자 ID 조회
//		Integer userId = userMapper.selectKakaoUserIdBySnsId(param);
//		if(userId == null){
//			return new UserResponseBody("N", "해당 Kakao ID를 가진 사용자 미존재", null);
//		}
//
//		// 최종 로그인 시간 업데이트
//		if(userMapper.updateUserLastLogin(userId) == 0){
//			return new UserResponseBody("N", "해당 사용자의 최종 로그인 시간 업데이트 실패", null);
//		}
//
//		if(!CommonUtil.isNull(deviceId))
//			pushTokenMapper.updateUserIdByDeviceId(new PushToken(platform, deviceId, userId, null, null));
//
//		return userService.readUser(userId, null, null, null);
//	}

	public User selectUser(int memIdx) {
		return userMapper.selectUserByMemIdx(memIdx);
	}

}
