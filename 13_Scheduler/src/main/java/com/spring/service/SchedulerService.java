package com.spring.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.dao.SqlInter;

@Service
public class SchedulerService {
	
	@Autowired private SqlSession sqlSession;
	SqlInter inter;

	/*
	 * fixedDelay = x : 이전에 호출된 task의 종료시간부터 몇 ms 후에 실행
	 * fixedRate : 이전에 호출된 task의 시작시간부터 몇 ms 후에 실행
	 * cron : 초 분 시 일 월 요일 연(생략가능)
	 * "0/5 * * * * *" : 5초마다 실행
	 * "0 0 12 * * MON-FRI" : 월-금 12시에 실행
	 * http://www.baeldung.com/cron-expressions
	 */
	@Scheduled(fixedRate=2000)
	public void loop() {
		System.out.println("loop 서비스 실행");
		
		inter = sqlSession.getMapper(SqlInter.class);
		System.out.println(inter.memberCnt());
	}
	
}
