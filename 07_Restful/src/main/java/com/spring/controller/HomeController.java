package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.UserInfo;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	// list
	@RequestMapping(value = "/list")
	public @ResponseBody ArrayList<String> respList() {
		logger.info("/list 요청");
		ArrayList<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		list.add("third");
		return list;
	}
	
	// map
	@RequestMapping(value = "/map")
	public @ResponseBody HashMap<String, Object> respMap() {
		logger.info("/map 요청");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "hello");
		map.put("age", 22);
		map.put("married", false);
		return map;
	}
	
	// obj
	@RequestMapping(value = "/obj")
	public @ResponseBody UserInfo respObj() {
		logger.info("/obj 요청");
		UserInfo info = new UserInfo();
		info.setId("user1");
		info.setName("Kim");
		info.setAge(26);
		info.setMarried(false);
		return info;
	}
	
	
}
