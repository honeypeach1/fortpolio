<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
       
  <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>

      <link rel="stylesheet" href="css/style.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
     
   <script>
    //추가 버튼
    $(document).on("click","button[name=addStaff]",function(){
         
        var addStaffText =  '<tr class="listitem" name="trStaff">'+
       ' <td class="td-address" title="Address"><input type="text" name="option_name"/></td>'+
       '<td class="td-address"><button name="delStaff"><img src="./images/mainimages/bb.jpg" style="width:20px; height:20px;"></button></td>'+
       ' </tr>';
             
            
            
            
            
        var trHtml = $( "tr[name=trStaff]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출
         
        trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.
         
    });
     
    //삭제 버튼
    $(document).on("click","button[name=delStaff]",function(){
         
        var trHtml = $(this).parent().parent();
         
        trHtml.remove(); //tr 테그 삭제
         
    });
   
    
    
    
    
    // 값 전달 파트
    
    	function sub(){
    		 var room_option_name="";  
    		 
    		 
		/* alert("변수 선언 완료");
		
		for(var i=0;i<option_name.length;i++){ //배열 출력
		    alert(option_name[i]);
		} */
		
		for (var i=0; i<document.getElementsByName('option_name').length; i++) {
	
		    
		    room_option_name += document.getElementsByName('option_name')[i].value

			if(i<document.getElementsByName('option_name').length-1){
		    room_option_name += ","
			}
		    
		}


			 
			     opener.document.fr.room_no.value = room_option_name;
			
	
			     window.close();
    
    }
 
</script>
     <style type="text/css">
   
    .myButton {
	-moz-box-shadow: 0px 0px 0px 0px #3dc21b;
	-webkit-box-shadow: 0px 0px 0px 0px #3dc21b;
	box-shadow: 0px 0px 0px 0px #3dc21b;
	background-color:#44c767;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #18ab29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:14px;
	font-weight:bold;
	padding:2px 8px;
	text-decoration:none;
	text-shadow:1px 0px 16px #2f6627;
}
.myButton:hover {
	background-color:#5cbf2a;
}
.myButton:active {
	position:relative;
	top:1px;
}

        
    
    </style>
</head>
<body>


<div class="container small my-5">
  <div class="row">
    <div class="container">
      <h1 class="h3 ml-2 mb-3 text-info">해당 객실 번호</h1>
    </div>
  </div>
  
  <button name="addStaff" class="myButton">옵션 추가</button><div style="padding-bottom:10px;"></div>
  <div class="table-responsive">
  
  
  <form action="" method="get" name="wfr">
    <table class="table responsive table-bordered" id="example"style="max-width:230px; color:white;">
      <thead> 
        <tr>
          
          <th>방번호</th>
          <th>삭제</th>
        
        </tr>
      </thead>
      <tbody>
        <tr name="trStaff" class="listitem">
          <td class="td-address" title="Address"><input type="text" name="option_name"/></td>
          <td class="td-address"><button name="delStaff"><img src="./images/mainimages/bb.jpg" style="width:20px; height:20px;"> </button></td>
        </tr>
        
        
        
        
        
      
      </tbody>
    </table>
    
    <input type="button" class="myButton" onclick="sub()" value="확인" style="position:relative; right:-183px;">
    </form>
    
    
    
    
    
  </div>
</div>
  <script src='https://code.jquery.com/jquery-3.4.0.min.js'></script>

  

    <script  src="js/index.js"></script>
    

</body>
</html>