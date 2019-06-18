<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- WebContent/index.jsp -->

<%

// 객실 세부 정보 보기 페이지
//response.sendRedirect("./RoomDetails.rd");


System.out.println("페이지 이동");

session.setAttribute("user_num", "1");
session.setAttribute("user_name", "권혁");
%>

<form action="./RoomDetails.rd" method="get">
	
	<input type="text" name="hotel_no" value="1"><br>
	<input type="text" name="check_in" value="2019-07-02"><br>
	<input type="text" name="check_out" value="2019-07-03"><br>
	<input type="text" name="people_num" value="1"><br>
	<input type="submit" value="전송~★!">
	
</form>


</body>
</html>