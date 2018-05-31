package com.spring.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.spring.controller.HomeController;
import com.spring.dao.SqlInterface;
import com.spring.dto.MemberDTO;

public class MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    @Autowired
	private SqlSession sqlSession;  // MyBatis 사용을 위한 객체
	SqlInterface inter;

	public MemberDTO login(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		logger.info("id : " + id + ", pw : " + pw);
		
		inter = sqlSession.getMapper(SqlInterface.class);
		MemberDTO dto = inter.login(id, pw);
		model.addAttribute("dto", dto);
		
		return dto;
	}

}
