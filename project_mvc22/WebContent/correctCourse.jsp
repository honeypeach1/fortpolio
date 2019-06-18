<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");

	String course_num = request.getParameter("course_num"); 
	String visa = request.getParameter("visa");
	
	System.out.println(visa);
	String NAME = null;
	if (session.getAttribute("STAY_NAME") != null) {
		NAME = session.getAttribute("STAY_NAME").toString();
	}
%>
<html>
<head>
<title><%=NAME %>님의 코스를 수정합니다.</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">
</head>
<body>
<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <div id="course">
    <h5><b><i class="fa fa-dashboard"></i> 코스를 수정합니다.</b></h5>
  </div>
  </header>

<div class="w3-half4 w3-red w3-container w3-center w3-round" style="height:1200px; width:70%; background-image">
    <div class="w3-padding-64 w3-padding-large w3-center w3-round" style="height:1000px; width:100%;">
      <h1> 메인 코스를 등록합니다. </h1>
      <form method="post" class="w3-container w3-card w3-padding-32 w3-white w3-round" action="/project_mvc22/view/CorrectCourse" target="_blank" style="height:1000px">
        <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-comment w3-text-red w3-large"> 상품명</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="CourseName"></div>
        </div>
        <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-user w3-text-yellow w3-large"> 국가</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="CourseNation"></div>
        </div>
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-home w3-text-brown w3-large"> 비자</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px">
		  <select class="w3-input" name = "visa" >
							<option value="WORKING HOLIDAY">워킹홀리데이 비자</option>
							<option value="WORK">워크 비자</option>
							<option value="STUDENT">학생 비자</option>
							<option value="IMMIGRATION">이민 비자</option>
							<option value="SHORT TRAVEL">단기 비자</option>
							<option value="LONG TRAVEL">장기 비자</option>
						</select>        
        </div>
        <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-share-alt w3-text-green w3-large"> 상품 가격</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="CoursePrice" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;"></div>
        </div>
         <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-users w3-text-gray w3-large"> 가능 인원수</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="CoursePeoples" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;"></div>
         </div>
		<%if(visa.equals("LONG TRAVEL") || visa.equals("SHORT TRAVEL")) {%>
        <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-users w3-text-gray w3-large"> 기간</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="number" id="LongDate" name="LongDate" placeholder="장단기 여행시 입력해주세요." onchange="allowDate(this)"></div>
        </div>
        <%} %>
        <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-users w3-text-gray w3-large"> 상품 시작 날짜</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="Start_date" placeholder="시작 날짜를 입력해주세요 (ex)YYYY-MM-DD"></div>
        </div>
        <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-users w3-text-gray w3-large"> 상품 마감 날짜</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="End_date" placeholder="시작 날짜를 입력해주세요 (ex)YYYY-MM-DD"></div>
        </div>
        <div class="w3-section" style="height:25%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-bookmark w3-text-teal w3-large"> 상품 설명</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><textarea name="CourseComment" class="w3-input" rows="10" cols="25"></textarea></div>
        </div>
        <div class="w3-section">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="fa fa-image w3-text-gray w3-large"><label>첫번째 사진 등록</label></div>
          <div style="border: 1px; float: left; width: 60%;height:50px"><input type="file" name="CourseImg1" value="이미지 저장"></div>
        </div>
        <div class="w3-section">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="fa fa-image w3-text-black w3-large"><label>상세 이미지1 등록</label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input type="file" name="CourseImg2" value="이미지 저장"></div>
        </div>
        <div class="w3-section">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="fa fa-image w3-text-gray w3-large"><label>상세 이미지2 등록</label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input type="file" name="CourseImg3" value="이미지 저장"></div>
        </div>
        <div class="w3-section">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="fa fa-image w3-text-black w3-large"><label>상세 이미지3 등록</label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input type="file" name="CourseImg4" value="이미지 저장"></div>
        </div>
        <div>
        <input type="hidden" name="course_num" value="<%=course_num %>" />
        <button type="submit" class="w3-button w3-black w3-center w3-large w3-round" style="width:50%">코스 수정하기</button>
      </div>
      </form>
    </div>
  </div>
</body>
</html>