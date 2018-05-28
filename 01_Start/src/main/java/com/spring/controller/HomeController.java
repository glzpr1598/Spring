package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 이 클래스가 컨트롤러임을 명시
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 이 메소드는 "/"를 get 방식으로 요청할 경우 실행
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		// 로그를 찍어주는 메소드
		logger.info("컨트롤러 접근");
		
		// msg라는 이름으로 Hello, Spring!을 전달
		model.addAttribute("msg", "안녕하세요." );
		
		// home.jsp로 보냄
		return "home";
	}
	
}
