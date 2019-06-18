<%@page import="java.sql.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.roomdetails.db.RoomTypeBean"%>
<%@page import="net.roomdetails.db.RoomDetailsDAO"%>
<%@page import="net.roomdetails.db.RoomDetailsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

div.modalDivMom{
	display:inline;
	text-align: center;
}
div.modalDiv{
	width: 50%;
	display: inline-block;
	float:left;
}

div.modalDiv > button.btnBookOrPay{
	background-color: #0063b6;
	color: white;
	padding: 5px;
	
	border: 2px solid #002341;
	border-radius: 6px;
	box-shadow: 0 2px #666;
}
div.modalDiv > button.btnBookOrPay:ACTIVE{
	box-shadow: 0 0 #666;
	transform: translateY(2px);
}


</style>

<!-- ///////////////////// DatePicker script - 시작 ///////////////////////////////// -->

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  

<script type="text/javascript">
$(function() {
    $( "#start_date" ).datepicker({
    	
    	dateFormat: "yy-mm-dd",
    	monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
    	monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        dayNamesMin:["일","월","화","수","목","금","토"],
        changeMonth: true,     // 월을 이동하기 위한 선택상자 표시여부
        minDate: 0,  //오늘 이전  날짜 선택 불가
		
    	
    	onClose: function( selectedDate ){
			// 시작일(fromDate) datepicker가 닫힐때
       	 	// 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
       	 	var selDate = new Date(selectedDate);
			var endDate = selDate.getDay()+0.5;
        	$("#end_date").datepicker( "option", "minDate", endDate );
   
		},
		
		onSelect: function (selectedDate) {
			var endDate = $('#end_date');
			var startDate = $(this).datepicker('getDate');
			var minDate = $(this).datepicker('getDate');
			endDate.datepicker('setDate', minDate);
			startDate.setDate(startDate.getDate() + 30);
			endDate.datepicker('option', 'maxDate', startDate);
			endDate.datepicker('option', 'minDate', minDate);
		}
		
    });// start_date - 끝
    
    
    $( "#end_date" ).datepicker({
    	
    	dateFormat: "yy-mm-dd",
    	monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
    	monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
        dayNamesMin:["일","월","화","수","목","금","토"],
        changeMonth: true,     // 월을 이동하기 위한 선택상자 표시여부
        minDate: +1,
        
        onClose: function( selectedDate ) {
            // 종료일(toDate) datepicker가 닫힐때
            // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
            $("#start_date").datepicker( "option", "maxDate", selectedDate );
        }
    	
    });// end_date - 끝
    
    
    
    

    
    
}); // function() - 끝
</script>
<!-- ///////////////////// DatePicker script - 시작 ///////////////////////////////// -->

