package project.admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListBean;
import project.list.db.ListDAO;

public class AdminHotelInforAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListDAO ldao = new ListDAO();
		
		//호텔의 정보 가져오기
		ArrayList<ListBean> getAdHotel = ldao.getAllList();
		
		if(getAdHotel != null) {
			System.out.println("호텔 리스트 가져오기 성공");
			
			request.setAttribute("getAdHotel", getAdHotel);
			
			ActionForward forward = new ActionForward();
			forward.setPath("/AdminHotelInfor.jsp");
			forward.setRedirect(false);
			return forward;
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디가 없습니다. 다시 확인 해주세요.'); ");
			out.println("location.href='/AdminHotelInfor.jsp'");
			out.println("</script>");

			out.close();
			return null;
		}
	}

}
