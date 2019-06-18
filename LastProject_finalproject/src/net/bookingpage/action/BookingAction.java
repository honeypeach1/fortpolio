package net.bookingpage.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bookingpage.db.BookingPageBean;
import net.bookingpage.db.BookingPageDAO;
import project.action.Action;
import project.action.ActionForward;

public class BookingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("BookingAction execute!");

		// 한글처리
		request.setCharacterEncoding("UTF-8");

		//////////////////////////////////////// 수정시작
		HttpSession session = request.getSession();

		if (session.getAttribute("user_num") == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("  alert('로그인을 해주세요');");
			out.println("  history.back();");
			out.println("</script>");
			out.close();
		}

		String user_num = session.getAttribute("user_num").toString();

		//////////////////////////////////////// 수정 끝

		int user_no = Integer.parseInt(user_num);

		int hotel_no = Integer.parseInt(request.getParameter("hotel_no"));
		int room_type_no = Integer.parseInt(request.getParameter("room_type_no"));
		String type = (String) request.getParameter("type");

		/*
		 * String ck_in = request.getParameter("check_in"); String ck_out =
		 * request.getParameter("check_out");
		 * System.out.println("ck_in : "+ck_in);
		 */

		Date check_in = Date.valueOf((String) request.getParameter("check_in"));
		Date check_out = Date.valueOf((String) request.getParameter("check_out"));

		int basic_people = Integer.parseInt(request.getParameter("people_num"));

		System.out.println(room_type_no);
		System.out.println(check_in);
		System.out.println(check_out);
		System.out.println(basic_people);

		BookingPageDAO bpdao = new BookingPageDAO();

		// 유저 정보 가져오기
		BookingPageBean bpb = new BookingPageBean();
		bpb = bpdao.getUserInfo(user_no);

		// 호텔 정보 가져오기
		BookingPageBean bpb_hotel = new BookingPageBean();
		bpb_hotel = bpdao.getHotelInfo(room_type_no, check_in, check_out);

		// -----------------------------------------
		// 룸 선택 옵션 가져오기
		String[] room_op_no_array = request.getParameterValues("room_option_no");

		// 룸 선택 옵션 정보 가져오기
		ArrayList getRoomOptionList = (ArrayList) bpdao.getRoomOptionList(room_op_no_array);

		request.setAttribute("bpb", bpb);
		request.setAttribute("bpb_hotel", bpb_hotel);
		request.setAttribute("getRoomOptionList", getRoomOptionList);

		request.setAttribute("hotel_no", hotel_no);
		request.setAttribute("room_type_no", room_type_no);

		request.setAttribute("check_in", check_in);
		request.setAttribute("check_out", check_out);
		request.setAttribute("people_num", basic_people);
		request.setAttribute("type", type);

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./bookingPage.jsp");
		forward.setRedirect(false);
		return forward;

	}

}