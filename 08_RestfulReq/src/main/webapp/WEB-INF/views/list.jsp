<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>  -->
<script src="resources/js/zer0boxPaging.js" type="text/javascript"></script>
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
	#divContainer {
		width: 600px;
		margin: 0px auto;
	}
	table {
		width: 600px;
		text-align: center;
	}
	#paging {
		text-align: center;
	}
	#curPage {
		font-weight: bold;
	}
</style>
</head>
<body>
	<div id="divContainer">
		<button id="btnWrite" onclick="location.href='writeForm'">글쓰기</button>
		<select id="numPerPage">
			<option value="5" selected>5개</option>
			<option value="10">10개</option>
			<option value="15">15개</option>
			<option value="20">20개</option>
		</select>
		<table id="table1">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody id="tbody1">
			</tbody>
		</table>
		<div id="paging">
			<div id="container"></div>
		</div>
<!-- 		<div class="container">
		    <nav aria-label="Page navigation">
		        <ul class="pagination" id="pagination"></ul>
		    </nav>
		</div> -->
	</div>
</body>
<script>
	var obj = {};
	obj.dataType = "json";
	obj.error = function(e) {console.log(e);};
	
	listCall(1);

	$("#numPerPage").change(function() {
		listCall(1);
	});

	function listCall(page) {
		// data로 데이터 전송
		/*
		obj.url = "./list";
		obj.type = "get";
		obj.data = {"page": page, "cntPerPage": $("#numPerPage").val()};
		*/
		// url로 데이터 전송
		obj.url = "./listSub/" + $("#numPerPage").val() + "/" + page;
		obj.type = "get";
		
		obj.success = function(data) {
			console.log(data);
			listPrint(data.list);
			//pagePrint(data.curPage, data.totalPage);
			
			// 페이징 플러그인
			$("#paging").zer0boxPaging({
                viewRange : 5,
                currPage : data.curPage,
                maxPage : data.totalPage,
                clickAction : function(e){
                    listCall($(this).attr('page'));
                }
            });
			
			/* 
			// 페이징 플러그인
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: data.totalPage,
				visiblePages: 10,
				onPageClick: function (event, page) {
					console.log(page);
					listCall(page)
				}
			}).on('page', function (event, page) {

			}); */
		}
		ajaxCall(obj);
	}
	
	function ajaxCall(obj) {
		$.ajax(obj);
	}
	
	// 받아온 리스트 출력
	function listPrint(list) {
		var content = "";
			
		list.forEach(function(item, index) {
			var date = new Date(item.reg_date);
			content += "<tr>";
			content += "<td>" + item.idx + "</td>";
			content += "<td>" + item.subject + "</td>";
			content += "<td>" + item.user_name + "</td>";
			content += "<td>" + date.toLocaleDateString("ko-KR") + "</td>";
			content += "<td>" + item.bHit + "</td>";
			content += "<td><a href=''>삭제</a></td>";
			content += "<tr>";
		});
		
		$("#tbody1").html(content);
	}
	
	/* 
	// 페이지 출력
	function pagePrint(curPage, totalPage) {
		var content = "";
		
		// 화면에 보여줄 페이지 수
		var pageNum = 5;
		
		// 시작 페이지, 마지막 페이지
		var startPage = curPage - ((curPage - 1) % pageNum);
		var endPage = startPage + pageNum - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		// 이전
		if(startPage != 1) {
			prevPage = startPage - 1;
			content += "<a href='javascript:listCall("+prevPage+")'> [이전] </a>";
		}
		
		// 페이지
		for (var i = startPage; i <= endPage; i++) {
			if (curPage == i) {
				content +="<a id='curPage' href='javascript:listCall("+i+")'> ["+i+"] </a>";
			} else {
				content +="<a href='javascript:listCall("+i+")'> ["+i+"] </a>";
			}
		}
		
		// 다음
		if (endPage < totalPage) {
			var nextPage = endPage + 1;
			content += "<a href='javascript:listCall("+nextPage+")'> [다음] </a>";
		}
		$("#paging").html(content);
	}
	 */
</script>
</html>