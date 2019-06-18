<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.CourseDAO"%>
<%@page import="model.Category"%>
<%@page import="model.Course"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
<%

	String course_num = request.getParameter("course_num");

%>
</script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>해당 상품에 등록된 추가 정보를 불러옵니다.</title>
<title>여행자의 예약상품 페이지입니다.</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="/project_mvc22/reservation/css/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png" href="/project_mvc22/create_acc/images/icons/favicon.ico"/>
<body>
 <div class="table-users">
   <div class="header">추가 정보 리스트입니다.</div>
   
   <table cellspacing="6">
      <tr>
         <th width=10%>순서</th>
         <th width=20%>세부 정보 종류</th>
         <th width=30%>세부 정보 이름</th>
         <th width=15%>세부 정보</th>
         <th width=10%>세부정보 삭제</th>
      </tr>
<%
 		int count = 1;
%>
 	<c:choose>
	<c:when test="${!empty requestScope.addlists}">
	<c:forEach var="temp" items="${requestScope.addlists}">
      <tr>
      	 <td>#<%=+count%></td>
         <td>${temp.category_kind}</td>
         <td>${temp.category_name}</td>
         <td>${temp.category_comments}</td>
         
         <td>
         <form action="/project_mvc22/CorrectInfor.jsp" method="get">
		 <input type="hidden" name="category_num" value="${temp.category_num}" />
         <button class="w3-button w3-block w3-gray w3-round" style="margin-bottom:10px">추가정보 수정</button>
         </form>
		 <form action="/project_mvc22/view/deleteInfor" method="get">
		 <input type="hidden" name="category_num" value="${temp.category_num}" />
         <button class="w3-button w3-block w3-gray w3-round">추가정보 삭제</button>
         </form>
         </td>
      </tr>
      
	    <%count++;%>
    	</c:forEach>
	</c:when>
	<c:otherwise>해당 코스 상품에 추가 정보가 없습니다.</c:otherwise>
  </c:choose>
  <tr>

  </tr>

   </table>
  <form action="/project_mvc22/addInfor.jsp" method="get">
  <input type="hidden" name="course_num" value=<%=course_num %> />
  <button class="w3-button w3-block w3-gray w3-round">추가정보 등록하기</button>
  </form>
</div>
</body>
</html>