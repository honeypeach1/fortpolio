package project.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserDAO;

public class MyinfoDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_num = session.getAttribute("user_num").toString();
		System.out.println("회원탈퇴 팝업 user_num : "+user_num);
		String user_id = request.getParameter("user_id");
		System.out.println("회원탈퇴 팝업 user_id : "+user_id	);
		
		ActionForward forward = new ActionForward();
		if(user_num == null){
			forward.setPath("./goToLoginPage.do");
			forward.setRedirect(true);
			return forward;
		}
		String user_pw = request.getParameter("user_pw");
		
		UserDAO udao = new UserDAO();
		int check = udao.deleteUser(user_id, user_pw);
		
		if(check == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호를 확인해주세요.');");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			
			return null;
		} else if(check == -1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('존재하지 않는 계정입니다.');");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		// 세션값 초기화하기
		session.invalidate();
		
		// 삭제 완료됨
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("  alert('그 동안 이용해주셔서 감사합니다.');");
		out.println("  window.close();");
		out.println("  opener.location.href='./goToMainPage.li';");
		out.println("</script>");
		out.close();
		
		return null;
	}

	
} 
