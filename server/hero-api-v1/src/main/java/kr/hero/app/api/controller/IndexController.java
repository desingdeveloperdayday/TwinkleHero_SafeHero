package kr.hero.app.api.controller;

import kr.hero.app.api.model.User;
import kr.hero.app.api.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
	
	@Autowired
	IndexService service;

	@RequestMapping
	public String index() {
		return "/index.html";
	}

	// 카카오ID로 로그인
	@PostMapping("/login")
	@ResponseBody
	public Object login(@RequestParam("kakaoId") String kakaoId
			, @RequestParam(value="platform",defaultValue="android",required=false) String platform
			, @RequestParam(value="deviceId",required=false) String deviceId) {
//		return indexService.login(kakaoId, platform, deviceId);
		return null;
	}

}