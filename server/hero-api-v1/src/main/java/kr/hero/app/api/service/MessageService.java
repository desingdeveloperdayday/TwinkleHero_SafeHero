package kr.hero.app.api.service;

import kr.hero.app.api.mapper.MessageMapper;
import kr.hero.app.api.model.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public void insertMessage(MessageDTO messageDTO){

        messageMapper.insertMessage(messageDTO);
    }

    public MessageDTO selectMessageByMemIdx(Integer memIdx){
        return messageMapper.selectMessageByMemIdx(memIdx);
    }
}
