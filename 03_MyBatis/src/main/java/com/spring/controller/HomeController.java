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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SqlSession sqlSession;  // MyBatis 사용을 위한 객체
	SqlInterface inter;
	
	// 리스트 보기
	@RequestMapping(value = "/")
	public String list(Model model) {
		// XML과 연결할 interface를 sqlSesseion에 등록
		inter = sqlSession.getMapper(SqlInterface.class);
		model.addAttribute("list", inter.list());
		
		return "list";
	}
	
	// 글쓰기 폼
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	// 글쓰기
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		inter = sqlSession.getMapper(SqlInterface.class);
		inter.write(userName, subject, content);
		
		return "redirect:/";
	}
	
	// 상세 보기
	@RequestMapping(value="/detail")
	public String detail(HttpServletRequest request, Model model) {
		String idx = request.getParameter("idx");
		
		inter = sqlSession.getMapper(SqlInterface.class);
		inter.upHit(idx);
		model.addAttribute("dto", inter.detail(idx));
		
		return "detail";
	}
	
	// 삭제
	@RequestMapping(value="delete")
	public String delete(HttpServletRequest request, Model model) {
		String idx = request.getParameter("idx");
		
		inter = sqlSession.getMapper(SqlInterface.class);
		inter.delete(idx);
		
		return "redirect:/";
	}
	
	// 수정 폼
	@RequestMapping(value="modifyForm")
	public String modifyForm(HttpServletRequest request, Model model) {
		String idx = request.getParameter("idx");
		
		inter = sqlSession.getMapper(SqlInterface.class);
		model.addAttribute("dto", inter.modifyForm(idx));
		
		return "modifyForm";
	}
	
	// 수정
	@RequestMapping(value="modify")
	public String modify(HttpServletRequest request, Model model) {
		String idx = request.getParameter("idx");
		String userName = request.getParameter("userName");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		inter = sqlSession.getMapper(SqlInterface.class);
		inter.modify(idx, userName, subject, content);
		
		return "redirect:/";
	}
	
}
