<%@page import="java.util.ArrayList"%>
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
	String Friend_num = request.getParameter("friends_num");

	UserTable usertables = null;
	if(request.getAttribute("loginInfor") != null){
		usertables = (UserTable) request.getAttribute("loginInfor");
	}
	
	UserProfile userprofile = null;
	
	if(request.getAttribute("userInfors") != null){
		userprofile = (UserProfile)request.getAttribute("userInfors");
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
<script type="text/javascript">
	function UploadLine(){
		if(document.fr.condition[0].selected == true){
			alert("현재 기분을 선택하세요!");
			return false;
		}
		else if(document.fr.comments.value == false){
			alert("공란으로 등록할수 없습니다.");
			return false;
		}
	}
</script>

<!-- 번역 API 가져오기 -->
<script src="//translate.google.com/translate_a/element.js?cb=googleSectionalElementInit&ug=section&hl=ko"></script>
<script type="text/javascript">

	function googleSectionalElementInit() {
		new google.translate.SectionalElement({
			sectionalNodeClassName : 'goog-trans-section',
			controlNodeClassName : 'goog-trans-control',
			background : '#78E7FF'
		}, 'google_sectional_element');
	}

</script>
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
              <h5 class="mb-0"><a href="/Project_KBJ1/view/getFriendInfor?friend_num=<%=usertables.getUser_num() %>" class="text-body"><%=usertables.getUser_name() %></a></h5>
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
            
            <!-- 친구 신청 하기 -->
            <form action="/Project_KBJ1/view/AddFriends" method="get" id="friend">
              <a href="javascript:window.alert('친구 신청을 보냈습니다.');"
              onclick="document.getElementById('friend').submit();"
				 class="text-body" data-toggle="tooltip" data-placement="top" title="Add Friend!">
                <span class="fa-layers fa-2x">
                  <i class="fas fa-user-friends"></i> 
                </span>
              </a>
              <input type="hidden" name="friends_num" value="<%=Friend_num%>">
              </form>
              <%%>
            </div>
            <div class="col-4 text-center">
 
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
        <%}else{%>
        <font style="size: 6px; color: blue"><i>아직 등록된 세부정보가 없습니다.<br></i></font>
        <%} %>
        </div>
      </div>
    </div>
      <div class="col-md-6 order-3 order-md-2">
      <div class="card shadow mb-3">
					<div class="card-body">
					<!-- 등록 버튼 -->
						<form action="/Project_KBJ1/view/registTimeLineFri" method="post" enctype="multipart/form-data" name="fr" onsubmit="return UploadLine()">
							<div class="form-group">
							
							<!-- 텍스트 구역 -->
								<textarea class="form-control" id="txt_post" rows="3" name="comments"
									placeholder="what are you doing? write your condition!!"></textarea>
							</div>
							
							<!-- 이미지 업로드 구역 -->
								<!-- <button class="btn btn-primary">
									<i class="fas fa-image" data-toggle="tooltip"
										data-placement="top" title="Upload an image"></i>
								</button> -->
								
								<label class="btn btn-primary">
								  <i class="fa fa-image"></i>Image<input type="file"
								  style="display: none;" name="timeLineImage">
								</label>
								
								<!-- condition 셀렉트 -->
								<select class="btn btn-primary" name ="condition" style="width:150px">
									<option value="null" selected="selected">Feelings</option>
									<option value=1>혼란</option>
									<option value=2>기쁨</option>
									<option value=3>화남</option>
									<option value=4>아픔</option>
									<option value=5>무념무상</option>
									<option value=6>슬픔</option>
								</select> 
								
								<!-- 파일 전송 구역 자동 submit -->
								<button class="btn btn-primary float-right">
									<i class="fas fa-comment"></i> Post
								</button>
								<input type="hidden" name="friend_num" value=<%=usertables.getUser_num() %>>
						</form>
					</div>
				</div>     
      
    <!-- 글쓴이 상태 창 등록후에 나타날 박스 --> 
    <c:choose>
	<c:when test="${!empty timeLine}">
	<c:forEach items="${timeLine}" var="dtos">  
      <div class="card shadow mb-3">
        <div class="card-body">
          <div class="d-flex">
            <div class="flex-shrink-1">
            <!-- "https://picsum.photos/200/200?random" 랜덤 이미지 추출 -->
              <img src="/Project_KBJ1/User_Image/${dtos.getUser_image()}" class="float-left mr-2" style="border-radius: 10px" width="90">
            </div>

            <div class="flex-grow-1">
              <h5>@ID ${dtos.getUser_id()}<small class="text-muted"> 글쓴 시간 : ${dtos.getTime_line_time()}</small> /
              <small class="text-muted">${dtos.getCondition_name()}</small></h5>
              
              
              <% //TODO 이미지 사이즈를 유동적으로 변경하고 싶은데 이거는 나중에 연구해기.. %>
             
             <!-- 이미지 없으면 아예 안나오게끔! -->
             <c:if test="${!empty dtos.getTime_line_image()}">
              <p><img src="/Project_KBJ1/images/${dtos.getTime_line_image()}" style="max-width:450px;"></p>
              </c:if>
                 
              
			<div class="goog-trans-section">
			<div class="goog-trans">
			<div class="goog-trans-control"></div></div>
              <p>${dtos.getTime_line_comments()}</p>
              </div>
            </div>  
            </div>     
        </div>
        	
				<c:if test="${dtos.getUser_num() eq sessionScope.USER_NUM}">			
			<form action="/Project_KBJ1/view/deleteLineFri" method="get">
				<button class="btn btn-link float-right"><i class="fas fa-times"></i> Discard</button>
				<!-- 글삭제시 가져갈 타임라인 넘버 -->
				<input type="hidden" name="time_line_num" value="${dtos.getTime_line_num()}" />
				<input type="hidden" name="friend_num" value=<%=usertables.getUser_num() %>>
			</form>
			</c:if>
			<hr>
			
			<table>
			<tr>
			<td style="width: 90%">
			<!-- /TODO 댓글 폼 -->
            <form action="/Project_KBJ1/view/commentsFri" method="get" style="float: left; width: 100%">
            <img style="border-radius:3px; margin:0 0 7px 7px; width:50px;height:50px" src="/Project_KBJ1/User_Image/Image_notfound.png">
            <input style="width:80%" type="text" placeholder="댓글을 입력하세요." name="user_comments">	<!-- 댓글 값 -->
            <input type="hidden" name="time_line_num" value="${dtos.getTime_line_num()}">			<!-- 해당 타임 라인 넘버 -->
            <input type="hidden" name="friend_num" value=<%=usertables.getUser_num() %>> 
            <button class="btn btn-link float-right">ADD</button>
            </form>
            </td>
            <td style="float: right; width: 25%; margin-right: 10px">
				<font class="btn btn-link float-right" color="blue">DEL</font>
			</td>
			</tr>
			</table>			
			
		
		<!-- 댓글 구현 -->
		<c:choose>
		<c:when test="${!empty Comment}">
		<c:forEach items="${Comment}" var="comm"> 
		<c:if test="${dtos.getTime_line_num() eq comm.getTime_line_num()}"> 
		<hr>
		<table>
			<tr>
			<td style="width: 8%">
			<img style="border-radius:3px; margin:0 0 7px 7px; width:50px;height:50px" src="/Project_KBJ1/User_Image/${comm.getUser_image()}">
            </td>
            <td style="width: 70%">
            <div style="width: 480px"><font color="blue"><b>${comm.getUser_name()}</b></font>&nbsp; ${comm.getComments()}</div>	<!-- 댓글 값 -->
            </td>
            <td style="width: 25%; margin: 0"><font color="gray" size="2px">글쓴 시간 <br>${comm.getComment_time()}</font></td>
            <td style="float: right; width: 15%; margin-right: 10px">
            
            <!-- 댓글삭제 하기 form -->
            <!-- 자기가 쓴 댓글만 지울수 있게 구현하기 -->
            <!-- 댓글쓴 유저 번호와 현재 세션 비교하기 -->
            <c:if test="${comm.getUser_num() eq sessionScope.USER_NUM}">
            <form action="/Project_KBJ1/view/DeleteCommFri" method="get">
				<button class="btn btn-link float-right">DEL</button>
				<input type="hidden" name="comment_num" value="${comm.getComment_num()}">
				<input type="hidden" name="user_num" value="${comm.getUser_num()}" />
				<input type="hidden" name="friend_num" value=<%=usertables.getUser_num() %>>
			</form>
			</c:if>
			</td>
			</tr>
			</table>
			</c:if>
        </c:forEach>
		</c:when>
  		</c:choose>
      </div>
      
        </c:forEach>
		</c:when>
		<c:otherwise><i style="font-size: 30px">작성한 타임라인이 없습니다...</i></c:otherwise>
  		</c:choose>
        <!-- 루프 돌릴곳 -->

    </div>
    <div class="col-md-3 mb-3 order-2 order-md-3">
      <div class="card shadow mb-3">
        <div class="card-body">
          <h5>My friend list</h5>
          <!-- 친구 리스트 -->
         
          <c:choose>
		<c:when test="${!empty friendList}">
		<c:forEach items="${friendList}" var="dto">
		<table>
		<tr><td width="80%">
            <p><img src="/Project_KBJ1/User_Image/${dto.getUser_image()}" class="float-left mr-2" width="40" height="40">
            ${dto.getUser_name()}		
            <a href="/Project_KBJ1/view/getFriendInfor?friends_num=${dto.getUser_num()}"><small class="text-muted">@ ${dto.getUser_id()}</small></a> 
          </p>
        </td><td width="5%">
          <form action="/Project_KBJ1/view/delFriend" method="post">
			<input class="btn btn-primary float-right" type="submit" value="DEL"/>         
			<input type="hidden" name="friend_num" value="${dto.getUser_num()}">          
          </form></td>
       </tr>
       </table>
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
    	int Month = cal.get(Calendar.MONTH) + 1; //월

    	int Date = cal.get(Calendar.DATE); //일
    	int Day = cal.get(Calendar.DAY_OF_WEEK); //요일
        %>
          - <%=Year %>년 <%=Month %>월 <%=Date %>일 -
        </div>
        <div class="card-footer">
        	 <font color="blue">번역 버튼을 클릭시 한국어로 번역 됩니다.</font> <br><font color="gray">한글 (KOR)</font> -> <font color="gray">English (US)</font>
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
