<%@page import="net.hotel.db.HotelOptionBean"%>
<%@page import="net.hotel.db.HotelBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- css 추가 -->
<link rel="stylesheet" href="./datepicker/kronos.css">
<link rel="stylesheet" href="./css/w3.css">
<link rel="stylesheet" href="./css/roomInfoTable.css">
<link rel="stylesheet" href="./css/comment2.css">


<!-- 지워도 문제없는 css는 일단 주석처리 함 -->
<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> -->
<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->

<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
.mySlides {display: none}
</style>

</head>
<body class="w3-content w3-border-left w3-border-right">


<!-- 헤더 영역  시작-->
<jsp:include page="./inc/header.jsp"/>
<!-- 헤더 영역 끝 -->


<!-- //////////////////////////////////////////////////////////////////////////////////////// -->
  

<!-- !PAGE CONTENT! -->
<!-- 페이지 시작 -->

<%
System.out.println("===roomDetail.jsp===");

HotelBean hb = (HotelBean) request.getAttribute("hb");
List optionList = (List) request.getAttribute("optionList");
List hotelImageList = (List) request.getAttribute("hotelImageList");
%>

<div class="w3-main w3-white">

  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:80px"></div>

	
  <!-- Slideshow Header -->
  <div class="w3-container" id="apartment">
			<div class="powerSpace2"></div>
			<h2 class="w3-text-HotelBlue"><%=hb.getHotel_name()%></h2>

			<%
				for (int i = 0; i < hotelImageList.size(); i++) {
					String src1 = (String) hotelImageList.get(i);
			%>
			<div class="w3-display-container mySlides">
				<img src="./hotelImage/<%=src1 %>" style="width: 100%; margin-bottom: -6px">
				<div class="w3-display-bottomleft w3-container w3-black">
				</div>
			</div>

			<%
				}
			%>
		</div>
		

		
		<div class="w3-row-padding w3-section">
		
			<%
				for (int i = 0; i < hotelImageList.size(); i++) {
					String src1 = (String) hotelImageList.get(i);
			%>
			<div class="w3-col s3">
				<img class="demo w3-opacity w3-hover-opacity-off"
					src="./hotelImage/<%=src1 %>"
					style="width: 100%; height: 150px; cursor: pointer" onclick="currentDiv(<%=i+1 %>)"
					title="Living room">
			</div>

			<%
				}
			%>			
		</div>
  
  
<!-- //////////////////////////////////////////////////////////////////////////////////////// -->


  <!-- class="w3-container" 1 시작 -->
  <div class="w3-container">
			<h4>
    <strong color="#4c5c7e">편의시설</strong>
   </h4>
   <div class="w3-row w3-large">
    <div class="w3-col s6">
     <%
      for (int i = 0; i < optionList.size(); i++) {
       HotelOptionBean hob = (HotelOptionBean)optionList.get(i);

       if (i % 3 == 0) {
     %>
    </div>
    <div class="w3-col s6">
     <%
      }
     %>
     <p>
      <img src="./icon/<%=hob.getHotel_option_name()%>" width="30px" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=hob.getHotel_option_title()%></p>

     <%
      }
     %>
    </div>
   </div>
			
	<hr>
    
    <h4><strong>호텔 소개</strong></h4>
    <p><%=hb.getHotel_instroduct() %></p>
    
    

    
    <!-- 객실 List 보여주기 -->
	<div class="w3-row w3-large">
	  <jsp:include page="./roomInfoTable.jsp"/>
	</div>
	
	
	
   <div class="w3-row w3-large">
    <%-- <jsp:include page="./review.jsp" /> --%>
    
    <!-- 구글 지도 -->
    <hr>
    <div class="w3-row w3-large">
     <h4>
      <strong>지도</strong>
     </h4>
     <div class="w3-row w3-large">
      <jsp:include page="./hotelMap.jsp" >
       <jsp:param value="<%=hb.getHotel_address()%>" name="addr"/>
      </jsp:include>
     </div>
     <br>
    </div>
    
    <!-- Comment 댓글 -->
    <hr>
    <h4>
     <strong id="review">최근 리뷰 보기</strong>
    </h4>
   	<jsp:include page="./review.jsp" />
    <div class="powerSpace"></div>
   </div>
  </div>
  <!-- class="w3-container" 1 끝 -->

<!-- End page content -->
</div>


<!-- //////////////////////////////////////////////////////////////////////////////////////// -->


  <div class="super_container" style="position: absolute; left: 0">
  <!-- 푸터 영역  시작-->
  <jsp:include page="./inc/Footer.jsp" />
  <!-- 푸터 영역 끝 -->
 </div>


<!-- //////////////////////////////////////////////////////////////////////////////////////// -->

<!-- id="subscribe" : 객실 이미지 클릭 시 나타나는 슬라이드 화면 -->
 <div id="subscribe" class="w3-modal">
  <div class="w3-modal-content w3-animate-zoom w3-padding-large">
   <div class="w3-container w3-white w3-center">
    
    <!-- [x] 버튼 -->
    <i onclick="document.getElementById('subscribe').style.display='none'"
     class="fa fa-remove w3-button w3-xlarge w3-right w3-transparent"></i>
    <h2 class="w3-wide"></h2>

    <!-- 그림 추가하기 -->
    <!-- Slideshow Header -->
	
	<div class="powerSpace"></div>

    <div class="w3-container" id="apartment">


     <div class="w3-content"
      style="max-width: 800px; position: relative">
	   
	   <!-- 사진 4개 -->
       <img class="smy_Slides w3-animate-opacity"
       src="./w3images/snow.jpg" style="width: 100%">
       <img
       class="smy_Slides w3-animate-opacity" src="./w3images/lights.jpg"
       style="width: 100%">
       <img
       class="smy_Slides w3-animate-opacity"
       src="./w3images/mountains.jpg" style="width: 100%">
       <img
       class="smy_Slides w3-animate-opacity" src="./w3images/forest.jpg"
       style="width: 100%">
       
       <!-- [<] 버튼 -->
       <a
       class="w3-button w3-hover-dark-grey"
       style="position: absolute; top: 45%; left: 0;"
       onclick="splusDivs(-1)">❮</a>
       
       <!-- [>] 버튼 -->
       <a
       class="w3-button w3-hover-dark-grey"
       style="position: absolute; top: 45%; right: 0;"
       onclick="splusDivs(+1)">❯</a>
     </div>

    </div>
   </div>
  </div>
  
  <script src="./script/script3_slideImage.js"></script>

</div>
<!-- id="subscribe" : 객실 이미지 클릭 시 나타나는 슬라이드 화면 끝 -->
    
    
    



<!-- //////////////////////////////////////////////////////////////////////////////////////// -->


<!-- hotel.jsp 상단 호텔 이미지 보여주는 스크립트 -->
<script src="./script/script.js"></script>


  
</body>


</html>