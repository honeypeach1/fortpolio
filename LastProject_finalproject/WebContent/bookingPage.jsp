<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="net.bookingpage.db.BookingPageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bookingPage.css">
<script src="./JQuery/jquery-3.4.0.min.js"></script>

</head>
<body>


	<!-- 헤더 영역  시작-->
	<jsp:include page="./bookingPageHeader.jsp" />
	<!-- 헤더 영역 끝 -->

	<!-- 바보야 메롱메롱메롱 안줄거얌 -->
	<div class="powerSpace">milestone</div>

	<%
		BookingPageBean bpb = (BookingPageBean) request.getAttribute("bpb");
		BookingPageBean bpb_hotel = (BookingPageBean) request.getAttribute("bpb_hotel");
		List getRoomOptionList = (ArrayList) request.getAttribute("getRoomOptionList");

		// bookingAction에서 값 받아오기
		int room_type_no = (int) request.getAttribute("room_type_no");
		int hotel_no = (int) request.getAttribute("hotel_no");
		Date check_in = (Date) request.getAttribute("check_in");
		Date check_out = (Date) request.getAttribute("check_out");
		int people_num = (int) request.getAttribute("people_num");
		/* int user_no = (int)request.getAttribute("user_no"); */

		int user_no = Integer.parseInt(session.getAttribute("user_num").toString());
	%>


	<!-- wrap1 - 시작 -->
	<div class="bp-wrap wrap1">

		<h2>예약자 정보 확인</h2>

		<div class="divFlex">

			<div class="bp-block name fstName">
				<span class="bp-spanBlock"> <span class="bp-font20">이름</span>
					<span class="bp-font16">한글으로만 입력해 주세요.</span>
				</span> <input type="text" name="booker_name" placeholder="(예: 홍길동)"
					value="<%=bpb.getUser_name()%>">
			</div>

			<!-- <div class="bp-block name lstName">
    <span class="bp-spanBlock">
      <span class="bp-font20">이름</span>
      <span class="bp-font16">영문으로만 입력해 주세요.</span>
    </span>
    <input type="text" placeholder="(예: Seonghwan)" value="">
  </div> -->

		</div>


		<div class="bp-block">
			<span class="bp-spanBlock"> <span class="bp-font20">전화번호</span>
				<span class="bp-font16">숙박 시설에서 고객님께 연락을 드릴 수 있습니다.</span> <input
				type="text" name="booker_phone" value="<%=bpb.getUser_phone()%>">
			</span>
		</div>
		<div class="bp-block">
			<span class="bp-font20">이메일 주소</span><span>이 주소로 확인 메일을
				보내드립니다.</span> <input type="text" name="booker_email"
				value="<%=bpb.getUser_email()%>">
		</div>
		<div>가입하기 위의 이메일 주소로 회원가입하시려면 비밀번호를 입력해 주세요 비밀번호 비밀번호 확인</div>

	</div>
	<!-- wrap1 - 끝 -->




	<!-- wrap2 - 시작 -->
	<!-- <div class="bp-wrap wrap2">
 
 <h2>지불 세부 정보</h2>
 <div class="bp-block">
   <span class="bp-spanBlock">
     <span class="bp-font20">카드 소유자 이름</span>
   </span>
     <input type="text">
   
 </div>
 
 <div class="bp-block">
   <span class="bp-spanBlock">
     <span class="bp-font20">카드번호</span>
   </span>
   <input type="text" placeholder="0000 0000 0000 0000">
 </div>
 
 <div class="bp-block expirationDate">
   <span class="bp-spanBlock">
     <span class="bp-font20">유효기간</span>
   </span>
   <div class="divFlex">
     <input type="text" maxlength="2">
     <span class="slash">/</span>
     <input type="text" maxlength="2">
   </div>
 </div>
 
 <div class="bp-block cvcNum">
   <span class="bp-spanBlock">
     <span class="bp-font20">신용카드 CVC 번호</span>
   </span>
   <input type="text" maxlength="3">
 </div>
 
