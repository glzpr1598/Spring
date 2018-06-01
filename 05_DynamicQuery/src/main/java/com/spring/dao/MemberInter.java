package com.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.dto.MemberDTO;

public interface MemberInter {
	
	// 회원가입
	int join(MemberDTO dto);
	// 검색한 리스트
	ArrayList<MemberDTO> list(HashMap<String, String> map);
	// 수정
	int update(HashMap<String, String> map);

}
