package project.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class MyinfoDeletePopup implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		int user_num = (Integer)session.getAttribute("user_num");
		
		System.out.println("MyinfoDeletePopup(삭제 페이지 팝업) ㅡ 연결된 유저 번호 : "+user_num);
		
		String user_id = request.getParameter("userid");
		
//		System.out.println("sdsdsdsdsdsdsdsdsdsd" + user_id);
		// U
		UserDAO udao = new UserDAO();
		UserBean ub = udao.getDUserinfo(user_num);
		request.setAttribute("ub", ub);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward = new ActionForward();
		forward.setPath("./withdraw.jsp");
		forward.setRedirect(false);

		/*forward.setPath("./MyinfoDelete.do");
		forward.setRedirect(true);*/
		return forward;
	}

	
}
