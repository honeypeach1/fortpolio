<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="controller.CourseDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Course"%>
<%
	request.setCharacterEncoding("UTF-8");

	String NAME = null;
	if (session.getAttribute("STAY_NAME") != null) {
		NAME = session.getAttribute("STAY_NAME").toString();
	}
%>
<!DOCTYPE>
<html>
<title>현지 거주자의 마이페이지입니다.</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet"href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel="stylesheet" href="/project_mvc22/reservation/css/style.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet"href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" type="image/png"href="/project_mvc22/create_acc/images/icons/favicon.ico" />
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<style>
	html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
	</style>
	
	<script>
		/* function popup_addLists() {
			window.open('', 'popup', 'top=0, left=0, width=900, height=600, scrollbars=yes, resizable=no ,status=no ,toolbar=no');
			
			var submitform = document.addListForm;
			
			submitform.target = 'popup';
			submitform.action = '/project_mvc22/view/addLists';
			
			submitform.submit();
		} */
		/* function popup_addLists(){
			
			$("addListForm").attr('target', 'popup')
			$("addListForm").attr('action', '/project_mvc22/view/addLists')
			console.log($("addListForm"))
			//$("addListForm").submit();
		} */
	</script>
</head>

<body class="w3-light-grey">

<!-- Top container -->

	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<jsp:include page="/head.jsp" flush="false" />
	</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-animate-left" style="z-index:3;width:300px;border:5px" id="mySidebar"><br>
  <div class="w3-container w3-row">
    
    <div class="w3-col s8 w3-bar">
      <span><%=NAME %>님의 마이페이지</span><br>
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>MyPage</h5>
  </div>
  <div class="w3-bar-block">
    <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  등록된 코스 보기</a>
    <a href="/project_mvc22/StayMypage.jsp" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>  코스 등록</a>
    <a href="/project_mvc22/view/Courselist" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>  코스 관리</a>
    <a href="/project_mvc22/view/StayMypageReserList" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>   예약 관리</a>
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <div id="course">
  </div>
  </header>
  <div class="w3-half4 w3-black w3-container w3-center w3-round" style="height:800px; width:1000px; background-image">
    <div style="overflow:scroll; width:950px; height:750px; padding:1px; background-color:gold;" class="w3-padding-64 w3-padding-large w3-center w3-round w3-white" style="height:1100px; width:100%;">
	<!-- !PAGE CONTENT! -->
	<nav>
		<div class="table-users">
			<div style="margin-top:20px;">
			<div class="header">등록된 코스 리스트입니다.</div>

			<table cellspacing="9">
				<tr>
					<th width=10%>순서</th>
					<th width=25%>메인 이미지</th>
					<th width=15%>비자</th>
					<th width=15%>시작일</th>
					<th width=10%>마감일</th>
					<th width=10%>인원수</th>
					<th width=10%>추가 정보</th>
					<th width=10%>코스 수정</th>
					<th width=10%>코스 삭제</th>
				</tr>
				<%
					int count = 1;
				%>
				<c:choose>
					<c:when test="${!empty requestScope.lists}">
						<c:forEach var="temp" items="${requestScope.lists}">
							<tr>
								<td>#<%=+count%></td>
								<td><img src="/project_mvc22/img/${temp.course_image1}" /></td>
								<td>${temp.course_name}</td>
								<td>${temp.start_date}</td>
								<td>${temp.end_date}</td>
								<td>${temp.people_num}</td>
								
								<td>
									<form action="/project_mvc22/view/addLists" target="popup" method="get" onsubmit="window.open('', 'popup', 'top=0, left=0, width=900, height=600, scrollbars=yes, resizable=no ,status=no ,toolbar=no');">
										<input type="hidden" name="course_num" value="${temp.course_num}" />
										<input type="submit" value="추가정보" class="w3-button w3-block w3-gray w3-round"/>
									</form>
								</td>
								
								<td>
									<form action="/project_mvc22/correctCourse.jsp" target="popup1" method="get" onsubmit="window.open('', 'popup1', 'top=0, left=0, width=900, height=600, scrollbars=yes, resizable=no ,status=no ,toolbar=no');">
										<input type="hidden" name="course_num" value="${temp.course_num}" />
										<input type="hidden" name="visa" value="${temp.visa}" />
										<input type="submit" value="코스수정" class="w3-button w3-block w3-gray w3-round"/>
									</form>
								</td>
								
								<td>
									<form action="/project_mvc22/view/deleteCourse" method="get">
										<input type="hidden" name="course_num" value="${temp.course_num}" />
										<button class="w3-button w3-block w3-gray w3-round">코스삭제</button>
									</form>
								</td>
							</tr>
							<%
								count++;
							%>
						</c:forEach>
					</c:when>
					<c:otherwise>아직 등록이 된 코스상품이 없습니다.</c:otherwise>
				</c:choose>
			</table>
			</div>
		</div>
	</nav>
	
    </div>
  </div>
	<!-- Footer -->
	<jsp:include page="/footer.jsp" flush="false" />

	<!-- End page content -->
	</div>

	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
			if (mySidebar.style.display === 'block') {
				mySidebar.style.display = 'none';
				overlayBg.style.display = "none";
			} else {
				mySidebar.style.display = 'block';
				overlayBg.style.display = "block";
			}
		}

		// Close the sidebar with the close button
		function w3_close() {
			mySidebar.style.display = "none";
			overlayBg.style.display = "none";
		}
	</script>
	
</body>
</html>
