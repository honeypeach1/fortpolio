<%@page import="java.util.ArrayList"%>
<%@page import="project.list.db.ListBean"%>
<%@page import="project.user.db.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="styles/w3.css">
<!-- <link rel="stylesheet" type="text/css" href="styles/font-awesome.min.css"> -->
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
<body>

<%
	String user_num = session.getAttribute("user_num").toString();

	UserBean ub = (UserBean)request.getAttribute("ub");
	ArrayList lb = (ArrayList) request.getAttribute("lb");
	ArrayList lb2 = (ArrayList) request.getAttribute("lb2");
	
	/* UserBean ub = null;
	if (request.getAttribute("ub") != null) {
		ub = (UserBean) request.getAttribute("ub");
	} else {
		System.out.println("UserBean 값을 안받아옴");
	}
	
	ListBean lb = null;
	ListBean lb2 = null;
	if (request.getAttribute("lb") != null) {
		lb = (ListBean) request.getAttribute("lb");
		lb2 = (ListBean) request.getAttribute("lb2");
	} else {
		System.out.println("ListBean 값을 안받아옴");
	} */
	/* System.out.println("mypage 현재 유저 번호 : "+ub.getUser_no());
	System.out.println("mypage 현재 유저 번호(새션) : @@@@@@@@"+user_num);
	System.out.println("mypage 받아온 예약자 이름 : "+lb.getUser_name()); */
%>
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
							<div class="home_title"><h1><font style="font-size: 85px">My Page</font></h1></div>
							<div class="home_text"><font style="font-size: 25px">What do you want to change your info?</font></div>
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

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> -->
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<body class="w3-light-grey">

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
			<div class="w3-display-container">
				<%
				if(ub.getUser_image() != null){
				%>	
				<img src="user_images/<%=ub.getUser_image()%>" style="width:300px;" alt="Avatar">
				<%	
				} else {				
				%>
				<img src="./images/mainimages/blog_3.jpg" style="width:100%" alt="Avatar">
				<% } %>
				<div class="w3-display-bottomleft w3-container w3-text-black">
            		<h2 class="text-shadow"><%=ub.getUser_name() %></h2>
        		</div>
        	</div>
 	       <div class="w3-container">
          	<!-- <i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i> -->
          	
<!--           	마이페이지에 들어가야할 기능 -->
          	
<!--           	유저 -->
<!--           	첫번째 : 열람가능한 나의 정보를 가져온다. -->
<!--           	두번째 : 나의 정보를 변경한다. -->
<!--           	세번째 : 회원탈퇴를 한다. -->
          	
<!--           	예약 -->
<!--           	첫번째 : 예약 리스트를 가져온다. -->
<!--           	두번째 : 예약 취소를 한다. -->
          	
