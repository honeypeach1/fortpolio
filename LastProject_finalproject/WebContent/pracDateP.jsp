<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  

<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  

<script type="text/javascript">
$(function() {
    $( "#start_date" ).datepicker({
    	
    	dateFormat: "yy-mm-dd",
    	monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
    	monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        dayNamesMin:["일","월","화","수","목","금","토"],
        changeMonth: true,     // 월을 이동하기 위한 선택상자 표시여부
        minDate: 0,  //오늘 이전  날짜 선택 불가
		onClose: function( selectedDate ){
			// 시작일(fromDate) datepicker가 닫힐때
            // 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
            $("#end_date").datepicker( "option", "minDate", selectedDate );
       
		}
    });// start_date - 끝
    
    
    $( "#end_date" ).datepicker({
    	
    	dateFormat: "yy-mm-dd",
    	monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
    	monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        dayNamesMin:["일","월","화","수","목","금","토"],
        changeMonth: true,     // 월을 이동하기 위한 선택상자 표시여부
        minDate: +1,
        onClose: function( selectedDate ) {
            // 종료일(toDate) datepicker가 닫힐때
            // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
            $("#start_date").datepicker( "option", "maxDate", selectedDate );
        }
    	
    });// end_date - 끝
    
    
    
    

    
    
}); // function() - 끝
</script>

</head>
<body>

<input type="text" id="start_date"">
<input type="text" id="end_date">


</body>
</html>