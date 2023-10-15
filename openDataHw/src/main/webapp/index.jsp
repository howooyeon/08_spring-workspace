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
 	<h2>인천국제공항공사 항공사별 항공통계 서비스 TOP10</h2>
	<button id="btn1">조회하기</button>
	
	<table id="result1" border="1">
		<thead>
			<tr>
				<th>항공사</th>
				<th>항공사편</th>
				<th>도착편수(편)</th>
				<th>출발편수(편)</th>
				<th>합계편수</th>
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
				     	//console.log(data);
				     	//console.log(data.response.body.items);
						
						const itemArr = data.response.body.items;
						
						let value = "";
						
						for(let i = 0; i < 10; i++){
							console.log(itemArr[i]);
							
							let item = itemArr[i]; // {}
							
							value += "<tr>"
									  + "<td>" + item.airline + "</td>"
									  + "<td>" + item.airlineCode + "</td>"
									  + "<td>" + item.arrFlights + "</td>"
									  + "<td>" + item.depFlights + "</td>"
									  + "<td>" + item.flights + "</td>"
								  + "</tr>"
						}
						$("#result1 tbody").html(value);
				    },
				    error: function () {
				        console.log("ajax 통신 실패!");
				    }
				})
			})
		})
	</script>
	
	<h2>지진겸용 임시주거시설 정보</h2>
	<input type="button" value="실행" id="btn2">
	<div id="result2"></div>
	
	<script>
		$(function(){
			$("#btn2").click(()=>{
				$.ajax({
					url:"disaster.do",
					success: data => {
						
						let $table = $("<table border='1'></table>");
						let $thead = $("<thead></thead>");
						let headTr = "<tr>"
									+ 	"<th>시설일련번호</th>"
									+ 	"<th>시도명</th>"
									+ 	"<th>시군구명</th>"
									+ 	"<th>시설명</th>"
									+ 	"<th>주소</th>"
									+ "</tr>";
									
						$thead.html(headTr);
						
						let $tbody = $("<tbody></tbody>");
						let bodyTr = "";
						$(data).find("row").each((i, row) => {
							
							bodyTr += "<tr>"
								+ "<td>" + $(row).find("acmdfclty_sn").text() + "</td>"
								+ "<td>" + $(row).find("ctprvn_nm").text() + "</td>"
								+ "<td>" + $(row).find("ctprvn_nm").text() + "</td>"
								+ "<td>" + $(row).find("vt_acmdfclty_nm").text() + "</td>"
								+ "<td>" + $(row).find("dtl_adres").text() + "</td>"
							  +"</tr>"
						})
						
						$tbody.html(bodyTr);
						$table.append($thead, $tbody)
							  .appendTo("#result2");
						
						
					}, error: () => {
						console.log("ajax 통신 실패!");
					}
				})
			})
		})
	</script>	
				
</body>
</html>