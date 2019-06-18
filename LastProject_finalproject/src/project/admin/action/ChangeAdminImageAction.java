package project.admin.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.action.Action;
import project.action.ActionForward;
import project.admin.db.AdminDAO;

public class ChangeAdminImageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletContext ctx = request.getServletContext();
		
		String realPath = ctx.getRealPath("images/mainimages");
		System.out.println("실제 서버 경로 주소값 : " + realPath);
		
		int max = 10 * 1024 * 1024; // 10MB
	
		System.out.println("request 값 : " + request);	//request를 가져오는지 확인하기

		MultipartRequest multi = new MultipartRequest(request, realPath, max, "UTF-8", new DefaultFileRenamePolicy());

		Enumeration e = multi.getFileNames(); // 파일이름들을 e로 가져온다.

		while (e.hasMoreElements()) {// 정보가 있으면 사용하겠다.
			String fname = (String) e.nextElement();

			System.out.println("파일 원본 이름 : " + multi.getOriginalFileName(fname));
			System.out.println("파일 시스템 이름 : " + multi.getFilesystemName(fname));

			// 서버에 저장되어있는 파일 정보를 가져온다.

			File f = multi.getFile(fname);
			System.out.println("파일 크기 : " + f.length() + "byte");
		}
		
		String admin_image = multi.getFilesystemName("admin_image");
		System.out.println("이미지 : " + admin_image);
		
		AdminDAO adao = new AdminDAO();
		
		boolean ChangeAdminImage = adao.ChangeAdminImage(admin_image);
		
		if(ChangeAdminImage != false) {
			System.out.println("about 페이지에서 관리자 이미지 바꾸기 성공");
			
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('about 페이지 관리자 이미지 변경이 성공 하였습니다.'); ");
			out.println(" location.href='./adminAbout.ad'");
			out.println("</script>");

			out.close();
			return null;
		}
			System.out.println("about 페이지에서 관리자 이미지 바꾸기 실패");

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('about 페이지 관리자 이미지 변경이 실패 하였습니다.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
	}
}
