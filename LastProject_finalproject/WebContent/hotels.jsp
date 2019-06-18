<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Major - 5* Hotel template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link href="plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/rooms.css">
<link rel="stylesheet" type="text/css" href="styles/rooms_responsive.css">
<title>The Third Milestone</title>
  <!-- --------------------------------------------------------------------------------------- -->
  <!-- --------------------------------------------------------------------------------------- -->
  <!-- --------------------------------------------------------------------------------------- -->
  <!-- --------------------------------------------------------------------------------------- -->
  
  <!-- 데이트피커 -->
  
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-135674074-1"></script>
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());
        gtag('config', 'UA-135674074-1');
    </script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.6/highlight.min.js"></script>
    <script src="datepicker/kronos.js"></script>
    <link rel="stylesheet" href="datepicker/kronos.css"></link>
    
   <!-- --------------------------------------------------------------------------------------- -->
   <!-- --------------------------------------------------------------------------------------- -->
   <!-- --------------------------------------------------------------------------------------- -->
   <!-- --------------------------------------------------------------------------------------- -->
</head>
<style>
.buttonT{
	background-color:#2E3F61;
	display: block;
    position: relative;
    width: 100%;
    height: 100%;
    font-size: 14px;
    font-weight: 500;
    color: #FFFFFF;
    line-height: 63px;
    text-transform: uppercase;
    z-index: 1;
}

.buttonT:hover {
    text-decoration: none;
    -webkit-font-smoothing: antialiased;
    -webkit-text-shadow: rgba(0,0,0,.01) 0 0 1px;
    text-shadow: rgba(0,0,0,.01) 0 0 1px;
	background-color: #637496;
}
</style>
<%
        Calendar cal = Calendar.getInstance();

    	int Year = cal.get(Calendar.YEAR); //년
    	int Month = cal.get(Calendar.MONTH) + 1; //월

    	int Date = cal.get(Calendar.DATE); //일
    	int Day = cal.get(Calendar.DAY_OF_WEEK); //요일
 %>
<body>

<div class="super_container">
	
	<jsp:include page="./inc/header.jsp" flush="false"/>

	<!-- Menu -->

	<div class="menu">
		<div class="background_image" style="background-image:url(images/mainimages/menu.jpg)"></div>
		<div class="menu_content d-flex flex-column align-items-center justify-content-center">
			<ul class="menu_nav_list text-center">
				<li><a href="#">Home</a></li>
				<li><a href="#">About us</a></li>
				<li><a href="#">Rooms</a></li>
				<li><a href="#">News</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
			<div class="menu_review"><a href="#">Book Now</a></div>
		</div>
	</div>

	<!-- Home -->

	<div class="home">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/mainimages/rooms_2.jpg" data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_title"><h1><font style="font-size: 85px">Hotels</font></h1></div>
							<div class="home_text"><font style="font-size: 25px">What can we do for you?</font></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- --------------------------------------------------------------------------------------- -->
