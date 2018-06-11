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
		width: 600px;
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
	#editable {
		height: 500px;
		border: thin solid gray;
		padding: 5px;
		overflow: auto;
	}
</style>
</head>
<body>
	<form id="form1" action="modify" method="post">
		<input type="hidden" name="idx" value="${dto.idx}" />
		<div id="container">
		<table>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="user_name" value="${dto.user_name}"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${dto.subject}"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="editable" contenteditable="true">${dto.content}</div>
					<input id="content" type="hidden" name="content" />
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="button" onclick="fileUp()" value="파일 업로드" />
				</th>
			</tr>
			<tr>
				<th colspan="2">
					<input type="button" onclick="location.href='./'" value="취소"/>
					&nbsp;
					<input id="btnSave" type="button" value="저장"/>
				</th>
			</tr>
		</table>
		</div>
	</form>
</body>
<script>
	var img = $("#editable img");
	for(var i = 0; i < img.length; i++) {
		$(img[i]).after("<input id='"+$(img[i]).attr("src")+"' type='button' value='삭제' onclick='del(this)' />");
	}

	function fileUp() {
		// 업로드 창
		var myWin = window.open("./uploadForm", "파일 업로드", "width=400, height=200");
	}
	
	function del(elem) {
		console.log(elem);
		var fileName = elem.id.split("/")[2];
		
		$.ajax({
			url: "./fileDel",
			type: "get",
			data: {"fileName": fileName},
			success: function(data) {
				if(data.success > 0) {
					$(elem).prev().remove();  // 이미지 삭제
					$(elem).remove();  // 버튼 삭제
				}
			},
			error: function(e) {console.log(e);}			
		});
	}
	
	$("#btnSave").click(function() {
		$("#editable input[type='button']").remove();  // 삭제 버튼 제거(복사하기 위해)
		$("#content").val($("#editable").html());  // div 내용을 input에 복사
		$("#form1").submit();
	});
</script>
</html>