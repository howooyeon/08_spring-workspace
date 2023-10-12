<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="common/header.jsp"/>
	
	<div class="content">
		<br><br>
		<div class="innerOuter">
		
		
			<h4>게시글 Top5</h4>
			<br>
			<a href="list.bo" style="float:right">더보기💨</a>
			<table id="boardList" class="table table-hover" align="center">
                <thead>
                  <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>첨부파일</th>
                  </tr>
                </thead>
                <tbody>
                	<!-- 현재 조회수가 가장 높은 상위 5개의 게시글 조회해서 뿌리기 (ajax) -->
                </tbody>
            </table>
			
		</div>
		
		<script>
			$(function(){
				topBoardList();
				
				// 이 방법으로는 동적으로 만들어진 요소에 이벤트 부여 불가
				// setInterval(topBoardList, 10000000000);
				/*
				$("#boardList>tbody>tr").click(function(){
   					location.href = 'detail.bo?bno=' + $(this).children().eq(0).text();
   				})
   				*/
   				// 동적으로 만들어진 요소에 이벤트 부여 방법
   				$(document).on("click", "#boardList>tbody>tr", function(){
   					location.href = 'detail.bo?bno=' + $(this).children().eq(0).text();
   				})
			})
			
			function topBoardList(){
				$.ajax({
					url:"topList.bo",
					success:function(list){
						console.log(list);
	    				
	    				let value = "";
	    				for(let i in list){
	    					value += "<tr>"
	    						  + "<td>" + list[i].boardNo + "</td>"
	    						  + "<td>" + list[i].boardTitle + "</td>"
	    						  + "<td>" + list[i].boardWriter + "</td>"
	    						  + "<td>" + list[i].count + "</td>"
	    						  + "<td>" + list[i].createDate + "</td>"
	    						  + "<td>" ;
	    				if(list[i].originName != null){ // 첨부파일이 있는 경우
	    					value += "🍍"
	    				}
	    				
	    				 value += "</td>" + "</tr>";
	    				}
	    				
	    				$("#boardList tbody").html(value);
	    				
	    				/* $("#boardList>tbody>tr").click(function(){
	    					location.href = 'detail.bo?bno=' + $(this).children().eq(0).text();
	    				}) */
					}, error:function(){
						console.log("조회수 top5 게시글 조회용 ajax 통신 실패!")
					}
				})
			}
			
			
		</script>
		
				
			
	
	</div>
	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>