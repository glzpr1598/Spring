<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<style></style>
</head>
<body>
	<h1>로그인</h1>
	<form action="login" method="post">
	<table>
		<tr>
			<th>ID</th>
			<td>
				<input type="text" name="id"/>
			</td>
		</tr>
		<tr>
			<th>PW</th>
			<td>
				<input type="password" name="pw"/>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="로그인"/>
			</th>
		</tr>
	</table>
	</form>
</body>
<script></script>
</html>