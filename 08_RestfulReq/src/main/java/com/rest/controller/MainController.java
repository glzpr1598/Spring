package com.rest.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rest.service.AjaxService;

@RestController
public class MainController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AjaxService service;
	
	@RequestMapping(value = "/")
	public HashMap<String, String> home() {
		HashMap<String, String> map = new HashMap<>();
		map.put("msg", "잘못된 요청입니다. 사용자 API를 참조하세요.");
		return map;
	}
	
	/* list/20/1 형식 */
	// restful은 일반적으로 url 형태로 요청하기 때문에 데이터 조회에만 주로 사용한다.
	@RequestMapping(value = "/listSub/{cntPerPage}/{page}")
	public HashMap<String, Object> listSub(@PathVariable int cntPerPage, @PathVariable int page) {
		HashMap<String, Object> map = new HashMap<>();
		logger.info(cntPerPage + "/" + page);
		
		return map;
	}
	
	// index 요청이 오면 list.jsp로 전송
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		
		return mav;
	}
	
	/* /list?cnt=20&page=1 형식 */
	@RequestMapping(value = "/list")
	public HashMap<String, Object> list(@RequestParam HashMap<String, String> params) {
		String page = params.get("page");
		String cntPerPage = params.get("cntPerPage");
		
		logger.info(page);
		logger.info(cntPerPage);
		
		HashMap<String, Object> map = service.pagingList(Integer.parseInt(page), Integer.parseInt(cntPerPage)); 

		return map;
	}
	
}