<!--           	찜 -->
<!--           	첫번쨰 : 찜한 목록을 가져온다. -->
<!--           	두번째 : 찜한 목록중에 취소를 할수 있게한다. -->
          	 
          
<!--           <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>Designer</p> -->
<!--           <p><i class="fa fa-key fa-fw w3-margin-right w3-large w3-text-teal"></i>●●●●●●●●</p> -->
<!--           <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>ex@mail.com</p> -->
<!--           <p><i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i>1224435534</p> -->
          
          <br>
          
          <input type="button" value="Edit" class="button2 editbtn" title="정보를 수정하려면 수정 버튼을 꼭 눌러야 해요.">
          
          
          <!-- EDIT 버튼 클릭 시 내용 수정기능 활성화 -->
          <script>
          	$(document).ready(function(){
				$('.iptcontent').attr('disabled', true);
				$('.editbtn').click(function(){
					$('.iptcontent').attr('disabled', false);
				});
			});
		  </script>
		  
		  <!-- 회원탈퇴 팝업 처리 -->
		  <script type="text/javascript">
		  	function wdopen(){
		  		var fid = document.udform.id.value;
		  		window.open("./MyinfoDelete.do?userid="+fid, "Withdraw", "width=680, height=250, resizable=no");
		  	}
		  </script>
		  
		  <!-- 비밀번호/비밀번호 확인 일치여부 체크 -->
		  <script type="text/javascript">
		  	function submitbtn(){
		  		if(document.udform.pass.value == ""){
		  			alert("비밀번호를 입력해야 수정이 완료됩니다.");
		  			document.udform.pass.focus();
		  			return false;
		  		}
		  		if(document.udform.pass.value != document.udform.passConfirm.value){
		  			alert("비밀번호가 일치하지 않습니다");
		  			document.udform.pass.focus();
		  			return false;
		  		}
		  	}
		  </script>
		  <%
		  	System.out.println("Mypage 현재 세션 값 : "+session);
		  
		  %>
          <form action="./MyinfoUpdateAction.do" method="post" id="updateinfo" name="udform" onsubmit="return submitbtn()">
        	<!-- 유저 번호 -->
        	<input type="hidden" id="user_no" name="USER_NO" value="<%=ub.getUser_no() %>">
        	<!-- 아이디 -->
         	<p><i class="fa fa-id-card fa-fw w3-margin-right w3-large w3-text-teal" title="아이디"></i>
          	<input type="text" id="id" name="USER_ID" value="<%=ub.getUser_id() %>" readonly><br></p>
         	<!-- 사용자 이름 -->
         	<p><i class="fa fa-info-circle fa-fw w3-margin-right w3-large w3-text-teal" title="이름"></i>
          	<input class="iptcontent" type="text" id="name" name="USER_NAME" value="<%=ub.getUser_name() %>"><br></p>
          	<!-- 비밀번호 -->
          	<p><i class="fa fa-key fa-fw w3-margin-right w3-large w3-text-teal" title="비밀번호"></i>
          	<input class="iptcontent" type="password" id="pass" name="USER_PW" placeholder="비밀번호" title="비밀번호를 입력해야만 정보 수정이 완료돼요. 기존의 비밀번호를 입력해도 괜찮아요."><br>
          	<i class="fa fa-check-circle fa-fw w3-margin-right w3-large w3-text-teal" title="비밀번호 확인"></i>
          	<input class="iptcontent" type="password" id="passConfirm" name="USER_PW_CONF" placeholder="비밀번호 확인" title="한 번 더 비밀번호를 입력해주세요."><br></p>
          	<!-- 이메일 -->
          	<p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal" title="이메일"></i>
          	<input class="iptcontent" type="email" id="email" name="USER_EMAIL" value="<%=ub.getUser_email() %>"><br></p>
          	<!-- 폰번호 -->
          	<p><i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal" title="전화번호"></i>
          	<input class="iptcontent" type="text" id="phone" name="USER_PHONE" value="<%=ub.getUser_phone() %>"><br></p>
          	          	
          	<input type="submit" value="Update" class="iptcontent button2" title="회원 정보 수정하기">
          	<input type="button" value="Withdraw" class="iptcontent button2" title="회원 탈퇴하기" onclick="wdopen()">
          </form>        
          
          <!-- 페이지 내 팝업창 -->
          <a class="expand">
			<div><font size="5">+</font></div>
				
					<h2>사진 바꾸기</h2>/ 클릭 시 변경 가능			
		</a>
		<div class="detail">
			<form action="./ChangePfImage.do" method="post" enctype="multipart/form-data">
				<p><i class="fa fa-user fa-fw w3-margin-right w3-large w3-text-teal" title="프로필 사진 변경하기"></i>
				<input type="file" name="user_image" id="user_image" title="여기서 사진을 변경할 수 있어요"></p>
				<input type="submit" value="Change Profile Img" class="button2">
			</form>
		</div>
          
          <!-- 페이지 내 팝업창 -->
          
          </div>
          <hr>
          

<!--           <p class="w3-large"><b><i class="fa fa-asterisk fa-fw w3-margin-right w3-text-teal"></i>Skills</b></p> -->
<!--           <p>Adobe Photoshop</p> -->
<!--           <div class="w3-light-grey w3-round-xlarge w3-small" style="border : 1px solid red;"> -->
<!--             <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:90%">90%</div> -->
<!--           </div> -->
<!--           <p>Photography</p> -->
<!--           <div class="w3-light-grey w3-round-xlarge w3-small"> -->
<!--             <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:80%"> -->
<!--               <div class="w3-center w3-text-white">80%</div> -->
<!--             </div> -->
<!--           </div> -->
<!--           <p>Illustrator</p> -->
<!--           <div class="w3-light-grey w3-round-xlarge w3-small"> -->
<!--             <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:75%">75%</div> -->
<!--           </div> -->
<!--           <p>Media</p> -->
<!--           <div class="w3-light-grey w3-round-xlarge w3-small"> -->
<!--             <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:50%">50%</div> -->
<!--           </div> -->
<!--           <br> -->

