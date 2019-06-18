<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Major - 5* Hotel template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link href="plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>
<%
	request.setCharacterEncoding("UTF-8");
	
	//초기값(비로그인시)
	String user_num = null;
	String user_name = null;
	
	
	//관리자(번호 1번)
	//유저(번호 2번부터~)
	
	//유저 (로그인시)
	if(session.getAttribute("user_num") != null){
		user_num = session.getAttribute("user_num").toString();
		user_name = session.getAttribute("user_name").toString();
	
	
		System.out.println("받아온 유저 넘버 값 " + user_num);
	}
	
%>
<body>
	<!-- 비로그인시 -->
	<%if(user_num == null){
	System.out.println("비로그인 헤더");
	%>
	<header class="header">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="header_content d-flex flex-row align-items-center justify-content-start">
						<div class="logo">
							<a href="./goToMainPage.li">
								<div>The Third Milestone</div>
								<div>Hotel Reservation Site</div>
							</a>
						</div>
						<nav class="main_nav">
							<ul class="d-flex flex-row align-items-center justify-content-start">
								<li class="active"><a href="./HotDeal.li">Hot Deal</a></li>
								<!-- <li><a href="about.html">new?</a></li> -->
								<li><a href="./goToHotel.li">All Hotels</a></li>
								<li><a href="./goToRecentlyView.li">Recently Viewed</a></li>
								<li><a href="./goToAbout.do">About us</a></li>
							</ul>
						</nav>
						<div class="header_extra d-flex flex-row align-items-center justify-content-start ml-auto">
							<div class="book_button trans_200"><a href="./goToLoginPage.do">Log In</a></div>
						</div>
						<div class="header_extra d-flex flex-row align-items-center justify-content-start ml-auto">
							<div class="book_button trans_200"><a href="./regHotel.jsp">Upload Hotel</a></div>
						</div>
						<div class="hamburger ml-auto"><i class="fa fa-bars" aria-hidden="true"></i></div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- 유저 로그인시 -->
	<%}else if(!(user_num.equals("1"))){
	System.out.println("유저 로그인 헤더");
	%>
	<header class="header">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="header_content d-flex flex-row align-items-center justify-content-start">
						<div class="logo">
							<a href="./goToMainPage.li">
								<div>The Third Milestone</div>
								<div>Hotel Reservation Site</div>
							</a>
						</div>
						<nav class="main_nav">
							<ul class="d-flex flex-row align-items-center justify-content-start">
								<li class="active"><a href="./HotDeal.li">Hot Deal</a></li>
								<li><a href="./goToHotel.li">All Hotels</a></li>
								<li><a href="./goToRecentlyView.li">Recently Viewed</a></li>
								<li><a href="./goToMyPage.do">My Page</a></li>
								<li><a href="./goToAbout.do">About us</a></li>
							</ul>
						</nav>
						<div class="header_extra d-flex flex-row align-items-center justify-content-start ml-auto">
							<div class="phone d-flex flex-row align-items-center justify-content-start"><span><%=user_name%>님</span></div>
							<div class="book_button trans_200"><a href="./UserLogout.do">Log Out</a></div>
						</div>
						<div class="hamburger ml-auto"><i class="fa fa-bars" aria-hidden="true"></i></div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- 관리자 로그인시 -->
	<%}else if(user_num.equals("1")){
	System.out.println("관리자 로그인 헤더");
	%>
		<header class="header">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="header_content d-flex flex-row align-items-center justify-content-start">
						<div class="logo">
							<a href="./goToMainPage.li">
								<div>The Third Milestone</div>
								<div>Hotel Reservation Site</div>
							</a>
						</div>
						<nav class="main_nav">
							<ul class="d-flex flex-row align-items-center justify-content-start">
								<li><a></a></li>
								<li><a></a></li>
								<li><a></a></li><li><a></a></li><li><a></a></li>
								<li><a href="./adAppHotelInfor.ad">Approval Hotel</a></li>
								<li><a href="./adHotelInfor.ad">All Hotel</a></li>
								<li><a href="./adminAbout.ad">About us</a></li><li><a></a></li>
							</ul>
						</nav>
						<div class="header_extra d-flex flex-row align-items-center justify-content-start ml-auto">
							<div class="phone d-flex flex-row align-items-center justify-content-start"><span><%=user_name%>님</span></div>
							<div class="book_button trans_200"><a href="./UserLogout.do">Log Out</a></div>
						</div>
						<div class="hamburger ml-auto"><i class="fa fa-bars" aria-hidden="true"></i></div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<%}%>
</body>
</html>