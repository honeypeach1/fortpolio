package project.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;

public class UserLogout implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 로그아웃 메세지 출력, 페이지 이동 
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println(" alert('로그아웃 처리 되셨습니다.'); ");
		out.println(" location.href='./mainPage.jsp'");
		out.println("</script>");
		out.close();
		return null;
	}

}
