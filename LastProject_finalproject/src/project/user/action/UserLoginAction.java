package project.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class UserLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//한글 처리
		request.setCharacterEncoding("UTF-8");
		
		//Login 처리하기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("아이디 값 : "+id);
		System.out.println("비밀번호 값 : "+pw);
		
		//유저 디비 처리하기 객체 생성
		UserDAO udao = new UserDAO();
		
		//체크하기
		int check = udao.idCheck(id,pw);
		
		//기본값 0
		if(check == 0) {
			//한글처리
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디가 없습니다. 다시 확인 해주세요.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
		}
		
		//비밀번호 오류 1
		else if(check == 1) {
			//한글처리
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('비밀번호가 일치하지 않습니다.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
		}
		
		else {
			//세션 생성하기
			HttpSession session = request.getSession();
			
			UserBean userBean = udao.getUserSession(id,pw);

			//유저 세션(유저 번호, 유저 이름) 생성
			session.setAttribute("user_num", userBean.getUser_no());
			session.setAttribute("user_name", userBean.getUser_name());
			
			
			// 페이지 이동
			ActionForward forward = new ActionForward();
			forward.setPath("./goToMainPage.li");
			forward.setRedirect(true);
			return forward;
		}
		
	}

}
