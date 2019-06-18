<%@page import="project.user.db.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">

</head>
<body class="wdback">
<%
	// 로그인 여부 판단하기
	UserBean ub = null;
	int user_num = (int)session.getAttribute("user_num");
	System.out.println("팝업창 현재 세션값 : "+session);
	if(session.getAttribute("user_num") == null){
		
	}
	if((UserBean)request.getAttribute("ub") != null){
		ub = (UserBean)request.getAttribute("ub");
		System.out.println("탈퇴 페이지 받아오는 아이디 : "+ub.getUser_id());
	}else{
		System.out.println("실패");
	}
	
		
	// 	if(user_id == null){
// 		response.sendRedirect("./goToLoginPage.do");
// 	}
	
	System.out.println("탈퇴 페이지 받아오는 유저 번호 : "+user_num);
%>
	<div>
		<div id="wdraw">
			<div class="w3-container">
				<h2>Withdraw</h2>
				<p class="wdinfo"><i class="fa fa-question-circle" aria-hidden="true"></i>
				비밀번호를 입력하세요. 아이디와 입력한 비밀번호가 일치하면 탈퇴 절차가 진행됩니다.</p>
				<div id="withinput">
				<form action="./MyinfoDeleteAction.do" method="post">
					<!-- 아이디 영역 -->
					<i class="fa fa-id-card fa-fw w3-margin-right w3-large w3-text-teal" title="아이디"></i>
					<input type="text" name="user_id" value="<%=ub.getUser_id() %>" readonly><br>
					<!-- 비밀번호 영역 -->
					<i class="fa fa-key fa-fw w3-margin-right w3-large w3-text-teal" title="비밀번호"></i>
					<input type="password" name="user_pw" title="계정에 맞는 비밀번호를 입력하세요" placeholder="Password"><br>
					<input type="submit" value="Execute Withdraw" class="button2">
				</form>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>