package net.project.hotellist.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.project.hotellist.db.HotelListDAO;

public class getHotelListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("getHotelListAction_execute");
		request.setCharacterEncoding("UTF-8");
		HotelListDAO hldao = new HotelListDAO();
		
		/*// 호텔 리스트 불러오기 메소드
		List hotelinfoList = hldao.getHotelInfoList();
		
		// 호텔 편의시설 리스트 불러오기 메소드
		List hotelfacList = hldao.getHotelFacList();
		
		request.setAttribute("hotelinfoList", hotelinfoList);
		request.setAttribute("hotelfacList", hotelfacList);*/
		
		ActionForward forward = new ActionForward();
		forward.setPath("/listPage.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}
