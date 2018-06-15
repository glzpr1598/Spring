<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	#content {
		border: thin solid gray;
		width: 300px;
		height: 300px;
	}
</style>
</head>
<body>
	<h3>Websocket Page</h3>
	<form>
		닉네임 : <input id="nick" type="text" />
		<input id="login" type="button" value="login" />
		<div id="content" contenteditable="true"></div>
		<div>
			message: <input id="msg" type="text" />
			<input id="send" type="button" value="send" />
			<input id="exit" type="button" value="exit" />
		</div>
	</form>
</body>
<script>
	var webSocket = null;
	var nick = new String();
	
	// login 버튼 클릭
	$("#login").click(function() {
		// 대화명 저장
		nick = $("#nick").val();
		
		// 웹소켓 연결
		var url = "ws://localhost/controller/chat/" + nick;
		webSocket = new WebSocket(url);
		
		// 웹소켓이 연결되었을 때
		webSocket.onopen = function(e) {
			$("#content").append("접속 성공<br />");
		}
		
		// 웹소켓이 종료되었을을 때
		webSocket.onclose = function(e) {
			$("#content").append("접속 종료<br />");
		}
		
		// 메시지 수신 시
		webSocket.onmessage = function(e) {
			$("#content").append(e.data + "<br />");
		}
	});
	
	// send 버튼 클릭
	$("#send").click(function() {
		// 메시지 전송
		webSocket.send(nick + "> " + $("#msg").val());
	});
	
	// exit 버튼 클릭
	$("#exit").click(function() {
		// 웹소켓 종료
		webSocket.close();
	});
</script>
</html>