<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<style></style>
</head>
<body>
	<h2>비밀번호 등록</h2>
	<form action="./join">
		비밀번호 : <input type="text" name="pass" />
		<input type="submit" value="저장" />
	</form>
	<h2>비밀번호 확인</h2>
	<form action="./confirm">
		비밀번호 : <input type="text" name="pass" />
		<input type="submit" value="확인" />
	</form>
	<h2>${msg}</h2>
</body>
<script></script>
</html>