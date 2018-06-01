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
	table, th, td {
		border: thin solid black;
		border-collapse: collapse;
		padding : 5px;
	}
</style>
</head>
<body>
	<h3>회원 리스트</h3>
	<table>
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>이메일</th>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.id}</td>
			<td>${dto.pw}</td>
			<td>${dto.name}</td>
			<td>${dto.age}</td>
			<td>${dto.gender}</td>
			<td>${dto.email}</td>
		</tr>
		</c:forEach>
	</table>
	
	<h3>수정</h3>
	<form action="update">
		<table>
			<tr>
				<th>수정할 아이디</th>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age" /></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="text" name="gender" /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="수정" /></th>
			</tr>
		</table>
	</form>

</body>
<script></script>
</html>