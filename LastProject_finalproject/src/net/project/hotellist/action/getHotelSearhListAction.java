package net.project.hotellist.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.project.hotellist.db.HotelListDAO;

public class getHotelSearhListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("getHotelSearhListAction_execute");
		request.setCharacterEncoding("UTF-8");
		HotelListDAO hldao = new HotelListDAO();
		String input_text = request.getParameter("input_text");
		int people_num = Integer.parseInt(request.getParameter("people_num"));
		request.setAttribute("input_text", input_text); // �˻��� ����� ���� üũ�ڽ� Ŭ���� input_text�� ������������ ���
		// ȣ�� ����Ʈ �ҷ����� �޼ҵ�
		List hotelinfoList = hldao.getHotelInfoList(input_text,people_num);
		
		// ȣ�� ���ǽü� ����Ʈ �ҷ����� �޼ҵ�
		List hotelfacList = hldao.getHotelFacList(input_text,people_num);
		
		request.setAttribute("hotelinfoList", hotelinfoList);
		request.setAttribute("hotelfacList", hotelfacList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/listPage.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