<!--           <p class="w3-large w3-text-theme"><b><i class="fa fa-globe fa-fw w3-margin-right w3-text-teal"></i>Languages</b></p> -->
<!--           <p>English</p> -->
<!--           <div class="w3-light-grey w3-round-xlarge"> -->
<!--             <div class="w3-round-xlarge w3-teal" style="height:24px;width:100%"></div> -->
<!--           </div> -->
<!--           <p>Spanish</p> -->
<!--           <div class="w3-light-grey w3-round-xlarge"> -->
<!--             <div class="w3-round-xlarge w3-teal" style="height:24px;width:55%"></div> -->
<!--           </div> -->
<!--           <p>German</p> -->
<!--           <div class="w3-light-grey w3-round-xlarge"> -->
<!--             <div class="w3-round-xlarge w3-teal" style="height:24px;width:25%"></div> -->
<!--           </div> -->
<!--           <br> -->
        </div>
      

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
    
<!--       <div class="w3-container w3-card w3-white w3-margin-bottom"> -->
<!--         <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Book Hotel List</h2> -->
<!--         <div class="w3-container"> -->
<!--           <h5 class="w3-opacity"><b>Front End Developer / w3schools.com</b></h5> -->
<!--           <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>Jan 2015 - <span class="w3-tag w3-teal w3-round">Current</span></h6> -->
<!--           <p>Lorem ipsum dolor sit amet. Praesentium magnam consectetur vel in deserunt aspernatur est reprehenderit sunt hic. Nulla tempora soluta ea et odio, unde doloremque repellendus iure, iste.</p> -->
<!--           <hr> -->
<!--         </div> -->
<!--         <div class="w3-container"> -->
<!--           <h5 class="w3-opacity"><b>Web Developer / something.com</b></h5> -->
<!--           <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>Mar 2012 - Dec 2014</h6> -->
<!--           <p>Consectetur adipisicing elit. Praesentium magnam consectetur vel in deserunt aspernatur est reprehenderit sunt hic. Nulla tempora soluta ea et odio, unde doloremque repellendus iure, iste.</p> -->
<!--           <hr> -->
<!--         </div> -->
<!--         <div class="w3-container"> -->
<!--           <h5 class="w3-opacity"><b>Graphic Designer / designsomething.com</b></h5> -->
<!--           <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>Jun 2010 - Mar 2012</h6> -->
<!--           <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. </p><br> -->
<!--         </div> -->
<!--       </div> -->

