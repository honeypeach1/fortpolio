package project.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class MyinfoBookCancel implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MyinfoReservCancel execute");
		
		HttpSession session = request.getSession();
		String user_num = session.getAttribute("user_num").toString();
		int booking_no = Integer.parseInt(request.getParameter("booking_no"));
		
		System.out.println("예약취소 컨트롤러 user_num : "+user_num);
		System.out.println("예약취소 컨트롤러 booking_no : "+booking_no);
		
		UserBean ub = new UserBean();
		UserDAO udao = new UserDAO();
		
		
		
		int check = udao.BookCancel(booking_no, user_num);
		
		
		if(check == 0){
			System.out.println("예약 취소 과정 중 오류 발생");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('예약 취소 처리가 정상적으로 작동되지 않았습니다.');");
			out.println(" history.back();  ");
			out.println("</script>");
			out.close();
			return null;
		} else {
			System.out.println("정상적으로 취소됨");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('예약취소가 완료되었습니다.');");
			out.println(" location.href='./goToMyPage.do'; ");
			out.println("</script>");
			out.close();
			return null;
		}

		
	}

	
}
