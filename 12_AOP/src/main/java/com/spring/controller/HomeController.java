package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.AopService;

@Controller
public class HomeController {
	
	@Autowired AopService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("홈 요청");
		
		return "home";
	}
	
	@RequestMapping(value = "/before")
	public String before() {
		logger.info("before 요청");
		
		service.before();
		
		return "home";
	}
	
	@RequestMapping(value = "/after")
	public String after() {
		logger.info("after 요청");
		
		service.after();
		
		return "home";
	}
	
	@RequestMapping(value = "/afterReturning")
	public String afterReturning() {
		logger.info("afterReturning 요청");
		
		logger.info(service.afterReturning());
		
		return "home";
	}
	
	@RequestMapping(value = "/afterThrowing")
	public String afterThrowing() {
		logger.info("afterThrowing 요청");
		
		try {
			service.afterThrowing();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/around")
	public String around() {
		logger.info("around 요청");
		
		service.around("Test1", "Test2");
		
		return "home";
	}
	
}
