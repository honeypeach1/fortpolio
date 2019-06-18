package project.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.admin.db.AdminDAO;

public class ChangeAdminInforsAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDAO adao = new AdminDAO();
		
		request.setCharacterEncoding("UTF-8");
		String admin_address = request.getParameter("admin_address");
		String admin_tel = request.getParameter("admin_tel");
		String admin_email = request.getParameter("admin_email");
		
		System.out.println("가져온 관리자 정보들(주소, 전화번호, 이메일)");
		System.out.println("가져온 주소 : " + admin_address);
		System.out.println("가져온 전화번호 : " + admin_tel);
		System.out.println("가져온 이메일 : " + admin_email);
		
		boolean ChangeAdminInfors = adao.ChangeAdminInfors(admin_address,admin_tel,admin_email);
		
		if(ChangeAdminInfors != false) {
			System.out.println("about 페이지에서 관리자 정보(이메일, 주소, 전화번호) 바꾸기 성공");
			
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('about 페이지 관리자 정보(이메일, 주소, 전화번호) 변경이 성공 하였습니다.'); ");
			out.println(" location.href='./goToAdminAbout.ad'");
			out.println("</script>");

			out.close();
			return null;
		}
			System.out.println("about 페이지에서 관리자 정보(이메일, 주소, 전화번호) 바꾸기 실패");

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('about 페이지 관리자 정보(이메일, 주소, 전화번호) 변경이 실패 하였습니다.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
	}

}
