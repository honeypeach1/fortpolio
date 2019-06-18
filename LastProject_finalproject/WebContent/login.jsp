<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Welcome to our WebSite!</title>
  
      <link rel="stylesheet" href="css/style.css">

</head>

<!-- 회원가입 체크하기 자바스크립트 -->
<script type="text/javascript">
	
	function checkSignUp() {
		
		if (document.ckform.user_id.value == false) {
			alert("아이디는 반드시 입력하셔야합니다.")
			return false;
			
		} else if (document.ckform.user_pw.value == false) {
			alert("비밀번호는 반드시 입력하셔야 합니다.");
			return false;
		
		} else if (document.ckform.user_name.value == false) {
			alert("이름을 입력하세요!");
			return false;
			
		} else if (document.ckform.user_email.value == false) {
			alert("이메일을 입력하셔야 합니다.");
			return false;
		} else if (document.ckform.user_id.value.length < 4 || document.ckform.user_id.value.length > 15) {
			alert("아이디는 4글자에서 15글자 사이여야 합니다.");
			return false;
		} else if (document.ckform.user_pw.value.length < 4) {
			alert("비밀번호는 4글자 이상이여야 합니다.");
			return false;
		}
	}
	
	function checkLogin(){
		if(document.ckLogin.id.value == false){
			alert("아이디를 입력하세요.");
			return false;
		}else if(document.ckLogin.pw.value == false){
			alert("비밀번호를 입력하세요.");
			return false;
		}
	}
	
	function ckAcc(){
		if(document.ckformAcc.user_name.value == false){
			alert("이름을 입력하세요.");
			return false;
		}else if(document.ckformAcc.user_email.value == false){
			alert("이메일을 입력하세요.");
			return false;
		}
	}

	function ckAccPass(){
		if(document.ckformPass.user_id.value == false){
			alert("아이디를 입력하세요.");
			return false;
		}else if(document.ckformPass.user_name.value == false){
			alert("이름을 입력하세요.");
			return false;
		}else if(document.ckformPass.user_email.value == false){
			alert("이메일을 입력하세요.");
			return false;
		}
	}
</script>
<style type="text/css">

/* Modal 창 - 시작 */
.white_content {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.8);
    opacity:0;
    -webkit-transition: opacity 400ms ease-in;
    -moz-transition: opacity 400ms ease-in;
    transition: opacity 400ms ease-in;
    pointer-events: none;
}
.white_content:target {
    opacity:1;
    pointer-events: auto;
}
.white_content > div {
	position: absolute;
	top: 15%;
	left: 25%;
	width: 40%;
	height: 60%;
	padding: 16px;
	border: 13px solid #2e3f61;
	background-color: white;
	overflow: auto;	
	z-index: 3;
	border-radius: 5px 0;
}
/* Modal 창 - 끝 */

