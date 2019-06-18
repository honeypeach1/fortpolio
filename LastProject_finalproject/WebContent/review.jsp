<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.roomdetails.db.RoomDetailsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="./css/comment2.css"> -->

<style type="text/css">

div.modalDiv3 > input{
	background-color: #0063b6;
	color: white;
	padding: 5px;
	
	border: 2px solid #002341;
	border-radius: 6px;
	box-shadow: 0 2px #666;
}
div.modalDiv3 > input:ACTIVE{
	box-shadow: 0 0 #666;
	transform: translateY(2px);
}

</style>


</head>
<body>

<!-- 원본: https://codepen.io/cesar_mejiag/pen/wKMBpP -->

<%
 System.out.println("=============review.jsp===============");
 // 현재 호텔에서 60일 이내에 숙박을 완료한 적이 있는지 확인하는 값 (RoomListAction.java 에서 넘어온 값)
 boolean writeReview = (boolean) request.getAttribute("writeReview");

 // 오늘 날짜 받아오기 - 받아올 필요 없음
 /* Date today = (Date)request.getAttribute("today");
 SimpleDateFormat todayFm = (SimpleDateFormat)request.getAttribute("todayFm"); */


 
 //////////////////////////////////////////수정시작
 
 int user_no = -1;
 
 if(session.getAttribute("user_num")!=null){
  user_no = Integer.parseInt(session.getAttribute("user_num").toString());
  System.out.println("#############review.jsp 수정1");
 }

 //////////////////////////////////////////////수정끝

 // user_id 받아오기 *** 임의로 설정한 값 나중에 session값으로 고칠 것
 /* String user_id = (String)request.getAttribute("user_id"); */

 // hotel_no 받아오기
 int hotel_no = (int) request.getAttribute("hotel_no");
 Date check_in = (Date) request.getAttribute("check_in");
 Date check_out = (Date) request.getAttribute("check_out");
 int people_num = (int) request.getAttribute("people_num");
 String user_image = (String)request.getAttribute("user_image");
 
 // 보여지는 리뷰 수
 String reviewNum = (String)request.getAttribute("reviewNum");
%>

