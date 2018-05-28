package com.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

public class CrudService implements CrudInter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void listView(Model model) {
		logger.info("리스트 보기 서비스 실행");
	}

	@Override
	public void write(Model model) {

	}

	@Override
	public void contentView(Model model) {

	}

	@Override
	public void modify(Model model) {

	}

	@Override
	public void delete(Model model) {

	}

}
