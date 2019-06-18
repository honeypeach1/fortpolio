package project.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.admin.db.AdminBean;
import project.admin.db.AdminDAO;

public class getAdminAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDAO adao = new AdminDAO();
		
		ArrayList<AdminBean> aboutInfor = adao.aboutInfor();
		
		request.setAttribute("aboutInfor", aboutInfor);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminAbout.jsp");
		forward.setRedirect(false);			
		return forward;
	}

}
