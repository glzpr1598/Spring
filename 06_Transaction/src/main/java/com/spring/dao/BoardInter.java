package com.spring.dao;

import java.util.ArrayList;

import com.spring.dto.BoardDTO;

public interface BoardInter {
	
	// 리스트 보기
	ArrayList<BoardDTO> list();
	// 조회수 올리기
	void upHit(String idx);
	// 상세보기
	BoardDTO detail(String idx);

}
