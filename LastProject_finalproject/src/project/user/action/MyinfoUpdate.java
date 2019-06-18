package project.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class MyinfoUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 여부 체크하기
		HttpSession session = request.getSession();
		String user_num = (String) session.getAttribute("user_num");
		System.out.println("myinfoupdate : "+user_num);
		
//		if(user_num == null){
//			forward.setPath("./goToLoginPage.do");
//			forward.setRedirect(true);
//			return forward;
//		}
		// UserBean, UserDAO 불러오기
		UserDAO udao = new UserDAO();
		UserBean ub = udao.getUserinfo(user_num);
		request.setAttribute("ub", ub);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./goToMyPage.do");
		forward.setRedirect(false);
		return forward;
	}

	
}
