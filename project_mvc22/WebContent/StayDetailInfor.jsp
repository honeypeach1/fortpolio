<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<!-- 세부정보 등록 페이지 -->
<title>코스 관리 페이지입니다.</title>
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
   <div class="header">등록된 코스 리스트입니다.</div>
   
   <table cellspacing="9">
      <tr>
      
<!--       css테이블 width 수정되어야함 -->
<!--       	<th width="70">순서</th>
         <th width="280">메인 이미지</th>
         <th width="230">판매자</th>
         <th width="600">비자</th>
         <th width="600">예약일</th>
         <th width="850">판매자 번호</th>
         <th width='250'>인원수</th>
         <th width="350">세부정보</th>
         <th width="350">예약취소</th>
 -->         
         <th width=5%>순서</th>
         <th width=20%>메인 이미지</th>
         <th width=15%>판매자</th>
         <th width=15%>비자</th>
         <th width=15%>예약일</th>
         <th width=10%>판매자 번호</th>
         <th width=10%>인원수</th>
         <th width=10%>세부정보</th>
         <th width=10%>예약취소</th>
      </tr>
<%
 		int count = 1;
%>
 	<c:choose>
	<c:when test="${!empty requestScope.list}">
	<c:forEach var="temp" items="${requestScope.list}">
      <tr>
      	 <td>#<%=+count%></td>
         <td><img src="/project_mvc22/img/${temp.course_image1}"/></td>
         <td>${temp.stay_id}</td>
         <td>${temp.visa}</td>
         <td>${temp.reser_date}</td>
         <td>${temp.stay_tel}</td>
         <td>${temp.peoples}</td>
         
         <form action="/project_mvc22/view/DetailReser" method="get">
         <input type="hidden" name="course_num" value="${temp.course_num}" />
         <input type="hidden" name="reser_num" value="${temp.reser_num}" />
         <td><button class="w3-button w3-block w3-gray w3-round" >예약정보</button></td>
		 </form>
		 <form action="/project_mvc22/view/CancelReser" method="get">
		 <input type="hidden" name="reser_num" value="${temp.reser_num}" />
         <td><button class="w3-button w3-block w3-gray w3-round">예약취소</button></td>
         </form>
      </tr>
	    <%count++;%>
    	</c:forEach>
	</c:when>
	<c:otherwise>아직 등록이 된 코스상품이 없습니다.</c:otherwise>
  </c:choose>
   </table>
</div>
</body>
</html>