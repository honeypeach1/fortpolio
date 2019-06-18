<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.LeavePeople"%>
    <%
    request.setCharacterEncoding("UTF-8");
    String leave_id = null;
    String leave_name = null;
    String leave_grade = null;

    String stay_id = null;
    String stay_name = null;
    String stay_grade = null;

    String admin_grade = null;

    if (session.getAttribute("STAY_ID") != null)
    {
    	 stay_id = session.getAttribute("STAY_ID").toString();
    	 stay_name = session.getAttribute("STAY_NAME").toString();
    	 stay_grade = session.getAttribute("GRADE").toString();
    }
    else if (session.getAttribute("LEAVE_ID") != null)
    {
    	 leave_id = session.getAttribute("LEAVE_ID").toString();
    	 leave_name = session.getAttribute("LEAVE_NAME").toString();
    	 leave_grade = session.getAttribute("GRADE").toString();
    }
    else if(session.getAttribute("ADMIN_ID") != null)
    {
         admin_grade = session.getAttribute("GRADE").toString();
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>milestone</title>
</head>
	<script>
	function logout(){
		alert("로그아웃 되었습니다.");
	}
	
    function openPop() { 
        window.open("/project_mvc22/view/checkreser", "test", "top=0, left=0, width=1200, height=900, scrollbars=yes, resizable=no ,status=no ,toolbar=no"); 
        
    }
    </script>
<body>
<%
	if(leave_grade == null && stay_grade == null && admin_grade == null){
	%>
<div class="w3-bar w3-white w3-large">
  <a href="/project_mvc22/mainpage.jsp" class="w3-bar-item w3-button w3-black w3-mobile"><i class="fa fa-bed w3-margin-right"></i>MILESTONE</a>
  <a href="/project_mvc22/choose/choose.jsp" class="w3-bar-item w3-button w3-mobile">로그인</a>
  <a href="create_acc/CreateLeave.jsp" class="w3-bar-item w3-button w3-mobile">회원가입</a>
  <a href="/project_mvc22/contact.jsp" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Contact</a>
</div>
	<!-- 비로그인시 -->
	<%}
	//leavepeople 일경우
	else if(leave_grade != null && stay_grade == null && admin_grade == null){
	%>	
			<div class="w3-bar w3-white w3-large" style="height:43px;">
  <a href="/project_mvc22/mainpage.jsp" class="w3-bar-item w3-button w3-red w3-mobile"><i class="fa fa-bed w3-margin-right"></i>MILESTONE</a>
  <a href="/project_mvc22/view/LeaveMypage" class="w3-bar-item w3-button w3-mobile">MyPage</a>
  <form action="javascript:openPop()" method="get">
  <button class="w3-bar-item w3-button w3-mobile" type="submit">Reservation</button>
  </form>
  <a href="/project_mvc22/out.jsp" onclick="logout()" class="w3-bar-item w3-button w3-mobile">Logout</a>
  <a class="w3-bar-item w3-mobile"><%= leave_name %>님 반갑습니다.</a>
  <a href="/project_mvc22/contact.jsp" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Contact</a>
	</div>	
	<!-- 출국예정자 로그인시 -->
	<%}
		//staypeople 일경우
		else if(leave_grade == null && stay_grade != null && admin_grade == null){
	%>
			<div class="w3-bar w3-white w3-large">
  <a href="/project_mvc22/mainpage.jsp" class="w3-bar-item w3-button w3-blue w3-mobile"><i class="fa fa-bed w3-margin-right"></i>MILESTONE</a>
  <a href="/project_mvc22/view/StayMypage" class="w3-bar-item w3-button w3-mobile">MyPage</a>
  <a href="/project_mvc22/out.jsp" onclick="logout()" class="w3-bar-item w3-button w3-mobile">Logout</a>
  <a class="w3-bar-item w3-mobile"><%= stay_name %>님 반갑습니다.</a>
  <a href="/project_mvc22/contact.jsp" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Contact</a>
	</div>
			<!-- 현지거주자 로그인시 -->
	<% 
		}
		//관리자
		else if(leave_grade == null && stay_grade == null && admin_grade != null){
	%>		
			<div class="w3-bar w3-white w3-large">
  <a href="/project_mvc22/mainpage.jsp" class="w3-bar-item w3-button w3-red w3-mobile"><i class="fa fa-bed w3-margin-right"></i>MILESTONE</a>
  <a href="mypage1.jsp" class="w3-bar-item w3-button w3-mobile">회원관리</a>
  <a href="/project_mvc22/out.jsp" onclick="logout()" class="w3-bar-item w3-button w3-mobile">Logout</a>
  <a class="w3-bar-item w3-mobile">관리자님 반갑습니다.</a>
  <a href="/project_mvc22/contact.jsp" class="w3-bar-item w3-button w3-right w3-light-grey w3-mobile">Contact</a>
	</div>
			<!-- 관리자 로그인시 -->
	<% 			
		}
	%>
	
</body>
</html>