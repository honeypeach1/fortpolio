<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방 정보를 등록합니다..</title>
<script type="text/javascript" src="assets/js/jquery-2.1.3.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
<!--  <script src='http://jquery-multifile-plugin.googlecode.com/svn/trunk/jquery.form.js' type="text/javascript" language="javascript"></script>
<script src='http://jquery-multifile-plugin.googlecode.com/svn/trunk/jquery.MetaData.js' type="text/javascript" language="javascript"></script>
<script src='http://jquery-multifile-plugin.googlecode.com/svn/trunk/jquery.MultiFile.js' type="text/javascript" language="javascript"></script>
<script src='http://jquery-multifile-plugin.googlecode.com/svn/trunk/jquery.blockUI.js' type="text/javascript" language="javascript"></script> 
 -->
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
/* JS for demo only */
var colors = ['1abc9c', '2c3e50', '2980b9', '7f8c8d', 'f1c40f', 'd35400', '27ae60'];

colors.each(function (color) {
  $$('.color-picker')[0].insert(
    '<div class="square" style="background: #' + color + '"></div>'
  );
});

$$('.color-picker')[0].on('click', '.square', function(event, square) {
  background = square.getStyle('background');
  $$('.custom-dropdown select').each(function (dropdown) {
    dropdown.setStyle({'background' : background});
  });
});

/*
 * Original version at
 * http://red-team-design.com/making-html-dropdowns-not-suck
 */ 
