<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.CourseDAO"%>
<%@page import="model.Course"%>
<%@page import="model.Information"%>
<%
request.setCharacterEncoding("UTF-8");

String leave_id = null;

if (session.getAttribute("LEAVE_ID") != null)
{
	 leave_id = session.getAttribute("LEAVE_ID").toString();
}
Course course = null;
if (request.getAttribute("course") != null)
{
	course = (Course)request.getAttribute("course");
}
%>

<!-- 메인페이지에서 상세보기 페이지 클릭시 넘어오는 페이지(장기,단기) -->
<!DOCTYPE html>
<html>
<title>상세 페이지 및 예약</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>

body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
.mySlides {display:none}
</style>
  
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
    $( "#datepicker" ).datepicker({ minDate: 0,
    	beforeShowDay: disableAllTheseDays});
  } ); 
  
  //var disabledDays =  ["2018-12-9 00:00:00","2018-12-24 00:00:00","2018-12-26 00:00:00"];
  var disabledDays =  ${reserdate}; //여기에 해당 코스번호로 예약 테이블의 예약 날짜를 배열 형식으로 불러와야함
  
  console.log(disabledDays);
  console.log(disabledDays[1]);
  var count = 0;
  var long_date = ${long_date};
  var before_date = 0;
  
  function disableAllTheseDays(date) { 
	  var m = date.getMonth(), d = date.getDate(), y = date.getFullYear(); 
	  m = m+1;
	  
	  var reser_date_count = 0;
	  if (m < 10 && m != 0)
			m = "0" + m;
	  
	  if (count > 0)
	  {	
		  if (before_date > d + 15)
			  before_date = 0;
		  
		  if ((before_date + 1) != d)
			  return [false];
		  
		  count--;
		  
		  before_date = d;
		  return [false];
	  }
	  
	  if($.inArray(y + "-" + m + "-" + d + " 00:00:00",disabledDays) != -1) {
		  before_date = d;
   		  count = (long_date[reser_date_count] - 1);
   		  reser_date_count++;
          return [false]; 
      }
	  return [true]; 
	}
  </script>
  <style>
  .ui-datepicker-week-end{color:red;}
  .ui-datepicker-week-end.ui-state-default{color:red;}
  </style>
</head>
<body class="w3-content w3-border-left w3-border-right">

<!-- Side bar/menu -->
<nav class="w3-sidebar w3-light-grey w3-collapse w3-top" style="z-index:3;width:260px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-transparent w3-display-topright"></i>
    <h3>상품 상세 정보</h3>
    <h3><%=course.getCourse_price() %>원</h3>
    <hr>
    
    <!-- 예약 테이블로 DB값 넘길 예정 -->
        <%if(leave_id != null) {%>
    <form action="/project_mvc22/view/MakeNewReserv" method="get" target="_blank" name="makeNewRes" onsubmit="return checkInput();">
      <p><label><i class="fa fa-calendar-check-o"></i> Reservation Date</label></p>
      <input type="w3-input w3-border" id="datepicker" placeholder="Month/Date/Year"name="reser_date" onchange="checkDate(this)" readonly/>          
     <p><label><i class="fa fa-male"></i> peoples</label></p>
      <input class="w3-input w3-border" type="number" value="1" id="peoples" name="peoples" min="1" onchange="peopleNum(this)"/>
      <input type="hidden" name="course_num" value="<%=course.getCourse_num()%>">             
      <p><button class="w3-button w3-block w3-green w3-left-align" type="submit"><i class="fa fa-search w3-margin-right"></i> 예약 하기 </button></p>
    </form>
        <%}%>
   <div class="w3-bar-block">
    <a href="#apartment" class="w3-bar-item w3-button w3-padding-16"><i class="fa fa-building"></i>패키지 이미지</a>
    <a href="#content" class="w3-bar-item w3-button w3-padding-16"><i class="fa fa-rss"></i>코스 구성 및 설명</a>
    <a href="#contact" class="w3-bar-item w3-button w3-padding-16"><i class="fa fa-envelope"></i>Contact</a>
  </div>
  </div>


  <script>
    	function checkDate(obj)
		{
    		var selectDate = new Date(obj.value);
    		var startDate = new Date($('#start_date').val());
    		var endDate = new Date($('#end_date').val());
    		
    		if ((selectDate <= startDate) || (selectDate > endDate))
   			{
   				alert('예약 가능한 날짜가 아닙니다!\n\n예약 가능 기간 : ' + $('#start_date').val() + " ~ " + $('#end_date').val());
   				$('#datepicker').val('');
   			}
		}
    	
    	function peopleNum(obj)
    	{
    		console.log(selectPeo);
    		var selectPeo = $('#peoples').val();
    		var peopleNum = $('#people_num').val(); //DB값
    		
    		if(parseInt(selectPeo) > parseInt(peopleNum))
   			{
   				alert('인원수를 초과하였습니다. 최대 인원수는 ' + $('#people_num').val() + '명 입니다.');
   				$('#peoples').val('');
   			}
    	}
    	
    	function checkInput(){
    		if ($('#datepicker').val() == '')
   			{
    			alert('날짜가 선택되지 않았습니다!');
   				return false;
   			}
    		if ($('#peoples').val() == '')
   			{
    			alert('인원수가 입력되지 않았습니다!');
   				return false;
   			}
    		return true;
    	}
    </script>
