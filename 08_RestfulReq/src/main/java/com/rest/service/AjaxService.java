package com.rest.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.AjaxInter;

@Service
public class AjaxService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SqlSession sqlSession;
	AjaxInter inter;

	public HashMap<String, Object> pagingList(int page, int cntPerPage) {
		inter = sqlSession.getMapper(AjaxInter.class);
		int start = (page - 1) * cntPerPage + 1;
		int end = page * cntPerPage;
		
		// 총 게시물 수
		int totalCnt = inter.totalCnt();
		logger.info("총 게시물 수 : {}", totalCnt);

		// 총 페이지
		int totalPage = (totalCnt - 1) / cntPerPage + 1;
		logger.info("총 페이지 : {}", totalPage);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", inter.list(start, end));
		map.put("totalPage", totalPage);
		map.put("curPage", page);
		
		return map;
	}

}
