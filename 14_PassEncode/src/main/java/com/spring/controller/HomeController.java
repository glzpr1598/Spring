package com.spring.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	String hash;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam HashMap<String, String> params) {
		logger.info("홈 요청");
		logger.info(params.get("msg"));
		return "home";
	}
	
	// 암호화 메서드
	@RequestMapping(value = "/join")
	public String join(@RequestParam("pass") String pass) {
		logger.info("join 요청");
		logger.info("입력값 : " + pass);
		
		// 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		hash = encoder.encode(pass);
		// salt 처리로 인해 같은 값을 입력해도 암호문이 달라진다.
		logger.info("암호문 : " + hash);
		
		return "redirect:/";
	}
	
	// 암호문 확인  메서드
	@RequestMapping(value = "/confirm")
	public String confirm(@RequestParam("pass") String pass, RedirectAttributes redirectAttr) {
		logger.info("confirm 요청");
		
		// 암호문 확인
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String msg;
		if(encoder.matches(pass, hash)) {
			logger.info("일치");
			msg = "matched";
		} else {
			logger.info("불일치");
			msg = "not matched";
		}

		redirectAttr.addFlashAttribute("msg", msg);
		//return "redirect:/?msg="+msg;
		return "redirect:/";
	}
}
