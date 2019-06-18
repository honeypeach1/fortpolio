<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Stay people sign up page</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="/project_mvc22/create_acc/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/fonts/iconic/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/css/util.css">
	<link rel="stylesheet" type="text/css" href="/project_mvc22/create_acc/css/main.css">
</head>
<body style="background-color: #999999;">
	
	<div class="limiter">
		<div class="container-login100">
			<div class="login100-more" style="background-image: url('/project_mvc22/create_acc/images/stay_people.jpg');"></div>

			<div class="wrap-login100 p-l-50 p-r-50 p-t-72 p-b-50">
				<form action="/project_mvc22/view/CreateStay" class="login100-form validate-form" method = "post">
					   
					<span class="login100-form-title p-b-59">
						Sign Up For Overseas Stay People
					   </span>
					   

					<div class="wrap-input100 validate-input" data-validate="ID must over 8 characters at least.">
						<span class="label-input100">ID</span>
						<input class="input100" type="text" name="stay_id" placeholder="id is...">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Password must over 8 characters at least.">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="stay_pw" placeholder="please write your password...">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "please enter exact your Email">
						<span class="label-input100">Email</span>
						<input class="input100" type="text" name="stay_email" placeholder="sir@naver.com">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "please enter exact your phone number">
						<span class="label-input100">Phone Number</span>
						<input class="input100" type="text" name="stay_tel" placeholder="010-0000-0000">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate="Please enter exact your name">
						<span class="label-input100">Stay People Name</span>
						<input class="input100" type="text" name="stay_nm" placeholder="Username...">
						<span class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" name ="member_reg_btn1" class="login100-form-btn">
								Sign Up
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="/project_mvc22/create_acc/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="/project_mvc22/create_acc/vendor/animsition/js/animsition.min.js"></script>
	<script src="/project_mvc22/create_acc/vendor/bootstrap/js/popper.js"></script>
	<script src="/project_mvc22/create_acc/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="/project_mvc22/create_acc/vendor/select2/select2.min.js"></script>
	<script src="/project_mvc22/create_acc/vendor/daterangepicker/moment.min.js"></script>
	<script src="/project_mvc22/create_acc/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="/project_mvc22/create_acc/vendor/countdowntime/countdowntime.js"></script>
	<script src="/project_mvc22/create_acc/js/main.js"></script>
	</body>
</html>