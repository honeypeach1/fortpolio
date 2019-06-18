<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
input_text {
	position: relative;
	width: 20%;
	height: 73px;
	cursor: pointer;
	padding-left: 28px;
	padding-right: 16px;
	background: #FFFFFF;
}

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
	// jsp페이지로 실행이 되기 때문에 가상주소로 매핑후 top3 리스트 가져오기
	if (request.getAttribute("getList") == null)
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/goToMainPage.li");
		dispatcher.forward(request, response);
	}

	Calendar cal = Calendar.getInstance();

	int Year = cal.get(Calendar.YEAR); //년
	int Month = cal.get(Calendar.MONTH) + 1; //월

	int Date = cal.get(Calendar.DATE); //일
	int Day = cal.get(Calendar.DAY_OF_WEEK); //요일

%>
<link rel="stylesheet" type="text/css" href="styles/rooms.css">
<body>
<jsp:include page="./inc/header.jsp" flush="false"/>

<div class="super_container">

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
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/mainimages/mainImage.jpg" data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_title"><h1><font style="font-size: 85px">Hotel & Resort</font></h1></div>
							<div class="home_text"><font style="font-size: 25px">What do you want Accommodations? Anything searching know!</font></div>
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



		<!-- Facilities -->

	<div class="facilities">
		<div class="container">
			<div class="row icon_box_row">

				<!-- Icon Box -->
				<div class="col-lg-4">
					<div class="icon_box text-center">
						<div class="icon_box_icon"><img src="images/mainimages/icon_1.svg" alt="https://www.flaticon.com/authors/monkik"></div>
						<div class="icon_box_title"><h2>Many Hotels</h2></div>
						<div class="icon_box_text">
							<p>심사를 거쳐 엄선된 수많은 호텔정보들이 있습니다. 어떤 호텔을 원하시는지, 어떻게 가야하는지, 호텔은 어떻게 생겼는지 걱정하지 마세요.</p>
						</div>
					</div>
				</div>

				<!-- Icon Box -->
				<div class="col-lg-4">
					<div class="icon_box text-center">
						<div class="icon_box_icon"><img src="images/mainimages/icon_9.svg" alt="https://www.flaticon.com/authors/monkik"></div>
						<div class="icon_box_title"><h2>Beautiful Rooms</h2></div>
						<div class="icon_box_text">
							<p>아름다운 방을 보고 싶다구요? 걱정하지 마세요. 상세 페이지에서 방의 사진들과 방의 정보를 열람하실수 있습니다. 편하게 버튼하나로 예약하세요!</p>
						</div>
					</div>
				</div>

				<!-- Icon Box -->
				<div class="col-lg-4">
					<div class="icon_box text-center">
						<div class="icon_box_icon"><img src="images/mainimages/icon_6.svg" alt="https://www.flaticon.com/authors/monkik"></div>
						<div class="icon_box_title"><h2>Luxury Resort</h2></div>
						<div class="icon_box_text">
							<p>호화스러운 리조트를 원하시나요? 상단 검색창을 이용하시면 수많은 호텔과 리조트 정보를 받으실수 있습니다. 검색창에 입력하시고 버튼을 클릭하세요!</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Intro -->
	<div class="intro" style="margin-bottom: -100px">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="section_title_container text-center">
						<div class="section_title"><h1>High StarLevel HOTEL</h1></div>
						<div class="section_text">
						This is the highest level hotel TOP 3. If you want to more about hotel information, please use searchbar.
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>

	<!-- Hotels -->

	<div class="rooms" style="margin-top: -50px">
		<div class="container">
			<div class="row rooms_row">
				
				
				<!-- 3개의 호텔 인기순으로 가져오기 -->
				<!-- 
					호텔 이름 0
					호텔 주소 0
					호텔 등급 0
					호텔 편의 시설 0
					호텔 메인 사진 0
					호텔 연락처 0
					호텔 정보
				 -->
				<!-- TOP N함수로 3개만 상위 등급만 추리기 -->
				<c:choose>
				<c:when test="${!empty getList}">
				<c:forEach items="${getList}" var="Lhdao">  
				<div class="col-lg-4">
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
						<br>
						<hr>		
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div>Hotel Number:</div></div>
									<div>${Lhdao.getHotel_tel()}</div>
								</li>
						<br>	
						<hr>
						<!-- 편의 시설 루프 -->	
						<c:choose>
						<c:when test="${!empty getFacilityList}">
						<c:forEach items="${getFacilityList}" var="Fhdao"> 
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
									
									<c:forEach begin="1" end="${Lhdao.getHotel_class()}" step="1">
										<img src="images/starimages/star_image.png" width="20px;" height="20px;">
									</c:forEach>
									
									<%-- <!-- 1성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 1}">
									<div><img src="images/star_image.jpg" width="20px" height="20px"></div>
									</c:if>

									<!-- 2성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 2}">
									<div><img src="images/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px"></div>
									</c:if>
									
									<!-- 3성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 3}">
									<div><img src="images/starimages/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px"></div>
									</c:if>
									
									<!-- 4성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 4}">
									<div><img src="images/starimages/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px"></div>
									</c:if>
									
									<!-- 5성일때  -->
									<c:if test="${Lhdao.getHotel_class() eq 5}">
									<div><img src="images/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px">
									<img src="images/starimages/star_image.jpg" width="20px" height="20px">
									<img src="images/star_image.jpg" width="20px" height="20px">
									<img src="images/star_image.jpg" width="20px" height="20px"></div>
									</c:if> --%>
								</li>
							</ul>
						</div>
						<div class="button rooms_button ml-auto mr-auto">
						<form action="./RoomDetails.rd" method="get">
						<input type="hidden" name="hotel_no" value="${Lhdao.getHotel_no()}">
						<input type="hidden" name="check_in" value="<%=Year%>-<%=Month%>-<%=Date%>">
						<input type="hidden" name="check_out" value="<%=Year%>-<%=Month%>-<%=Date+1%>">
						<input type="hidden" name="people_num" value="1">
						<input class="buttonT" type="submit" value="More Information">
						</form>
						</div>						
					</div>
				</div>
				</c:forEach>
				</c:when>
				</c:choose>

			</div>
		</div>
	</div>
	
	<!-- Registering a hotel -->

	<div class="room d-flex flex-lg-row flex-column align-items-start justify-content-start">
		<div class="room_content">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="room_title">
							<div class="section_title_container_2">
								<div class="section_title"><i><h1>호텔을 등록하고<br>싶으신가요?</h1></i></div>
							</div>
						</div>
						<div class="room_list">
							<ul>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div>로그인을 하지 않고도 호텔정보를 입력하실수 있습니다.</div>
								</li>
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><font style="color: #8C8C8C"><i>단 심사를 거쳐 검증되지 않은 호텔일 경우 정보가<br> 삭제될수도 있습니다.</i></font></div>
								</li>
							</ul>
						</div>
						<table><tr><td>
						<%if(session.getAttribute("user_num") == null){ %>
						<div class="button room_button trans_200"><a href="./regHotel.jsp">Registering now!</a></div>
						</td><td><div class="button room_button trans_200"><a href="#" onclick="window.open('CheckHotel.jsp','window팝업','width=410, height=460, menubar=no, status=no, toolbar=no');">Checking now!</a></div>
						<%}else{ %>
						<br>
						<div><font style="font-size: 18px; color: white;"><i>- 비회원만 호텔을 등록할수 있습니다. -</i></font></div>
						<%} %>
						</td></tr></table>
					</div>
				</div>
			</div>		
		</div>
		<div class="room_image">
			<div class="background_image" style="background-image:url(images/mainimages/advice1.jpg)"></div>
		</div>
	</div>
	

</div>

<jsp:include page="./inc/Footer.jsp" flush="false"/>

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
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyC7Mwb0KdtW4wJCYdVG_rh5_qqvYLjK3Fg"></script>
<script src="js/custom.js"></script>
</body>
</html>