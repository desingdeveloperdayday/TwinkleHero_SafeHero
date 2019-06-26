package kr.hero.app.api.controller;

import kr.hero.app.api.service.CertService;
import kr.hero.app.api.service.UserService;
import kr.hero.app.api.model.User;
import kr.hero.app.api.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	IndexService service;

	@GetMapping("/detail")
	@ResponseBody
	public User detail(@RequestParam("memIdx") int memIdx) throws Exception {

		return service.selectUser(memIdx);
	}

	@GetMapping("/info")
	@ResponseBody
	public Map<String, Object> userInfo(@RequestParam("memIdx") int memIdx) throws Exception{

		User userDTO = service.selectUser(memIdx);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", userDTO);

		return resultMap;

	}


}