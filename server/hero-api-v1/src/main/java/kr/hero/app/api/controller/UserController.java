package kr.hero.app.api.controller;

import kr.hero.app.api.model.MessageDTO;
import kr.hero.app.api.model.ProtectorDTO;
import kr.hero.app.api.service.*;
import kr.hero.app.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	IndexService service;

	@Autowired
	ProtectorService protectorService;

	@Autowired
	MessageService messageService;

	@GetMapping("/detail")
	@ResponseBody
	public User detail(@RequestParam("memIdx") int memIdx) throws Exception {

		return service.selectUser(memIdx);
	}

	@GetMapping("/info")
	@ResponseBody
	public Map<String, Object> userInfo(@RequestParam("memIdx") int memIdx) throws Exception{

		//회원 기본정보
		User userDTO = service.selectUser(memIdx);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("memberInfo", userDTO);

		//회원 보호자 정보
		List<ProtectorDTO> protectorDTOList = protectorService.selectProtectorByMemIdx(memIdx);
		List<Map<String, Object>> protectorsList = new ArrayList<>();

		if(protectorDTOList.size() != 1){

			for(ProtectorDTO tempDTO: protectorDTOList){
				String protectorNameKey = "protectorName_" + tempDTO.getProtectorOrder();
				String protectorPhoneKey = "protectorPhone_" + tempDTO.getProtectorOrder();

				Map<String, Object> protectorMap = new HashMap<>();
				protectorMap.put(protectorNameKey, tempDTO.getProtectorName());
				protectorMap.put(protectorPhoneKey, tempDTO.getProtectorPhone());

				protectorsList.add(protectorMap);
			}

		}else{
			ProtectorDTO tempDTO = protectorDTOList.get(0);
			Map<String, Object> protectorMap = new HashMap<>();

			protectorMap.put("protectorName_1", tempDTO.getProtectorName());
			protectorMap.put("protectorPhone_1", tempDTO.getProtectorPhone());

			protectorsList.add(protectorMap);

		}

		resultMap.put("memberProtectors" , protectorsList);


		//회원 긴급 메세지 정보
		MessageDTO messageDTO = messageService.selectMessageByMemIdx(memIdx);

		resultMap.put("memberEmergencyMessage", messageDTO);

		return resultMap;

	}


}