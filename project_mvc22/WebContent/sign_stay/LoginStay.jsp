<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
	<title>해외 거주자 로그인 페이지</title>
	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="icon" type="image/png" href="/project_mvc22/sign_leave/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/css/util.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/sign_leave/css/main.css">

</head>
<body style="background-color: #666666;">
	
	<div class="limiter">
		<div class="container-login100">
		<div class="wrap-login100">
				<form action="/project_mvc22/view/LoginStay" class="login100-form validate-form" method="post">
					<span class="login100-form-title p-b-43">
						Login to Continue!
					</span>
					
					
					<div class="wrap-input100 validate-input" data-validate = "You must enter at least four characters.">
						<input class="input100" type="text" name="id">
						<span class="focus-input100"></span>
						<span class="label-input100">ID</span>
					</div>
					
					
					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="pw">
						<span class="focus-input100"></span>
						<span class="label-input100">Password</span>
					</div>

					<div class="flex-sb-m w-full p-t-3 p-b-32">
						<!-- <div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label>
						</div> -->

					</div>
						<div class="container-login100-form-btn">
						<button class="login100-form-btn" style="width: 450px;" name="leave_people" type="submit">
							Stay people sign up
						</button>
						</div>
					</form>
					<div class="login100-more" style="background-image: url('/project_mvc22/sign_stay/images/stay2.jpg');">
				</div>
			</div>
		</div>
	</div>
	
	<script src="/project_mvc22/sign_leave/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="/project_mvc22/sign_leave/vendor/animsition/js/animsition.min.js"></script>
	<script src="/project_mvc22/sign_leave/vendor/bootstrap/js/popper.js"></script>
	<script src="/project_mvc22/sign_leave/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="/project_mvc22/sign_leave/vendor/select2/select2.min.js"></script>
	<script src="/project_mvc22/sign_leave/vendor/daterangepicker/moment.min.js"></script>
	<script src="/project_mvc22/sign_leave/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="/project_mvc22/sign_leave/vendor/countdowntime/countdowntime.js"></script>
	<script src="/project_mvc22/sign_leave/js/main.js"></script>

</body>
</html>