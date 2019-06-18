<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.CourseDAO"%>
<%@page import="model.Course"%>
<%@page import="model.Information"%>
<%
request.setCharacterEncoding("UTF-8");
Course course = null;
if (request.getAttribute("list") != null)
{
	course = (Course)request.getAttribute("list");
}
%>
<!DOCTYPE html>
<html>
<title>예약 상세정보</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/project_mvc22/resources/demos/style.css"><style>

body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
.mySlides {display:none}
</style>
  
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body class="w3-content w3-border-left w3-border-right">

<!-- Sidebar/menu -->

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <span class="w3-bar-item">Rental</span>
  <a class="w3-right w3-bar-item w3-button"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main w3-white" style="margin-left:260px">

  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:80px"></div>

  <!-- Slideshow Header -->
  <div class="w3-container" id="apartment">
    <h2 class="w3-text-green"><%=course.getCourse_name() %></h2>
    <div class="w3-display-container mySlides">
    <img src="/project_mvc22/img/<%=course.getCourse_image1() %>" style="width:100%;margin-bottom:-6px">
      <div class="w3-display-bottomleft w3-container w3-black">
      </div>
    </div>
    <div class="w3-display-container mySlides">
    <img src="/project_mvc22/infor/img/<%=course.getCourse_image2() %>" style="width:100%;margin-bottom:-6px">
      <div class="w3-display-bottomleft w3-container w3-black">
      </div>
    </div>
    <div class="w3-display-container mySlides">
    <img src="/project_mvc22/infor/img/<%=course.getCourse_image3() %>" style="width:100%;margin-bottom:-6px">
      <div class="w3-display-bottomleft w3-container w3-black">
      </div>
    </div>
    <div class="w3-display-container mySlides">
    <img src="/project_mvc22/infor/img/<%=course.getCourse_image4() %>" style="width:100%;margin-bottom:-6px">
      <div class="w3-display-bottomleft w3-container w3-black">
      </div>
    </div>
  </div>
  <div class="w3-row-padding w3-section">
    <div class="w3-col s3">
      <img class="demo w3-opacity w3-hover-opacity-off" src="/project_mvc22/img/<%=course.getCourse_image1() %>" style="width:100%;cursor:pointer" onclick="currentDiv(1)">
    </div>
    <div class="w3-col s3">
      <img class="demo w3-opacity w3-hover-opacity-off" src="/project_mvc22/infor/img/<%=course.getCourse_image2() %>" style="width:100%;cursor:pointer" onclick="currentDiv(2)">
    </div>
    <div class="w3-col s3">
      <img class="demo w3-opacity w3-hover-opacity-off" src="/project_mvc22/infor/img/<%=course.getCourse_image3() %>" style="width:100%;cursor:pointer" onclick="currentDiv(3)">
    </div>
    <div class="w3-col s3">
      <img class="demo w3-opacity w3-hover-opacity-off" src="/project_mvc22/infor/img/<%=course.getCourse_image4() %>" style="width:100%;cursor:pointer" onclick="currentDiv(4)">
    </div>
  </div>

  <div class="w3-container" id = "content">
    <h4><strong>Reservation Information</strong></h4>
    <div class="w3-row w3-large">
      <div class="w3-col s6">
        <p><i class="fa fa-fw fa-male"></i> 최대 가능 인원 : <%=course.getPeople_num()%>명</p>
        <p><i class="fa fa-fw fa-tv"></i><%=course.getVisa()%> VISA</p>
      </div>
      <div class="w3-col s6">
        <p><i class="fa fa-fw fa-tv"></i>국가 <%=course.getNation()%></p>
        <p><i class="fa fa-fw fa-tv"></i>예약 가능 일 <%=course.getStart_date()%> ~ <%=course.getEnd_date() %></p>
      </div>
    </div>
    <input type="hidden" id="start_date" value="<%=course.getStart_date() %>">
    <input type="hidden" id="end_date" value="<%=course.getEnd_date() %>">
 	<hr>
 	<h4><strong>Additional Information</strong></h4>
 	<hr>
 	<%
 		int count = 1;
 		if (course.getInformation() != null){
	 		for (Information info : course.getInformation())
	 		{
	 			%>
		    <!-- 카테고리의 정보들  -->
		    <h4><strong><%="Option #"+count%></strong></h4>
		    <div>
		    	<p><i class="fa fa-fw fa-tv"></i>세부정보 종류  = <%=info.getCategory_kind()%></p>
		    	<p><i class="fa fa-fw fa-tv"></i>세부정보 이름 = <%=info.getCategory_name()%></p>
		    	<p><i class="fa fa-fw fa-tv"></i>세부정보  = <%=info.getCategory_comments()%></p>
		    </div>
		    <hr>
		    <%
		    count++;
	 		}
 		}
     %>
    <h4><strong>Comments</strong></h4>
    <p><%=course.getComments()%></p>
    <p>We accept: <i class="fa fa-credit-card w3-large"></i> <i class="fa fa-cc-mastercard w3-large"></i> <i class="fa fa-cc-amex w3-large"></i> <i class="fa fa-cc-cc-visa w3-large"></i><i class="fa fa-cc-paypal w3-large"></i></p>
   </div>
  <hr>
  <hr>
  <input class="w3-button w3-block w3-black w3-center-align" type="button" value="출력하기" onclick="window.print()" />
<!-- End page content -->
</div>

<script>
// Script to open and close sidebar when on tablets and phones
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

// Slideshow Apartment Images
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function currentDiv(n) {
  showDivs(slideIndex = n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
     dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
  }
  x[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " w3-opacity-off";
}
</script>

</body>
</html>
