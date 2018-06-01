package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.MemberInter;
import com.spring.dto.MemberDTO;

@Service
public class JoinService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
	private SqlSession sqlSession;  // MyBatis 사용을 위한 객체
	MemberInter inter;

	public ModelAndView join(HashMap<String, String> map) {
		// mapper 연결
		inter = sqlSession.getMapper(MemberInter.class);
		
		// 파라미터로 받은 값을 dto에 set
		MemberDTO dto = new MemberDTO();
		dto.setId(map.get("user_id"));
		dto.setPw(map.get("user_pw"));
		dto.setName(map.get("user_name"));
		dto.setAge(Integer.parseInt(map.get("user_age")));
		dto.setGender(map.get("user_gender"));
		dto.setEmail(map.get("user_email"));
		
		// 쿼리 실행
		int success = inter.join(dto);
		logger.info("join 결과 : " + success);

		// 결과 확인
		String page = "joinForm";
		String msg = "회원가입에 실패했습니다.";
		if (success > 0) {
			page = "result";
			msg = "회원가입에 성공했습니다.";
		}
		
		// ModelAndView에 데이터 넣기
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName(page);
		
		return mav;
	}

	public ModelAndView list(HashMap<String, String> map) {
		inter = sqlSession.getMapper(MemberInter.class);
		
		ArrayList<MemberDTO> list = inter.list(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("list");
		
		return mav;
	}

	public ModelAndView update(HashMap<String, String> map) {
		inter = sqlSession.getMapper(MemberInter.class);
		
		String msg = "수정에 실패했습니다.";
		int success = 0;
		
		if (map.get("id") == "") {
			msg = "수정할 ID를 입력해주세요.";
		} else {
			success = inter.update(map);
		}
		
		if (success > 0) {
			msg = "수정에 성공했습니다.";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("result");
		
		return mav;
	}

}
