package com.spring.service;

import org.springframework.ui.Model;

public interface CrudInter {

	// 리스트 보기
	public void listView(Model model);
	
	// 글쓰기
	public void write(Model model);
	
	// 상세보기
	public void contentView(Model model);
	
	// 수정 폼
	public void modifyForm(Model model);
	
	// 수정
	public void modify(Model model);
	
	// 삭제
	public void delete(Model model);
	
}
