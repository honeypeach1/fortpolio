package project.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListDAO;

public class HotelInforRemoveAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int hotel_no = Integer.parseInt(request.getParameter("hotel_no"));
		System.out.println("All Hotel에서 가져오는 호텔 넘버 값 : " + hotel_no);

		ListDAO ldao = new ListDAO();
		
		//승인 대기 모듈(action)에서 사용한 DenyHotel 메소드 재사용(기능이 동일함)
		int RemoveHotel = ldao.DenyHotel(hotel_no);
		
		if(RemoveHotel == 1) {
			System.out.println("All Hotel 리스트에서 해당 호텔 삭제 완료");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println(" alert('All Hotel 리스트에서 해당 호텔 삭제 완료되었습니다.'); ");
			out.println(" location.href='./adHotelInfor.ad' ");
			out.println("</script>");

			out.close();
			return null;
		}else {
			System.out.println("All Hotel 리스트에서 해당 호텔 삭제 실패. 코드를 다시 확인하세요.");			
		
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println(" All Hotel 리스트에서 해당 호텔 삭제 실패. 코드를 다시 확인하세요.'); ");
			out.println(" history.back() ");
			out.println("</script>");

			out.close();
			return null;
		}
	}
}
