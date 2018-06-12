package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.service.InterService;

@Controller
public class HomeController {
	
	@Autowired
	InterService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("홈 요청");
		
		return "home";
	}
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
		logger.info("로그인 요청");
		
		String page = "home";
		
		if (service.login(id, pw)) {
			session.setAttribute("loginId", id);
			page = "redirect:/list";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		logger.info("리스트 요청");
		
		return "list";
	}
	
}
