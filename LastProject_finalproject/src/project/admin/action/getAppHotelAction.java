package project.admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListBean;
import project.list.db.ListDAO;

public class getAppHotelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ListDAO ldao = new ListDAO();
		
		ArrayList<ListBean> AppHotel = ldao.AppHotel();
		
		
		if(AppHotel != null) {
			System.out.println("승인 대기 호텔 리스트 불러오기 성공");
			
			request.setAttribute("AppHotel", AppHotel);
			
			ActionForward forward = new ActionForward();
			forward.setPath("./AdminAppHotelInfor.jsp");
			forward.setRedirect(false);
			return forward;
		}else {
			System.out.println("승인 대기 호텔 리스트 불러오기 실패");
			
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('승인 대기 호텔 리스트를 불러오기 실패 하였습니다.'); ");
			out.println(" location.href='/goToMainPage.li' ");
			out.println("</script>");

			out.close();
			return null;
		}
	}
}
