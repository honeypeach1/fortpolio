package net.bookingpage.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bookingpage.db.BookingPageDAO;
import net.bookingpage.db.InfoForBookBean;
import project.action.Action;
import project.action.ActionForward;

public class BookAndPaymentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("BookAndPaymentAction execute!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// session 아이디값 받아오기 (현재 임의 설정 나중에 session으로 바꿀것)
		String user_id = "id1";
		
		// 파라미터값 받아오기
		int room_type_no = Integer.parseInt(request.getParameter("room_type_no"));
		Date check_in = Date.valueOf(request.getParameter("check_in"));
		Date check_out = Date.valueOf(request.getParameter("check_out"));
		int basic_people = Integer.parseInt(request.getParameter("people_num"));
		String booker_name = request.getParameter("booker_name");
		String booker_phone = request.getParameter("booker_phone");
		String booker_email = request.getParameter("booker_email");
		int type = Integer.parseInt(request.getParameter("type"));
		String[] room_op_no_array = request.getParameterValues("room_option_no");
		
		// DAO 객체 생성
		BookingPageDAO bpdao = new BookingPageDAO();
		
		// 예약에 필요한 정보를 담는 InfoForBookBean 객체 생성 & 정보 저장
		InfoForBookBean ifbbean = new InfoForBookBean();
		ifbbean.setUser_id(user_id);
		ifbbean.setRoom_type_no(room_type_no);
		ifbbean.setCheck_in(check_in);
		ifbbean.setCheck_out(check_out);
		ifbbean.setBooking_total_people(basic_people);
		ifbbean.setBooker_name(booker_name);
		ifbbean.setBooker_phone(booker_phone);
		ifbbean.setBooker_email(booker_email);
		ifbbean.setRoom_option_no(room_op_no_array);
		ifbbean.setType(type);
		
		// booking_table에 insert하는 메서드
		bpdao.bookRoom(ifbbean);
		
		// 페이지이동 -> 
		ActionForward forward = new ActionForward();
		forward.setPath("mainPage.jsp");
		forward.setRedirect(false);	
		return forward;
	}
	
	
}
