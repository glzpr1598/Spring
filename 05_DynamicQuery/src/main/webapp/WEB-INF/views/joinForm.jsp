<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<style></style>
</head>
<body>
	${msg}
	<form action="join" method="post">
		<fieldset>
			<legend>회원가입</legend>
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="user_id" /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="user_pw" /></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="user_name" /></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="text" name="user_age" /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><input type="text" name="user_gender" /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="user_email" /></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="회원가입" /></th>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
<script></script>
</html>