</head>
<body>

	<%
			
		// RoomListAction에서 값 받아오기
		int hotel_no = (int)request.getAttribute("hotel_no");
		Date check_in = (Date)request.getAttribute("check_in");
		Date check_out = (Date)request.getAttribute("check_out");
		int people_num = (int)request.getAttribute("people_num");

	%>

	<hr>
	<h4>
		<strong>룸 정보 보여주기 테이블</strong>
	</h4>
	<div class="div-table">

		<table>
			<!-- 1층 -->
			<tr class="floor-one">
				<td colspan="4" class="floor-one">객실선택</td>
			</tr>

			<!-- 2층 : 검색바 -->
			<tr class="floor-two">
				<td colspan="4" class="floor-two">
					<div class="two_box">
					
						<!-- 검색 폼 태그 -->
						<form class="search_form" action="./RoomDetails.rd" method="get"
							id="search_form">
							<div lass="d-flex flex-lg-row flex-column align-items-center justify-content-start">
								
								<!-- DatePicker input tag -->
								<input type="text" id="start_date" name="check_in" readonly value="<%=check_in %>">
								<input type="text" id="end_date" name="check_out" readonly value="<%=check_out %>">

								<!-- 인원 셀렉트 부분 -->
								<select style="border-radius: 5px; width: 160px; height: 35px; margin-right: 5px;" name="people_num">
									<option value="1" <%if(people_num==1){%>selected<%} %>>성인 1명</option>
									<option value="2" <%if(people_num==2){%>selected<%} %>>성인 2명</option>
									<option value="4" <%if(people_num==4){%>selected<%} %>>가족(4인 기준)</option>
									<option value="5" <%if(people_num==5){%>selected<%} %>>단체</option>
								</select>
								
								<input type="hidden" name="hotel_no" value="<%=hotel_no %>">
								<input class="search_button" type="submit" value="search">
							</div>
						</form>
					</div>
				</td>
			</tr>

			<!-- 3층 -->
			<tr class="floor-three">
				<td>객실유형</td>
				<td>옵션</td>
				<td>객실당 1박 요금</td>
				<td>예약하기</td>
			</tr>

			<!-- DB에서 객실 리스트 가져오는 로직 -->
			<%
				// 호텔의 객실 리스트를 불러오는 List (RoomListAction.java 에서 넘어온 값)
				//List roomDetailsList = (List)request.getAttribute("roomDetailsList"); 		
				List roomTypeList = (List) request.getAttribute("roomTypeList"); // 이거 되면 위에 줄 삭제
				HashMap roomImageList = (HashMap) request.getAttribute("roomImageList");

				// 객실의 선택 옵션을 불러오기 위한 DAO, List
				RoomDetailsDAO rddaoSec = new RoomDetailsDAO();
				List roomOptionList = null;
			%>
			<%
				if (roomTypeList.size() != 0) {
					for (int i = 0; i < roomTypeList.size(); i++) {
						RoomTypeBean rtb = (RoomTypeBean) roomTypeList.get(i);
						
						List roomImage = (List)roomImageList.get(rtb.getRoom_type_no());
						
			%>
			
			
			<!-- 4층 부터 : 룸 정보를 보여줌  -->
			<tr class="room-info room1">
				<!-- 객실 유형 : 호텔 방 이미지 + 클릭하면 더 많은 사진을 볼 수 있도록 함 -->
				<td class="show-room-image">
					<div>
						<!-- 객실 대표 이미지 컬럼 추가! -->
						<img alt="" src="./roomImage/HotelToscana/<%=roomImage.get(0) %>" width="250px"
							onclick="document.getElementById('subscribe<%=i %>').style.display='block'">

						<!-- id="subscribe" : 객실 이미지 클릭 시 나타나는 슬라이드 화면 -->
						<div id="subscribe<%=i %>" class="w3-modal">
							<div class="w3-modal-content w3-animate-zoom w3-padding-large">
								<div class="w3-container w3-white w3-center">

									<!-- [x] 버튼 -->
									<i
										onclick="document.getElementById('subscribe<%=i %>').style.display='none'"
										class="fa fa-remove w3-button w3-xlarge w3-right w3-transparent"></i>
									<h2 class="w3-wide"></h2>

									<!-- 그림 추가하기 -->
									<!-- Slideshow Header -->

									<div class="powerSpace"></div>

									<div class="w3-container" id="apartment">


										<div class="w3-content"
											style="max-width: 800px; position: relative">

											<!-- 사진 4개 -->
											<%
											for(int j=0; j<roomImage.size(); j++){
												
												%>
												<%-- <img class="smy_Slides w3-animate-opacity"
												src="./w3images/<%=roomImage.get(j) %>" style="width: 100%"> --%>
												<img class="smy_Slides<%=i %> w3-animate-opacity"
												src="./roomImage/HotelToscana/<%=roomImage.get(j) %>" style="width: 100%">
												<%					
											}								
											%> 
											
											
<!-- 											<img class="smy_Slides w3-animate-opacity"
												src="./w3images/snow.jpg" style="width: 100%"> <img
												class="smy_Slides w3-animate-opacity"
												src="./w3images/lights.jpg" style="width: 100%"> <img
												class="smy_Slides w3-animate-opacity"
												src="./w3images/mountains.jpg" style="width: 100%"> <img
												class="smy_Slides w3-animate-opacity"
												src="./w3images/forest.jpg" style="width: 100%">  -->

											<!-- [<] 버튼 -->
											<a class="w3-button w3-hover-dark-grey"
												style="position: absolute; top: 45%; left: 0;"
												onclick="splusDivs<%=i %>(-1)">❮</a>

											<!-- [>] 버튼 -->
											<a class="w3-button w3-hover-dark-grey"
												style="position: absolute; top: 45%; right: 0;"
												onclick="splusDivs<%=i %>(+1)">❯</a>
										</div>

									</div>
								</div>
							</div>

							<script type="text/javascript">
							//Slideshows
							   var sslideIndex<%=i %> = 1;

							   function splusDivs<%=i %>(n) {
							    sslideIndex<%=i %> = sslideIndex<%=i %> + n;
							    sshowDivs<%=i %>(sslideIndex<%=i %>);
							   }

							   function sshowDivs<%=i %>(n) {
							    var x = document.getElementsByClassName("smy_Slides<%=i %>");
							    if (n > x.length) {
							     sslideIndex<%=i %> = 1
							    }
							    if (n < 1) {
							     sslideIndex<%=i %> = x.length
							    }
							    ;
							    for (i = 0; i < x.length; i++) {
							     x[i].style.display = "none";
							    }
							    x[sslideIndex<%=i %> - 1].style.display = "block";
							   }

							   sshowDivs<%=i %>(1);
							</script>

						</div>
						<!-- id="subscribe" : 객실 이미지 클릭 시 나타나는 슬라이드 화면 끝 -->




					</div>
					<div class="room-name"><%=rtb.getRoom_name()%></div>
					<div class="room-info"><%=rtb.getRoom_info()%></div>
				</td>

				<!-- 옵션 -->
				<td class="show-room-option">
					<form name="f" action="./BookingAction.bp" method="get">
					<div>
						
						<%
							roomOptionList = rddaoSec.getRoomOptionList(rtb.getRoom_type_no());

									if (roomOptionList.size() != 0) {
										for (int j = 0; j < roomOptionList.size(); j++) {
											RoomTypeBean rdbSec = (RoomTypeBean) roomOptionList.get(j);
						%>
						<div style="">
							<input type="checkbox" name="room_option_no" value="<%=rdbSec.getRoom_option_no()%>">
							<%=rdbSec.getRoom_option_name()%>, 1박당 ₩<%=rdbSec.getOption_add_price()%>
						</div>
						<%
							}
									}
						%>

					</div>
				</td>

				<!-- 객실당 1박 요금 -->
				<td class="show-room-rate">
					<div>
						<div class="room-remained">
							남은 객실
							 <%=rtb.getLeft_room() %>개
						</div>
						<div class="room-rate">
							&#8361;<%=rtb.getRoom_price_str() %>
							<!-- input type="hidden"은 결제 페이지로 넘길때 사용할 객실 가격 -->
							<input type="hidden" value="<%=rtb.getRoom_price()%>">
						</div>
					</div>
				</td>

				<!-- 예약하기 버튼 -->
<!-- 				<td class="btn-reservation">
					<div>
						<button>예약하기</button>
					</div>
				</td> -->

				
				<%
	  		if(rtb.getLeft_room()!=0){
	  			// 남은 방이 있을때
	  			
	  			%>
				<td class="btn-reservation">
					<div>
						<input type="hidden" name="hotel_no" value="<%=hotel_no %>">
						<input type="hidden" name="room_type_no" value="<%=rtb.getRoom_type_no() %>">
						<input type="hidden" name="check_in" value="<%=check_in %>">
						<input type="hidden" name="check_out" value="<%=check_out %>">
						<input type="hidden" name="people_num" value="<%=people_num %>">
						
						
						<input type="submit" value="예약하기" onclick="return btn_reserve()">
						
						</form>
					</div>
					
				</td>

				<script>
				
   					function btn_reserve(){    	
   						
   						var chck_in = document.f[<%=i%>].check_in.value;
   						var chck_out = document.f[<%=i%>].check_out.value;
   						
   						
   						
    					if(chck_in==chck_out){
        					alert("날짜를 다시 확인해 주세요. 체크인 날짜와 체크아웃 날짜를 다르게 입력해 주세요.");
        					return false;
        				}else{
        					return true;
            			}
    					
    				}
  				</script>

				<%
		
	  		}else{
	  			// 남은 방이 없을때
	  			%>
				<td class="btn-reservation">
					<div>매진되었습니다.</div>
				</td>
				<%
	  			
	  			
	  		}
	  		
	  		%> 

			</tr>
			<%
					} // 4층 for문 끝
				} else { // 4층 if문 끝
				}
			%>
			
			

		</table>
	</div>



</body>
</html>