package project.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserDAO;

public class checkHotelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		String hotel_name = request.getParameter("hotel_name");
		String hotel_tel = request.getParameter("hotel_tel");
		
		System.out.println("호텔 승인 확인하기 파라미터값");
		System.out.println("호텔 이름 : " + hotel_name);
		System.out.println("호텔 전화번호 : " + hotel_tel);
		
		UserDAO udao = new UserDAO();
		int CheckHotel = udao.checkHotel(hotel_name,hotel_tel);
			
		/*
		 
		 상태 -> 1 승인 대기 상태
		 상태 -> 2 승인 완료
		 상태 -> 3 조회 정보가 없음
		 
		 */
		System.out.println("체크값 : " + CheckHotel);
		
		if(CheckHotel == 1) {
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println(" alert('승인 대기 상태 입니다. 문의사항은 about페이지를 참조하세요.'); ");
		out.println(" history.back(); ");
		out.println("</script>");

		out.close();
		return null;
		
		}else if(CheckHotel == 2) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('승인 완료가 되었습니다.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;	
			
		}else {
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('조회 정보가 없습니다. 다시 한번 확인하세요.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
		}
	}

}
