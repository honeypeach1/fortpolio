package project.admin.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hotel.db.HotelBean;
import net.hotel.db.HotelDAO;
import project.action.Action;
import project.action.ActionForward;
import project.adminGetList.db.AdminGetListBean;
import project.adminGetList.db.AdminGetListDAO;

public class AppHotelRoomAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//관리자가 승인 호텔 리스트에서 보는 호텔의 세부 정보
		
		String hotel_no = request.getParameter("hotel_no");

		System.out.println("승인 호텔에서 호텔의 세부정보 보기 동작 action 모듈");
		System.out.println("가져와야 하는 hotel 넘버 값 : " + hotel_no);

		
		AdminGetListDAO adao = new AdminGetListDAO();

		
		/*
			승인 호텔 정보 보기 페이지
			가져와야하는 정보들
		
		 	호텔 테이블
			호텔 이름
			호텔 정보
			
			호텔 사진 리스트
			시설물 리스트
		*/
		
		//호텔 정보 가져오기

		//해당 객체에 호텔 정보와 시설물 리스트 저장 완료
		AdminGetListBean AppAdminGetHotel = adao.AppAdminGetHotel(hotel_no);
		
		request.setAttribute("AppHotelInfor", AppAdminGetHotel);
		
		
		//호텔 시설물 리스트 가져오기
		ArrayList<AdminGetListBean> AppFacilityHotel = adao.AppFacilityHotel(hotel_no);

		request.setAttribute("AppFacilityHotel", AppFacilityHotel);

		/*	
			
			객실 테이블
			객실 명
			객실 정보
			
			객실 추가 옵션 테이블
			옵션명
			옵션 가격
			
			객실 사진 테이블
		 * */

		ArrayList<AdminGetListBean> AppAdminRoom = adao.AppAdminRoom(hotel_no);
		
		request.setAttribute("AppAdminRoom", AppAdminRoom);
		
		
		//모든 방 옵션 정보 가져오기(jsp 페이지에서 일치하는 정보만 출력하기
		ArrayList<AdminGetListBean> AppRoomOption = adao.AppRoomOption();
		
		request.setAttribute("AppRoomOption", AppRoomOption);
		
		
		//방의 세부 이미지 리스트 가져오기(jsp 페이지에서 일치하는 정보만 출력하기
		ArrayList<AdminGetListBean> AppRoomImages = adao.AppRoomImages();
		
		request.setAttribute("AppRoomImages", AppRoomImages);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AppRoomDetail.jsp");
		forward.setRedirect(false);		
		return forward;
	}
}
