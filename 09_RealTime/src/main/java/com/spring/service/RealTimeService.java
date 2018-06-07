package com.spring.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.SqlInter;

@Service
public class RealTimeService {
	@Autowired
	private SqlSession sqlSession;
	SqlInter inter;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public HashMap<String, Object> list() {
		logger.info("list service 요청");
		
		inter = sqlSession.getMapper(SqlInter.class);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", inter.list());
		
		return map;
	}

	public HashMap<String, Object> update(HashMap<String, String> map) {
		logger.info("update service 요청");
		
		inter = sqlSession.getMapper(SqlInter.class);
		
//		String num = map.get("num");
//		String column = map.get("column");
//		String value = map.get("value");
		
		HashMap<String, Object> result = new HashMap<>();
		result.put("success", inter.update(map));
		
		return result;
	}
}
