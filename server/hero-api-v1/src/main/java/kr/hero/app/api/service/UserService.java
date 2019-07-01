package kr.hero.app.api.service;

import kr.hero.app.api.mapper.UserMapper;
import kr.hero.app.api.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class UserService {

    //Mapper
    @Autowired
    UserMapper userMapper;

    //사용자테이블 user insert
//    public Integer insertUser(User userInfo) {
//        return userMapper.insertUser(userInfo);
//    }
}
