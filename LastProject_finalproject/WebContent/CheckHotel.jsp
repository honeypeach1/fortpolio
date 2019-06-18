<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>호텔 승인 여부를 확인합니다.</title>
</head>
      <link rel="stylesheet" href="css/style.css">
      <!-- <link href="https://fonts.googleapis.com/css?family=PT+Sans|Ubuntu:400,500" rel="stylesheet"> -->
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<style>
	body {
	background: white;
}
.form-container{
	top: 20px;
	left: 20px;
}
</style>
<script type="text/javascript"> 
	function ckApp(){
		if(document.ckAppForm.hotel_name.value == false){
			alert("등록하신 호텔의 이름을 입력하세요.");
			return false;
		}else if(document.ckAppForm.hotel_tel.value == false){
			alert("등록하신 호텔의 전화번호를 입력하세요.");
			return false;
		}
	}

</script>
<body>
   <div class="form-container" style="height: 450px;">
      <div class="sign-up">  
            
        <!-- 회원가입 폼 -->
        <h2 class="form-header">Check Hotel</h2>
        <form action="./checkHotel.do" method="post" name="ckAppForm" onsubmit="return ckApp()">
        <input type="text" name="hotel_name" id="hotel_name" placeholder="호텔 이름을 입력하세요."><i class="fa fa-user"></i></input>
        <input type="text" name="hotel_tel" id="hotel_tel" placeholder="호텔 전화번호를 입력하세요."><i class="fa fa-envelope-o"></i></input>
        <input type="submit" class="form-btn" value="FIND UP!">
        </form>
      </div>
      <div><font style="font-size: 14px"><i>관리자에게 궁금한점이 있다구요?<br><br>메인페이지 상단에 about페이지를 이용해주세요!<br><br>간편하게 메일을 보낼수 있습니다.</i></font></div>
    </div>
</body>
</html>