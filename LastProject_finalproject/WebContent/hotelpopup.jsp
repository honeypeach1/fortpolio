<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/jQuery.scrollSpeed.js"></script>

 <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
 <script type="text/javascript">
 
 function setParentText(){
     opener.document.getElementById("pInput").value = document.getElementById("cInput").value
     window.close();
}

 
   </script>
 <style type="text/css">
@-moz-keyframes borderscale {
  50% {
    box-shadow: 0 0 0 2px #999;
  }
}
@-webkit-keyframes borderscale {
  50% {
    box-shadow: 0 0 0 2px #999;
  }
}
@keyframes borderscale {
  50% {
    box-shadow: 0 0 0 2px #999;
  }
}
html, body {
  height: 100%;
}

body {
  font-family: "Open Sans", "Helvetica Neue", Arial, sans-serif;
  font-weight: normal;
  font-size: 16px;
  line-height: 24px;
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  justify-content: center;
  background: #2d2d2d;
}

form {
  background: #fff;
  border-radius: 20px;
  box-shadow: 5px 5px 15px rgba(107, 173, 182, 0.6);
  padding: 1rem 2rem;
  min-width: 20vw;
}
form li {
  margin: 0.3rem 0;
}
form div {
  margin: 1.5rem 0;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

input[type="checkbox"],
input[type="radio"] {
  border: 0;
  clip: rect(0 0 0 0);
  height: 1px;
  margin: -1px;
  overflow: hidden;
  padding: 0;
  position: absolute;
  width: 1px;
}
input[type="checkbox"]:hover + label:before,
input[type="radio"]:hover + label:before {
  border-color: #999;
}
input[type="checkbox"]:active + label:before,
input[type="radio"]:active + label:before {
  transition-duration: 0;
  filter: brightness(0.2);
}
input[type="checkbox"] + label,
input[type="radio"] + label {
  position: relative;
  padding-left: 26px;
  font-weight: normal;
}
input[type="checkbox"] + label:before, input[type="checkbox"] + label:after,
input[type="radio"] + label:before,
input[type="radio"] + label:after {
  box-sizing: content-box;
  position: absolute;
  content: '';
  display: block;
  left: 0;
}
input[type="checkbox"] + label:before,
input[type="radio"] + label:before {
  top: 50%;
  width: 16px;
  height: 16px;
  margin-top: -10px;
  border: 2px solid #d9d9d9;
  text-align: center;
}
input[type="checkbox"] + label:after,
input[type="radio"] + label:after {
  background-color: #00bad2;
  top: 50%;
  left: 6px;
  width: 8px;
  height: 8px;
  margin-top: -4px;
  transform: scale(0);
  transform-origin: 50%;
  transition: transform 200ms ease-out;
}

input[type="radio"]:checked + label:before {
  -moz-animation: borderscale 300ms ease-in;
  -webkit-animation: borderscale 300ms ease-in;
  animation: borderscale 300ms ease-in;
  background-color: #fff;
}
input[type="radio"]:checked + label:after {
  transform: scale(1);
}
input[type="radio"] + label:before, input[type="radio"] + label:after {
  border-radius: 50%;
}

input[type="checkbox"] + label:after {
  background-color: transparent;
  top: 50%;
  left: 5px;
  width: 7px;
  height: 4px;
  margin-top: -5px;
  border-style: solid;
  border-color: #00bad2;
  border-width: 0 0 3px 3px;
  -moz-transform: rotate(-45deg) scale(0);
  -ms-transform: rotate(-45deg) scale(0);
  -webkit-transform: rotate(-45deg) scale(0);
  transform: rotate(-45deg) scale(0);
  -moz-transition: none;
  -o-transition: none;
  -webkit-transition: none;
  transition: none;
}
input[type="checkbox"]:checked + label:before {
  -moz-animation: borderscale 200ms ease-in;
  -webkit-animation: borderscale 200ms ease-in;
  animation: borderscale 200ms ease-in;
}
input[type="checkbox"]:checked + label:after {
  content: '';
  -moz-transform: rotate(-45deg) scale(1);
  -ms-transform: rotate(-45deg) scale(1);
  -webkit-transform: rotate(-45deg) scale(1);
  transform: rotate(-45deg) scale(1);
  -moz-transition: -moz-transform 200ms ease-out;
  -o-transition: -o-transform 200ms ease-out;
  -webkit-transition: -webkit-transform 200ms ease-out;
  transition: transform 200ms ease-out;
}

input[type="text"] {
  font-family: "Open Sans", "Helvetica Neue", Arial, sans-serif;
  font-weight: normal;
  font-size: 16px;
  line-height: 24px;
  padding: 5px 10px;
  background-color: #fff;
  border: 2px solid #d9d9d9;
}
input[type="text"]:focus {
  outline: none;
}
input[type="text"]:focus, input[type="text"]:hover {
  border-color: #999;
}



/* IE 10/11+ - This hides native dropdown button arrow so it will have the custom appearance, IE 9 and earlier get a native select - targeting media query hack via http://browserhacks.com/#hack-28f493d247a12ab654f6c3637f6978d5 - looking for better ways to achieve this targeting */

@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {
  select::-ms-expand {
    display: none;
  }

  select:focus::-ms-value {
    background: transparent;
    color: grey;
  }
}



</style>
</head>

<body>
<script> 

	 $(document).ready(function(){
    	$(".checkbox").change(function(){
    
    	
        	if($(".checkbox").is(":checked")){
		//컨트롤 ㅡ 액션 ㅡ dao,bean - 돌아오는건 success 옵션
		//alert("체크");
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		// 여기서 체크된 것들만 골라서 배열로 저장을 해야하는데 어떻게 해야하누
		
		/*   var str = "";  // 여러개가 눌렸을 때 전부 출력이 될 수 있게 하나의 객체에 담는다.
		$(".checkbox").each(function(){  // .each()는 forEach를 뜻한다.
			if($(this).is(":checked"))  // ":checked"를 이용하여 체크가 되어있는지 아닌지 확인한다.
				str += $(this).val();// 체크된 객체를 str에 저장한다.		
		});
		$("#multiPrint").text(str);  // #multiPrint에 체크된 원소를 출력한다.
        $(".cInput").val(str);
		 }else{
			 str="";
            //alert("체크박스 체크 해제!");           
        }  */
        
        	var str = new Array();  // 여러개가 눌렸을 때 전부 출력이 될 수 있게 하나의 객체에 담는다.
     		$(".checkbox").each(function(){  // .each()는 forEach를 뜻한다.
     			if($(this).is(":checked"))  // ":checked"를 이용하여 체크가 되어있는지 아닌지 확인한다.
     				//str = $(this).val();// 체크된 객체를 str에 저장한다.	
     				str.push($(this).val());	
     		});
     			$("#multiPrint").text(str);  // #multiPrint에 체크된 원소를 출력한다.
             	$(".cInput").val(str);
     		
              
     		
              
     		 }else{
                 alert("오류");           
             }  
             
             
        	/* function abc(){
        	  var val = "123";
        	   
        	  opener.document.getElementById("abc").value = val;
        	  window.opener.form.abc.value = val;
        	  window.close();
        	}


        	출처: https://queserasera.tistory.com/8 [기발한 개발노트]
     		  */
        	
    
        });
	}); 
	 	 
		</script>
		
		
		
<form action="" target="" method="post" name="fr">

    <h2>호텔 부가서비스</h2>
  
    <div>
  <!--  체크박스 값 확인 : <span id="multiPrint" ></span>  -->
        <ul>
       		<li><input class="checkbox" id="checkbox1" name="hotelpopup[]" type="checkbox" value="와이파이"> <label for="checkbox1">와이파이</label></li>
            <li><input class="checkbox" id="checkbox2" name="hotelpopup[]" type="checkbox" value="수영장"> <label for="checkbox2">수영장</label></li>
            <li><input class="checkbox" id="checkbox3" name="hotelpopup[]" type="checkbox" value="주차장"> <label for="checkbox3">주차장</label></li>
            <li><input class="checkbox" id="checkbox4" name="hotelpopup[]" type="checkbox" value="금연"> <label for="checkbox4">금연</label></li>
            <li><input class="checkbox" id="checkbox5" name="hotelpopup[]" type="checkbox" value="환전소"> <label for="checkbox5">환전소</label></li>
            <li><input class="checkbox" id="checkbox6" name="hotelpopup[]" type="checkbox" value="레스토랑"> <label for="checkbox6">레스토랑</label></li>
            <li><input class="checkbox" id="checkbox7" name="hotelpopup[]" type="checkbox" value="피트니스"> <label for="checkbox7">피트니스</label></li>
            <li><input class="checkbox" id="checkbox8" name="hotelpopup[]" type="checkbox" value="안내데스크"> <label for="checkbox8">안내데스크</label></li>
            <li><input class="checkbox" id="checkbox9" name="hotelpopup[]" type="checkbox" value="칵테일바"> <label for="checkbox9">칵테일바</label></li>
       		<!-- <li><input class="checkbox" id="checkbox4" type="checkbox" value="바"> <label for="checkbox4"><input type="text" value="" placeholder="기타"></label></li> -->
        </ul>
    </div>
    
<input type="text" id="cInput" class="cInput" style="display:none;"><input type="button" value="확인" onclick="setParentText();" >
</form>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
</body>
</html>