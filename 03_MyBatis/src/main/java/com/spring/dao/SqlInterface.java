package com.spring.dao;

import java.util.ArrayList;

import com.spring.dto.BoardBean;

public interface SqlInterface {
	
	// 리스트 보기
	ArrayList<BoardBean> list();
	// 글쓰기
	void write(String userName, String subject, String content);
	// 조회수 증가
	void upHit(String idx);
	// 상세보기
	BoardBean detail(String idx);
	// 삭제
	void delete(String idx);
	// 수정 폼
	BoardBean modifyForm(String idx);
	// 수정
	void modify(String idx, String userName, String subject, String content);

}
