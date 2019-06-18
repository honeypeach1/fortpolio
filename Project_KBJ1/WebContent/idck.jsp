<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
	function ck(){
		if(document.ckform.id.value == false){
			alert('아이디를 입력하세요');
			return false;
		}else if(document.ckform.id.value.length < 3){
			alert('아이디가 너무 짧습니다. 3글자 이상이 되어야합니다.');
			return false;
		}
	}
	
	function setCkValue(){
 		
 		 if(confirm("해당 아이디로 사용하시겠습니까?")){
			if(document.ckform.id.value == false){
				alert('아이디를 입력하세요.');
				return false;
			}else if(document.ckform.id.value.length <= 3){
				alert('아이디가 너무 짧습니다. 3글자 이상이 되어야합니다.');		
			}else{
			opener.document.getElementById("setId").value = document.ckform.id.value;
			window.close();
			}
		}else{
		alert('취소되었습니다.');
		}
	}

</script>
<style>
input[type="text"] {
	width: 80px;
	height: 30px;
    background: rgba(0, 0, 0, 0.05);
    border: none;
    box-shadow: none;
    font-weight: bold;
    font-size: 14px;
    padding: 5px 10px !important;
}

.btn {
	width: 150px;
	height:  40px;
	background: #f78536;
	box-shadow: none;
	color: #fff !important;
	letter-spacing: 0;
	text-transform: none;
	font-weight: bold;
	font-size: 18px;
	border: 2px solid transparent !important;
	opacity: .9;
	text-align: center;
	touch-action: manipulation;
	white-space: nowrap;
	line-height: 1.42857;
	border-radius: 4px;
	font-family: inherit;
}

.btn:hover {
	background: #393e46 !important;
	outline: none !important;
}
</style>
<body onresize="parent.resizeTo(520,200)" onload="parent.resizeTo(520,200)">
<div style="text-align: center;">
	<form action="/Project_KBJ1/view/ckid" method="post" name="ckform" onsubmit="return ck();" style="text-align: center;">
	<table style="text-align: center;">
	<tr><h5><font size="3em"><i>사용 가능한 아이디를 체크하고, 원하는 아이디를 사용하세요!</i></font></h5></tr>
	<tr>
		<td><input type="text" name="id" id="pSetId" style="width: 190px"></td>
		<td><input class="btn" type="submit" value="Checking!"></td>
		<td><input class="btn" type="button" value="Using ID!" onclick="setCkValue();">
	</tr>
	</table>
	</form>
</div>
</body>
</html>