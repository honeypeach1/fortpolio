package project.user.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class ChangeProfileImage implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("Change Profile Image executed");
		
		
		HttpSession session = request.getSession();
		String user_num = session.getAttribute("user_num").toString();
		System.out.println("사진 바꾸기 user_num : "+user_num);
		
		ServletContext ctx = request.getServletContext();
		
		String realPath = ctx.getRealPath("user_images");
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
		request.setCharacterEncoding("UTF-8");
		String user_image = multi.getFilesystemName("user_image");
		System.out.println("이미지 : " + user_image);
		
		UserBean ub = new UserBean();
		ub.setUser_image(user_image);
		
		UserDAO udao = new UserDAO();
		int check = udao.changeImage(ub, user_num);
		
		if(check == 0){
			System.out.println("사진 변경 실패");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('프로필 사진 변경 과정에서 오류가 발생하였습니다.'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			
			return null;
		} else {
			System.out.println("사진 변경 성공");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('프로필 사진이 정상적으로 변경되었습니다.'); ");
			out.println(" location.href='./goToMyPage.do'; ");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
	}

	
}
