<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="resources/spring-icon.jpg">
<title>파일 업로드</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<style></style>
</head>
<body>
	<form id="form1" action="./upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" onchange="fileUpload()" />
	</form>
</body>
<script>
	function fileUpload() {
		$("#form1").submit();
	}
	
	var filePath = "${path}";
	// 경로가 있으면 이미지를 부모창으로 보냄
	if(filePath != "") {
		var content = "<img src='${path}' width='300px' />";
		content += "<input id='${path}' type='button' value='삭제' onclick='del(this)' />";
		$("#editable", opener.document).append(content);
		self.close();
	}
	
</script>
</html>