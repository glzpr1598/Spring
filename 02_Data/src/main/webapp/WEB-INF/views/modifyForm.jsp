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
	table{
		width: 500px;
	}
	input[type='text']{
		width:100%;
	}
	textarea{
		width:100%;
		resize:none;
	}
	td{
		text-align: center;
	}
	
</style>
</head>
<body>
	<form action="update" method="post">
	<table>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="userName" value="${dto.user_name}"/>
				<input type="hidden" name="idx" value="${dto.idx}"/>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" value="${dto.subject}"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="15" name="content">${dto.content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="./detail?id=${dto.idx}">취소</a>
				&nbsp;&nbsp;
				<button>저장</button>
			</td>
		</tr>
	</table>
	</form>
</body>
<script></script>
</html>