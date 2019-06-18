package project.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListDAO;

public class AppHotelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String hotel_no = request.getParameter("hotel_no");
		System.out.println("가져온 호텔 넘버 값 : " + hotel_no);
		
		ListDAO ldao = new ListDAO();
		
		boolean ApprovalHotel = ldao.ApprovalHotel(hotel_no);
		
		//승인 완료(APPROVAL = 1)
		if(ApprovalHotel == true) {
			System.out.println("호텔 승인 완료 되었습니다.");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println(" alert('호텔 승인 완료 되었습니다.'); ");
			out.println(" location.href='./adAppHotelInfor.ad' ");
			out.println("</script>");

			out.close();
			return null;
		}else {
			System.out.println("호텔 승인 실패 하였습니다. 코드를 다시 확인하세요.");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println(" alert('호텔 승인 실패 하였습니다. 코드를 다시 확인하세요.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
		}
	}
}
