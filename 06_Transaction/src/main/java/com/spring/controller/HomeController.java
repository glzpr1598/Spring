package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.BoardService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		logger.info("list 요청");
		service.list(model);
		return "list";
	}
	
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("idx") String idx) {
		logger.info("{}번 게시물 상세보기", idx);
		return service.detail(idx);
	}
	
}
