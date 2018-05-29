package com.spring.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.spring.dao.CrudDAO;
import com.spring.dto.CrudDTO;

public class CrudService implements CrudInter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void listView(Model model) {
		logger.info("리스트 보기 서비스 실행");
		CrudDAO dao = new CrudDAO();
		ArrayList<CrudDTO> list = dao.list();
		model.addAttribute("list", list);
	}

	@Override
	public void write(Model model) {
		// model에서 request 추출
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		// 파라미터 추출
		String userName = request.getParameter("userName");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// DTO 생성
		CrudDTO dto = new CrudDTO();
		dto.setUser_name(userName);
		dto.setSubject(subject);
		dto.setContent(content);
		
		// DAO로 전달(글쓰기)
		CrudDAO dao = new CrudDAO();
		int success = dao.write(dto);
		if (success != 0) {
			logger.info("글쓰기 성공");
		} else {
			logger.info("글쓰기 실패");
		}
	}

	@Override
	public void contentView(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		CrudDAO dao = new CrudDAO();
		CrudDTO dto = dao.contentView(id);
		model.addAttribute("dto", dto);
	}
	
	@Override
	public void modifyForm(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		CrudDAO dao = new CrudDAO();
		CrudDTO dto = dao.contentView(idx);
		model.addAttribute("dto", dto);
	}

	@Override
	public void modify(Model model) {

	}

	@Override
	public void delete(Model model) {

	}

}
