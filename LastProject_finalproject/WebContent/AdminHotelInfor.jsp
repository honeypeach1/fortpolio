<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.io.PrintWriter" %>
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
<title>The Third Milestone</title>
<%
	String admin = session.getAttribute("user_num").toString();
	System.out.println("세션 번호 : " + admin);	
	
	if(!admin.equals("1")){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();

		pw.print("<script>");
		pw.println("alert('허용 되지 않은 접근입니다. 메인 페이지로 돌아갑니다.');");
		pw.println("location.href='./goToMainPage.li'");
		pw.println("</script>");
	}
%>
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
    <link rel="stylesheet" href="css/AdminHotel.css"></link>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.css'>
<link rel='stylesheet' href='https://cdn.datatables.net/responsive/1.0.4/css/dataTables.responsive.css'>
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
    width: 60%;
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
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/mainimages/adminpage2.jpg" data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_title"><h1><font style="font-size: 85px">Hotels Information</font></h1></div>
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
	
	
	<!-- 호텔 리스트 목록(수정, 삭제 기능) -->

	<div class="intro" style="margin-bottom: -100px">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="section_title_container text-center">
						<div class="section_title"><h1>A list of hotels for Admin</h1></div>
					</div>
				</div>
			</div>
		</div>
	</div>
			
			
	<!-- 모든 호텔 리스트 -->

		<div class="rooms">
			<div class="container">
				<div class="row rooms_row">
					<table id="list" class="responstable">

						<tr style="padding: 10px">
							<th id="listTh">NO</th>
							<th id="listTh">Image</th>
							<th id="listTh">Hotel name</th>
							<th id="listTh">Tel Number</th>
							<th id="listTh">Star</th>
							<th id="listTh" style="text-align:right">Remove Infor</th>
						</tr>
					
					<c:choose>
					<c:when test="${!empty getAdHotel}">
					<c:forEach items="${getAdHotel}" var="getHotel"> 
						<tr>
							<td>${getHotel.getRownum()}</td>
							<td align="center">
							<img src="images/hotelimages/${getHotel.getHotel_main_image()}"style="width:80px; height:80px; border-radius: 50%">
							</td>
							<td>${getHotel.getHotel_name()}</td>
							<td>${getHotel.getHotel_tel()}</td>
							
							<c:if test="${getHotel.getHotel_class() eq 1}">
							<td><img src="images/starimages/star_image.png" width="20px" height="20px"></td>
							</c:if>
							
							<c:if test="${getHotel.getHotel_class() eq 2}">
							<td><img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px"></td>
							</c:if>
							
							<c:if test="${getHotel.getHotel_class() eq 3}">
							<td><img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px"></td>
							</c:if>
							
							<c:if test="${getHotel.getHotel_class() eq 4}">
							<td><img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px"></td>
							</c:if>
							
							<c:if test="${getHotel.getHotel_class() eq 5}">
							<td><img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px">
							<img src="images/starimages/star_image.png" width="20px" height="20px"></td>
							</c:if>
							
							
							<td align="right"><form action="./adHotelInforRemove.ad" method="post"><input type="hidden" name="hotel_no" value="${getHotel.getHotel_no()}"><input type="submit" value="Remove" class="buttonT"></form></td>
						</tr>
						</c:forEach>
						</c:when>
						</c:choose>
					</table>

				</div>
			</div>
		</div>
	</div>

</body>
<jsp:include page="./inc/Footer.jsp" flush="false"/>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js'></script>
<script src='https://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.js'></script>
<script src='https://cdn.datatables.net/responsive/1.0.4/js/dataTables.responsive.js'></script>
<script src="js/index.js"></script>


<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap-4.1.2/popper.js"></script>
<script src="styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="plugins/progressbar/progressbar.min.js"></script>
<script src="plugins/colorbox/jquery.colorbox-min.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
</html>