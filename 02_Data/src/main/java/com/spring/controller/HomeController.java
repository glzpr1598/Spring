package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

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
	
	// 글쓰기 화면으로 이동
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		logger.info("글쓰기 폼 요청");
		return "writeForm";
	}
	
	// 글쓰기
	@RequestMapping(value = "/write")
	public String wirte(HttpServletRequest request, Model model) {
		logger.info("글쓰기 요청");
		inter = new CrudService();
		// model에 request를 담아서 보냄.
		model.addAttribute("request", request);
		inter.write(model);
		return "redirect:/";
	}
	
	// 상세보기
	@RequestMapping(value = "/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		logger.info("상세보기 요청");
		inter = new CrudService();
		
		model.addAttribute("request", request);
		inter.contentView(model);
		
		return "contentView";
	}
	
	// 수정 폼
	@RequestMapping(value = "/modifyForm")
	public String modifyForm(HttpServletRequest request, Model model) {
		logger.info("수정 폼 요청");
		inter = new CrudService();
		
		model.addAttribute("request", request);
		inter.modifyForm(model);
		
		return "modifyForm";
	}
	
	// 수정
	
	// 삭제
	
}
