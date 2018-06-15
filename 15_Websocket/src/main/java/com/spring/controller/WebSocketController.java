package com.spring.controller;

import java.util.HashMap;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/chat/{nick}")
public class WebSocketController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	static HashMap<String, Session> userMap = new HashMap<String, Session>();
	
	// 서버 접속 시
	@OnOpen
	public void onOpen(Session session, @PathParam("nick") String nick) {
		logger.info("서버 접속");
		logger.info("Session id : " + session.getId());

		if(userMap.get(nick) != null) {  // 닉네임 중복 시
			sendMsg(session, "이미 사용중인 닉네임입니다.");
		} else {  // 중복되지 않을 시
			userMap.put(nick, session);
			sendMsgAll(nick+"님이 입장하셨습니다.");
		}
	}
	
	// 서버 종료 시
	@OnClose
	public void onClose(Session session) {
		logger.info("서버 종료");
		logger.info("Session id : " + session.getId());
		
		// 종료하려는 Session id
		String sessionId = session.getId();
		
		Set<String> keys = userMap.keySet();
		for(String key: keys) {
			// Session id가 일치할 경우
			if(sessionId.equals(userMap.get(key).getId())) {
				logger.info("종료 하려는 sessionId : "+key);
				// userMap에서 제거
				sendMsgAll(key+" 님께서 나가셨습니다.(현재 접속자 "+userMap.size()+"명)");
				userMap.remove(key);
			}
		}
	}
	
	// 메시지 수신 시
	@OnMessage
	public void onMessage(Session session, String msg) {
		logger.info("메시지 수신");
		sendMsgAll(msg);
	}
	
	// 에러 발생 시
	@OnError
	public void onError(Session session, Throwable e) {
		logger.info(session.toString());
		logger.info(e.toString());
	}
	
	// 특정 세션에 메시지 전달
	public void sendMsg(Session session, String msg) {
		try {
			session.getBasicRemote().sendText(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 모든 세션에게 메시지 전달
	public void sendMsgAll(String msg) {
		try {
			Set<String> keys = userMap.keySet();
			for(String key: keys) {
				Session session = userMap.get(key);
				session.getBasicRemote().sendText(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
