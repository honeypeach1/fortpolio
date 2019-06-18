<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
    <h5><b><i class="fa fa-dashboard"></i> 나의 코스를 등록하세요!</b></h5>
  </div>
  </header>

<div class="w3-half4 w3-red w3-container w3-center w3-round" style="height:1200px; width:70%; background-image">
    <div class="w3-padding-64 w3-padding-large w3-center w3-round" style="height:1000px; width:100%;">
      <h1> 메인 코스를 등록합니다. </h1>
      <form method="post" class="w3-container w3-card w3-padding-32 w3-white w3-round" enctype="multipart/form-data" action="/project_mvc22/view/CourseRegist" target="_blank" style="height:1000px">
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
        <div class="w3-section" style="height:5%">
          <div style="border: 1px; float: left; width: 30%; height:50px" class="w3-input"><label><i class="fa fa-users w3-text-gray w3-large"> 기간</i></label></div>
          <div style="border: 1px; float: left; width: 60%; height:50px"><input class="w3-input" type="number" id="LongDate" name="LongDate" placeholder="장단기 여행시 입력해주세요." onchange="allowDate(this)"></div>
        </div>
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
          <div style="border: 1px; float: left; width: 60%;height:50px"><input type="file" name="CourseImg1" value="이미지 저장">
          <!-- <input type="submit" style="height:50px; margin-right:5px;height:50px" value="등록"><input type="reset" style="height:50px" value="취소"> -->
		</div>
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
        <button type="submit" class="w3-button w3-black w3-center w3-large w3-round" style="width:50%">코스 상품 등록하기</button>
      </div>
      </form>
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
