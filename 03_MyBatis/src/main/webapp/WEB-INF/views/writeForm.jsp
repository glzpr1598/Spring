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
	table{
		width: 600px;
		text-align: center;
	}
	input[type='text']{
		width:100%;
	}
	textarea{
		width:100%;
		resize:none;
	}
	#container {
		width: 600px;
		margin: 0px auto;
	}
</style>
</head>
<body>
	<form action="write" method="post">
		<div id="container">
		<table>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="userName"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject"/></td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="15" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" onclick="location.href='./'" value="취소"/>
					&nbsp;
					<button>저장</button>
				</td>
			</tr>
		</table>
		</div>
	</form>
</body>
<script></script>
</html>