<!--       <div class="w3-container w3-card w3-white w3-margin-bottom"> -->
<!--         <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Preferred List</h2> -->
<!--         <div class="w3-container"> -->
<!--           <h5 class="w3-opacity"><b>W3Schools.com</b></h5> -->
<!--           <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>Forever</h6> -->
<!--           <p>Web Development! All I need to know in one place</p> -->
<!--           <hr> -->
<!--         </div> -->
<!--         <div class="w3-container"> -->
<!--           <h5 class="w3-opacity"><b>London Business School</b></h5> -->
<!--           <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>2013 - 2015</h6> -->
<!--           <p>Master Degree</p> -->
<!--           <hr> -->
<!--         </div> -->
<!--         <div class="w3-container"> -->
<!--           <h5 class="w3-opacity"><b>School of Coding</b></h5> -->
<!--           <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>2010 - 2013</h6> -->
<!--           <p>Bachelor Degree</p><br> -->
<!--         </div> -->
<!--       </div> -->
      
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-18"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Book Hotel List</h2>
        <i class="fa fa-arrow-circle-right fa-fw w3-margin-right w3-large w3-text-teal"></i> 호텔 이름을 클릭하면 상세 정보가 나타납니다. 상세 정보의 내용을 클릭하면 해당 페이지로 이동합니다.
        <div class="w3-container">
        
        	
        	<!-- https://codepen.io/anon/pen/EJeKLQ 에서 추가한 부분 -->
			<div id="item-list">
				<ul>
					
					<!--
						확장하기 전 정보 : 호텔 이름(h1), 호텔 내 방번호, 결제 금액, 예약인원 수, 체크인-체크아웃 날짜
						확장 후 정보 : 
					 -->
					<%for(int i =0; i<lb.size(); i++){ %>
						<%
						ListBean lbean1 = (ListBean)lb.get(i);
						
						%>
						 
					
					<li>
					<form id="booklist" action="./MyinfoBookCancel.do" method="post">
					<a class="expand">
						<div class="right-arrow">+</div>
						<div class="icon london"><img src="./images/hotelimages/<%=lbean1.getHotel_main_image() %>" style="width:80px; height: 80px;"></div>
						<h2><%=lbean1.getHotel_name() %></h2>
						<span>
						<i class="fa fa-calendar-o fa-fw w3-margin-right w3-large w3-text-teal" title="체크 인 / 체크 아웃"></i> <%=lbean1.getBooking_enter_date() %> ~ <%=lbean1.getBooking_leave_date() %>
						<i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal" title="예약 일자"></i> <%=lbean1.getBooking_date() %>
						<i class="fa fa-users fa-fw w3-margin-right w3-large w3-text-teal" title="총 예약 인원"></i> <%=lbean1.getBooking_total_people() %> 명 <br>						
						<i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal" title="호텔의 주소"></i><%=lbean1.getHotel_address() %><!-- 주소 -->
						<input type="hidden" id="booking_no" name="booking_no" value="<%=lbean1.getBooking_no() %>"><!-- 예약 테이블 DB PK / 취소할 항목만 골라서 취소하기 위함 -->
						<%
							System.out.println("Mypage.jsp 예약 번호 : "+lbean1.getBooking_no());
						%>
						
						</span>
					</a>

					<div class="detail">
						<div>
							<span>
							<%
							if(lbean1.getBooking_state() == 1){
							%>
							<i class="fa fa-credit-card-alt fa-fw w3-margin-right w3-large w3-text-teal"></i> 예약 상태 - 결제 대기 중
							<%	
							} else if(lbean1.getBooking_state() == 2){
							%>
							<i class="fa fa-credit-card-alt fa-fw w3-margin-right w3-large w3-text-teal"></i> 결제 완료됨
							<%
							} else if(lbean1.getBooking_state() == 3){
							%>
							<i class="fa fa-credit-card-alt fa-fw w3-margin-right w3-large w3-text-teal"></i> 예약 취소 및 환불됨
							<%
							} else if(lbean1.getBooking_state() == 4){
							%>
							<i class="fa fa-credit-card-alt fa-fw w3-margin-right w3-large w3-text-teal"></i> 숙박 완료
							<%
							} else if(lbean1.getBooking_state() == 5){
							%>
							<i class="fa fa-credit-card-alt fa-fw w3-margin-right w3-large w3-text-teal"></i> 리뷰 작성 완료
							<%
							}
							%>
							<br>
							<%
							if(lbean1.getHotel_instroduct() != null){
							%>
							<i class="fa fa-info fa-fw w3-margin-right w3-large w3-text-teal"></i>
							<%=lbean1.getHotel_instroduct() %> <!-- 호텔 정보 --><br>
							<%
							}
							%>
							<i class="fa fa-bed fa-fw w3-margin-right w3-large w3-text-teal"></i><%=lbean1.getRoom_name() %><!-- 방 이름 --> &nbsp;&nbsp;
							
							<!-- 신청한 부가서비스 -->
							<br>
							<i class="fa fa-money fa-fw w3-margin-right w3-large w3-text-teal"></i>KRW(￦) <%=lbean1.getRoom_price() %> 원<!-- 총 가격 --><br>
							</span>
						</div>
					<br /> 
					<input type="button" class="button2" value="Show Detail" 
					onclick="location.href='./RoomDetails.rd?hotel_no=<%=lbean1.getHotel_no() %>&check_in=<%=lbean1.getBooking_enter_date() %>&check_out=<%=lbean1.getBooking_leave_date() %>&people_num=<%=lbean1.getBooking_total_people() %>';"><!-- 호텔 상세 페이지 바로 이동(URL) -->
					<%
					if(lbean1.getBooking_state() == 1 || lbean1.getBooking_state() == 2){
					%>
					<input type="submit" class="button2" value="Cancel Reservation">
					<% } %> <!-- 예약 상태 혹은 결제만 진행된 상태(아직 미숙박 상태)일 때만 버튼 활성화 -->
					<%
						System.out.println("호텔 번호 테스트 : "+lbean1.getHotel_no());
					%>
					</div>
					</form>
					</li>
					<%} %>
					
					
					<!-- <li><a class="expand">
						<div class="right-arrow">+</div>
						<div class="icon newyork"></div>
							<h2>Dallas Hotel</h2> 
							<span>
							<i class="fa fa-calendar-o fa-fw w3-margin-right w3-large w3-text-teal"></i> 2019-04-30 ~ 2019-05-02 &nbsp;
							<i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i> 2019-04-02 
							<i class="fa fa-users fa-fw w3-margin-right w3-large w3-text-teal"></i> 3명 %nbsp;<br>						
							<i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i>
							부산광역시 부산진구 동천로 109 삼한골든게이트
							</span>
						</a>

						<div class="detail">
							<div>
								<i class="fa fa-info fa-fw w3-margin-right w3-large w3-text-teal"></i>
									수영장 기본 포함, 호텔 투숙객 헬스 제공, 킹갓엠퍼러띵호텔! 호텔 정보<br>
								<i class="fa fa-plus-square fa-fw w3-margin-right w3-large w3-text-teal"></i>
								더블베드 / 조식 불포함 / 무료 Wi-fi / 창문 포함 신청한 부가서비스<br>
								<i class="fa fa-money fa-fw w3-margin-right w3-large w3-text-teal"></i>￦ 297,000총 가격<br>
								<i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>주소
								</span>
							</div>
						<br /> 
						<input type="button" class="button2" value="Show Detail" onclick="">
						<input type="button" class="button2" value="Cancel Reservation">
						</div></li> -->
					</ul>
				</div>

		<!-- 예약한 호텔 이름 클릭 시 정보가 확장되는 것을 구현한 JQuery -->				
		<script type="text/javascript">
				$(function() {
		  			$(".expand").on( "click", function() {
		    			$(this).next().slideToggle(300);
		    			$expand = $(this).find(">:first-child");
		    	
					    if($expand.text() == "+") {
		      				$expand.text("-");
		    			} else {
		      				$expand.text("+");
		    			}
		  			});
				});
		</script>
		<!-- https://codepen.io/anon/pen/EJeKLQ 에서 추가한 부분 끝 -->
				
			</div>
			
        
      </div>
		
	
	
	
		<!-- 즐겨찾기 호텔 -->
