<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");

	String NAME = null;
	if (session.getAttribute("STAY_NAME") != null) {
		NAME = session.getAttribute("STAY_NAME").toString();
	}
	
	String course_num = request.getParameter("course_num");
%>
<html>
<head>
<title><%=NAME %>님의 추가 정보를 등록합니다.</title>
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
<div class="w3-main w3-center" style="margin-left:300px;margin-top:23px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:5px">
    <div id="course">
  </div>
  </header>

<div class="w3-half4 w3-red w3-container w3-center w3-round" style="height:700px; width:80%; background-image">
    <div class="w3-padding-64 w3-padding-large w3-center w3-round" style="height:600px; width:100%;">
      <h1> 추가 정보를 등록합니다. </h1>
      <form action="/project_mvc22/view/addinfor" method="get" class="w3-container w3-card w3-padding-32 w3-white w3-round" style="height:500px; width:100%;">
        <div class="w3-section" style="height:10%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-users w3-text-gray w3-large"> 추가 정보 종류</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="inforKind" placeholder="학원, 일자리등등 한가지 정보를 입력하세요."></div>
        </div>
        <div class="w3-section" style="height:10%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-users w3-text-gray w3-large"> 추가 정보 이름</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="text" name="inforName" placeholder="해당 정보의 이름을 입력하세요."></div>
        </div>
        <div class="w3-section" style="height:40%; margin-bottom:10px;">
          <div style="border: 1px; float: left; width: 30%; height:40px" class="w3-input"><label><i class="fa fa-bookmark w3-text-teal w3-large"> 추가 정보 설명</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:40px"><textarea name="inforComments" class="w3-input" rows="6" cols="30"></textarea></div>
        </div>
        <div>
        <input type="hidden" name="course_num" value="<%=course_num %>" />
        <button type="submit" class="w3-button w3-black w3-center w3-large w3-round" style="width:50%">추가 정보 등록하기</button>
      </div>
      </form>
    </div>
  </div>
</body>
</html>