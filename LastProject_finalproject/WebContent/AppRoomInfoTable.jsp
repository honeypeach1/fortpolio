<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<br>
	  <div class="div-table">
	  
	  <table>
	    <!-- 1층 -->
	    <tr class="floor-one">
	      <td colspan="4" class="floor-one">객실 정보</td>
	    </tr>
	
	  	<!-- 2층 -->
	  	<tr class="floor-three">
	  		<td style="width: 350px">객실유형</td>
	  		<td style="width: 250px">메인 옵션</td>
	  		<td>추가 옵션</td>
	  	</tr>
	  	
	  	<!-- DB에서 객실 리스트 가져오는 로직 -->
	  	<c:choose>
	  	<c:when test="${!empty AppAdminRoom}">
		<c:forEach items="${AppAdminRoom}" var="AppRoom">  
	  	<!-- 룸 정보 가져오기 -->
	  	<!-- 4층 부터 : 룸 정보를 보여줌  -->
	  	<tr class="room-info room1">
	  		<!-- 객실 유형 : 호텔 방 이미지 + 클릭하면 더 많은 사진을 볼 수 있도록 함 -->
	  		<td class="show-room-image">
	  		<c:choose>
	  		<c:when test="${!empty AppRoomImages}">
	  		<c:forEach items="${AppRoomImages}" var="AppImage">
	  		<c:if test="${AppRoom.getRoom_type_no() eq AppImage.getRoom_type_no()}">
	  			<div><img alt="" src="./images/${AppImage.getRoom_pic()}" style="width: 150px;height:150px"></div>
	  		</c:if>
	  		</c:forEach>
	  		</c:when>
	  		</c:choose>	
	  			<div class="room-name">${AppRoom.getRoom_type()}</div>
	  			<div>일반 방 가격 : ${AppRoom.getRoom_price()}원</div>
	  		</td>
	  		
	  		<!-- 옵션 -->
	  		<td class="show-room-option" style="width: 270px">
	  			<div>
	  			  <div><font style="font-size: 15px;color: gray;">${AppRoom.getRoom_info()}</font></div>
	  			  <br>
	  			  <div><font style="font-size: 15px; color: black;">기본 인원수 : ${AppRoom.getRoom_basic_people()}</font></div>
	  			  <br>
	  			  <div><font style="font-size: 15px; color: black;">최대 인원수 : ${AppRoom.getRoom_max_people()}</font></div>
	  			</div>
	  		</td>
	  		
	  		<!-- 객실당 1박 요금 -->
	  		<td class="show-room-rate">
	  			<div>
	  				<div class="room-remained">총 객실 ${AppRoom.getCount()}개</div>
	  				<br>
	  				<c:choose>
	  				<c:when test="${!empty AppRoomOption}">
	  				<c:forEach items="${AppRoomOption}" var="RoomOp">
	  				<c:if test="${AppRoom.getRoom_type_no() eq RoomOp.getRoom_type_no()}"> 
	  				<table style="border: 1px;"><tr>
	  				<td><font style="font-size: 15px">추가옵션 명 : ${RoomOp.getRoom_option_name()}</font></td>
	  				<td><font style="font-size: 15px">추가옵션 가격 : ${RoomOp.getOption_add_price()}원</font></td>
	  				</tr></table>
	  				</c:if>
	  				</c:forEach>
	  				</c:when>
	  				<c:otherwise><div>추가 정보가 없습니다.</div></c:otherwise>
	  				</c:choose>
	  			</div>
	  		</td>
	  	</tr>
		</c:forEach>
	  	</c:when>
	  	<c:otherwise><tr><td colspan="3" align="center"><font><h2>등록된 방 정보가 없습니다.</h1></font></td></tr></c:otherwise>
	  	</c:choose>
	  	
	  </table>
	  </div> 

</body>
</html>