<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controller.LeavePeopleDAO"%>
<%
request.setCharacterEncoding("UTF-8");

String NAME = null;
String NUM = null;

if (session.getAttribute("LEAVE_NAME") != null){
		NAME = session.getAttribute("LEAVE_NAME").toString();
		NUM = session.getAttribute("LEAVE_NUM").toString();	
}
%>
<!DOCTYPE>
<html>
<title>출국 예정자의 마이페이지입니다.</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" type="image/png" href="/project_mvc22/sign_stay/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/fonts/iconic/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/css/util.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_stay/css/main.css">
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
      <span><%=NAME %>님의 마이페이지</span><br>
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>MyPage</h5>
  </div>
  <div class="w3-bar-block">
<a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  등록된 코스 보기</a>
    <a href="/project_mvc22/LeaveMypageCorrect.jsp" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>  회원 정보 수정</a>
    <a href="javascript:deleteCheck();" class="w3-bar-item w3-button w3-padding" ><i class="fa fa-history fa-fw"></i>  회원 탈퇴</a>
<!--     <a href="/project_mvc22/view/deleteAccount;" class="w3-bar-item w3-button w3-padding" ><i class="fa fa-history fa-fw"></i>  회원 탈퇴</a> -->
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
  </header>

 <body style="background-color: #999999;">
	
	<div class="limiter">
		<div class="container-login100">
			<div class="login100-more" style="background-image: url('/project_mvc22/create_acc/images/leavepeople.jpg');"></div>

			<div class="wrap-login100 p-l-50 p-r-50 p-t-72 p-b-50">
				<form class="login100-form validate-form" action="/project_mvc22/view/correctLeave" method="post">
				   
					<span class="login100-form-title p-b-59">
						To correct my information
					   </span>
					   
					<div class="wrap-input100 validate-input" data-validate = "Password must over 8 characters at least.">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="leave_pw" placeholder="please write your password...">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Please enter exact your name">
						<span class="label-input100">Stay People Name</span>
						<input class="input100" type="text" name="leave_nm" placeholder="Username...">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "please enter exact your phone number">
						<span class="label-input100">Phone Number</span>
						<input class="input100" type="text" name="leave_tel" placeholder="010-0000-0000">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "please enter exact your email address">
						<span class="label-input100">Email</span>
						<input class="input100" type="text" name="leave_email">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "please enter exact your photo image">
						<span class="label-input100">your face image</span>
						<input class="input100" type="file" name="leave_image">
						<span class="focus-input100"></span>
					</div>

					<div class="flex-m w-full p-b-33">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						</div>

						
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn" name ="member_reg_btn2" type="submit">
								Correction
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="/project_mvc22/sign_up/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="/project_mvc22/sign_up/vendor/animsition/js/animsition.min.js"></script>
	<script src="/project_mvc22/sign_up/vendor/bootstrap/js/popper.js"></script>
	<script src="/project_mvc22/sign_up/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="/project_mvc22/sign_up/vendor/select2/select2.min.js"></script>
	<script src="/project_mvc22/sign_up/vendor/daterangepicker/moment.min.js"></script>
	<script src="/project_mvc22/sign_up/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="/project_mvc22/sign_up/vendor/countdowntime/countdowntime.js"></script>
	<script src="/project_mvc22/sign_up/js/main.js"></script>

</body>	
 <!-- Footer -->
  <jsp:include page="/footer.jsp" flush="false"/>

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
