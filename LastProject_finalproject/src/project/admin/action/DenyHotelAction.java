package project.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListDAO;

public class DenyHotelAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int hotel_no = Integer.parseInt(request.getParameter("hotel_no")); 
		System.out.println("가져온 호텔 넘버 값 : " + hotel_no);
		
		ListDAO ldao = new ListDAO();
		
		int DenyHotel = ldao.DenyHotel(hotel_no);
		System.out.println(DenyHotel);
		//true 삭제 완료(초기값 false)
		if(DenyHotel == 1) {
			System.out.println("승인 호텔 삭제 완료");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println(" alert('승인 호텔 삭제 완료 되었습니다.'); ");
			out.println(" location.href='./adAppHotelInfor.ad' ");
			out.println("</script>");

			out.close();
			return null;
		}else {
			System.out.println("승인 호텔 삭제 실패. 코드를 다시 확인하세요.");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println(" alert('승인 호텔 삭제 실패. 코드를 다시 확인하세요.'); ");
			out.println(" history.back() ");
			out.println("</script>");

			out.close();
			return null;
		}
	}
}
