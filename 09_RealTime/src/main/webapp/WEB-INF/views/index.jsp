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
	body {
		font-size: 14px;
	}
	table, th, td {
		border: thin solid lightgray;
		border-collapse: collapse;
		padding: 5px 10px;
		text-align: center;
	}
	input[type='text'] {
		width: 100px;
		background-color: lightgray;
	}
	input[type='text']:focus {
		background-color: white;
	}
</style>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>팀번호</th>
				<th>팀장</th>
				<th>팀원1</th>
				<th>팀원2</th>
				<th>팀원3</th>
				<th>팀원4</th>
				<th>팀원5</th>
				<th>프로젝트명</th>
			</tr>
		</thead>
		<tbody id="tbody1">
		</tbody>
	</table>
</body>
<script>
	listCall();
	
	var val;
	
	// Focus in
	$(document).on("focusin", "input[type='text']", function() {
		val = $(this).val();
	});
	/* 
	$("table").focusin(function(e) {
		val = e.target.value
	}); */

	// Focus out
	$("table").focusout(function(e) {
		// 수정된 경우 수정 요청
		if (e.target.value != val) {
			$.ajax({
				url: "./update",
				type: "get",
				data: {
					"num": e.target.classList[0],
					"column": e.target.classList[1],
					"value": e.target.value
				},
				dataType: "json",
				success: function(data) {
					if (data.success > 0) {
						console.log("수정 완료");
					} else {
						console.log("수정 실패");
					}
				},
				error: function(e) {console.log(e);}
			});
		}
	});
	
	// Ajax로 리스트 요청하고 출력
	function listCall() {
		$.ajax({
			url: "./list",
			type: "get",
			dataType: "json",
			success: function(data) {
				printList(data.list);
			},
			error: function(e) {console.log(e);}
		});
	}
	
	// 리스트를 받아서 테이블에 출력
	function printList(list) {
		var content;
		list.forEach(function(item) {
			content += "<tr>";
			content += "<td>"+item.num+"</td>";
			content += "<td><input type='text' class='"+item.num+" leader' value='"+item.leader+"' /></td>";
			content += "<td><input type='text' class='"+item.num+" member1' value='"+item.member1+"' /></td>";
			content += "<td><input type='text' class='"+item.num+" member2' value='"+item.member2+"' /></td>";
			content += "<td><input type='text' class='"+item.num+" member3' value='"+item.member3+"' /></td>";
			content += "<td><input type='text' class='"+item.num+" member4' value='"+item.member4+"' /></td>";
			content += "<td><input type='text' class='"+item.num+" member5' value='"+item.member5+"' /></td>";
			content += "<td><input type='text' class='"+item.num+" project' value='"+item.project+"' /></td>";
			content += "</tr>";
		});
		$("#tbody1").html(content);
	}
</script>
</html>