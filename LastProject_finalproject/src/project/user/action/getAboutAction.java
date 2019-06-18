package project.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.admin.db.AdminBean;
import project.admin.db.AdminDAO;

public class getAboutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//일반유저와 비로그인시 보일 about 페이지
		AdminDAO adao = new AdminDAO();
		
		ArrayList<AdminBean> aboutInfor = adao.aboutInfor();
		
		request.setAttribute("aboutInfor", aboutInfor);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/about.jsp");
		forward.setRedirect(false);			
		return forward;
	}

}
