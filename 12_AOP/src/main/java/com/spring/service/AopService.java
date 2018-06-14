package com.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AopService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void before() {
		logger.info("before 서비스 실행");
	}

	public void after() {
		logger.info("after 서비스 실행");
	}

	public String afterReturning() {
		logger.info("afterReturning 서비스 실행");
		return "afterReturning test!!";
	}

	public void afterThrowing() throws Exception {
		logger.info("afterThrowing 서비스 실행");
		// 예외 발생 시키기
		throw new Exception("Test Exception");
	}

	public void around(String str1, String str2) {
		logger.info("around 서비스 실행");
	}

}
