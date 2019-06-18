<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Calendar"%>
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
<!-- hot deal페이지 css -->
<link rel="stylesheet" type="text/css" href="styles/blog.css">
<link rel="stylesheet" type="text/css" href="styles/blog_responsive.css">
<body>

	<div class="super_container">

		<jsp:include page="./inc/header.jsp" flush="false" />

		<!-- Menu -->

		<div class="menu">
			<div class="background_image"
				style="background-image: url(images/mainimages/menu.jpg)"></div>
			<div
				class="menu_content d-flex flex-column align-items-center justify-content-center">
				<ul class="menu_nav_list text-center">
					<li><a href="#">Home</a></li>
					<li><a href="#">About us</a></li>
					<li><a href="#">Rooms</a></li>
					<li><a href="#">News</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<div class="menu_review">
					<a href="#">More Information</a>
				</div>
			</div>
		</div>

		<!-- Home -->

		<div class="home">
			<div class="parallax_background parallax-window"
				data-parallax="scroll" data-image-src="images/mainimages/hotDealImage.jpg"
				data-speed="0.8"></div>
			<div class="home_container">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="home_content text-center">
								<div class="home_title">
									<h1>
										<font style="font-size: 85px">Hot Deals</font>
									</h1>
								</div>
								<div class="home_text">
									<font style="font-size: 25px">Do you want to find the hotel information in the order of popularity?</font>
								</div>
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

		<!-- hot deal 리스트 -->
		<!-- 	
			해당 모듈에서 가져오는 리스트 정보
			1.순번
			2.호텔이름
			3.방 타입 번호
			4.호텔번호
			5.최소인원
			6.최대인원
			7.방 정보
			8.원래 가격
			9.방 타입
			10.할인가
			11.할인율

			
			사진은 따로 리스트를 구현해서 가져옴.
			그래서 위의 모듈의 방번호와 일치하면 해당 리스트에 이미지 뿌려주기
		 -->
		<c:choose>
		<c:when test="${!empty getHotDeal}">
		<c:forEach items="${getHotDeal}" var="hdeal">  
		<div class="blog">
		<h1 style="margin-bottom: 20px; text-align: left; margin-left: 270px; color: #5D5D5D"><i>Hot Deal <font style="size:55px;color:#000;">${hdeal.getRownum()}</font>순위</i></h1>
			<div class="container">
				<div class="row">
					<div class="col">

							<!-- Blog Post -->
							<div class="blog_post">
								<div class="row">
									<div class="col-xl-7 col-lg-6">
										<div class="blog_post_image">
										
										<!-- 해당 핫딜 리스트에 맞는 가장 낮은 번호의 이미지를 가져옴 -->
										<c:choose>
										<c:when test="${!empty getHotDealImage}">
										<c:forEach items="${getHotDealImage}" var="hdImage"> 
										<c:if test="${hdeal.getRoom_type_no() eq hdImage.getRoom_type_no()}"> 
											<img src="hotelImage/${hdImage.getRoom_pic()}" alt="https://unsplash.com/@derekrliang">
										</c:if>
										</c:forEach>
										</c:when>
										</c:choose>
										
										</div>
									</div>
									<div class="col-xl-5 col-lg-6">
										<div class="blog_post_content">
											<div class="bp_date trans_200">
												<a><font style="color: white; width:180px; font-family: 'Alike', sans-serif;"><b>${hdeal.getHotel_name()}</b></font></a>
											</div>
											<div class="pb_title">
												<a href="#" style="font-family: 'Alike', sans-serif;">방 타입 : ${hdeal.getRoom_name()}</a>
											</div>
											<div class="pb_info" style="font-family: 'Alike', sans-serif;">
												<ul class="d-flex flex-row align-items-center justify-content-start">
													
													<li>
												<ul class="d-flex flex-row align-items-center justify-content-start">
													<li><font style="font-size: 14px">최소 인원 : ${hdeal.getRoom_basic_people()}</font></li>
												</ul>
													</li>
													<li><font style="font-size: 14px">최대 인원 : ${hdeal.getRoom_max_people()}</font></li>
												</ul>
											</div>
											<div class="pb_text">
												<p style="height: 200px">${hdeal.getRoom_info()}</p>
											</div>
											
											<table><tr>
											<td>
											<div style="font-size: 18px;font-family: 'Alike', sans-serif;">&nbsp;&nbsp;원가 :<font style="text-decoration: line-through;"> ${hdeal.getRoom_price()}원</font></div>
											</td>
											<td>
											<div style="font-size: 18px;font-family: 'Alike', sans-serif;">&nbsp;&nbsp;할인가 : <font style="color: red">${hdeal.getDiscount_price()}원</font><br></div>
											</td>
											<td>
											<div style="border:1px solid;font-family: 'Alike', sans-serif;">할인율 : ${hdeal.getDiscount_rate()}&#37;</div>
											</td>
											<tr></table>
											<div class="button blog_button">
											<form action="./RoomDetails.rd" method="get">
											
											<input type="hidden" name="check_in" value="<%=Year%>-<%=Month%>-<%=Date%>">
											<input type="hidden" name="check_out" value="<%=Year%>-<%=Month%>-<%=Date+1%>">
											<input type="hidden" name="people_num" value="1">
											<input type="hidden" name="hotel_no" value="${hdeal.getHotel_no()}">
												<input class="buttonT" type="submit" value="More!">
											</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
			</c:when>
			<c:otherwise><div class="facilities">
		<div class="container"><div align="center"><h2>Hot Deal 리스트가 없습니다. <br><br> 하지만 상심하지 마세요! 곧 핫딜 리스트가 나올것입니다. </h2></div></div></div></c:otherwise>
			</c:choose>
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
<script src="js/custom.js"></script>
</body>
<jsp:include page="./inc/Footer.jsp" flush="false"/>
</html>