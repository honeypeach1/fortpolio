<%@page import="model.UserProfile"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.UserTable"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="Controller.projectDAO" %>
<%@page import="java.util.Calendar"%>
<%
	//형식(로그인에 담긴 정보)가져올시 인코딩 필!
	request.setCharacterEncoding("UTF-8");

	String NUM = (String)session.getAttribute("USER_NUM");
	
	UserTable usertables = null;
	if(request.getAttribute("loginInfor") != null){
		usertables = (UserTable) request.getAttribute("loginInfor");
	}
	
	UserProfile userprofile = null;
	
	if(request.getAttribute("userInfor") != null){
		userprofile = (UserProfile)request.getAttribute("userInfor");
	}
	
	String chImg = null;

	if(request.getAttribute("chImg") != null){
		chImg = (String)request.getAttribute("chImg");		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Hello? Welcome to our SNS!</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
  <link rel="stylesheet" href="css/style.css">

</head>
<style>
.my-pic {
  top: -70px;
}

.my-skeleton-img {
  background-color: var(--gray);
  width: 60px;
  height: 60px;
  border-radius: 50%
}

.my-skeleton-card {
  animation: my-skeleton 2s infinite;
}

.my-skeleton {
  opacity: 0.25;
}

.my-skeleton-link {
  background-color: var(--primary);
  width: 150px;
  height: 1.25rem;
}

.my-skeleton-at-mark {
  background-color: var(--gray);
  width: 100px;
  height: 1rem;
}

.my-skeleton-content {
  background-color: var(--dark);
  width: 100%;
  height: 200px;
}

@keyframes my-skeleton {
  50% {
    opacity: 0.5;
  }
}
</style>
<body>

<!-- 상단 정보 창 -->
<jsp:include page="/header.jsp" flush="false"/>
<div class="container-fluid mt-3">
  <div class="row">
    <div class="col-md-3 mb-3 order-1">
      <div class="card shadow mb-3">
      <!-- 서브 이미지 가져오기 -->
		<%if(chImg == null){ %>
        <img class="card-img-top" src="https://picsum.photos/800/400?image=1019">
        <%}else{ %>
      <img class="card-img-top" src="/Project_KBJ1/sub_Image/<%=chImg%>" style="height:400;width:800; ">
      <%} %>   
        <div class="card-body">
          <div class="row">
            <div class="col-12" style="width: 100px">
              <img src="/Project_KBJ1/User_Image/<%=usertables.getUser_image() %>" class="rounded-circle position-absolute my-pic" width="70" height="70">
              <h5 class="mb-0"><a href="/Project_KBJ1/view/InforPage" class="text-body"><%=usertables.getUser_name() %></a></h5>
              <h5><a class="text-body"><small class="text-muted">@<%=usertables.getUser_id() %></small></a></h5>
              <%if(userprofile != null){%>
              <h5><a class="text-body"><small class="text-muted">결혼 여부 #<%=userprofile.getPro_marriage() %></small></a></h5>
              <h5><a class="text-body"><small class="text-muted">사는곳 #<%=userprofile.getPro_address() %></small></a></h5>
            <%}else{ %>
              <h5><a class="text-body"><small class="text-muted">결혼 여부 #아직 등록된 정보가 없습니다.</small></a></h5>
              <h5><a class="text-body"><small class="text-muted">사는곳 #아직 등록된 정보가 없습니다.</small></a></h5>
            <%} %>
            </div>

            <div class="col-4 text-center">
            
            <!-- 내 정보 페이지와 친구 정보 페이지 별도 구분 필요 -->
            
            <!-- 친구 추가 기능 -->
            <!-- 조건1 - 자기 자신은 친구 추가 버튼을 볼 수 없다. -->
            <!-- 조건2 - 친구추가가 되었을 경우에는 이 버튼을 볼 수 없다. -->
            <%-- <form action="" method="get" id="frm">
            <!-- 자바스크립트 함수로 확인 취소 alert 띄우기 -->
            <input type="hidden>
              <a href="#" onclick="document.getElementById('frm').submit();" class="text-body" data-toggle="tooltip" data-placement="top" title="Followers">
                <span class="fa-layers fa-2x">
                  <i class="fas fa-user-plus"></i>
                  <span class="fa-layers-counter bg-primary fa-layers-bottom-right">789</span>
                </span>
              </a>
              </form> --%>
            </div>
          </div>
        </div>
      </div>
      <div class="card shadow mb-3">
        <div class="card-body">
        <h5>More Information</h5>
          <%if(userprofile != null){ %>
         <div class="card-body">
          <small class="text-muted">#Elementary School</small>
          <div align="right"><font size="4px" color="blue"><i><%=userprofile.getElement_school_name() %></i></font></div>
        </div>
        
        <div class="card-body">
          <small class="text-muted">#Middle School</small>
		<div align="right"><font size="4px" color="blue"><i><%=userprofile.getMiddle_school_name() %></i></font></div>
        </div>
        
        <div class="card-body">
          <small class="text-muted">#High School</small>
        <div align="right"><font size="4px" color="blue"><i><%=userprofile.getHigh_school_name() %></i></font></div>
        </div>
        
        <div class="card-body">
          <small class="text-muted">#University</small>
        <div align="right"><font size="4px" color="blue"><i><%=userprofile.getUniversity_name() %></i></font></div>
        </div>
        
        <hr>
        
          <h5>#Jobs</h5>
        <p><font style="size: 10px"><i><%=userprofile.getJob_name() %></i></font><small class="text-muted">에서 재직중....</small></p>  
        <br>
        <%}else{ %>
        <font style="size: 6px; color: blue"><i>아직 등록된 세부정보가 없습니다.<br><br>profile에서 등록하세요!</i></font>
        <%} %>
        </div>
      </div>
    </div>
    <div class="col-md-6 order-3 order-md-2">
          <br><h5><i style="font-size: 40px"><%out.print(request.getParameter("search")); %>의 검색 결과....</i></h5>
          <br>
  
  
  <!-- 검색했을때 가져오는 리스트항목 -->
  <div>
  <c:choose>
	<c:when test="${!empty searchList}">
	<c:forEach items="${searchList}" var="dto">
      <div class="card shadow mb-3">
        <div class="card-body">
			<p><img src="/Project_KBJ1/User_Image/${dto.getUser_image()}" class="float-left mr-2" style="border-radius: 7px" width="40" height="60">
            <font size="4px"; color="blue">${dto.getUser_name()}</font> <small class="text-muted">@${dto.getUser_id()}</small>&nbsp;<small class="text-muted"><b><i>${dto.getUser_email()}</i></b></small>
            <small class="text-muted">#Tel)<b><font size="3px">${dto.getUser_tel()}</font></b></small>&nbsp;<small class="text-muted"><br>#nation<b><font size="3px">${dto.getNation_name()}</font></b></small>
            <small class="text-muted">#Birthday <b><font size="3px">${dto.getBorn_date()}</font></b></small>
            <small class="text-muted">#gender <b><font size="3px"><i>${dto.getUser_gender()}</i></font></b></small></p>
          <hr>
          <div style="align-content: right">
          
          <!-- 친구 신청 하기 -->
          <form action="/Project_KBJ1/view/AddFriends" method="get" onclick="javascript:window.alert('친구 신청 하였습니다.');">
          	<button class="btn btn-primary float-right">Friend!</button>
          	<!-- 친구 신청하는 유저 넘버 넘기기 -->
          	<input type="hidden" name="friends_num" value="${dto.getUser_num()}" >
          </form>
          
          <!-- 친구 상세 정보 보기 -->
          <form action="/Project_KBJ1/view/getFriendInfor" method="get">
          	<button class="btn btn-primary float-right">Detail</button>
          	<!-- 친구 넘버 값-->
          	<input type="hidden" name="friends_num" value="${dto.getUser_num()}" >
          </form>
          
          </div> 
        </div>
      </div>
</c:forEach>
	</c:when>
	<c:otherwise>검색 결과가 존재하지 않습니다.!</c:otherwise>
  </c:choose>
	</div>
    </div>
    <div class="col-md-3 mb-3 order-2 order-md-3">
      <div class="card shadow mb-3">
        <div class="card-body">
          <h5>My friend list</h5>
          <!-- 친구 리스트 -->
          <c:choose>
		<c:when test="${!empty friendList}">
		<c:forEach items="${friendList}" var="dto">
          
          <p><img src="/Project_KBJ1/User_Image/${dto.getUser_image()}" class="float-left mr-2" width="40" height="40">
            <a href="#">${dto.getUser_name()} <small class="text-muted">@ ${dto.getUser_id()}</small></a></p>
          <hr>
          
          </c:forEach>
		</c:when>
		<c:otherwise><small class="text-muted">친구 목록에 친구가 없습니다. 친구 추가하세요!</small></c:otherwise>
  		</c:choose>
          <!-- 루프 -->
          
        </div>
        <div class="card-footer">
          <a href="#">친구 관리</a>
        </div>
      </div>
      <div class="card shadow">
        <div class="card-body">
        <%
        Calendar cal = Calendar.getInstance();

    	int Year = cal.get(Calendar.YEAR); //년
    	int Month = cal.get(Calendar.MONTH); //월

    	int Date = cal.get(Calendar.DATE); //일
    	int Day = cal.get(Calendar.DAY_OF_WEEK); //요일
        %>
          - <%=Year %>년 <%=Month %>월 <%=Date %>일 -
        </div>
        <div class="card-footer">
          Languages <br><a href="#">한글 (KOR)</a>/<a href="#">English (US)</a>
        </div>
      </div>
    </div>
  </div>
</div>
  <script src='https://code.jquery.com/jquery-3.3.1.slim.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js'></script>
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js'></script>
<script src='https://use.fontawesome.com/releases/v5.4.1/js/all.js'></script>

  

    <script  src="js/index.js"></script>

</body>

	<!-- footer 페이지 -->
	<jsp:include page="/footer.jsp" flush="false"/>
</html>
