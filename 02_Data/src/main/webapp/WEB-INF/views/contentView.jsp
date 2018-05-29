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
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding: 5px 10px;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>작성자</th>
			<td>${dto.user_name}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${dto.reg_date}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${dto.bHit}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="./">리스트 가기</a>
				&nbsp;&nbsp;
				<a href="./modifyForm?idx=${dto.idx}">수정</a>
				&nbsp;&nbsp;
				<a href="./delete?idx=${dto.idx}">삭제</a>
			</td>
		</tr>
	</table>
</body>
<script></script>
</html>