<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.CourseDAO"%>
<%@page import="model.Course"%>
<%@page import="controller.LeavePeopleDAO"%>

<!DOCTYPE html>
<jsp:include page="/head.jsp" flush="false"/>
<html>
<title>what can we do for you?</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png" href="create_acc/images/icons/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="main/style.css">

<%
	if (request.getAttribute("list") == null)
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/initMainPage");
		dispatcher.forward(request, response);
		
		return;
	}
%>

<body class="w3-light-grey">

<header class="w3-display-container w3-content" style="max-width:1500px;">
		<img class="w3-image" src="/project_mvc22/main/mainpic.jpg" alt="mainpic"
			style="min-width: 1200px" width="2500px" height="300px">
			
		<div class="w3-display-left w3-padding w3-col l6 m8">
</header>

<!-- Page content -->
<div class="w3-content" style="max-width:1532px;">
    
    <div class="w3-container w3-padding-32 w3-black w3-opacity w3-card w3-hover-opacity-off" style="margin:32px 0;">
    <div><h2>What are you looking for? you do to Search for anything!</h2>
    
    <form method="get" action="/project_mvc22/view/Search">
    
    <!-- 검색 비자별 분류 -->
    <div style="border: 10px black; float: left; width:30%; height:50px">
    <select class="w3-input w3-border" name = "visa" placeholder="VISA">
							<option value="WORKING HOLIDAY">WORKING HOLIDAY VISA</option>
							<option value="LONG TRAVEL">LONG TRAVEL VISA</option>
							<option value="SHORT TRAVEL">SHORT TRAVEL VISA</option>
							<option value="IMMIGRATION">IMMIGRATION VISA</option>
							<option value="STUDENT">STUDENT VISA</option>
							<option value="WORK">WORK VISA</option>
						</select>
    </div>
    
    <!-- 국가별 검색 -->
    <div style="border: 10px black; float: left; width:30%; height:50px">
    <input class="w3-input w3-border" type="search" name="nation" placeholder="NATION">
    </div>
    
    
    <div style="border: 2px black; float: left; width:35%; height:50px; margin:1px">
    <button class="w3-button w3-blue" style="width:35%" type="submit">검색하기</button>
    </div>
  	
  	
  	</form>
  </div>
  </div>
  
  <div class="w3-container w3-margin-top" id="Packages">
    <h3>There are several types of visas in the line below!</h3>
    <p>What kind of visa course would you like?, Just click over here!</p>
  </div>
  
  <div class="w3-row-padding">
    <div class="w3-col m2">
      <a class="w3-button w3-block w3-black w3-box" href="/project_mvc22/view/getCourse?type=Long">Long Travel Course</a>
    </div>
        <div class="w3-col m2">
      <a class="w3-button w3-block w3-black w3-box" href="/project_mvc22/view/getCourse?type=Short">Short Travel Course</a>
    </div>
        <div class="w3-col m2">
      <a class="w3-button w3-block w3-black w3-box" href="/project_mvc22/view/getCourse?type=Work">Work Course</a>
    </div>
    <div class="w3-col m2">
      <a class="w3-button w3-block w3-black w3-box" href="/project_mvc22/view/getCourse?type=Stay">Stay Course</a>
    </div>
        <div class="w3-col m2">
      <a class="w3-button w3-block w3-black w3-box" href="/project_mvc22/view/getCourse?type=Student">Student Course</a>
    </div>
            <div class="w3-col m2">
      <a class="w3-button w3-block w3-black w3-box" href="/project_mvc22/view/getCourse?type=Holiday">Holiday Course</a>
    </div>
  </div>
	</div>
<div class="w3-row-padding w3-padding-16 w3-center w3-round w3-margin">
  <c:choose>
	<c:when test="${!empty list}">
	<c:forEach items="${list}" var="dto">
	<div class="w3-third w3-margin-bottom w3-center w3-margin" style="height:850px; border: 2px; float: center; width: 30%; padding:10px;"> <!-- forEach문 위로 뺌 -->
	
		<div class="w3-container w3-white w3-margin"> <!-- one -->
		<div>
		<img src="/project_mvc22/img/${dto.course_image1}"  style="width:100%">
		</div>
		<h3>${dto.course_name}</h3>
		<h6 class="w3-opacity">가격 = ${dto.course_price}</h6>
        <p>판매자 = ${dto.stay_name}</p>
        <p>${dto.comments}</p>
        <p>${dto.visa} VISA</p>
        <c:if test="${dto.long_date != null}">
        <p>기간 ${dto.long_date}일</p>
        </c:if>
        <p>가능 인원 ${dto.people_num}명 까지</p>
        <p>예약 가능일 ${dto.start_date} ~ ${dto.end_date} 까지</p>
        <p>국가 = ${dto.nation}</p>
        <c:if test="${dto.visa == 'WORKING HOLIDAY'}">
          <form action="/project_mvc22/view/DetailInfor" method="get">
            <input type="hidden" name="course_num" value="${dto.course_num}" />
  			<button class="w3-button w3-block w3-teal w3-margin-bottom">워홀 상품 보기</button>
  			</form>
  			</c:if>
  			
  			<c:if test="${dto.visa == 'WORK'}">
          <form action="/project_mvc22/view/DetailInfor" method="get">
            <input type="hidden" name="course_num" value="${dto.course_num}" />
  			<button class="w3-button w3-block w3-cyan w3-margin-bottom">워크 상품 보기</button>
  			</form>
  			</c:if>
  			
  			<c:if test="${dto.visa == 'STUDENT'}">
          <form action="/project_mvc22/view/DetailInfor" method="get">
            <input type="hidden" name="course_num" value="${dto.course_num}" />
  			<button class="w3-button w3-block w3-brown w3-margin-bottom">학생 상품 보기</button>
  			</form>
  			</c:if>
  			
  			<c:if test="${dto.visa == 'IMMIGRATION'}">
          <form action="/project_mvc22/view/DetailInfor" method="get">
            <input type="hidden" name="course_num" value="${dto.course_num}" />
  			<button class="w3-button w3-block w3-black w3-margin-bottom">이민 상품 보기</button>
  			</form>
  			</c:if>
  			
  			<c:if test="${dto.visa == 'SHORT TRAVEL'}">
          <form action="/project_mvc22/view/ShortTravel" method="get">
            <input type="hidden" name="course_num" value="${dto.course_num}" />
  			<button class="w3-button w3-block w3-gray w3-margin-bottom">단기여행 상품 보기</button>
  			</form>
  			</c:if>
  			
  			<c:if test="${dto.visa == 'LONG TRAVEL'}">
          <form action="/project_mvc22/view/LongTravel" method="get">
            <input type="hidden" name="course_num" value="${dto.course_num}" />
  			<button class="w3-button w3-block w3-indigo w3-margin-bottom">장기여행 상품 보기</button>
  			</form>
  			</c:if>
  			
       </div>
    </div>
	</c:forEach>
	</c:when>
	<c:otherwise>등록된 코스가 없습니다.</c:otherwise>
  </c:choose>
</div>

<!-- Footer -->
<jsp:include page="/footer.jsp" flush="false"/>
</body>
</html>
