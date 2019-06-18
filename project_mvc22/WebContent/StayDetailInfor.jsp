<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<!-- �������� ��� ������ -->
<title>�ڽ� ���� �������Դϴ�.</title>
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
   <div class="header">��ϵ� �ڽ� ����Ʈ�Դϴ�.</div>
   
   <table cellspacing="9">
      <tr>
      
<!--       css���̺� width �����Ǿ���� -->
<!--       	<th width="70">����</th>
         <th width="280">���� �̹���</th>
         <th width="230">�Ǹ���</th>
         <th width="600">����</th>
         <th width="600">������</th>
         <th width="850">�Ǹ��� ��ȣ</th>
         <th width='250'>�ο���</th>
         <th width="350">��������</th>
         <th width="350">�������</th>
 -->         
         <th width=5%>����</th>
         <th width=20%>���� �̹���</th>
         <th width=15%>�Ǹ���</th>
         <th width=15%>����</th>
         <th width=15%>������</th>
         <th width=10%>�Ǹ��� ��ȣ</th>
         <th width=10%>�ο���</th>
         <th width=10%>��������</th>
         <th width=10%>�������</th>
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
         <td><button class="w3-button w3-block w3-gray w3-round" >��������</button></td>
		 </form>
		 <form action="/project_mvc22/view/CancelReser" method="get">
		 <input type="hidden" name="reser_num" value="${temp.reser_num}" />
         <td><button class="w3-button w3-block w3-gray w3-round">�������</button></td>
         </form>
      </tr>
	    <%count++;%>
    	</c:forEach>
	</c:when>
	<c:otherwise>���� ����� �� �ڽ���ǰ�� �����ϴ�.</c:otherwise>
  </c:choose>
   </table>
</div>
</body>
</html>