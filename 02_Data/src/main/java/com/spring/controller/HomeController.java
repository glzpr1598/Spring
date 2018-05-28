package com.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.service.CrudInter;
import com.spring.service.CrudService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	CrudInter inter;
	
	// method를 쓰지 않으면 post, get 모두 받음
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		logger.info("리스트 보기 요청");
		inter = new CrudService();
		inter.listView(model);
		return "list";
	}
	
	
}