</nav>

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
        <p><i class="fa fa-fw fa-male"></i> 최대 가능 인원 : <%=course.getPeople_num()%>명</p>
        <p><i class="fa fa-fw fa-tv"></i><%=course.getVisa()%> VISA</p>
         <p><i class="fa fa-fw fa-tv"></i>예약 가능 일 <%=course.getStart_date()%> ~ <%=course.getEnd_date() %></p>
      </div>
      <div class="w3-col s6">
        <p><i class="fa fa-fw fa-clock-o"></i> 기간 = <%=course.getLong_date()%>일</p>
        <p><i class="fa fa-fw fa-tv"></i>국가 <%=course.getNation()%></p>
      </div>
      <input type="hidden" id="people_num" value="<%=course.getPeople_num() %>">
      <input type="hidden" id="start_date" value="<%=course.getStart_date() %>">
      <input type="hidden" id="end_date" value="<%=course.getEnd_date() %>">
    </div>
    <hr>
    <hr>
    <h4><strong>Additional Information</strong></h4>
 	<hr>
 	<%
 		int count = 1;
 	if(course.getInformation() != null){
 		for (Information info : course.getInformation())
 		{
 			%>
	    <!-- 카테고리의 정보들  -->
	    <h4><strong><%="Information #"+count%></strong></h4>
	    <div>
	    	<p><i class="fa fa-fw fa-tv"></i>세부정보 종류  = <%=info.getCategory_kind()%></p>
	    	<p><i class="fa fa-fw fa-tv"></i>세부정보 이름 = <%=info.getCategory_name()%></p>
	    	<p><i class="fa fa-fw fa-tv"></i>세부정보  = <%=info.getCategory_comments()%></p>
	    </div>
	    <hr>
	    <%
	    count++;
 		}
 	}else{
 		%>
 		<h4>추가 정보가 없습니다.</h4>
 		<hr>
 		<hr>
 		<%
 	}
     %>
    <h4><strong>Comments</strong></h4>
    <p><%=course.getComments()%></p>
    <p>We accept: <i class="fa fa-credit-card w3-large"></i> <i class="fa fa-cc-mastercard w3-large"></i> <i class="fa fa-cc-amex w3-large"></i> <i class="fa fa-cc-cc-visa w3-large"></i><i class="fa fa-cc-paypal w3-large"></i></p>
    <hr>


  <hr>
  
<!-- Contact -->
  <div class="w3-container" id="contact">
    <h2>현지 거주자에게 메일을 보내세요!</h2>
    <i class="fa fa-map-marker" style="width:30px"></i>국가 : <%=course.getNation() %><br>
    <i class="fa fa-phone" style="width:30px"></i>전화번호 : <%=course.getStay_tel()%><br>
    <i class="fa fa-envelope" style="width:30px"> </i> 메일 : <%=course.getStay_email()%><br>
    <i class="fa fa-male" style="width:30px"> </i> 이름 : <%=course.getStay_name()%><br>
    <p>Questions? Go ahead, ask them:</p>
    <form action="/action_page.php" target="_blank">
      <p><input class="w3-input w3-border" type="text" placeholder="Name" required name="Name"></p>
      <p><input class="w3-input w3-border" type="text" placeholder="Email" required name="Email"></p>
      <p><input class="w3-input w3-border" type="text" placeholder="Message" required name="Message"></p>
    <button type="submit" class="w3-button w3-green w3-third">Send a Message</button>
    </form>
  </div>

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
