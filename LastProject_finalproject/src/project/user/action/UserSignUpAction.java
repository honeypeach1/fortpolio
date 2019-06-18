package project.user.action;

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
import project.user.db.UserBean;
import project.user.db.UserDAO;

public class UserSignUpAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ServletContext ctx = request.getServletContext();
		
		String realPath = ctx.getRealPath("./user_images");
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
		String user_id = multi.getParameter("user_id");
		String user_pw = multi.getParameter("user_pw");
		String user_name = multi.getParameter("user_name");
		String user_image = multi.getFilesystemName("user_image");
		String user_email = multi.getParameter("user_email");
		String user_phone = multi.getParameter("user_phone");
		
		//회원정보 확인하기
		System.out.println("회원가입 파라미터 가져오기");
		System.out.println("회원 아이디 : " + user_id);
		System.out.println("회원 비밀번호 : " + user_pw);
		System.out.println("회원 이름 : " + user_name);
		System.out.println("이미지 : " + user_image);
		System.out.println("회원 이메일 : " + user_email);
		System.out.println("회원 휴대폰 번호 : " + user_phone);
		
		//빈 객체 생성후 DB처리하기
		UserBean ub = new UserBean();
		ub.setUser_id(user_id);
		ub.setUser_pw(user_pw);
		ub.setUser_name(user_name);
		ub.setUser_image(user_image);
		ub.setUser_email(user_email);
		ub.setUser_phone(user_phone);
		
		//DB생성 및 처리
		UserDAO udao = new UserDAO();
		
		//0 초기값
		//1 아이디 중복
		//2 회원가입 성공
		
		int SignUp = udao.SignUpUser(ub);
		
		//회원가입 성공
		if(SignUp == 2) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('회원가입을 진심으로 축하드립니다.'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			
			return null;
		
		//아이디 중복
		}else if(SignUp == 1) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디가 존재 합니다. 다른 아이디를 사용해주세요.'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			
			return null;
		//회원가입 실패	
		}else{
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('회원가입을 실패하였습니다. 다시 시도해주세요.'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			
			return null;
		}
	}
}
