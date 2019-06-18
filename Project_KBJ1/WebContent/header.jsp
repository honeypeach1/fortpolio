<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.FriendApproval"%>
<%
request.setCharacterEncoding("UTF-8");
String user_id = null;
String user_name = null;
String user_image = null;

if (session.getAttribute("USER_ID") != null)
{
    user_id = session.getAttribute("USER_ID").toString();
    user_name = session.getAttribute("USER_NAME").toString();
    user_image = session.getAttribute("USER_IMAGE").toString();
}

String countFriend = null;
if(request.getAttribute("countFriend") != null){
	countFriend = request.getAttribute("countFriend").toString();
	System.out.println("신청 들어온 총 친구수 = " + countFriend);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
	function searchChk() {
		if (document.chk.search.value == false) {
			alert("공란으로 검색 하실 수 없습니다.");
			return false;
		}
	}
	
</script>
<body>
<nav class="navbar navbar-expand navbar-light bg-light shadow">
  <ul class="navbar-nav mr-auto">
    <li class="nav-item">
    <form action="/Project_KBJ1/view/InforPage" method="post" id="frm">
      <a class="nav-link active" href="#" onclick="document.getElementById('frm').submit();"><i class="fas fa-home fa-2x" style="color:#F78536"></i></a>
    </form>
    </li>
    
  
    <li class="nav-item dropdown">
       <a class="nav-link" href="#" data-toggle="dropdown">
        <span class="fa-layers fa-fw fa-2x">
          <i class="fas fa-bell" style="color:#F78536"></i>
          <%if(countFriend != null) {%>
          <span class="fa-layers-counter bg-danger fa-layers-bottom-right"><%=countFriend%></span>
          <%} %>
        </span>
      </a>
 
       
      <!-- 만들어야 할 친구 신청 리스트 -->
      <div class="dropdown-menu dropdown-menu-left" style="padding:1px; overflow-y:scroll; width:360px;height: 300px">
      <a class="dropdown-item" href="#" onclick="document.getElementById('frm').submit();">
          <i class="fas fa-user"></i> 친구 신청 목록
        </a>
      <c:choose>
		<c:when test="${!empty getFriend}">
		<c:forEach items="${getFriend}" var="dto">
		
		<table>
		<tr style="height: 55px">
		<td width="70%">
        <a class="dropdown-item" style="width: 200px">
        <img src="/Project_KBJ1/User_Image/${dto.getUser_image()}" class="float-left mr-2" width="30" height="30">
		<small class="text-muted"><font size=4px" color="#8C8C8C"><b>${dto.getUser_name()}</b></font>님이 친구 신청</small>
		</a>
		</td>
		<td style="width: 50px">
      <form action="/Project_KBJ1/view/Approval" method="post">
		<input class="btn btn-primary" type="submit" value="승인" onclick="javascript:window.alert('친구 신청을 승인 하였습니다.');">
      	<input type="hidden" name="friend_num" value="${dto.getFri_app_num()}">	<!-- 로그인 기준으로 app_num는 나에게 친추를 건 친구 넘버 -->
      </form> 
      </td>
      <td>
      <form action="/Project_KBJ1/view/Deny" method="post">
        <input class="btn btn-primary" type="submit" value="거절" onclick="javascript:window.alert('친구 승인을 거절 하였습니다.');">
        <input type="hidden" name="friend_num" value="${dto.getFri_app_num()}">	<!-- 로그인 기준으로 app_num는 나에게 친추를 건 친구 넘버 -->
      </form> 
      </td>
      </tr> 
        </table>
        
        
          

        </c:forEach>
		</c:when>
		<c:otherwise><small class="text-muted">친구 신청이 들어온 목록이 없습니다.</small></c:otherwise>
  		</c:choose>
      </div>
      
    </li>
     
    <li class="nav-item">
    <!-- 새로운 알람 띄우기 미구현 -->
      <!-- <a class="nav-link" href="#">
        <span class="fa-layers fa-fw fa-2x">
          <i class="far fa-envelope" style="color:#F78536"></i>
          <span class="fa-layers-counter bg-danger fa-layers-bottom-right">456</span>
        </span>
      </a> -->
    </li>
  </ul>
  <form class="form-inline ml-auto" action="/Project_KBJ1/view/searchInfor" method="get" name="chk" onsubmit="return searchChk()">
    <div class="input-group" style="width:300px">
    <!-- 검색창 -->
      <input type="text" class="form-control" placeholder="Search" aria-label="Search" aria-describedby="search" name="search">
      <div class="input-group-append">
        <input type="submit" value="search!" class="btn btn-primary">
      </div>
    </div>
  </form>
  <ul class="navbar-nav">
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="profile_dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="fa-stack fa-1x">
          <i class="fas fa-circle fa-stack-2x"></i>
          <i class="fas fa-user fa-stack-1x fa-inverse"></i>
        </span>
      </a>
      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="profile_dropdown">
      <form action="/Project_KBJ1/view/InforPage" method="post" id="frm">
        <a class="dropdown-item" href="#" onclick="document.getElementById('frm').submit();">
          <i class="fas fa-home"></i> Home
        </a>
        </form>
        <form action="/Project_KBJ1/view/MyInfor" method="post" id="frms">
        <a class="dropdown-item" href="#" onclick="document.getElementById('frms').submit();">
          <i class="fas fa-user"></i> Profile
        </a>
        </form>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="/Project_KBJ1/logout.jsp">
          <i class="fas fa-sign-out-alt"></i> Log out
        </a>
      </div>
    </li>
  </ul>
</nav>
</body>
</html>