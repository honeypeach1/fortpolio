<%@page import="project.adminGetList.db.AdminGetListBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>호텔의 상세 보기 페이지입니다.</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- css 추가 -->
<link rel="stylesheet" href="./css/w3.css">
<link rel="stylesheet" href="./css/roomInfoTable.css">
<link rel="stylesheet" href="./css/comment2.css">

<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Raleway", Arial, Helvetica, sans-serif
}

.mySlides {
	display: none
}
</style>
<%
	AdminGetListBean AdGetList = null;

	if(request.getAttribute("AppHotelInfor") != null){
		AdGetList= (AdminGetListBean)request.getAttribute("AppHotelInfor");
	}
%>
</head>
<body class="w3-content w3-border-left w3-border-right">

	<!-- //////////////////////////////////////////////////////////////////////////////////////// -->


	<!-- !PAGE CONTENT! -->
	<!-- 페이지 시작 -->

	<div class="w3-main w3-white">

		<!-- Push down content on small screens -->
		<div class="w3-hide-large" style="margin-top: 80px"></div>


		<!-- Slideshow Header -->
		<div class="w3-container" id="apartment">
			<h2 class="w3-text-green"><%=AdGetList.getHotel_name()%></h2>



			<!-- 호텔 이미지 리스트 가져오기 -->
			<% 
				if(AdGetList.getAdGetImages() != null){
					for(AdminGetListBean images : AdGetList.getAdGetImages()){
			%>
			<div class="w3-display-container mySlides">
				<img src="images/<%=images.getHotel_pic()%>"
					style="width: 100%; height:500px; margin-bottom: -6px">
				<div class="w3-display-bottomleft w3-container w3-black"></div>
			</div>
			<%}
				}else{
					System.out.println("호텔의 부가 이미지가 없습니다.");
			}%>
		</div>



		<div class="w3-row-padding w3-section">
			
			<% 
				if(AdGetList.getAdGetImages() != null){
					int i = 0;
					for(AdminGetListBean images : AdGetList.getAdGetImages()){			

			%>
			
			<div class="w3-col s3">
				<img class="demo w3-opacity w3-hover-opacity-off"
					src="images/<%=images.getHotel_pic()%>" style="width: 100%; cursor: pointer"
					onclick="currentDiv(<%=i+=1%>)" title="Living room">
			</div>

			<%
			   }
				}else{
					System.out.println("호텔의 부가 이미지가 없습니다.");
				}%>

		</div>


		<!-- //////////////////////////////////////////////////////////////////////////////////////// -->


		<!-- class="w3-container" 1 시작 -->
		<div class="w3-container">
			<h4>
				<strong color="#4c5c7e">편의시설</strong>
			</h4>
			<div class="w3-row w3-large">
				<div class="w3-col s6">
					
					<%-- <%
					   if(AdGetList.getAdGetList() != null){
						   System.out.println("확인하기 : " + AdGetList.getAdGetList().size());
						for(AdminGetListBean facility : AdGetList.getAdGetList()){
							int i = 0;
							
							
							//3개씩 띄워 쓸려궁 ㅎㅎ
							if(i % 3 == 0){
					%> --%>
					<!-- 편의시설 리스트 가져오기 -->
					
					
				</div>
				<div class="w3-col s6">
					<c:choose>
					<c:when test="${!empty AppFacilityHotel}">
					<c:forEach items="${AppFacilityHotel}" var="facility">
					<%-- <%
							}
					%> --%>
					<!-- 편의시설 이미지, 이름 -->
						<p>
						<img src="icon/${facility.getHotel_option_name()}" width="30px" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${facility.getHotel_option_title()}
						<br>
						</p>
					<%-- <%
					System.out.println("jsp 페이지 추가 시설 이미지 이름 : "+facility.getHotel_option_image());
					System.out.println("jsp 페이지 추가 시설 옵션 이름 : "+facility.getHotel_option_name());
						}
					   }else{
						   out.print("해당 호텔의 시설물 정보가 없습니다.");
					   }
					
					%> --%>
					</c:forEach>
					</c:when>
					</c:choose>
				</div>
			</div>

			<hr>

			<h4>
				<strong>호텔 소개</strong>
			</h4>
			<p><%=AdGetList.getHotel_instroduct()%></p>


			<!-- 객실 List 보여주기 -->
			<div class="w3-row w3-large">
				<jsp:include page="/AppRoomInfoTable.jsp" flush="false"/>
			</div>
			<div class="w3-row w3-large">
			</div>
		</div>
		<!-- class="w3-container" 1 끝 -->

		<!-- <hr> -->




		<!-- End page content -->
	</div>

	<!-- id="subscribe" : 객실 이미지 클릭 시 나타나는 슬라이드 화면 -->
	<div id="subscribe" class="w3-modal">
		<div class="w3-modal-content w3-animate-zoom w3-padding-large">
			<div class="w3-container w3-white w3-center">

				<!-- [x] 버튼 -->
				<i onclick="document.getElementById('subscribe').style.display='none'" class="fa fa-remove w3-button w3-xlarge w3-right w3-transparent" style="background-color: black;"></i>
				<h2 class="w3-wide"></h2>

				<!-- 그림 추가하기 -->
				<!-- Slideshow Header -->

				<div class="powerSpace"></div>

				<div class="w3-container" id="apartment">


					<div class="w3-content"
						style="max-width: 800px; position: relative">

									<img class="smy_Slides w3-animate-opacity" src="./w3images/snow.jpg" style="width: 100%">

						<!-- [<] 버튼 -->
						<a class="w3-button w3-hover-dark-grey"
							style="position: absolute; top: 45%; left: 0;"
							onclick="splusDivs(-1)">❮</a>

						<!-- [>] 버튼 -->
						<a class="w3-button w3-hover-dark-grey"
							style="position: absolute; top: 45%; right: 0;"
							onclick="splusDivs(+1)">❯</a>
					</div>

				</div>
			</div>
		</div>

		<script src="script/script3_slideImage.js"></script>

	</div>
	<!-- id="subscribe" : 객실 이미지 클릭 시 나타나는 슬라이드 화면 끝 -->






	<!-- //////////////////////////////////////////////////////////////////////////////////////// -->


	<!-- hotel.jsp 상단 호텔 이미지 보여주는 스크립트 -->
	<script src="./script/script.js"></script>



</body>


</html>