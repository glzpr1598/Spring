package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dao.SqlInterface;
import com.spring.dto.MemberDTO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
	@Autowired
	private SqlSession sqlSession;  // MyBatis 사용을 위한 객체
	SqlInterface inter;
	
	@RequestMapping(value = "/")
	public String home(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {
		// 이동할 페이지
		String page = "login";
		
		// 파라미터 get
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// login 쿼리 실행 (dto 가져오기)
		inter = sqlSession.getMapper(SqlInterface.class);
		MemberDTO dto = inter.login(id, pw);
		model.addAttribute("dto", dto);
		
		// 로그인 성공 판단
		if (dto != null) {
			logger.info("로그인 성공");
			// 세션에 id 저장
			request.getSession().setAttribute("id", id);
			logger.info("세션에 저장된 id : " + request.getSession().getAttribute("id"));
			// 로그인 성공 페이지
			page = "loginOK";
		} else {
			logger.info("로그인 실패");
		}
		
		return page;
	}
	
}
