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
	${msg}
	<form action="list" method="get">
		<fieldset>
			<select name="opt">
				<option value="id">아이디</option>
				<option value="name">이름</option>
				<option value="email">이메일</option>
			</select>
			<input type="text" name="keyword" placeholder="검색어를 입력하세요." />
			<input type="submit" value="search" />
		</fieldset>
	</form>
</body>
<script></script>
</html>