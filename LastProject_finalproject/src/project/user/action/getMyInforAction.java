package project.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListBean;
import project.list.db.ListDAO;
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class getMyInforAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 체크하기
		HttpSession session = request.getSession();
		String user_num = session.getAttribute("user_num").toString();
		System.out.println("myinfoupdate : " + user_num);
		// if(user_num == null){
		// forward.setPath("./goToLoginPage.do");
		// forward.setRedirect(true);
		// return forward;
		// }
		// UserBean, UserDAO 불러오기
		UserDAO udao = new UserDAO();
		UserBean ub = udao.getUserinfo(user_num);
		
		ListDAO ldao = new ListDAO();
		ArrayList<ListBean> lb = ldao.getBookList(user_num);
		ArrayList<ListBean> lb2 = ldao.getBookOptionList(user_num);
		
		System.out.println("lb = " + lb.size());
		System.out.println("lb2 = " + lb2.size());
		if(ub != null){
			System.out.println("ub 값을 받아옴");
			request.setAttribute("ub",ub);
		}
		
		if(lb != null && lb2 != null){
			System.out.println("lb 값 받아옴 ");
			request.setAttribute("lb",lb);
			request.setAttribute("lb2", lb2);
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MyPage.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
