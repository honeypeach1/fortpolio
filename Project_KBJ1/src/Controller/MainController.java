package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import javax.mail.Authenticator;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;

import Controller.MyAuthentication;
	
import Controller.projectDAO;
import model.CommentTable;
import model.FriendApproval;
import model.TimeLine;
import model.UserProfile;
import model.UserTable;

/**
 * Servlet implementation class MainController
 */
/*@WebServlet(name = "/MainController", value = "/view/*")*/ /* xml로 주소 매핑할거라 앞의 소스코드 주석처리 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Abstract = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* MainController 어디로 Mapping이 이루어졌는지 확인하기 위한 코드 */
		String subPath = request.getPathInfo();
		String viewPage = null;
		System.out.println("[MainController.java] 경로");
		System.out.println("[MainController.java] 도착한 subPath : " + subPath);
		System.out.println();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		
		//검색버튼 클릭시
		if (subPath.equals("/searchInfor")) {
			String infor = request.getParameter("search");
			String user_num = session.getAttribute("USER_NUM").toString();
			projectDAO db = new projectDAO();
			
			System.out.println("검색 : " + infor);
			
			UserTable usertables = null;
			
			//가지고 오는 user_num값으로 유저 정보 가지고 오기
			try {
				usertables = db.InforPage(user_num);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//자신의 프로필 가져오기
			UserProfile userprofile = db.getProfile(user_num);	
			
			if(userprofile != null) {
				System.out.println("get your Profile.");
				request.setAttribute("userInfor", userprofile);
			}else {
				System.out.println("you don't have your profile.");
				request.setAttribute("userInfor", userprofile);
			}
			
			//메인페이지 이미지 유무에 따라 기본 사진 띄우기 기능
			String chImg = db.chImg(user_num);
			System.out.println("서브 이미지 체크 하기 - > " + chImg);
			if(chImg != null) {
				System.out.println("이미지 체크 성공(이미지 존재)");
				request.setAttribute("chImg", chImg);
			}
			else {
				System.out.println("이미지 체크 성공(이미지 없음)");
				chImg = null;
			}
			
			//회원정보 가져오기
			request.setAttribute("loginInfor", usertables);
			System.out.println("user정보 가져오기 성공");
			
			ArrayList<UserTable> search = db.searchInfor(infor);
			
			if(search != null) {
				request.setAttribute("searchList", search);
				
				System.out.println("넘버값 : " + user_num);
				//친구 리스트 가져오기 객체 및 메서드 생성
				ArrayList<UserTable> getFriendList = db.friendList(user_num);
				
				//친구 목록 가져오기 비교문
				if(getFriendList != null) {
					System.out.println("User have friend list.");
					request.setAttribute("friendList", getFriendList);
				}else {
					System.out.println("User haven't friend list.");
				}
				
				viewPage = "/searchInfor.jsp";
			}
			else {
				System.out.println("결과 없음");
				//검색 결과가 없다는 페이지 하나 제작!
			}
		}
		
		//타임 라인 등록후의 처리
		
		//타임 라인 이미지 등록가능하게 하기(post로 처리 완료, 백업용 소스)
		/*else if(subPath.equals("/registTimeLine")) {
			projectDAO db = new projectDAO();
			request.setCharacterEncoding("UTF-8");
			
			System.out.println("타임 라인 등록 값 가져오는것 확인하기");
			System.out.println("con_num : " + request.getParameter("condition"));
			System.out.println("user_num : " + request.getParameter("USER_NUM"));
			System.out.println("comments : " + request.getParameter("comments"));
			System.out.println("image : " + request.getParameter("timeLineImage"));
			System.out.println();
			
			
			TimeLine time = new TimeLine();
			time.setCondition_num(Integer.parseInt(request.getParameter("condition")));
			time.setUser_num(Integer.parseInt(request.getParameter("USER_NUM")));
			time.setTime_line_comments(request.getParameter("comments"));
			time.setTime_line_image(request.getParameter("timeLineImage"));
			time.setUpload_num(Integer.parseInt(request.getParameter("USER_NUM"))); 	//나의 타임라인을 남기는것이므로 user_num과 동일한 값
			
			boolean registLine = db.registLine(time);
			
			if(registLine) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('타임 라인 등록이 되었습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('타임 라인 등록이 실패 했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}*/
		
		//타임 라인 삭제
		else if(subPath.equals("/deleteLine")){
			projectDAO db = new projectDAO();
			String timeNum = request.getParameter("time_line_num");
			
			boolean timeLineDel = db.timeLineDelete(timeNum);
			
			if(timeLineDel)
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('타임 라인 삭제가 완료되었습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
			}
			else {
				System.out.println("타임 라인 삭제 실패");
			}
			
		}
		
		// 친구 타임 라인 삭제
				else if(subPath.equals("/deleteLineFri")){
					projectDAO db = new projectDAO();
					String timeNum = request.getParameter("time_line_num");
					String friend_num = request.getParameter("friend_num");
					
					boolean timeLineDel = db.timeLineDelete(timeNum);
					
					if(timeLineDel)
					{
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();

						out.print("<script>");
						out.println("location.href='/Project_KBJ1/view/getFriendInfor?friends_num=" + friend_num + "'");
						out.println("</script>");
					}
					else {
						System.out.println("타임 라인 삭제 실패");
					}
					
				}
		
		//친구 정보 보기
		else if(subPath.equals("/getFriendInfor")) {
			projectDAO db = new projectDAO();
			String my_num = session.getAttribute("USER_NUM").toString();
			String user_num = request.getParameter("friends_num");	//hidden으로 넘어온 값
			System.out.println("나의 세션 값 : " + my_num + ", 친구 번호 값 : " + user_num);
			
			//UserTable 객체 생성후 null로 초기화
			UserTable usertables = null;
			
			//가지고 오는 user_num값(친구 번호)으로 유저 정보 가지고 오기
			try {
				usertables = db.InforPage(user_num);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//회원정보 가져오기
			request.setAttribute("loginInfor", usertables);
			System.out.println("친구 정보 가져오기 성공");
			
			//이것은 나의 리스트 가져오기 객체 및 메서드 생성
			ArrayList<UserTable> getFriendList = db.friendList(my_num);
			
			//나의 친구 목록 가져오기 비교문
			if(getFriendList != null) {
				System.out.println("User have friend list.");
				request.setAttribute("friendList", getFriendList);
			}else {
				System.out.println("User haven't friend list.");
			}
			
			//TODO
			//친구가 작성한 글목록 가져오기 && 다른 친구가 타임라인에 친구 정보 가져오기
			ArrayList<TimeLine> getTimeLine = db.TimeLinesFri(user_num);
			ArrayList<CommentTable> getComment = db.GetcommFri(user_num);

			if(getTimeLine != null) {
				System.out.println("get your time line.");
				request.setAttribute("timeLine", getTimeLine);
				request.setAttribute("Comment", getComment);
			}else {
				System.out.println("user doesn't to have time line.");
			}
			
			//친구의 프로필 가져오기
			UserProfile userprofile = db.getProfile(user_num);	
			
			if(userprofile != null) {
				System.out.println("get your Profile.");
				request.setAttribute("userInfors", userprofile);
			}else {
				System.out.println("you don't have your profile.");
			}
			
			//친구의 이미지 유무에 따라 기본 사진 띄우기 기능
			String chImg = db.chImg(user_num);
			System.out.println(chImg);
			
			if(chImg != null) {
				System.out.println("이미지 체크 성공(이미지 존재)");
				request.setAttribute("chImg", chImg);
			}
			else {
				System.out.println("이미지 체크 성공(이미지 없음)");
				chImg = null;
			}

			viewPage = "/FriendInfor.jsp";
			
		}
		
		//나의 댓글 달기
		else if(subPath.equals("/comments")) {
			projectDAO db = new projectDAO();
			String my_num = session.getAttribute("USER_NUM").toString();	//글쓴이 즉, 내 세션값(만약 다른 글쓴이 경우 글삭제시에 해당 유저와 번호 비교)
			String time_line_num = request.getParameter("time_line_num");				//comments	
			String user_comments = request.getParameter("user_comments");	//유저가 남긴 댓글
			
			System.out.println("세션값 : " + my_num + ", 해당 글번호 : " + time_line_num + ", 댓글 : " + user_comments);
		
			CommentTable comment = new CommentTable();
			comment.setTime_line_num(Integer.parseInt(time_line_num));
			comment.setUser_num(Integer.parseInt(my_num));
			comment.setComments(user_comments);
			
			boolean registComm = db.registComm(comment);
			
			if(registComm == true) {		
				System.out.println("나의 댓글 달기 성공");
				viewPage = "/view/InforPage";

			}else {
				System.out.println("나의 댓글 달기 실패");
				viewPage = "/view/InforPage";
			}
		}
		
		//post에 있는 InforPage에  get으로 넘어온 정보를 전달하기
		else if(subPath.equals("/InforPage")) {
			this.doPost(request, response);
		}
				
		//친구 신청하기 페이지 처리
		else if (subPath.equals("/AddFriends")) {
			projectDAO db = new projectDAO();
			String my_num = session.getAttribute("USER_NUM").toString();	//내 번호
			String friends_num = request.getParameter("friends_num");		//친구 번호
			
			System.out.println("친구 신청 하기");
			System.out.println("친구 번호 확인 하기 " + friends_num);

			boolean addfriend = db.addfriend(friends_num,my_num);

			if (addfriend == true) {
				System.out.println("친구 신청 성공");
				viewPage = "/view/InforPage";
			} else {
				System.out.println("친구 신청 실패");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('이미 친구인 상태에서는 친구 추가가 불가합니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//나의 타임 라인 댓글 지우기
		else if(subPath.equals("/DeleteComm")) {
			projectDAO db = new projectDAO();
			String comment_num = request.getParameter("comment_num");
			String user_num = request.getParameter("user_num");
			
			System.out.println("나의 타임라인의 댓글 삭제하기");
			System.out.println("유저 번호 : " + user_num + " / 댓글 번호 : " + comment_num);
			
			boolean delComm = db.delComm(comment_num,user_num);
			
			if(delComm == true) {
				System.out.println("댓글 삭제 성공");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('해당 댓글을 삭제 하였습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
			}else {
				System.out.println("댓글 삭제 실패");
			
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('해당 댓글을 삭제 실패 하였습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
			}
		}
		
		//친구의 타임 라인 댓글 지우기
		else if(subPath.equals("/DeleteCommFri")) {
			projectDAO db = new projectDAO();
			String comment_num = request.getParameter("comment_num");
			String user_num = request.getParameter("user_num");
			String friend_num = request.getParameter("friend_num");
			
			System.out.println("나의 타임라인의 댓글 삭제하기");
			System.out.println("유저 번호 : " + user_num + " / 댓글 번호 : " + comment_num);
			
			boolean delComm = db.delComm(comment_num,user_num);
			
			if(delComm == true) {
				System.out.println("댓글 삭제 성공");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('해당 댓글을 삭제 하였습니다.');");
				out.println("location.href='/Project_KBJ1/view/getFriendInfor?friends_num=" + friend_num + "'");
				out.println("</script>");
			}else {
				System.out.println("댓글 삭제 실패");
			
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('해당 댓글을 삭제 실패 하였습니다.');");
				out.println("location.href='/Project_KBJ1/view/getFriendInfor?friends_num=" + friend_num + "'");
				out.println("</script>");
			}
		}
		
		// 친구의 댓글 달기
		else if (subPath.equals("/commentsFri")) {
			projectDAO db = new projectDAO();
			String my_num = session.getAttribute("USER_NUM").toString(); // 글쓴이 즉, 내 세션값(만약 다른 글쓴이 경우 글삭제시에 해당 유저와 번호
																			// 비교)
			String time_line_num = request.getParameter("time_line_num"); // comments
			String user_comments = request.getParameter("user_comments"); // 유저가 남긴 댓글
			String friend_num = request.getParameter("friend_num"); // 친구의 번호

			System.out.println("세션값 : " + my_num + ", 해당 글번호 : " + time_line_num + ", 댓글 : " + user_comments);

			CommentTable comment = new CommentTable();
			comment.setTime_line_num(Integer.parseInt(time_line_num));
			comment.setUser_num(Integer.parseInt(my_num));
			comment.setComments(user_comments);

			boolean registComm = db.registComm(comment);

			if (registComm == true) {
				System.out.println("친구의 댓글 달기 성공");

				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				//out.println("alert('친구의 댓글 달기 성공 하였습니다.');");
				out.println("location.href='/Project_KBJ1/view/getFriendInfor?friends_num=" + friend_num + "'");
				out.println("</script>");

			} else {
				System.out.println("친구의 댓글 달기 실패");

				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				//out.println("alert('친구의 댓글 달기 실패 하였습니다.');");
				out.println("location.href='/Project_KBJ1/view/getFriendInfor?friends_num=" + friend_num + "'");
				out.println("</script>");
			}
		}

		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			return;
		} else {
			/*System.out.println("viewPage가 NULL입니다. 다시 확인하세요.");*/
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subPath = request.getPathInfo();
		String viewPage = null;
		System.out.println("[MainController.java] 경로");
		System.out.println("[MainController.java] 도착한 subPath : " + subPath);
		System.out.println();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		// User Login (로그인 처리후 InforPage로 넘기기)
		if(subPath.equals("/loginCheck")) {
			projectDAO db = new projectDAO();
			
			//POST 한글처리
			request.setCharacterEncoding("UTF-8");
			//메인페이지에서 담아오는 id,pw 값
			String user_id = request.getParameter("id");
			String user_pw = request.getParameter("pw");
			System.out.println("아이디 = " + user_id + "/ 비밀번호 = " + user_pw);
			
			//UserTable 객체 생성후 null로 초기화
			UserTable usertable = null;
			
			try {
				usertable = db.checkUser(user_id, user_pw);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(usertable != null) {
				session.setAttribute("USER_NUM", usertable.getUser_num());
				
				viewPage = "/view/InforPage";
				System.out.println("로그인 완료");
				
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('아이디와 비밀번호를 다시 확인해 주세요.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		// 로그인후 넘어오는 페이지 and InforPage내에서 home버튼 클릭시 넘어오는 페이지
		// 담아와야 하는 정보 (자신의 친구 리스트, 자신의 정보, 자신이 작성한 글)
		else if(subPath.equals("/InforPage")) {
			projectDAO db = new projectDAO();
			String user_num = session.getAttribute("USER_NUM").toString();
			
			System.out.println("USER_NUM = " + user_num);
			//UserTable 객체 생성후 null로 초기화
			UserTable usertables = null;
			
			//가지고 오는 user_num값으로 유저 정보 가지고 오기
			try {
				usertables = db.InforPage(user_num);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//회원정보 가져오기
			request.setAttribute("loginInfor", usertables);
			System.out.println("user정보 가져오기 성공");
			
			//친구 리스트 가져오기 객체 및 메서드 생성
			ArrayList<UserTable> getFriendList = db.friendList(user_num);
			
			//친구 목록 가져오기 비교문
			if(getFriendList != null) {
				System.out.println("User have friend list.");
				request.setAttribute("friendList", getFriendList);
			}else {
				System.out.println("User haven't friend list.");
			}
			
			//작성된 글목록 가져오기
			ArrayList<TimeLine> getTimeLine = db.TimeLines(user_num);
			ArrayList<CommentTable> getComment = db.Getcomm(user_num);
			
			if(getTimeLine != null) {
				System.out.println("get your time line.");
				request.setAttribute("timeLine", getTimeLine);
				request.setAttribute("Comment", getComment);
						
			}else {
				System.out.println("user doesn't to have time line.");
			}
			
			//자신의 프로필 가져오기
			UserProfile userprofile = db.getProfile(user_num);
			
			if(userprofile != null) {
				System.out.println("get your Profile.");
				request.setAttribute("userInfor", userprofile);
			}else {
				System.out.println("you don't have your profile.");
				request.setAttribute("userInfor", userprofile);
			}
			
			//친구 신청 들어온 리스트 가져오기
			ArrayList<FriendApproval> friendApproval = db.getFriend(user_num);
			if(friendApproval != null) {
				System.out.println("친구 신청 목록 가져오기 성공");
				request.setAttribute("getFriend", friendApproval);
			}else {
				System.out.println("친구 신청 목록이 없습니다.");
			}
			
			//메인페이지 이미지 유무에 따라 기본 사진 띄우기 기능
			String chImg = db.chImg(user_num);
				
			if(chImg != null) {
				System.out.println("이미지 체크 성공(이미지 존재)");
				request.setAttribute("chImg", chImg);
			}
			else {
				System.out.println("이미지 체크 성공(이미지 없음)");
				chImg = null;
			}
			
			//친구 신청 들어온 갯수(count) 가져오기
			int countFriend = db.countFriend(user_num);
			
			if(countFriend != 0) {
				System.out.println("친구 신청 count값 가져오기 성공(존재하는 값)");
				request.setAttribute("countFriend",countFriend);
			}else {
				System.out.println("친구 신청 count값 가져오기 성공(0값)");
				countFriend = 0;
			}
			
			viewPage = "/InforPage.jsp";
		}
		
		// 나의 세부 정보 가져오기 페이지로 이동
		else if(subPath.equals("/MyInfor")) {
			projectDAO db = new projectDAO();
			
			String user_num = session.getAttribute("USER_NUM").toString();
			System.out.println("유저 번호 = " + user_num);

			//user_num값으로 마이 페이지(기본정보) 가져오기
			UserTable usertables = null;
			try {
				usertables = db.InforPage(user_num);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(usertables != null) {
				System.out.println("get your Profile to my Inforpage.");
				request.setAttribute("userProfile", usertables);
			}
			
			//user_num값으로 마이 페이지(세부정보) 가져오기
			UserProfile userprof = null;
			try {
				userprof = db.addInfor(user_num);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(userprof != null) {
				System.out.println("get your Add Profile to my Inforpage.");
				request.setAttribute("userprofs", userprof);
			}
		
			viewPage="/myInfor/MyInfor.jsp";
			
		}
		
		// 회원 탈퇴하기
		else if(subPath.equals("/deleteRegist")) {
			projectDAO db = new projectDAO();
			
			String user_num = session.getAttribute("USER_NUM").toString();
			String user_pw = request.getParameter("user_pw");
			
			System.out.println("가져온 PW값 : " + user_pw + "/ 현재 세션값 : " + user_num);
			
			//0 초기값, 1 비밀번호 불일치, 2 성공
			int ckpw = db.ckpw(user_num,user_pw);

			if(ckpw == 1) {
				System.out.println("비밀번호 불일치");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다!');");
				out.println("history.back();");
				out.println("</script>");
			}else if(ckpw == 2) {
				System.out.println("비밀번호 일치 탈퇴 진행하기");
				
				boolean deleteregist = db.deleteRegist(user_num);
				
				if(deleteregist == true) {
					System.out.println("회원 탈퇴 성공");
					
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();

					out.print("<script>");
					out.println("alert('회원탈퇴가 성공적으로 진행되었습니다!');");
					out.println("location.href='/Project_KBJ1/logout.jsp'");
					out.println("</script>");
					
				}else {
					System.out.println("회원 탈퇴 실패");
				}
			}
		}

		// 아이디 체크하기
		else if(subPath.equals("/ckid")) {
			projectDAO db = new projectDAO();
			String ck_id = request.getParameter("id");
			
			System.out.println("가져온 ID값 : " + ck_id);
			
			boolean ckid = db.ckid(ck_id);

			if(ckid == false) {
				System.out.println("아이디 체크 성공(사용 가능)");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('아이디를 사용하실 수 있습니다.');");
				out.println("history.back();");
				out.println("</script>");
				
			}else {
				System.out.println("아이디 체크 성공(이미 해당 아이디가 존재함)");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('해당 아이디는 이미 사용하고 있습니다. 다른 아이디를 사용하세요!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		// User 회원가입
		else if (subPath.equals("/SignUp")) {
			projectDAO db = new projectDAO();
			
			ServletContext ctx = getServletContext();
			
			String realPath = ctx.getRealPath("User_Image");
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
			
			
			UserTable user = new UserTable();
			
			//테스팅 필요 한글 깨지는것!
			request.setCharacterEncoding("UTF-8");
			user.setUser_id(multi.getParameter("id"));
			user.setUser_pw(multi.getParameter("pw"));
			user.setUser_name(multi.getParameter("userName"));
			user.setUser_tel(multi.getParameter("telephone"));
			user.setUser_email(multi.getParameter("email"));
			user.setBorn_date(multi.getParameter("borndate"));
			user.setNation_num(multi.getParameter("nation"));
			user.setUser_gender(multi.getParameter("gender"));
			user.setUser_image(multi.getFilesystemName("files"));	//파일 정보 가져오기
			
			System.out.println("[/signup] user sign up");
			System.out.println("[/signup] user_id : " + user.getUser_id());
			System.out.println("[/signup] user_pw : " + user.getUser_pw());
			System.out.println("[/signup] user_name : " + user.getUser_name());
			System.out.println("[/signup] user_tel : " + user.getUser_tel());
			System.out.println("[/signup] user_email : " + user.getUser_email());
			System.out.println("[/signup] born_date : " + user.getBorn_date());
			System.out.println("[/signup] nation_num : " + user.getNation_num());
			System.out.println("[/signup] user_gender : " + user.getUser_gender());
			System.out.println("[/signup] user_image : " + user.getUser_image());
			System.out.println();

			boolean SignUp = db.CreateUser(user);

			if (SignUp == true) {
				
				//TODO
				//메일 보내기 기능 구현
				String mailProtocol = "smtp";
				String mailHost = "smtp.gmail.com";
				String mailPort = "587";
				String mailId = "kbjae1991@gmail.com"; // 구글계정
				String mailPassword = "oapqmfwewpwicyci"; // 구글계정 비밀번호 
				String fromName = "Milestone";
				String fromEmail = "kbjae1991@gmail.com"; // 보내는 사람 메일
				String toName = user.getUser_name();	//받는 사람 이름
				String toEmail = user.getUser_email(); // 받는 사람 메일(구글 아이디로만)
				String mailTitle = "Milestone에 회원가입을 축하드립니다.";
				String mailContents = "메일 보내기 기능 테스트하기.";
				String debugMode = "false";
				String authMode = "true";

				try {
					boolean debug = Boolean.valueOf(debugMode).booleanValue();
					
					Properties mailProps = new Properties();
					
					mailProps.put("mail.smtp.starttls.enable", "true");
					mailProps.setProperty("mail.transport.protocol", mailProtocol); 
					mailProps.put("mail.debug", debugMode);
					mailProps.put("mail.smtp.host", mailHost);
					mailProps.put("mail.smtp.port", mailPort);
					mailProps.put("mail.smtp.connectiontimeout", "5000");
					mailProps.put("mail.smtp.timeout", "5000");  
					mailProps.put("mail.smtp.auth", authMode);

					Session msgSession = null;
					if(authMode.equals("true")) {
				        Authenticator auth = new MyAuthentication(mailId, mailPassword);
						msgSession = Session.getInstance(mailProps, auth);
					} else {
						msgSession = Session.getInstance(mailProps, null); 
					}
					msgSession.setDebug(debug);

					MimeMessage msg = new MimeMessage(msgSession);

					msg.setFrom(new InternetAddress(fromEmail, fromName));
					msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName));
					msg.setSubject(mailTitle);
					msg.setContent(mailContents, "text/html; charset=euc-kr");

					
					// 스태틱함수로 직접 보내지 않고 객체를 이용해서 보내고 객체를 닫아준다. 
					Transport t = msgSession.getTransport(mailProtocol);
					try {
						t.connect();
						t.sendMessage(msg, msg.getAllRecipients());
					} finally {
					  t.close();
					}
			  
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('회원가입에 성공하였습니다.');");
				out.println("history.back();");
				out.println("</script>");
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('회원가입에 실패하였습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		// 친구의 타임 라인 남기기(post)
		else if (subPath.equals("/registTimeLineFri")) {
			projectDAO db = new projectDAO();
			request.setCharacterEncoding("UTF-8");

			ServletContext ctx = getServletContext();

			String realPath = ctx.getRealPath("images");
			System.out.println("실제 서버 경로 주소값 : " + realPath);

			int max = 10 * 1024 * 1024; // 10MB

			System.out.println("request 값 : " + request); // request를 가져오는지 확인하기

			MultipartRequest multi = new MultipartRequest(request, realPath, max, "UTF-8", new DefaultFileRenamePolicy());

			if(multi.getFilesystemName("timeLineImage") != null) {
			Enumeration e = multi.getFileNames(); // 파일이름들을 e로 가져온다.

			while (e.hasMoreElements()) {// 정보가 있으면 사용하겠다.
				String fname = (String) e.nextElement();

				System.out.println("파일 원본 이름 : " + multi.getOriginalFileName(fname));
				System.out.println("파일 시스템 이름 : " + multi.getFilesystemName(fname));

				// 서버에 저장되어있는 파일 정보를 가져온다.

				File f = multi.getFile(fname);
				System.out.println("파일 크기 : " + f.length() + "byte");
			}
			}
			String my_num = session.getAttribute("USER_NUM").toString(); // 내번호
			String friend_num = multi.getParameter("friend_num"); // 친구 번호

			System.out.println("타임 라인 등록 값 가져오는것 확인하기");
			System.out.println("con_num : " + multi.getParameter("condition"));
			System.out.println("friend_num : " + multi.getParameter("friend_num"));// 타임 라인 남기는 친구 번호
			System.out.println("comments : " + multi.getParameter("comments"));
			System.out.println("image : " + multi.getFilesystemName("timeLineImage"));
			System.out.println("my_num : " + session.getAttribute("USER_NUM").toString());// 글쓴이의 넘버(즉 글을 쓰는 나의 번호값)
			System.out.println();

			TimeLine times = new TimeLine();
			times.setCondition_num(Integer.parseInt(multi.getParameter("condition")));
			times.setUser_num(Integer.parseInt(multi.getParameter("friend_num")));
			times.setTime_line_comments(multi.getParameter("comments"));
			times.setTime_line_image(multi.getFilesystemName("timeLineImage"));
			times.setUpload_num(Integer.parseInt(session.getAttribute("USER_NUM").toString()));
			boolean registLineFri = db.registLinefri(times);

			if (registLineFri) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('친구의 타임 라인 등록이 되었습니다.');");
				// out.println("history.back();");
				// out.println("opener.location.reload();");
				out.println("location.href='/Project_KBJ1/view/getFriendInfor?friends_num=" + friend_num + "'");
				out.println("</script>");
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('친구의 타임 라인 등록이 실패 했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}

		}

		// 친구 승인 하기
		else if(subPath.equals("/Approval")) {
		   projectDAO db = new projectDAO();
		   String my_num = session.getAttribute("USER_NUM").toString();	//내 세션값(fri_acc_num)
		   String friend_num = request.getParameter("friend_num");		//친구 넘버(fri_app_num)
		   
		   System.out.println("친구 승인 : 내 번호 " + my_num + ", 친구 번호 " + friend_num);
		   
		   boolean Approval = db.approval(my_num,friend_num);
		   
		   if(Approval == true) {
			System.out.println("친구 승인 성공");
			viewPage = "/view/InforPage";
		   }else {
			   System.out.println("친구 승인 실패");
		   }
		}
		
		// 친구 승인 거절
		else if(subPath.equals("/Deny")) {
		   projectDAO db = new projectDAO();
		   String my_num = session.getAttribute("USER_NUM").toString();		   
		   String friend_num = request.getParameter("friend_num");
		   
		   System.out.println("친구 거절 " + my_num + "/" + friend_num);
		   
		   boolean Deny = db.deny(my_num,friend_num);
		   
		   if(Deny == true) {
			System.out.println("친구 거절 성공");
			viewPage = "/view/InforPage";
		   }else {
			   System.out.println("친구 거절 실패");
			   
			   response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('친구 거절 실패.');");
				out.println("history.back();");
				out.println("</script>");
		   }
		}
		
		//서브 정보 처음 등록 버튼
		else if(subPath.equals("/subUpload")) {
			projectDAO db = new projectDAO();
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			out.print("<script>");
			out.println("alert('유저의 세부 정보를 등록합니다.');");
			out.println("window.open(\"/Project_KBJ1/subUpload.jsp\", \"test\", \"top=0, left=0, width=350, height=700, scrollbars=yes, resizable=no ,status=no ,toolbar=no\")");
			out.println("history.back();");
			out.println("</script>");
		}
		
		//서브 정보 DB에 저장하기
		else if(subPath.equals("/UploadPage")) {
			projectDAO db = new projectDAO();
			
			String user_num = session.getAttribute("USER_NUM").toString();
			String element_name = request.getParameter("element_name");
			String middle_name = request.getParameter("middle_name");
			String high_name = request.getParameter("high_name");
			String univ_name = request.getParameter("univ_name");
			String job_name = request.getParameter("job_name");
			String user_address = request.getParameter("user_address");
			String marriage = request.getParameter("marriage");
			
			System.out.println("가져온 유저 세부 정보 출력");
			System.out.println("user_num : " + user_num);
			System.out.println("element_name : " + element_name);
			System.out.println("middle_name : " + middle_name);
			System.out.println("high_name : " + high_name);
			System.out.println("univ_name : " + univ_name);
			System.out.println("job_name : " + job_name);
			System.out.println("user_address :" + user_address);
			System.out.println("marriage : " + marriage);
					
			
			boolean uploadSubInfor = db.chSubUpload(user_num,element_name,middle_name,high_name,univ_name,job_name,user_address,marriage);
			
			if(uploadSubInfor == true) {
				System.out.println("세부 정보 등록이 완료");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('세부 정보 등록이 완료 되었습니다.');");
				out.println("window.close();");
				out.println("opener.location.reload();");
				out.println("</script>");
				
			}else {
				System.out.println("세부 정보 등록이 실패");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('세부 정보 등록이 실패하였습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//메인 정보 바꾸기 이름, 비밀번호 확인
		else if(subPath.equals("/checkInfor")) {
			projectDAO db = new projectDAO();
			
			//해당 접속 아이디의 기본키값 가져오기
			String user_num = session.getAttribute("USER_NUM").toString();
			
			//기본키값으로 나오는 이름값과 비밀번호값 대조하기 위해 가져오는 값
			String user_name = request.getParameter("user_name");
			String user_pw = request.getParameter("user_pw");
			
		
			//가져온 이름과 비밀번호로 체크하기
			int usercheck = db.userCheck(user_num,user_name,user_pw);
			
			if(usercheck == 1) {
				System.out.println("유저 정보 체크 확인 성공");

				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('성공적으로 유저정보를 확인하였습니다.');");
				out.println("window.open(\"/Project_KBJ1/MainInfor.jsp\", \"test\", \"top=0, left=0, width=290, height=620, scrollbars=yes, resizable=no ,status=no ,toolbar=no\")");
				out.println("history.back();");
				out.println("</script>");
				
			}else{
				System.out.println("유저 정보 체크 확인 실패");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('해당 아이디의 이름과 비밀번호가 일치하지 않습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//세부 정보 바꾸기 이름, 비밀번호 확인
		else if(subPath.equals("/checkInfor1")) {
			projectDAO db = new projectDAO();
			
			//해당 접속 아이디의 기본키값 가져오기
			String user_num = session.getAttribute("USER_NUM").toString();
			
			//기본키값으로 나오는 이름값과 비밀번호값 대조하기 위해 가져오는 값
			String user_name = request.getParameter("user_name");
			String user_pw = request.getParameter("user_pw");
			
		
			//가져온 이름과 비밀번호로 체크하기
			int usercheck = db.userCheck(user_num,user_name,user_pw);
			
			if(usercheck == 1) {
				System.out.println("유저 세부 정보 체크 확인 성공");

				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('성공적으로 유저정보를 확인하였습니다.');");
				out.println("window.open(\"/Project_KBJ1/MainInfor1.jsp\", \"test\", \"top=0, left=0, width=300, height=600\")");
				out.println("history.back();");
				out.println("</script>");
				
			}else{
				System.out.println("유저 세부 정보 체크 확인 실패");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('해당 아이디의 이름과 비밀번호가 일치하지 않습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//메인 정보 수정하기
		else if(subPath.equals("/changeMainInfor")) {
			projectDAO db = new projectDAO();
			String user_num = session.getAttribute("USER_NUM").toString();
			String user_name = request.getParameter("user_name");
			String user_pw = request.getParameter("user_pw");
			String user_gender = request.getParameter("gender");
			String user_tel = request.getParameter("user_tel");
			String user_email = request.getParameter("user_email");
			String nation_num = request.getParameter("nation_num");
			String born_date = request.getParameter("born_date");
			
			System.out.println("가져온 유저 정보 출력");
			System.out.println("user_num : " + user_num);
			System.out.println("user_name : " + user_name);
			System.out.println("user_pw : " + user_pw);
			System.out.println("user_gender : " + user_gender);
			System.out.println("user_tel : " + user_tel);
			System.out.println("user_email : " + user_email);
			System.out.println("nation_num :" + nation_num);
			System.out.println("born_date : " + born_date);
					
			
			boolean changeMainInfor = db.chMainInfor(user_name,user_pw,user_gender,user_tel,user_email,nation_num,born_date,user_num);
			
			if(changeMainInfor == true) {
				System.out.println("메인 회원정보 수정 성공");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('회원 정보 수정이 완료 되었습니다.');");
				out.println("window.close();");
				out.println("opener.location.reload();");
				out.println("</script>");
				
			}else {
				System.out.println("메인 회원정보 수정 실패");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('회원 정보 수정이 실패하였습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//세부 정보 수정하기
				else if(subPath.equals("/changeSubInfor")) {
					projectDAO db = new projectDAO();
					String user_num = session.getAttribute("USER_NUM").toString();
					String element_name = request.getParameter("element_name");
					String middle_name = request.getParameter("middle_name");
					String high_name = request.getParameter("high_name");
					String univ_name = request.getParameter("univ_name");
					String job_name = request.getParameter("job_name");
					String user_address = request.getParameter("user_address");
					String marriage = request.getParameter("marriage");
					
					System.out.println("가져온 유저 세부 정보 출력");
					System.out.println("user_num : " + user_num);
					System.out.println("element_name : " + element_name);
					System.out.println("middle_name : " + middle_name);
					System.out.println("high_name : " + high_name);
					System.out.println("univ_name : " + univ_name);
					System.out.println("job_name : " + job_name);
					System.out.println("user_address :" + user_address);
					System.out.println("marriage : " + marriage);
							
					
					boolean changeSubInfor = db.chSubInfor(element_name,middle_name,high_name,univ_name,job_name,user_address,marriage,user_num);
					
					if(changeSubInfor == true) {
						System.out.println("세부 회원 정보 수정 성공");
						
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();

						out.print("<script>");
						out.println("alert('세부 회원 정보 수정이 완료 되었습니다.');");
						out.println("window.close();");
						out.println("opener.location.reload();");
						out.println("</script>");
						
					}else {
						System.out.println("세부 회원 정보 수정 실패");
						
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();

						out.print("<script>");
						out.println("alert('세부 회원 정보 수정이 실패하였습니다.');");
						out.println("history.back();");
						out.println("</script>");
					}
				}
		
		//나의 타임라인 등록하기. post
		else if (subPath.equals("/registTimeLine1")) {
			projectDAO db = new projectDAO();
			request.setCharacterEncoding("UTF-8");
			
			ServletContext ctx = getServletContext();

			String realPath = ctx.getRealPath("images");
			System.out.println("실제 서버 경로 주소값 : " + realPath);

			int max = 10 * 1024 * 1024; // 10MB

			System.out.println("request 값 : " + request); // request를 가져오는지 확인하기

			MultipartRequest multi = new MultipartRequest(request, realPath, max, "UTF-8", new DefaultFileRenamePolicy());
			
			if(multi.getFilesystemName("timeLineImage") != null) {
			Enumeration e = multi.getFileNames(); // 파일이름들을 e로 가져온다.

				while (e.hasMoreElements()) {// 정보가 있으면 사용하겠다.
					String fname = (String) e.nextElement();
	
					System.out.println("파일 원본 이름 : " + multi.getOriginalFileName(fname));
					System.out.println("파일 시스템 이름 : " + multi.getFilesystemName(fname));
	
					// 서버에 저장되어있는 파일 정보를 가져온다.
	
					File f = multi.getFile(fname);
					System.out.println("파일 크기 : " + f.length() + "byte");
				}
			}

			System.out.println("타임 라인 등록 값 가져오는것 확인하기");
			System.out.println("con_num : " + multi.getParameter("condition"));
			System.out.println("user_num : " + multi.getParameter("USER_NUM"));
			System.out.println("comments : " + multi.getParameter("comments"));
			System.out.println("image : " + multi.getFilesystemName("timeLineImage")); // 전송하는 파일 가져오기
			System.out.println();

			TimeLine time = new TimeLine();
			time.setCondition_num(Integer.parseInt(multi.getParameter("condition")));
			time.setUser_num(Integer.parseInt(multi.getParameter("USER_NUM")));
			time.setTime_line_comments(multi.getParameter("comments"));
			time.setTime_line_image(multi.getFilesystemName("timeLineImage"));
			time.setUpload_num(Integer.parseInt(multi.getParameter("USER_NUM"))); // 나의 타임라인을 남기는것이므로 user_num과 동일한 값

			boolean registLine = db.registLine(time);

			if (registLine) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('타임 라인 등록이 되었습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('타임 라인 등록이 실패 했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}

		}
		
		// 메인 이미지 변경하기
		else if (subPath.equals("/MainimageUpload")) {
			projectDAO db = new projectDAO();
			
			ServletContext ctx = getServletContext();
			
			String realPath = ctx.getRealPath("User_Image");
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
			
			String image_file = multi.getFilesystemName("image_file");
			String user_num = session.getAttribute("USER_NUM").toString();
			
			boolean MainImage = db.mainImage(image_file,user_num);
			
			if(MainImage == true) {
				System.out.println("메인 이미지 변경 완료");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('메인 이미지 변경이 완료되었습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
				
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('메인 이미지 변경이 실패하였습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}

		// 서브이미지 업로드 여기!
		else if (subPath.equals("/imageUpload1")) {
			projectDAO db = new projectDAO();

			// MultipartRequest : 파일 업로드를 직접적으로 처리하는 클래스
			// cos.jar -> 안에 포함이 되어있다.

			// 현재 실행중인 웹프로젝트에 접근(프로젝트의 정보를 가져오기)
			ServletContext ctx = getServletContext();

			// 업로드(컴퓨터 HDD) 가상 경로
			// /upload => 업로드 폴더 생성(내 포르젝트에서는 sub 이미지를 업로드 할것이기 떄문에 sub_Image 폴더 생성)

			// 업로드할 실제 경로
			// C:\Users\Jamey Aesthetic\eclipse-workspace\Project_KBJ1\WebContent\sub_Image

			// 업로드 실제 서버 경로 가져오기
			String realPath = ctx.getRealPath("sub_Image");
			// String realPath = request.getRealPath("sub_Image");
			System.out.println("실제 서버 경로 주소값 : " + realPath);

			// 업로드 처리가능한 파일의 최대 용량
			int max = 10 * 1024 * 1024; // 10MB

			// 실제 파일 업로드 기능을 처리하는 클래스를 생성하기
			// 첫번째 인자 - form 태그에서 가져온 파일/텍스트 정보를 저장하기 위한 request 객체
			// 두번째 인자 - 업로드될 파일의 위치
			// 세번째 인자 - 업로드 하는 파일의 최대크기(용량)
			// 네번째 인자 - 파일 이름이 한글일 경우에 꺠지는것을 방지하기 위한 인코딩 방식
			// 마지막 인자 - 동일한 파일의 이름이 업로드 될경우, 중복처리가 되지 않도록 자동으로 파일 이름을 변환해주는 기능
			System.out.println(request);

			MultipartRequest multi = new MultipartRequest(request, realPath, max, "UTF-8", new DefaultFileRenamePolicy());
			// 만약 추가 정보가 있을경우
			// 예로 이름, 제목 저장
			// String 이름 = multi.getParameter("이름");
			// String 제목 = multi.getParameter("제목");
			Enumeration e = multi.getFileNames(); // 파일이름들을 e로 가져온다.

			while (e.hasMoreElements()) {// 정보가 있으면 사용하겠다.
				String fname = (String) e.nextElement();

				System.out.println("파일 원본 이름 : " + multi.getOriginalFileName(fname));
				System.out.println("파일 시스템 이름 : " + multi.getFilesystemName(fname));

				// 서버에 저장되어있는 파일 정보를 가져온다.

				File f = multi.getFile(fname);
				System.out.println("파일 크기 : " + f.length() + "byte");
			}
			
			String image_file = multi.getFilesystemName("image_file");
			String user_num = session.getAttribute("USER_NUM").toString();
			
			boolean subImage = db.subImage(image_file,user_num);

			if(subImage == true) {
				System.out.println("세부 이미지 변경 완료");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('세부 이미지 변경이 완료되었습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage';");
				out.println("</script>");
				
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('먼저 세부 정보를 등록하셔야 이미지 등록이 가능합니다.!!!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//친구 리스트 지우기
		else if(subPath.equals("/delFriend")) {
			projectDAO db = new projectDAO();
			String my_num = session.getAttribute("USER_NUM").toString(); //내 번호
			String friend_num = request.getParameter("friend_num");		 //친구 번호
			
			System.out.println("친구 지우기(내번호) : " + my_num + " / (친구 번호) : " + friend_num);
			
			int delFriend = db.delFriend(my_num,friend_num);
			
			if(delFriend == 1) {
				System.out.println("친구 삭제 성공");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('친구 삭제가 완료 되었습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
			}else{
				System.out.println("친구 삭제 실패");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.print("<script>");
				out.println("alert('친구 삭제가 실패 하였습니다.');");
				out.println("location.href='/Project_KBJ1/view/InforPage'");
				out.println("</script>");
			}
			
		}
		
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			/*System.out.println("viewPage가 NULL입니다. 다시 확인하세요.");*/
		}
	}

}
