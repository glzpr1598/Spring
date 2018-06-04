package com.spring.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.BoardInter;

@Service
public class BoardService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSession sqlSession;
	BoardInter inter;
	
	// 리스트
	public void list(Model model) {
		inter = sqlSession.getMapper(BoardInter.class);
		model.addAttribute("list", inter.list());
	}
	
	// 상세보기
	@Transactional
	public ModelAndView detail(String idx) {
		ModelAndView mav = new ModelAndView();
		
		inter = sqlSession.getMapper(BoardInter.class);
		// 조회수 올리기
		inter.upHit(idx);
		// 상세보기
		mav.addObject("dto", inter.detail(idx));
		mav.setViewName("detail");
		return mav;
	}
	
}
