<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오픈데이터 숙제</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
 	<h2>지진실내 구호소 정보조회</h2>
	<button id="btn1">조회하기</button>
	
	<table id="result1" border="1">
		<thead>
			<tr>
				<th>시도명</th>
				<th>시군구명</th>
				<th>상세주소</th>
				<th>시설면적</th>
				<th>관리부서</th>
				<th>결과메세지</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	
	<script>
		$(function(){
			$("#btn1").click(function(){
			 // console.log($("#fromMonth").val());
				$.ajax({
				    url: "airplane.do",
				    success: function (data) {
				     	sad
				    },
				    error: function () {
				        console.log("ajax 통신 실패!");
				    }
				})
			})
		})
	</script>
</body>
</html>