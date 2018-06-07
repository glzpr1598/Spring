package com.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.dto.TableDTO;

public interface SqlInter {

	// 리스트
	ArrayList<TableDTO> list();
	// 수정
	int update(HashMap<String, String> map);
	
}
