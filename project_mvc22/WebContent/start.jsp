<%@page import="java.util.ArrayList"%>
<%@page import="controller.CourseDAO"%>
<%@page import="model.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	
	String visa = request.getParameter("visa");
	String nation = request.getParameter("nation");
	
	CourseDAO dao = new CourseDAO();
	
	if(visa != null){
		ArrayList<Course> dtos = dao.SearchPack(visa,nation);
		request.setAttribute("list",dtos);
	}
	else{
		ArrayList<Course> dtos = dao.AllList();
		request.setAttribute("list",dtos);
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher("/mainpage.jsp?visa="+visa+"&nation="+nation);
	dispatcher.forward(request, response);
%>
</body>
</html>

