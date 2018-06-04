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
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserInfo;

// @RestController를 쓰면 반환타입에 @ResponseBody를 사용하지 않아도 된다.
@RestController
@RequestMapping(value = "/rest")
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	// list
	@RequestMapping(value = "/list")
	public ArrayList<String> respList() {
		logger.info("/rest/list 요청");
		ArrayList<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		list.add("third");
		return list;
	}
	
	// map
	@RequestMapping(value = "/map")
	public HashMap<String, Object> respMap() {
		logger.info("/rest/map 요청");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "hello");
		map.put("age", 22);
		map.put("married", false);
		return map;
	}
	
	// obj
	@RequestMapping(value = "/obj")
	public UserInfo respObj() {
		logger.info("/rest/obj 요청");
		UserInfo info = new UserInfo();
		info.setId("user1");
		info.setName("Kim");
		info.setAge(26);
		info.setMarried(false);
		return info;
	}
	
	
}
