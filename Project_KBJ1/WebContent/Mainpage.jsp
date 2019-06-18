<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Milestone</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />

<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link rel="shortcut icon" href="favicon.ico">

<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="css/cs-select.css">
<link rel="stylesheet" href="css/cs-skin-border.css">
<link rel="stylesheet" href="css/style.css">
<%
	String NUM = (String)session.getAttribute("USER_NUM");
	
	if(NUM != null){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();

		pw.print("<script>");
		pw.println("alert('현재 로그인이 되어 있어 정보 페이지로 이동합니다.');");
		pw.println("location.href='/Project_KBJ1/view/InforPage'");
		pw.println("</script>");
	}
%>
<script src="js/modernizr-2.6.2.min.js"></script>
	<script type="text/javascript">
		/* 로그인 폼 관리 */
		function logfun(){
			
			if(document.fr.id.value == false){
				alert("아이디 사용유무를 체크하십시오!");
				return false;
			}else if(document.fr.pw.value == false){
				alert("비밀번호를 입력하세요!");
				return false;
			}else if(document.fr.nm.value == false){
				alert("이름을 공란으로 두실수 없습니다.");
				return false;
			}else if(document.fr.borndate.value == false){
				alert("생년월일을 입력하세요.")
				return false;
			}else if(document.fr.telephone.value == false){
				alert("전화번호를 입력하셔야 합니다.");
				return false;
			}else if(document.fr.nation[0].selected == true){
				alert("국가를 선택하세요!");
				return false;
			}else if(document.fr.images.value == false){
				alert("기본 이미지를 등록하셔야 합니다.");
				return false;
			}
		}	
	</script>
</head>
<body>
		<!-- header(login status bar) -->
		<header id="fh5co-header-section" class="sticky-banner">
		<div class="container">
		<div class="nav-header">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
		<h1 id="fh5co-logo"><a href="/Project_KBJ1/Mainpage.jsp" style="width:50%">MILESTONE</a></h1>
		<!-- START #fh5co-menu-wrap -->
		<nav id="fh5co-menu-wrap" role="navigation"  style="width:50%;">
			<form action="/Project_KBJ1/view/loginCheck" method="post">		
				ID : <input type="text" name="id" style="width:20%">
				PASSWORD : <input type="password" name="pw" style="width:20%"/>
				<input class="btn btn-primary" type="submit" value="Login!" style="width: 170px; margin-left:0px;">			
			</form>
		</nav>
		</div></div>
	</header>

			<!-- background -->

