package net.bookingpage.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;

public class getBookingPaymentInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("getBookingPaymentInfoAction_execute!!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터값 받아오기 (유저 id, 호텔 번호, 객실 타입 번호, 체크인, 아크아웃
		String user_id = "id1"; // 현재 'id1'로 임의 설정 추후에 session값으로 변경할 것
		int hotel_no = 1;
		int room_type_no = 1;
		Date booking_enter_date;
		Date booking_leave_date;
		
		
		// 1. user_id에 해당하는 개인 정보 가져오기(이름, 전화번호, 이메일)
		
		
		
		// 2. 객실 및 가격 세부 정보 가져오기(호텔이름, 객실이름, 체크인, 체크아웃, 가격, 선택옵션+가격, 세금및수수료10%)
		
		
		// ***** id값 임의 처리 나중에 sessin으로 고칠 것!
		request.setAttribute("user_id", user_id);
		
		return null;
	}
	
	

}
