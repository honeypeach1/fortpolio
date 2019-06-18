package project.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class MyinfoUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MyinfoUpdateAction 실행됨");
		UserBean ub = new UserBean();
		HttpSession session = request.getSession();
//		String user_num = request.getParameter("user_no");
		int user_num = (int)session.getAttribute("user_num");
		Integer user_num2 = new Integer(user_num);
		
		System.out.println("업데이트 액션 세션값 : "+session);
		ActionForward forward = new ActionForward();
		if(user_num2 == null){
			System.out.println("이거 튕기는 각인데요");
			forward.setPath("./goToLoginPage.do");
			forward.setRedirect(true);
			return forward;
		}
		System.out.println("업데이트 액션 유저 번호 체크 : "+user_num+", "+user_num2);
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		
		
//		String user_id = request.getParameter("USER_ID"); 
//		String user_pw = request.getParameter("USER_PW");
//		String user_name = request.getParameter("USER_NAME");
//		String user_email = request.getParameter("USER_EMAIL");
//		String user_phone = request.getParameter("USER_PHONE");
		ub.setUser_no(Integer.parseInt(request.getParameter("USER_NO")));
		ub.setUser_id(request.getParameter("USER_ID"));
		ub.setUser_pw(request.getParameter("USER_PW"));
		ub.setUser_name(request.getParameter("USER_NAME"));
		ub.setUser_email(request.getParameter("USER_EMAIL"));
		ub.setUser_phone(request.getParameter("USER_PHONE"));
		
		
		System.out.println(request.getParameter("USER_ID")+", "+request.getParameter("USER_PW")+", "+request.getParameter("USER_NAME")+", "+request.getParameter("USER_EMAIL")+", "+request.getParameter("USER_PHONE"));
		
		
		UserDAO udao = new UserDAO();
		int check = udao.updateUserinfo(ub);
		
		if(check == 0){
			// 비번 오류
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('비밀번호를 확인해주시기 바랍니다.');");
			out.println(" history.back();  ");
			out.println("</script>");
			out.close();
			return null;
		} else if(check == -1){
			// 아이디 없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('존재하지 않는 계정입니다.');");
			out.println(" history.back();  ");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// 수정 완료됨
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println(" 	alert('회원 정보가 수정되었습니다.');");
		out.println(" 	location.href='./goToMyPage.do';");
		out.println("</script>");
		out.close();
		return null;
	}

	
}
