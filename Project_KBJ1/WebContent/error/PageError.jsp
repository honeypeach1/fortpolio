<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Error!</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<link rel='stylesheet' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
<link rel="stylesheet" href="css/style.css">
</head>
<style>
@font-face {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  src: local('Roboto'), local('Roboto-Regular'), url(https://fonts.gstatic.com/s/roboto/v18/KFOmCnqEu92Fr1Mu4mxP.ttf) format('truetype');
}
html {
  background: url(https://38.media.tumblr.com/546c0cd48d71f210f9b67a389003790d/tumblr_neq0yw9rMA1tm16jjo1_500.gif) no-repeat center center fixed;
  background-size: cover;
  font-family: 'Roboto', sans-serif;
}
h1 {
  font-size: 16em;
  margin: 0.2em 0.5em;
  color: rgba(255, 255, 255, 0.7);
  margin-bottom: 0px;
}
h2 {
  font-size: 1.7em;
  margin: 0.2em 0.5em;
  color: rgba(255, 255, 255, 0.6);
}
h2 .material-icons {
  font-size: 1.5em;
  position: relative;
  top: 10px;
}
div.error {
  position: absolute;
  top: 30%;
  margin-top: -8em;
  width: 100%;
  text-align: center;
}
</style>
<body>

  <div class="error">
  <h1>이런! </h1>
  <h2>페이지 에러가 발생했습니다! 
  <i class="material-icons">sentiment_very_dissatisfied</i></h2>
  
  <form action="/Project_KBJ1/Mainpage.jsp" method="post" id="frm">
  	<h2><a href="#" onclick="document.getElementById('frm').submit();">메인페이지로 돌아가기</a></h2>
  </form><br>
  <h2><a href="/Project_KBJ1/logout.jsp">로그아웃 처리</a></h2>
 
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js Copy'></script>

  

</body>
</html>