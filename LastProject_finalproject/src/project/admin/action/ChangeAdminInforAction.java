package project.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.admin.db.AdminDAO;

public class ChangeAdminInforAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDAO adao = new AdminDAO();
		
		request.setCharacterEncoding("UTF-8");
		String admin_infor = request.getParameter("admin_infor");
		System.out.println("가져온 값 : " + admin_infor);
		
		boolean ChangeAdminInfor = adao.ChangeAdminInfor(admin_infor);
		
		if(ChangeAdminInfor != false) {
			System.out.println("about 페이지에서 관리자 정보(코멘트) 바꾸기 성공");
			
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('about 페이지 관리자 정보(코멘트) 변경이 성공 하였습니다.'); ");
			out.println(" location.href='./goToAdminAbout.ad'");
			out.println("</script>");

			out.close();
			return null;
		}
			System.out.println("about 페이지에서 관리자 정보(코멘트) 바꾸기 실패");

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('about 페이지 관리자 정보(코멘트) 변경이 실패 하였습니다.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
	}

}
