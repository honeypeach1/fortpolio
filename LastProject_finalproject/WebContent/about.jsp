<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>The Third Milestone</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Major - 5* Hotel template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link href="plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/contact.css">
<link rel="stylesheet" type="text/css" href="styles/contact_responsive.css">

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
    width: 30%;
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
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/mainimages/rooms_3.jpg" data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_title"><h1><font style="font-size: 85px">About Us</font></h1></div>
							<div class="home_text"><font style="font-size: 25px">We are.....</font></div>
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

	<!-- Contact -->
	<c:choose>
	<c:when test="${!empty aboutInfor}">
	<c:forEach items="${aboutInfor}" var="aboutInfor">
	
	<div class="contact_section" style="margin-top: 100px">
	
		<div class="container">
			<div class="row">

				<!-- Contact Content -->
				<div class="col-lg-5">
					<div class="contact_content">
						<div class="contact_logo">
							<div class="logo">
								<a href="#">
								<div>The Third Milestone</div>
								<div>Hotel Reservation Site</div>
								</a>
							</div>
						</div>
						<div class="contact_section_text">
							<p>${aboutInfor.getAdmin_infor()}
							</p>
						</div>
						<div class="contact_section_info">
							<ul>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div><div class="d-flex flex-column align-items-center justify-content-center"><img src="images/mainimages/icon_1.png" alt=""></div></div>
									<div><font style="font-size: 14px">${aboutInfor.getAdmin_address()}</font></div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div><div class="d-flex flex-column align-items-center justify-content-center"><img src="images/mainimages/icon_2.png" alt=""></div></div>
									<div>${aboutInfor.getAdmin_tel()}</div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div><div class="d-flex flex-column align-items-center justify-content-center"><img src="images/mainimages/icon_3.png" alt=""></div></div>
									<div>${aboutInfor.getAdmin_email()}</div>
								</li>
							</ul>
						</div>
					</div>
				</div>

				<!-- Contact Image -->
				<div class="col-lg-7 contact_section_col">
					<div class="contact_image"><img src="images/mainimages/${aboutInfor.getAdmin_image()}" style="height:480px;border-radius: 10px 0 10px 0" alt="관리자 페이지">${aboutInfor.getAdmin_image()}</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Contact -->

	<div class="contact">
		<div class="container">
			<div class="row">
				
				<!-- Contact Map -->
				<div class="col-xl-6">
					<div class="contact_map_container">
						
						<!-- Contact Map -->
						<div class="contact_map">

							<!-- Google Map -->
							<div class="map">
								<div id="google_map" class="google_map">
									<div class="map_container">
										<div id="map"></div>
									</div>
								</div>
							</div>
							</div>

						<!-- Contact Info Box -->
						<!-- 구글 지도안의 흰색 정보 네모 박스 -->
						<div class="contact_info_box d-flex flex-column align-items-center justify-content-center" style="width: 240px;height: 260px">
							<ul class="contact_info_list">
								<li class="d-flex flex-row align-items-start justify-content-start">
									<div><div class="contact_info_icon d-flex flex-column align-items-center justify-content-center"><img src="images/placeholder.png" alt=""></div></div>
									<div class="contact_info_text">The 7 Class Team</div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div><div class="contact_info_icon d-flex flex-column align-items-center justify-content-center"><img src="images/smartphone.png" alt=""></div></div>
									<div class="contact_info_text">${aboutInfor.getAdmin_tel()}</div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div><div class="contact_info_icon d-flex flex-column align-items-center justify-content-center"><img src="images/mail.png" alt=""></div></div>
									<div class="contact_info_text">${aboutInfor.getAdmin_email()}</div>
								</li>
							</ul>
						</div>

					</div>
				</div>
				
				<!-- Contact Form -->
				<div class="col-xl-6 contact_col">
					<div class="contact_form_container">
						<div class="section_title_container_2">
							<div class="section_title"><h1>Contact for us</h1></div>
							<div class="section_text">물어볼것이 있다구요? 언제든 저희에게 연락을 주세요.<br>상세하게 답변드립니다.</div>
						</div>
						
						<!-- 문의 메일 보내기 기능 -->
						<form action="./SendMail.do" class="contact_form" id="contact_form">
							<div class="row contact_row">
								<div class="col-md-6"><input type="text" class="contact_input" name="user_name" placeholder="Name" required="required"></div>
								<div class="col-md-6"><input type="email" class="contact_input" name="user_email" placeholder="E-mail" required="required"></div>
							</div>
							<div><textarea class="contact_input contact_textarea" name="user_comment" placeholder="Message" required="required"></textarea></div>
							<input class="buttonT" type="submit" value="send message">
						</form>
					</div>
				</div>
			</div>
		</div>


</div>
	</c:forEach>
	</c:when>
	</c:choose>
<jsp:include page="./inc/Footer.jsp" flush="false"/>

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
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyC7Mwb0KdtW4wJCYdVG_rh5_qqvYLjK3Fg"></script>
<script src="js/about.js"></script>
</body>
</html>