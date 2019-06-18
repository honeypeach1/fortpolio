<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="controller.LeavePeopleDAO"%>
<%@page import="model.LeavePeople"%>>
<%
	request.setCharacterEncoding("UTF-8");

	String NAME = null;
	String NUM = null;

	if (session.getAttribute("LEAVE_NAME") != null) {
		NAME = session.getAttribute("LEAVE_NAME").toString();
		NUM = session.getAttribute("LEAVE_NUM").toString();
	}

	LeavePeople leavepeople = null;
	if (request.getAttribute("leaveinfor") != null) {
		leavepeople = (LeavePeople) request.getAttribute("leaveinfor");
	}
%>
<!DOCTYPE>
<html>
<title>출국 예정자의 마이페이지입니다.</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/project_mvc22/Leave.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png"
	href="/project_mvc22/sign_stay/images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/css/util.css">
<link rel="stylesheet" type="text/css"
	href="/project_mvc22/sign_stay/css/main.css">
</head>
<body class="w3-light-grey">

	<!-- Top container -->

	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 3; height:43px;">
		<jsp:include page="/head.jsp" flush="false" />
	</div>

	<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-animate-left" style="z-index:3;width:300px;border:5px" id="mySidebar"><br>
  <div class="w3-container w3-row">

			<div class="w3-col s8 w3-bar">
				<span><%=NAME%>님의 마이페이지</span><br>
			</div>
		</div>
		<hr>
		<div class="w3-container">
			<h5>MyPage</h5>
		</div>
		<div class="w3-bar-block">
			<a href="#"
				class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i> 등록된 코스 보기</a> <a
				href="/project_mvc22/LeaveMypageCorrect.jsp"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-users fa-fw"></i>  회원 정보 수정</a> <a
				href="javascript:deleteCheck();"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-history fa-fw"></i>  회원 탈퇴</a>
		</div>
	</nav>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">

		<!-- Header -->
		<header class="w3-container" style="padding-top: 22px"> </header>
		<div class="w3-half4 w3-black w3-container w3-center w3-round"
			style="height: 800px; width: 900px;">
			<div
				style="width: 950px; height: 750px; padding: 1px; background-color: gold;"
				class="w3-padding-64 w3-padding-large w3-center w3-round w3-white"
				style="height:1100px; width:100%;">

				<body style="background-color: #999999;">
					<script
						src="/project_mvc22/sign_up/vendor/jquery/jquery-3.2.1.min.js"></script>
					<script
						src="/project_mvc22/sign_up/vendor/animsition/js/animsition.min.js"></script>
					<script src="/project_mvc22/sign_up/vendor/bootstrap/js/popper.js"></script>
					<script
						src="/project_mvc22/sign_up/vendor/bootstrap/js/bootstrap.min.js"></script>
					<script src="/project_mvc22/sign_up/vendor/select2/select2.min.js"></script>
					<script
						src="/project_mvc22/sign_up/vendor/daterangepicker/moment.min.js"></script>
					<script
						src="/project_mvc22/sign_up/vendor/daterangepicker/daterangepicker.js"></script>
					<script
						src="/project_mvc22/sign_up/vendor/countdowntime/countdowntime.js"></script>
					<script src="/project_mvc22/sign_up/js/main.js"></script>
					<div class="container">
						<div class="row" style="width: 1850px;" align="center">
							<!-- Column -->
							<div class="col-lg-4 col-xlg-3 col-md-5" align="center">
								<div class="card contact">
									<div class>
										<div>
											<div class="img-wrap rounded-circle" align="center">
												<img style="width: 350px; height: 350px; align: center;"
													src="/project_mvc22/LeavePeopleImage/<%=leavepeople.getLeave_image()%>"
													alt="profile-img">
											</div>
										</div>
									</div>
									<h4>My Name is...</h4>
									<p style="font-size: 30px"><%=leavepeople.getLeave_name()%></p>
									<hr>
									<h4>Phone Number</h4>
									<p style="font-size: 30px"><%=leavepeople.getLeave_tel()%></p>
									<hr>

									<div class="infor">
										<h4>Email Address</h4>
										<p style="font-size: 30px"><%=leavepeople.getLeave_email()%></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</body>

			</div>
		</div>
		<!-- Footer -->
		<jsp:include page="/footer.jsp" flush="false" />

		<!-- End page content -->
	</div>

	<script>
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

// Close the sidebar with the close button
function w3_close() {
    mySidebar.style.display = "none";
    overlayBg.style.display = "none";
}

function deleteCheck(){ 
	if (confirm("정말로 회원 탈퇴를 진행하시겠습니까? 한번 탈퇴가 진행이 되면 다시 복구 하실 수 없습니다.") == true){    //확인
	    alert("성공적으로 회원 탈퇴가 되었습니다.")
/* 		window.replace(location.href='/project_mvc22/sessionDestroy.jsp'); */
 		window.replace(location.href='/project_mvc22/view/deleteAccount');
	}else{   //취소
		alert("취소되었습니다.")
	    return;
	}
	} 

</script>

</body>
</html>