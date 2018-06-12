package com.spring.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.MemberInter;

@Service
public class InterService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSession sqlSession;
	MemberInter inter;
	
	// 로그인
	public boolean login(String id, String pw) {
		logger.info("로그인 서비스");
		inter = sqlSession.getMapper(MemberInter.class);
		
		boolean success = false;
		
		if (inter.login(id, pw) != null) {
			success = true;
		}
		
		return success;
	}
	
}
