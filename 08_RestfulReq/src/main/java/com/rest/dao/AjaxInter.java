package com.rest.dao;

import java.util.ArrayList;

import com.rest.dto.BoardDTO;

public interface AjaxInter {

	// 특정 구간 게시물 가져오기
	ArrayList<BoardDTO> list(int start, int end);
	// 전체 게시물 수
	int totalCnt();

}
