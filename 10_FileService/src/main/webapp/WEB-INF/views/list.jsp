<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="resources/spring-icon.jpg" />
<title>Home</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding: 5px 10px;
	}
	a {
		color: black;
		text-decoration: none;
	}
	a:hover {
		font-weight: bold;
	}
	#container {
		width: 600px;
		margin: 0px auto;
	}
	table {
		width: 600px;
		text-align: center;
	}
</style>
</head>
<body>
	<div id="container">
		<button>글쓰기</button>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>삭제</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr>
				<td>${item.idx}</td>
				<td><a href="detail?idx=${item.idx}">${item.subject}</a></td>
				<td>${item.user_name}</td>
				<td>${item.reg_date}</td>
				<td>${item.bhit}</td>
				<td><a href="delete?idx=${item.idx}">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script>
	$("button").click(function() {
		location.href="writeForm"
	});
</script>
</html>