<div class="fh5co-hero" >
<div class="fh5co-overlay"  style="border-top:3px solid #F78536"></div>
	<div class="fh5co-cover" data-stellar-background-ratio="0.5" style="background-image: url(images/background.jpg);">
	<div class="desc">
	<div class="container">
	<div class="row">
	<div class="col-sm-5 col-md-5">
	<div class="tabulation animate-box" style="border-radius:3%; margin-top: -200px; width: 500px">

	<!-- 회원가입 창 상단 flights -->
	<ul class="nav nav-tabs" role="tablist" style="align:center;">
		<li role="presentation" class="active">
		<a href="#flights" aria-controls="flights" role="tab" data-toggle="tab" style="border-radius:10px 0 10px 0">Don't you have ID? let's get sign up right now!</a></li>
	</ul>
	<!-- 회원가입 창 --><br>
	<form action="/Project_KBJ1/view/SignUp" method="post" enctype="multipart/form-data" name="fr" onsubmit="return logfun()">
	<div class="tab-content" style="margin-top: -45px; width: 500px">
	<div role="tabpanel" class="tab-pane active" id="flights">
	<div class="row">
	<div class="col-xxs-12 col-xs-6 mt" style="height: 69.22px">
		<div class="input-field">
		<table><tr><td>
		<label for="id">ID : </label> <input type="text" style="width:145px" class="form-control" id="setId" name="id" placeholder="Click Right Button" readonly="readonly"/>
		</td><td>
		<input type="button" onclick="ckid();" style="margin-top: 30px; margin-left:7px; width: 50px; background-color: #F78536; border-radius: 2px" value="체크"/>	
		</td></tr></table>
		</div>
	</div>
	    <div class="col-xxs-12 col-xs-6 mt">
	    <div class="input-field">
		<label for="pw">Password</label> <input type="password" class="form-control" id="pw" name="pw" placeholder="between 4 and 20." />
		</div>
		</div>
	<div class="col-xxs-12 col-xs-6 mt alternate">											
		<div class="input-field">
		<label for="name">Name</label> <input type="text" class="form-control" id="nm" name="userName" placeholder="What's your name?" />
		</div>													
		</div>
	<div class="col-xxs-12 col-xs-6 mt alternate">
		<div class="input-field">
		<label for="telephone">Telephone</label> <input type="text" class="form-control" id="telephone" name="telephone"/>
		</div>
	</div>
	<div class="col-xxs-12 col-xs-6 mt alternate">
		<div class="input-field">
		<label for="email">Email</label> <input type="text" class="form-control" id="email" name="email"/>
		</div>
	</div>
	<div class="col-xxs-12 col-xs-6 mt alternate">
		<div class="input-field">
		<label for="borndate">Born date</label> <input type="text" class="form-control" id="date-start" placeholder="mm/dd/yyyy" name="borndate" readonly/>
		</div>
	</div>
	<div class="col-xxs-12 col-xs-6 mt alternate">
	<div class="input-field">
		<section>
		<label for="class">Nation</label>
			<select class="cs-select cs-skin-border" name="nation">
			<option value="null" disabled selected>Select Nation</option>
			<option value=82>KOREA</option>
			<option value=1>USA</option>
			<option value=44>U.K</option>
			<option value=86>CHINA</option>
			<option value=81>JAPAN</option>
			</select>
		</section>
		</div>
	</div>
	<div class="col-xxs-12 col-xs-6 mt alternate">
		<div class="input-field">
		<label for="image">image</label><input style="font-size: 2px" type="file" class="form-control" id="images" name="files"/>
		</div>
	</div>
	<div class="col-xxs-12 col-xs-12 mt">
		<section>
		<!-- <label for="class">Gender</label>
			<select class="cs-select cs-skin-border" name="gender">
			<option value="male" disabled selected>Male</option>
			<option value="female">Female</option>
			</select> -->
			<label for="class" style="border:2em;">gender</label><br>
			<div style="width:100%">
				<div class="col-xxs-12 col-xs-5 mt alternate">
				<label for="class" style="font-size: 22px">Male &nbsp;</label><input name="gender" style="font-size: 22px" type="radio" value="male"></div>
				<div class="col-xxs-12 col-xs-7 mt alternate">
				<label for="class" style="font-size: 22px">Female &nbsp;</label><input name="gender" style="font-size: 22px" type="radio" value="female"></div>
			</div>
		</section>
	</div>
	
	<!-- 로그인 창  -->
	<div class="col-xs-12">
		<input type="submit" class="btn btn-primary btn-block" value="Sign Up!">
	</div></div></div></div></form>
	</div></div>

	<!-- 로그인창 옆 설명 div -->
	<div class="desc2 animate-box">
		<div class="col-sm-7 col-sm-push-1 col-md-7 col-md-push-1">
		<h2><font size="22px">Don't be concerned!</font></h2><br>
		<h3>Do you want to make friends?</h3>
		</div></div></div></div></div></div></div>
	
	<script type="text/javascript">
		function ckid() {
		window.open("/Project_KBJ1/idck.jsp", "test", "top=0, left=0, width=520, height=200"); 
	}
	</script>
	<!-- footer 페이지 -->
	<jsp:include page="/footer.jsp" flush="false"/>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/sticky.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/selectFx.js"></script>
	<script src="js/main.js"></script>
</body>
</html>