<!--       <div class="w3-container w3-card w3-white w3-margin-bottom"> -->
<!--         <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-gratipay fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Favorite List</h2> -->
<!--         <div class="w3-container"> -->
<!--           <div id="item-list"> -->
<!-- 				<ul> -->
<!-- 					<li><a class="expand"> -->
<!-- 						<div class="right-arrow">+</div> -->
<!-- 						<div class="icon london"></div> -->
<!-- 						<h2>London</h2> -->
<!-- 						<span>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy</span> -->
<!-- 					</a> -->

<!-- 					<div class="detail"> -->
<!-- 						<div> -->
<!-- 							<span>Duis autem vel eum iriure dolor in hendrerit -->
<!-- 							in vulputate velit esse molestie consequat, vel illum -->
<!-- 							dolore eu feugiat nulla facilisis at vero eros et accumsan -->
<!-- 							et iusto odio dignissim qui blandit praesent luptatum -->
<!-- 							zzril delenit augue duis dolore te feugait nulla facilisi.</span> -->
<!-- 						</div> -->
<!-- 					<br /> <input type="button" class="button2" value="Cancel"> -->
<!-- 					</div></li> -->
<!-- 					<li><a class="expand"> -->
<!-- 						<div class="right-arrow">+</div> -->
<!-- 						<div class="icon newyork"></div> -->
<!-- 							<h2>New York</h2> <span>Duis autem vel eum iriure -->
<!-- 							dolor in hendrerit in vulputate velit esse molestie -->
<!-- 							consequat</span> -->
<!-- 						</a> -->

<!-- 						<div class="detail"> -->
<!-- 							<div> -->
<!-- 								<span>Duis autem vel eum iriure dolor in hendrerit -->
<!-- 								in vulputate velit esse molestie consequat, vel illum -->
<!-- 								dolore eu feugiat nulla facilisis at vero eros et accumsan -->
<!-- 								et iusto odio dignissim qui blandit praesent luptatum -->
<!-- 								zzril delenit augue duis dolore te feugait nulla facilisi.</span> -->
<!-- 							</div> -->
<!-- 						<br /> <input type="button" class="button2" value="Cancel"> -->
<!-- 						</div></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!--       		</div> -->
<!--       </div> -->
      
<!-- https://codepen.io/anon/pen/EJeKLQ 에서 추가한 부분 끝 -->

    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
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