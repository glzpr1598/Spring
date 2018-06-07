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
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.RealTimeService;

@Controller
public class HomeController {
	@Autowired
	RealTimeService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "index";
	}
	
	@RequestMapping(value = "/list")
	public @ResponseBody HashMap<String, Object> list() {
		logger.info("list 요청");
		
		return service.list();
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody HashMap<String, Object> update(@RequestParam HashMap<String, String> map) {
		logger.info("update 요청");
		
		return service.update(map);
	}
	
}