<!-- --------------------------------------------------------------------------------------- -->
<!-- --------------------------------------------------------------------------------------- -->

	<!-- Search Box -->

	<div class="search_box" style="height: 120px">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="search_box_container d-flex flex-row align-items-center justify-content-start">
						<div class="search_form_container">


							<!-- 검색 폼 태그 -->
							<form class="search_form" action="./goToListPageSearch.hdo" method="get" id="search_form">
								<div class="d-flex flex-lg-row flex-column align-items-center justify-content-start">
									<!-- 검색창 -->
										<div id="outer" style="margin-right: 5px">
											<div id="inner">
												<section class="demo">
												<div class="cont">
													<input class="input_text" type="text" id="kronos6-1" readonly="readonly" placeholder="체크인" style="width: 200px; height: 65px" name="check_in">
													<input class="input_text" type="text" id="kronos6-2" readonly="readonly" placeholder="체크아웃" style="width: 200px; height: 65px" name="check_out">
													<script>
														$('#kronos6-1').kronos({periodTo : '#kronos6-2'});
														$('#kronos6-2').kronos({periodFrom : '#kronos6-1'});
													</script>
												</div>
												</section>
											</div>
										</div>
										
										<!-- 인원 셀렉트 부분 -->
											<select style="width: 200px; height: 65px; margin-left: -1px; margin-right: 2px" name="people_num">
													<option value="1">성인 1명</option>
													<option value="2">성인 2명</option>
													<option value="4">가족(4인 기준)</option>
													<option value="5">단체</option>
											</select>
										<input class="input_text" type="text" name="input_text" placeholder="호텔이나 지역을 입력하세요." style="width:300px; height: 65px">
									<input class="search_button" type="submit" value="search">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- --------------------------------------------------------------------------------------- -->
	<!-- --------------------------------------------------------------------------------------- -->
	<!-- --------------------------------------------------------------------------------------- -->
	
		<!-- Intro -->

	<div class="intro" style="margin-bottom: -100px">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="section_title_container text-center">
						<div class="section_title"><h1>All HOTEL</h1></div>
						<div class="section_text">
						Gets the list of all hotels.
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>
	
	<!-- Rooms -->

	<div class="rooms">
		<div class="container">
			<div class="row rooms_row">
				
				<!-- Room -->
				<c:choose>
				<c:when test="${!empty getLists}">
				<c:forEach items="${getLists}" var="Lhdao">  
				<div class="col-lg-4" style="margin-bottom: 50px">
					<div class="rooms_item">
						<div class="rooms_image"><img src="images/hotelimages/${Lhdao.getHotel_main_image()}" width="360px" height="295px" alt="https://unsplash.com/@yuni_ladyday2"></div>
						<div class="rooms_title_container text-center">
							<div class="rooms_title">
							<h1>${fn:split(Lhdao.getHotel_name(),'(')[0]}</h1>
							<h4>(${fn:split(Lhdao.getHotel_name(),'(')[1]}</h4>
							</div>
						</div>
						<div class="rooms_list">
							<ul>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div>
									Address<br>
										${Lhdao.getHotel_address()}
									</div>
								</li>
								
								<hr>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div>
									<b>information</b><br>
									${fn:substring(Lhdao.getHotel_instroduct(),0,250)}....
									</div>
								</li>
										
						<hr>					
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>Hotel Number:</div></div>
									<div>${Lhdao.getHotel_tel()}</div>
								</li>	
						<hr>
						<!-- 편의 시설 루프 -->	
						<c:choose>
						<c:when test="${!empty getFacilityLists}">
						<c:forEach items="${getFacilityLists}" var="Fhdao"> 
						<c:if test="${Lhdao.getHotel_no() eq Fhdao.getHotel_no()}"> 
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>Convenient Facilities#:</div></div>
									<div>${Fhdao.getHotel_option_title()}</div>
								</li>
						</c:if>
						</c:forEach>
						</c:when>
						<c:otherwise>등록된 편의 시설이 없습니다.</c:otherwise>
						</c:choose>
								
								<!-- 별점 갯수에 따라 별이미지 띄우기 -->
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>STAR</div></div>
									
									<!-- 별점 갯수 별로 별갯수 이미지 변환 -->
									<%-- ${Lhdao.getHotel_class()} --%>
									
									<!-- 1성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 1}">
									<div><img src="images/starimages/star_image.png" width="20px" height="20px"></div>
									</c:if>

									<!-- 2성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 2}">
									<div><img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px"></div>
									</c:if>
									
									<!-- 3성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 3}">
									<div><img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px"></div>
									</c:if>
									
									<!-- 4성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 4}">
									<div><img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px"></div>
									</c:if>
									
									<!-- 5성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 5}">
									<div><img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px">
									<img src="images/starimages/star_image.png" width="20px" height="20px">
									</div>
									</c:if>
								</li>
							</ul>
						</div>
						<div class="button rooms_button ml-auto mr-auto">
						<div class="button rooms_button ml-auto mr-auto">
						<form action="./RoomDetails.rd" method="get">
						
						<input type="hidden" name="check_in" value="<%=Year%>-<%=Month%>-<%=Date%>">
						<input type="hidden" name="check_out" value="<%=Year%>-<%=Month%>-<%=Date+1%>">
						<input type="hidden" name="people_num" value="1">
						<input type="hidden" name="hotel_no" value="${Lhdao.getHotel_no()}">
						<input class="buttonT" type="submit" value="More Information">
						</form>
						</div>
						</div>
					</div>
				</div>
				</c:forEach>
				</c:when>
				</c:choose>
			</div>
		</div>
	</div>


	
</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap-4.1.2/popper.js"></script>
<script src="styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/progressbar/progressbar.min.js"></script>
<script src="plugins/colorbox/jquery.colorbox-min.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCIwF204lFZg1y4kPSIhKaHEXMLYxxuMhA"></script>
<script src="js/rooms.js"></script>
</body>
<jsp:include page="./inc/Footer.jsp" flush="false"/>
</html>