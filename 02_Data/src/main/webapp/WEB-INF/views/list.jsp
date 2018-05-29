<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="resources/spring-icon.png"/>
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
	<button onclick="location.href='writeForm'">글쓰기</button>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${list}" var="bbs">
			<tr>
			<td>${bbs.idx}</td>
			<td><a href="contentView?id=${bbs.idx}">${bbs.subject}</a></td>
			<td>${bbs.user_name}</td>
			<td>${bbs.reg_date}</td>
			<td>${bbs.bHit}</td>
			<td><a href="del?id=${bbs.idx}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
<script></script>
</html>