</style>
<body>

  <link href="https://fonts.googleapis.com/css?family=PT+Sans|Ubuntu:400,500" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<body>
  <div class="wrapper">
    <div class="background">
      <div class="left">
        <h2 class="back-header">Dont have an account yet?</h2>
        <p class="back-p">You should sign up today!</p>
        <button class="back-btn signup-but">SIGN UP</button>
      </div>
      <div class="right">
        <h2 class="back-header">Do you already have an account?</h2>
        <p class="back-p">Let's get you logged in!</p>
        <button class="back-btn login-but">LOGIN</button>
      </div>
    </div>
    <div class="form-container" style="height: 450px; border-radius: 30px 0">
      <div class="sign-up">  
            
        <!-- 회원가입 폼 -->
        <h2 class="form-header">SIGN UP</h2>
        <form action="./userSignUp.do" method="post" enctype="multipart/form-data" name="ckform" onsubmit="return checkSignUp()">
        <input type="text" name="user_id" id="user_id" placeholder="It must be between 4 and 15"><i class="fa fa-user"></i></input>
        <input type="password" name="user_pw" id="user_pw" placeholder="Only Password have to over 4"><i class="fa fa-lock"></i></input>
        <input type="text" name="user_name" id="user_name" placeholder="have to do write your name!"><i class="fa fa-user"></i></input>
        <input type="file" name="user_image" id="user_image" placeholder="Upload your image"><i class="fa fa-user"></i></input>
        <input type="text" name="user_email" id="email" placeholder="email"><i class="fa fa-envelope-o"></i></input>
        <input type="text" name="user_phone" id="user_phone" placeholder="What is your phone number?"><i class="fa fa-phone"></i></input>
        <input type="submit" class="form-btn" value="SIGN UP!">
        </form>
      </div>
      <div class="login hide">
      
      <!-- 로그인 폼 -->
      <br>
      <br>
        <h2 class="form-header">LOGIN</h2><br>
        <form action="./userLogin.do" method="post" name="ckLogin" onsubmit="return checkLogin()">
        <input type="text" name="id" id="id" placeholder="id"><i class="fa fa-envelope-o"></i></input><br>
        <input type="password" name="pw" id="password" placeholder="Password"><i class="fa fa-lock"></i></input>
        <input class="form-btn" type="submit" value="Login!">
        </form>
        
        <br>
        <a class="forgot" href="#openDltModal1" class="open">Forgot Account</a> /
        <a class="forgot" href="#openDltModal2" class="open">Forgot Password</a>
        
      </div>
    </div>
  </div>
  
  <!-- ----------------------------------------------------------------------- -->
  
  <div class="white_content" id="openDltModal1" style="border-radius: 5px 0">
	<div class="deleteModal">
	<div align="right"><a href="#close" class="close"><img src="images/mainimages/exitImage.png" style="width: 40px; height: 40px;"></a></div>            

        <h2 class="form-header"><font size="40px">Find Account</font></h2>
        <form action="./FindAccount.do" method="post" name="ckformAcc" onsubmit="return ckAcc()">
        <input type="text" name="user_name" id="user_name" placeholder="이름을 입력하세요." style="margin-top: 20px;width: 70%;height: 30px;border: 0;border-bottom: 1px solid #888; font-size: 20px"><i class="fa fa-user"></i></input><br><br>
        <input type="text" name="user_email" id="user_email" placeholder="이메일을 입력하세요." style="margin-top: 20px;width: 70%;height: 30px;border: 0;border-bottom: 1px solid #888; font-size: 20px"><i class="fa fa-envelope-o"></i></input>
        <input type="submit" class="form-btn" value="FIND UP!" style="display: block; margin-top: 30px;
    		width: 200px;height: 55px;font-size: 18px;border: 0;border-radius: 3px;background-color: #2e3f61;color: #fafafa;transition: .4s all;">
        </form>
        <br>
        <br>
        <a><font style="color: gray; font-size: 20px"><i>일치하는 유저 정보의 이메일로 아이디를 보내드립니다.</i></font></a>
	</div>
</div>
  
  
  <!-- ----------------------------------------------------------------------- -->
  
  <!-- ----------------------------------------------------------------------- -->
  
  
  <div class="white_content" id="openDltModal2" style="border-radius: 5px 0">
	<div class="deleteModal">
	<div align="right"><a href="#close" class="close"><img src="images/mainimages/exitImage.png" style="width: 40px; height: 40px;"></a></div>            
        <!-- 회원가입 폼 -->
        <h2 class="form-header"><font size="40px">Find Password</font></h2>
        <form action="./FindPass.do" method="post" name="ckformPass" onsubmit="return ckAccPass()">
        <input type="text" name="user_id" id="user_id" placeholder="아이디를 입력하세요." style="margin-top: 20px;width: 70%;height: 30px;border: 0;border-bottom: 1px solid #888; font-size: 20px"><i class="fa fa-user"></i></input>
        <input type="text" name="user_name" id="user_name" placeholder="이름을 입력하세요." style="margin-top: 20px;width: 70%;height: 30px;border: 0;border-bottom: 1px solid #888; font-size: 20px"><i class="fa fa-user"></i></input>
        <input type="text" name="user_email" id="user_email" placeholder="이메일을 입력하세요." style="margin-top: 20px;width: 70%;height: 30px;border: 0;border-bottom: 1px solid #888; font-size: 20px"><i class="fa fa-envelope-o"></i></input>
        <input type="submit" class="form-btn" value="FIND UP!" style="display: block; margin-top: 30px;
    		width: 200px;height: 55px;font-size: 18px;border: 0;border-radius: 3px;background-color: #2e3f61;color: #fafafa;transition: .4s all;">
        </form>
        <br>
        <br>
        <a><font style="color: gray; font-size: 20px"><i>회원정보와 일치된 이메일로 임시 비밀번호를 보내드립니다.</i></font></a>
	</div>
</div>
  
  
  <!-- ----------------------------------------------------------------------- -->
</body>
<!-- ---------------------------------------------------------------------------------------- -->	

<script type="text/javascript">
	/* 리뷰에서 삭제 버튼을 클릭하면 페이지를 보이지 않게함 */
	$(document).ready(function(){
		$('.open').bind('click', function(){
			$('html, body').css({'overflow': 'hidden', 'height': '100%'});
		});
	});
	
	/* 리뷰 삭제 모달창에서 닫기 버튼을 클릭하면 페이지를 다시 보이게함 */
	$(document).ready(function(){
		$('.close').bind('click', function(){
			$('html, body').css({'overflow': 'visible', 'height': '100%'});
		});
	});
</script>

<!-- ---------------------------------------------------------------------------------------- -->
  <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>

  

    <script  src="js/index.js"></script>




</body>

</html>
