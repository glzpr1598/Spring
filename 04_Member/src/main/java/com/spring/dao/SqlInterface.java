package com.spring.dao;

import com.spring.dto.MemberDTO;

public interface SqlInterface {
	MemberDTO login(String id, String pw);
}