<%if(writeReview == true){ %>
<div class="comments-app" ng-app="commentsApp" ng-controller="CommentsController as cmntCtrl" style="width: 100%;">

  <!-- 리뷰 입력 From - 시작 -->
  <div class="comment-form">
  
    <!-- 회원 사진 보여주기 -->
    <div class="comment-avatar">
     <img src="./user_images/<%=user_image %>">
    </div>

    <form method="post" action="./ReviewWriteAction.rd" class="form" name="form" ng-submit="form.$valid && cmntCtrl.addComment()" novalidate>
      
      
      <!-- type="hidden"으로 숨길 정보들 -->
      <!-- <input type="hidden" name="user_id" value="id1"> --><!-- id1은 임의 설정 - 추 후 session값으로 변경할 것 -->
      <input type="hidden" name="hotel_no" value="<%=hotel_no %>">
      <input type="hidden" name="check_in" value="<%=check_in %>">
	  <input type="hidden" name="check_out" value="<%=check_out %>">
	  <input type="hidden" name="people_num" value="<%=people_num %>">
      
      <div class="form-row">
        <textarea
        	name="review_content"
        	class="input"
        	ng-model="cmntCtrl.comment.text"
        	placeholder="리뷰를 입력하세요..."
        	required></textarea>
      </div>
      
	   
      <%-- <div class="form_row star">
      	<jsp:include page="./selectReviewStar.html"/>
      </div> --%>

      <div class="form-row">
        <jsp:include page="./selectReviewStar.html"/>
        <input type="submit" value="리뷰 쓰기">
      </div>
      
    </form>
  </div>
  <!-- 리뷰 입력 From - 끝 -->
<%}else{ %>
	<!-- 현재 호텔에서 60일 이내에 숙박을 완료한 적이 없으면 리뷰입력 부분이 나타나지 않음 -->
<%} %>

  <!-- 리뷰 List 보여주기 - 시작 -->
  <div class="comments">
  
  	<%
 		// 호텔의 리뷰 리스트를 불러오는 List (RoomListAction.java 에서 넘어온 값)
		List reviewList = (List)request.getAttribute("reviewList");
  	%>
  
  	<%
  	if(reviewList.size()!=0){
  		for(int k=0; k<reviewList.size(); k++){
  			RoomDetailsBean rdbReview = (RoomDetailsBean)reviewList.get(k);
  	%>
    <div class="comment" ng-repeat="comment in cmntCtrl.comments | orderBy: '-date'">
      <!-- Comment Avatar -->
      <div class="comment-avatar">
        <img src="./user_images/<%=rdbReview.getUser_image() %>">
      </div>

      <!-- Comment Box -->
      <div class="comment-box">
        <div class="comment-text" style="word-wrap: break-word;"><%=rdbReview.getReview_content() %></div>
        <div class="comment-footer">
          <div class="comment-info">
            <span class="comment-author">
              <em ng-if="comment.anonymous"><%=rdbReview.getUser_id() %></em>
            </span>
            <span class="comment-date">별점 : <%=rdbReview.getReview_star() %></span>
            <span class="comment-date">입력일 : <%=rdbReview.getReview_input_date() %></span>
          </div>
          <div class="comment-actions">
         	<!-- 현재 로그인된 user_id와 해당 리뷰를 쓴 user_id가 일치할 경우 삭제버튼 나타남 -->
          	<%if(user_no == rdbReview.getUser_no()){ %>
            	<a href="#openDltModal<%=k %>" class="deleteTxtBtn">삭제</a>
            <%} %>
          </div>

        </div>
      </div>
    </div>
    <%}
    }%>
    
    <!-- 리뷰 삭제 Modal 창 - 시작 -->
    <%
  	if(reviewList.size()!=0){
  		for(int k=0; k<reviewList.size(); k++){
  			RoomDetailsBean rdbReview = (RoomDetailsBean)reviewList.get(k);
  	%>
  				<div class="white_content" id="openDltModal<%=k %>">
        			<div class="deleteModal">
            			<div class="closeDtlDiv"><a href="#close" class="closeDltTxtBtn">닫기</a></div>
            			
            			<form action="./ReviewDeleteAction.rd" method="post">
            				<input type="hidden" name="review_no" value="<%=rdbReview.getReview_no() %>">
            				<input type="hidden" name="hotel_no" value="<%=hotel_no %>">
           					<input type="hidden" name="check_in" value="<%=check_in %>">
          					<input type="hidden" name="check_out" value="<%=check_out %>">
          					<input type="hidden" name="people_num" value="<%=people_num %>">
            				<%-- <input type="hidden" name="user_id" value="<%=user_id %>"> --%>
            				<div class="modalDiv1" style="margin-top: 10px;"><h2>리뷰를 삭제하시겠습니까?</h2></div>
            				<div class="modalDiv2" style="margin-top: 40px;">
            					<input type="text" name="user_pw" class="modalInput" placeholder="비밀번호를 입력하세요">
            				</div>
            				<div class="modalDiv3" style="margin-top: 10px;">
            					<input type="submit" value="리뷰 삭제">
            				</div>
            			</form>
        			</div>
    			</div>
  	
  	<%
  		}
  	}
  	%>
  	<!-- 리뷰 삭제 Modal 창 - 끝 -->
    
  </div>
  <!-- 리뷰 List 보여주기 - 끝 -->
  
  
  <!-- 리뷰 더보기 버튼 - 시작 -->
  <div id="bottomRv">
    <a href="RoomDetails.rd?hotel_no=<%=hotel_no %>&check_in=<%=check_in %>&check_out=<%=check_out %>&people_num=<%=people_num %>&reviewNum=<%=Integer.parseInt(reviewNum)+5 %>#bottomRv">
    더보기
    </a>
  </div>
  <!-- 리뷰 더보기 버튼 - 끝 -->
</div>

<script type="text/javascript">
	
	/* Modal창이 띄어진 상태에서 새로고침할 경우 페이지를 보이지 않게 함*/
	var url = document.location.href.split("#");
	var idValue = url[1].substring(0,12);

	if(idValue == 'openDltModal'){
		$('html, body').css({'overflow': 'hidden', 'height': '100%'});
	}else{
		$('html, body').css({'overflow': 'visible', 'height': '100%'});
	}


	/* 리뷰에서 삭제 버튼을 클릭하면 페이지를 보이지 않게함 */
	$(document).ready(function(){
		$('.deleteTxtBtn').bind('click', function(){
			$('html, body').css({'overflow': 'hidden', 'height': '100%'});
		});
	});
	
	/* history.back으로 돌아왔을 때 모달창이 생긴 경우 페이지를 보이지 않게함 - 완성 못함 */
	/* var url = document.location.href.split("#");
	   var idValue = url[1].substring(0,12);
	   alert(url[1]);
	   alert(idValue); */
	
	
	/* 리뷰 삭제 모달창에서 닫기 버튼을 클릭하면 페이지를 다시 보이게함 */
	$(document).ready(function(){
		$('.closeDltTxtBtn').bind('click', function(){
			$('html, body').css({'overflow': 'visible', 'height': '100%'});
		});
	});
	
</script>



</body>
</html>