<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.CourseDAO"%>
<%@page import="model.Course"%>
<%@page import="controller.LeavePeopleDAO"%>

<!DOCTYPE html>
<link rel="stylesheet" href="/project_mvc22/main/style.css">

<jsp:include page="/head.jsp" flush="false"/>

<html>
<title>what can we do for you?</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png" href="create_acc/images/icons/favicon.ico"/>
	
<body class="w3-light-grey">

<header class="w3-display-container w3-content" style="max-width:1500px;">
		<img class="w3-image" src="/project_mvc22/main/mainpic.jpg" alt="mainpic"
			style="min-width: 1000px" width="2300" height="200">
			
		<div class="w3-display-left w3-padding w3-col l6 m8">
</header>

<!-- Page content -->
<div class="w3-content" style="max-width:1532px;">

  <div class="w3-container w3-margin-top" id="rooms">
    <h3>There is a kind of VISA under line</h3>
    <p>What kind of do you want about VISA?, Just click over here!</p>
  </div>
  
  <div class="w3-row-padding">
    <div class="w3-col m2">
      <a class="w3-button w3-block w3-gray w3-round" href="/project_mvc22/view/getCourse?type=Short"><h2>단기 여행 코스</h2></a>
    </div>
        <div class="w3-col m2">
      <a class="w3-button w3-block w3-indigo w3-round" href="/project_mvc22/view/getCourse?type=Long"><h2>장기 여행 코스</h2></a>
    </div>
        <div class="w3-col m2">
      <a class="w3-button w3-block w3-cyan w3-round" href="/project_mvc22/view/getCourse?type=Work"><h2>워크 코스</h2></a>
    </div>
    <div class="w3-col m2">
      <a class="w3-button w3-block w3-black w3-round" href="/project_mvc22/view/getCourse?type=Stay"><h2>체류 코스</h2></a>
    </div>
        <div class="w3-col m2">
      <a class="w3-button w3-block w3-brown w3-round" href="/project_mvc22/view/getCourse?type=Student"><h2>학생 코스</h2></a>
    </div>
            <div class="w3-col m2">
      <a class="w3-button w3-block w3-teal w3-round" href="/project_mvc22/view/getCourse?type=Holiday"><h2>홀리데이 코스</h2></a>
    </div>
  </div>
</div>
<div align="center">
<hr>
	<img class="w3-image" src="/project_mvc22/img/contact.jpg" alt="The Hotel"
	style="min-width: 800px" width="1200" height="800">
			
			<h6 class="w3-opacity" font-size="30px">해당 사이트는 출국 예정인 사람과 해당 국가의 사람들의 정보 교류를 위한 사이트입니다.</h6>
			
	<img class="w3-image" src="/project_mvc22/img/contact2.jpg" alt="The Hotel"
	style="min-width: 800px" width="1200" height="800">
</div>
</body>
<!-- Footer -->
<jsp:include page="/footer.jsp" flush="false"/>
</html>