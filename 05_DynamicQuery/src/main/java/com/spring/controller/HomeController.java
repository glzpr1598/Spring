package com.spring.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.JoinService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// service를 빈으로 사용
	@Autowired
	JoinService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("회원가입 페이지 요청");
		return "joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView join(@RequestParam HashMap<String, String> map) {
		logger.info("회원가입 요청");
		logger.info("id : " + map.get("user_id"));
		logger.info("pw : " + map.get("user_pw"));
		logger.info("name : " + map.get("user_name"));
		logger.info("age : " + map.get("user_age"));
		logger.info("gender : " + map.get("user_gender"));
		logger.info("email : " + map.get("user_email"));
		
		return service.join(map);
	}
	
	@RequestMapping(value = "/result")
	public String result() {
		return "result";
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam HashMap<String, String> map) {
		logger.info("리스트 요청");
		logger.info("opt : " + map.get("opt"));
		logger.info("keyword : " + map.get("keyword"));
		
		return service.list(map);
	}
	
	@RequestMapping(value = "/update")
	public ModelAndView update(@RequestParam HashMap<String, String> map) {
		logger.info("수정 요청");
		
		return service.update(map);
	}
	
}
