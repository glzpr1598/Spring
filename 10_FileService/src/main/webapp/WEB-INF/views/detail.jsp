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
	table {
		width: 600px;
	}
	#container {
		width: 600px;
		margin: 0px auto;
	}
</style>
</head>
<body>
	<div id="container">
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
				<td>${dto.bhit}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.subject}</td>
			</tr>
			<tr>
				<td colspan="2">${dto.content}</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td id="attach"></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="button" onclick="location.href='./'" value="목록"/>
					&nbsp;
					<input type="button" onclick="location.href='./modifyForm?idx=${dto.idx}'" value="수정"/>
					&nbsp;
					<input type="button" onclick="location.href='./delete?idx=${dto.idx}'" value="삭제"/>
				</th>
			</tr>
		</table>
	</div>
</body>
<script>
	var fileMap = new Object();
	var fileCnt = ${size};
	var content = new String();
	
	<c:forEach items="${files}" var="list">
		fileMap["${list.newFile}"] = "${list.oldFile}";
	</c:forEach>
	console.log("fileMap", fileMap);
	
	if (fileCnt > 0) {
		console.log(Object.keys(fileMap));
		for(key in fileMap) {
			console.log(key, fileMap[key]);
			content += " <img height='15px' src='resources/image-icon.png' />"
			content += "<a href='./download?file="+key+"'>"+fileMap[key]+"</a> ";
		}
		$("#attach").append(content);
	} else {
		$("#attach").html("첨부파일이 없습니다.");
	}
</script>
</html>