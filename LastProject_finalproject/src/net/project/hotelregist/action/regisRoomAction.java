package net.project.hotelregist.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.project.hotelregist.db.HotelRegisBean;
import net.project.hotelregist.db.HotelRegisDAO;
import net.project.roomregist.db.RoomRegisBean;
import net.project.roomregist.db.RoomRegisDAO;
import project.action.Action;
import project.action.ActionForward;

public class regisRoomAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("regisRoomAction");
		request.setCharacterEncoding("UTF-8");

		
		
		
		
		
		
		String realPath = request.getRealPath("/hotelImage");
		System.out.println(realPath);
		
		int maxSize = 100 * 1024 * 1024;
		
		
		
		
		// MultipartRequest 객체 생성
		MultipartRequest multi = 
				new MultipartRequest(
						request,
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);
		
		
		
		RoomRegisBean rb = new RoomRegisBean();
		// *************** 호텔 번호 받아오기
		int hotel_num=Integer.parseInt(multi.getParameter("hotel_num"));
		// **************** 호텔 번호 받아오기 끝
		
		
		// ***********************여기서부터 객실 옵션 
		String option_name = multi.getParameter("room_option_name");
		System.out.println("resgisroomaction옵션 이름값 : "+option_name);
		
		String[] room_option_name = option_name.split(",");
		
		for(int i=0; i<room_option_name.length; i++){
			System.out.println("regisRoomAction페이지의 옵션 이름"+room_option_name[i]);
		}
		
		
		String option_price = multi.getParameter("room_option_price");
		System.out.println("resgisroomaction 옵션가격 : "+option_price);
		
		String[] room_option_price_STR = option_price.split(","); // 이걸 숫자 형식으로 바꿔야함.
		int[] room_option_price = new int[room_option_price_STR.length];
		
		for(int i = 0; i< room_option_price_STR.length; i++){
			room_option_price[i] = Integer.parseInt(room_option_price_STR[i]);
		}
		
		for(int i=0; i<room_option_price.length; i++){
			System.out.println("regisRoomAction페이지의 옵션가격"+room_option_price[i]);
		}
		// 8888888888888888888888888888 여기까지 객실옵션
		
		
		
		//************************ 여기서부터 방번호
		String room_no = multi.getParameter("room_no");
		System.out.println("resgisroomaction옵션 이름값 : "+room_no);
		
		String[] room_number = room_no.split(",");
		
		for(int i=0; i<room_number.length; i++){
			System.out.println("regisRoomAction페이지의 옵션 이름"+room_number[i]);
		}
		//************************ 여기까지 방번호
		
		
		
		
		System.out.println("regisroomaction 페이지 hotel값"+multi.getParameter("hotel_num"));
		
		rb.setHotel_no(hotel_num);
		rb.setRoom_name(multi.getParameter("room_name"));
		rb.setRoom_basic_people(Integer.parseInt(multi.getParameter("room_basic_people")));
		rb.setRoom_max_people(Integer.parseInt(multi.getParameter("room_max_people")));
		rb.setRoom_info(multi.getParameter("room_info"));
		rb.setRoom_price(Integer.parseInt(multi.getParameter("room_price")));
		rb.setRoom_pic1(multi.getFilesystemName("room_pic1"));
		rb.setRoom_pic2(multi.getFilesystemName("room_pic2"));
		rb.setRoom_option_name(room_option_name);
		rb.setRoom_option_price(room_option_price);
		rb.setRoom_number(room_number);
		
		
		System.out.println("room 값 저장 완료");
		
		RoomRegisDAO rmao = new RoomRegisDAO();
		
		rmao.insertRoom(rb);
		
		
		
		
		
		
		System.out.println(" DAO 에서 Action 페이지로 돌아옴!");

		request.setAttribute("hotel_num", hotel_num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./regRoom.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
