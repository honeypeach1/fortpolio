<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="controller.CourseDAO"%>
<%
request.setCharacterEncoding("UTF-8");

String NAME = null;
if (session.getAttribute("STAY_NAME") != null){
		NAME = session.getAttribute("STAY_NAME").toString();
	}
%>
<!DOCTYPE>
<html>
<title>현지 거주자의 마이페이지입니다.</title>
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
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
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
    <h5></h5>
  </div>
  </header>
 <div class="w3-half4 w3-black w3-container w3-center w3-round" style="height:800px; width:1000px; background-image">
    <div style="overflow:scroll; width:950px; height:750px; padding:1px; background-color:gold;" class="w3-padding-64 w3-padding-large w3-center w3-round w3-white" style="height:1100px; width:100%;">
  <!-- Call to reservation list -->
  	<nav>
		<div class="table-users">
			<div style="margin-top:20px;">
			<div class="header">예약자 리스트를 불러옵니다.</div>

			<table cellspacing="6">
				<tr>
					<th width=10%>순서</th>
					<th width=20%>예약자-ID</th>
					<th width=20%>예약자-TEL</th>
					<th width=15%>예약자-NAME</th>
					<th width=20%>예약일</th>
					<th width=15%>예약자 취소</th>
				</tr>
				<%
					int count = 1;
				%>
				<c:choose>
					<c:when test="${!empty requestScope.reserlists}">
						<c:forEach var="temp" items="${requestScope.reserlists}">
							<tr>
								<td>#<%=+count%></td>
								<td>${temp.leave_id}</td>
								<td>${temp.leave_tel}</td>
								<td>${temp.leave_name}</td>
								<td>${temp.reser_date}</td>
		
								<form action="/project_mvc22/view/deleteReser" method="post">
									<input type="hidden" name="reser_num" value="${temp.reser_num}" />
									<td><button class="w3-button w3-block w3-gray w3-round">예약자 취소</button></td>
								</form>
							</tr>
							<%
								count++;
							%>
						</c:forEach>
					</c:when>
					<c:otherwise>아직 예약한 출국예정자가 없습니다.</c:otherwise>
				</c:choose>
			</table>
			</div>
		</div>
	</nav>
		
    </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/footer.jsp" flush="false"/>

  <!-- End page content -->
</div>

<script>

//가능 기간
function allowDate(obj)
{
	console.log(possDate);
	var possDate = $('#LongDate').val(); //입력값
	
	if(pareInt(possDate) > 31)
		{
			alert('유효 가능 기간을 넘어섰습니다. 다시 입력해주세요!');
		}
}

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