// 여기까지가 토글 비슷한거 js 파일


  $("form").on("change", ".file-upload-field", function(){ 
	    $(this).parent(".file-upload-wrapper").attr("data-text",         
	    		
	    		$(this).val().replace(/.*(\/|\\)/, '') );
	}); 
	
	// 여기까지가 파일업로드 js 파일
	
	var openWin;
    
        function openChild()
        {
            // window.name = "부모창 이름"; 
            window.name = "parentForm";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
            openWin = window.open("hotelpopup.jsp",
                    "childForm", "width=570, height=350, resizable = no, scrollbars = no");    
        }

        
        function openRoom()
        {
            // window.name = "부모창 이름"; 
            window.name = "parentForm";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
           
            openWin = window.open("roompopup.jsp",
                    "roompopupForm", "width=1000, height=500, resizable = no, scrollbars = no");    
        }
        
        function openRoom_no()
        {
            // window.name = "부모창 이름"; 
            window.name = "parentForm";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
           
            openWin = window.open("room_no_option.jsp",
                    "room_no_optionForm", "width=1000, height=500, resizable = no, scrollbars = no");    
        }
        
	  </script>
	  
	  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample6_execDaumPostcode() {
        new daum.Postcode({
        	oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

<style type="text/css">

/* CodePen demo */
body {
  background: #2a2a2b;
  color: #fff;
  //text-align: center;
  font-family: Arial, Helvetica;
}

.big {
  font-size: 1.2em;
}

.small {
  font-size: .7em;
}

.square {
  width: .7em;
  height: .7em;
  margin: .5em;
  display: inline-block;
}

/* Custom dropdown */
.custom-dropdown {
  position: relative;
  display: inline-block;
  vertical-align: middle;
  margin: 10px; /* demo only */
}

.custom-dropdown select {
  background-color: rgba(255,255,255,.1);
  color: #fff;
  font-size: inherit;
  padding: .5em;
  padding-right: 2.5em; 
  width: 258px;
  height:48px;
  border: 0;
  margin: 0;
  border-radius:25px;
  text-indent: 0.01px;
  text-overflow: '';
  -webkit-appearance: button; /* hide default arrow in chrome OSX */
}



.custom-dropdown::before,
.custom-dropdown::after {
  content: "";
  position: absolute;
  pointer-events: none;
}

.custom-dropdown::after { /*  Custom dropdown arrow */
  content: "\25BC";
  height: 1em;
  font-size: .625em;
  line-height: 1;
  right: 1.2em;
  top: 50%;
  margin-top: -.5em;
}

.custom-dropdown::before { /*  Custom dropdown arrow cover */
  width: 2em;
  right: 0;
  top: 0;
  bottom: 0;
  border-radius: 0 25px 25px 0;
}

.custom-dropdown select[disabled] {
  color: rgba(0,0,0,.3);
}

.custom-dropdown select[disabled]::after {
  color: rgba(0,0,0,.1);
}

.custom-dropdown::before {
  background-color: rgba(0,0,0,.15);
}

.custom-dropdown::after {
  color: rgba(0,0,0,.4);
}

/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 여기까지 select 박스 */ 


body{
	margin:0;
	color:#6a6f8c;
	background:#c8c8c8;
	font:600 16px/18px 'Open Sans',sans-serif;
}
*,:after,:before{box-sizing:border-box}
.clearfix:after,.clearfix:before{content:'';display:table}
.clearfix:after{clear:both;display:block}
a{color:inherit;text-decoration:none}

.login-wrap{
	width:100%;
	margin:auto;
	max-width:1000px;
	min-height:750px;
	position:relative;
	background:url(./hotel1.jpg) center;
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
}
.login-html{
	width:100%;
	height:100%;
	position:absolute;
	padding:80px 70px 30px 70px;
	background:rgba(40,57,101,.9);
}
.login-html .sign-in-htm,
.login-html .sign-up-htm{
	top:0;
	left:0;
	right:0;
	bottom:0;
	position:absolute;
	transform:rotateY(180deg);
	backface-visibility:hidden;
	transition:all .4s linear;
}
.login-html .sign-in,
.login-html .sign-up,
.login-form .group .check{
	display:none;
}
.login-html .tab,
.login-form .group .label,
.login-form .group .button{
	text-transform:uppercase;
}
.login-html .tab{
	font-size:22px;
	margin-right:15px;
	padding-bottom:5px;
	margin:0 15px 10px 0;
	display:inline-block;
	border-bottom:2px solid transparent;
}
.login-html .sign-in:checked + .tab,
.login-html .sign-up:checked + .tab{
	color:#fff;
	border-color:#1161ee;
}
.login-form{
	min-height:345px;
	position:relative;
	perspective:1000px;
	transform-style:preserve-2d;
}
.login-form .group{
	margin-bottom:15px;
}
.login-form .group .label,
.login-form .group .input,
.login-form .group .button{
	width:100%;
	color:#fff;
	display:block;
}

.login-form .group .input,
.login-form .group .button{
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:rgba(255,255,255,.1);
}
.login-form .group input[data-type="password"]{
	text-security:circle;
	-webkit-text-security:circle;
}
.login-form .group .label{
	color:white;
	font-size:12px;
}
.login-form .group .button{
	background:#1161ee;
}
.login-form .group label .icon{
	width:15px;
	height:15px;
	border-radius:2px;
	position:relative;
	display:inline-block;
	background:rgba(255,255,255,.1);
}
.login-form .group label .icon:before,
.login-form .group label .icon:after{
	content:'';
	width:10px;
	height:2px;
	background:#fff;
	position:absolute;
	transition:all .2s ease-in-out 0s;
}
.login-form .group label .icon:before{
	left:3px;
	width:5px;
	bottom:6px;
	transform:scale(0) rotate(0);
}
.login-form .group label .icon:after{
	top:6px;
	right:0;
	transform:scale(0) rotate(0);
}
.login-form .group .check:checked + label{
	color:#fff;
}
.login-form .group .check:checked + label .icon{
	background:#1161ee;
}
.login-form .group .check:checked + label .icon:before{
	transform:scale(1) rotate(45deg);
}
.login-form .group .check:checked + label .icon:after{
	transform:scale(1) rotate(-45deg);
}
.login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
	transform:rotate(0);
}
.login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
	transform:rotate(0);
}

.hr{
	height:2px;
	//margin:60px 0 50px 0;
	background:rgba(255,255,255,.2);
}
.foot-lnk{
	text-align:center;
}

 /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ  여기까지 본문 css*/

 .in-line{
      width:350px;
      height:40px;
    }
    input{
      margin:0;
    } 
    input[type="text"]{
      width:70%;
      height:100%;
      border:none;
      font-size:1em;
      padding-left: 5px;
      font-style: oblique;
      display:inline;
     // outline:none;
      box-sizing: border-box;
      color:black;

    }
    input[type=button]{
      width: 100px;
      height:48px;
      border:none;
      background-color: lightgray;
      font-size:1em;
      color:#042AaC;
      outline:none;
      display:inline;
      margin-left: 765px;
      box-sizing: border-box;
      ;
      margin-top:-48px;
      border-radius:10px;
    }
      input[type=button]:hover{
      background-color: white;
    }  
    
   /* 여기까지가 버튼박스  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
@import url(https://fonts.googleapis.com/css?family=Lato:400,700,300);
body {
  font-family: 'Lato', sans-serif;
}

 .container {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  -webkit-box-align: center;
  -moz-box-align: center;
  box-align: center;
  -webkit-align-items: center;
  -moz-align-items: center;
  -ms-align-items: center;
  -o-align-items: center;
  align-items: center;
  -ms-flex-align: center;
  display: -webkit-box;
  display: -moz-box;
  display: box;
  display: -webkit-flex;
  display: -moz-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -moz-box-pack: center;
  box-pack: center;
  -webkit-justify-content: center;
  -moz-justify-content: center;
  -ms-justify-content: center;
  -o-justify-content: center;
  justify-content: center;
  -ms-flex-pack: center;
  background-color: #bf7a6b;
  background-image: -webkit-linear-gradient(bottom left, #bf7a6b 0%, #e6d8a7 100%);
  background-image: linear-gradient(to top right,#bf7a6b 0%, #e6d8a7 100%);
}
 
 
.form {
  width: 100%;
  height:48px;
}

.file-upload-wrapper {
  position: relative;
  width: 30%;
  height: 48px;
}
.file-upload-wrapper:after {
  content: attr(data-text);
  font-size: 18px;
  position: absolute;
  top: 0;
  left: 0;
  background: rgba(255,255,255,.1);
  padding: 10px 15px;
  display: block;
  width: calc(100% - 40px);
  pointer-events: none;
  z-index: 20;
  height: 48px;
  line-height: 24px;
  color: #999;
  border-radius: 25px;
  font-weight: 300;
}
.file-upload-wrapper:before {
  content: 'Upload';
  position: absolute;
  top: 0;
  right: 0;
  display: inline-block;
  height: 48px;
  background: #4daf7c;
  color: #fff;
  font-weight: 700;
  z-index: 25;
  font-size: 16px;
  line-height: 48px;
  padding: 0 15px;
  text-transform: uppercase;
  pointer-events: none;
  border-radius: 0 25px 25px 0;
}
.file-upload-wrapper:hover:before {
  background: #3d8c63;
}
.file-upload-wrapper input {
  opacity: 0;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 99;
  height: 48px;
  margin: 0;
  padding: 0;
  display: block;
  초
  cursor: pointer;
  width: 100%;
}
/* 여기까지가 파일업로드 css */



</style>
</head>
<body>
<form action="./regisRoomAction.bo" method="post" enctype="multipart/form-data" name="fr">
<div class="login-wrap">
	<div class="login-html">
	
	
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">방 정보 입력</label>
		<input id="tab-2" type="hidden" name="tab" class="sign-up"><label for="tab-2" class="tab">호텔 정보 입력</label>
		<div class="login-form">
		
		
			<div class="sign-in-htm">
				<div class="group">
					<label for="user" class="label" style="float:left; padding-top:0px; margin-top:0px;">방 타입</label>
					<input id="user" name="room_name" type="text" class="input" style="width:30%; float:left;">
				</div>
				
				<div class="group">
					<label for="user" class="label" style="position:relative; left:30px; bottom:17px;">방 호수</label>
					<input id="user" name="room_no" type="text" class="input" style="width:30%; float:left; position:relative; left:30px; bottom:18px;"readonly>
				</div>
				
				<div class="group">
					<input type="button" name="roname" value="Add" onclick="openRoom_no()"style="display:inline; position:relative; top:-20px;left:-60px;">
				</div>
				
				
				
				
				<div class="group">
					<label for="user" class="label" style="float:left; position:relative; top:-11px;">기본 인원</label>
					<input id="user" name="room_basic_people" type="text" class="input" style="width:30%; float:left; position:relative; top:-11px;">
				</div>
				
				<div class="group">
					<label for="user" class="label" style="position:relative;right:-30px;;top:-28px;">최대 인원</label>
					<input id="user" name="room_max_people" type="text" class="input" style="width:30%;  position:relative; left:30px; top:-30px;">
				</div>
	
				<!-- <div class="group">
					<label for="user" class="label" style="position:relative; left:585px; top:-110px;">객실 타입</label>
					<input id="user" type="text" class="input" style="width:30%; position:relative; left:585px; top:-110px;">
					
					<span class="custom-dropdown big" id="user" class="input" style="width:258px; position:relative; left:578px; top:-120px;">
    				<select style="width:258px;">    
        			<option>1-</option>
        			<option>2성</option>  
   					</select>
					</span>
				</div> -->
				
				
				
				
				<div class="group">
					<label for="user" class="label" style="position:relative; top:-22px;">객실 이미지</label>
					
					
    				<div class="file-upload-wrapper"  style="position:relative; top:-22px;"> 
     				 <input name="room_pic1" type="file" class="file-upload-field">
   					 </div>
   					 <div class="file-upload-wrapper"  style="position:relative;top:-70px; right:-287px;"> 
     				 <input name="room_pic2" type="file" class="file-upload-field">
   					 </div>
  					
  					<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
				</div>
				
				<div class="group">
					<label for="user" class="label" style="display:inline; position:relative; bottom:-18px;">객실 정보</label>
					<input id="user" name="room_info" type="text" class="input" style="display:inline; position:relative; bottom:-18px; ">
				</div>
				
				
				
				<div class="group">
				
					<label for="user" class="label" style="position:relative; top:-147px; ">객실 부가서비스</label>
					
					<input id="ppInput" type="text" class="input" style="display:inline; width:75%; position:relative; top:-147px;" readonly>
					<input type="button" name="name" value="Add" onclick="openRoom()"style="display:inline; position:relative; top:-147px;left:-60px;">
				
				</div>
				
				<div class="group">
					<label for="user" class="label" style="display:inline; position:relative; top:-10px; left:550px; font-size:3em; color:white;">￦</label>
					<input id="user" name="room_price" type="text" class="input" style="display:inline; position:relative; top:-20px; left:550px; width:30%;color:black; background:white;">
				</div>
				
				 <input type="hidden" id="id" name="room_option_name">
				<input type="hidden" id="pass" name="room_option_price">
				
				 <%
				 int hotel_num = (Integer)request.getAttribute("hotel_num");
				%>
				<input type="hidden" name="hotel_num" id="hotel_num" value=<%= hotel_num  %>>
				 
				<div class="group">
					<input type="submit" class="button" value="Next" style="display:inline; float:left; width:45%; position:relative; ">
				</div>
				<div class="group">
					<input type="button" class="button" value="Finish" onclick="location.href='./goToMainPage.li'" style="display:inline; float:right; width:45%; position:relative; ">
				</div>
				
				<!-- <div class="group">
					<input type="submit" class="button" value="Submit" style="display:inline; float:right; width:45%; position:relative; top:-50px; ">
				</div> -->
				
				
				
			<script  src="js/index.js"></script>	

			</div>
		</div>
	</div>
</div>

</form>


</body>
</html>