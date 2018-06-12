package com.spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PreCheck extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/* 컨트롤러 요청 전에 실행 */
		System.out.println("preHandle call");
		
		// 세션에서 로그인 확인
		HttpSession session = request.getSession();
		if (session.getAttribute("loginId") == null) {
			response.sendRedirect("./");
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/* 컨트롤러 요청 후, View 처리 전 */
		System.out.println("postHandle call");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");
		
		String content = "<div id='loginArea'>안녕하세요, "+id+"님!</div>";
		modelAndView.addObject("loginBox", content);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		/* 컨트롤러, View 처리 후 */
		System.out.println("afterCompletion call");
	}
	
}