</div> -->
	<!-- wrap2 - 끝 -->




	<!-- wrap3 - 시작 -->
	<div class="bp-wrap wrap3">
		<h2>객실 및 가격 세부 정보</h2>
		<div class="bp-section">
			<br>
			<div class="imgCropping">
				<img src="./hotelImage/<%=bpb_hotel.getHotel_main_image()%>">
			</div>

			<h3><%=bpb_hotel.getHotel_name()%></h3>

			<span class="bp-spanBlock"><%=bpb_hotel.getRoom_name()%></span>
			<!-- <span class="bp-spanBlock">호텔 정보</span> -->

			<span class="bp-spanBlock"> <span class="fstSpanText">체크인</span><span
				class="scndSpanText"><%=bpb_hotel.getCheck_in()%></span>
			</span> <span class="bp-spanBlock"> <span class="fstSpanText">체크아웃</span><span
				class="scndSpanText"><%=bpb_hotel.getCheck_out()%></span>
			</span>
		</div>

		<hr>

		<div class="bp-section">
			<%
				SimpleDateFormat forCal = new SimpleDateFormat("yyyy-MM-dd (E)");
				Calendar cal = Calendar.getInstance();
				cal.setTime(bpb_hotel.getCheck_in());
			%>
			<div class="pricePerDay">
				<a><span class="fstSpanText"><%=bpb_hotel.getDay()%>박 ▼</span><span
					class="scndSpanText"><%=bpb_hotel.getRoom_price() * bpb_hotel.getDay()%>원</span></a>
				<ul class="hide">
					<%
						for (int i = 0; i < bpb_hotel.getDay(); i++) {
					%>
					<li><span class="fstSpanTextS"><%=forCal.format(cal.getTime())%></span><span
						class="scndSpanTextS"><%=bpb_hotel.getRoom_price()%>원</span></li>
					<%
						cal.add(Calendar.DATE, 1);
						}
					%>
				</ul>
			</div>

			<div class="pricePerDay">
				<%
					int sumOpPrice = 0;
					for (int i = 0; i < getRoomOptionList.size(); i++) {
						BookingPageBean bpbOptionList = (BookingPageBean) getRoomOptionList.get(i);
						sumOpPrice += bpbOptionList.getOption_add_price() * bpb_hotel.getDay();
					}
				%>
				<a><span class="fstSpanText">선택 옵션 ▼</span><span
					class="scndSpanText"><%=sumOpPrice%>원</span></a>
				<ul class="hide">
					<%
						for (int i = 0; i < getRoomOptionList.size(); i++) {
							BookingPageBean bpbOptionList = (BookingPageBean) getRoomOptionList.get(i);
					%>
					<li><span class="fstSpanTextS"><%=bpbOptionList.getRoom_option_name()%>(<%=bpb_hotel.getDay()%>일)</span><span
						class="scndSpanTextS"><%=bpbOptionList.getOption_add_price() * bpb_hotel.getDay()%>원</span></li>
					<%
						}
					%>
				</ul>
			</div>

			<div class="pricePerDay">
				<a><span class="fstSpanText">세금 및 수수료</span><span
					class="scndSpanText"><%=bpb_hotel.getRoom_price() * bpb_hotel.getDay() * 0.1%>원</span></a>
				<ul class="hide">
					<li><span class="taxText">◈ 세금은 Milestone에서 공급업체(예:
							호텔)에 지불하는 세금입니다.</span></li>
					<li><span class="taxText">◈ 수수료는 저희가 제공하는 여행 예약 서비스에 대한
							것입니다.</span></li>
				</ul>
			</div>

			<div class="pricePerDay">
				<hr style="border: 1px solid black;">
				<span class="fstSpanTextL">총 금액</span><span class="scndSpanTextL">총
					<%=bpb_hotel.getRoom_price() * bpb_hotel.getDay() + sumOpPrice
					+ bpb_hotel.getRoom_price() * bpb_hotel.getDay() * 0.1%>원
				</span>
			</div>

			<div class="btn-update">
				<hr>

				<form action="./BookAndPaymentAction.bp" method="post" name="f">
					<input type="hidden" name="booker_name"
						value="<%=bpb.getUser_name()%>"> <input type="hidden"
						name="booker_email" value="<%=bpb.getUser_email()%>"> <input
						type="hidden" name="booker_phone"
						value="<%=bpb.getUser_phone()%>"> <input type="hidden"
						name="room_type_no" value="<%=room_type_no%>"> <input
						type="hidden" name="people_num" value="<%=people_num%>">
					<input type="hidden" name="check_in" value="<%=check_in%>">
					<input type="hidden" name="check_out" value="<%=check_out%>">
					<input type="hidden" name="totalPrice"
						value="<%=bpb_hotel.getRoom_price() * bpb_hotel.getDay() + sumOpPrice
					+ bpb_hotel.getRoom_price() * bpb_hotel.getDay() * 0.1%>">

					<%
						for (int i = 0; i < getRoomOptionList.size(); i++) {
							BookingPageBean bpbOptionList = (BookingPageBean) getRoomOptionList.get(i);
					%>
					<input type="hidden" name="room_option_no"
						value="<%=bpbOptionList.getRoom_option_no()%>">
					<%
						}
					%>

					<input type="hidden" id="type" name="type">

					<div class="modalDivMom">

						<div class="modalDiv">
							<br>
							<h2>숙박 시 결제 하기</h2>

							<p>숙박 완료 후 호텔에 직접 결제합니다.</p>
							<br>
							<button onclick="btnCheck_a(1)" class="btnBookOrPay">예약하기</button>
						</div>


						<div class="modalDiv">
							<br>
							<h2>지금 결제 하기</h2>

							<p>Milestone을 통해 결제가 처리됩니다.</p>
							<br>
							<button onclick="btnCheck_a(2)" class="btnBookOrPay">결제하기</button>
						</div>

					</div>
				</form>

			</div>
		</div>


	</div>
	<!-- wrap3 - 끝 -->


	<!-- slideMenu 스크립트 -->
	<script>
		$(document).ready(function() {
			// pricePerDay 클래스 바로 하위에 있는 a 태그를 클릭했을때
			$(".pricePerDay>a").click(function() {
				var submenu = $(this).next("ul");

				// submenu가 화면상에 보일때는 위로 접고 아니면 아래로 펼치기
				if (submenu.is(":visible")) {
					submenu.slideUp();
				} else {
					submenu.slideDown();
				}
			});
		});
	</script>
	<script>
		function btnCheck_a(index) {

			if (index == 1) {
				document.getElementById("type").value = '1';
			} else if (index == 2) {
				document.getElementById("type").value = '2';
			}
			document.f.submit();
		}
	</script>

</body>
</html>