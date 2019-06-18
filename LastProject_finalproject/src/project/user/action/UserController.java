package project.user.action;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.action.getMainListAction;

/**
 * Servlet implementation class UserController
 */
/* @WebServlet("/UserController") */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 가상 주소 처리

		// URI
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : " + requestURI);
		// 프로젝트명
		String contextPath = request.getContextPath();
		System.out.println(" contextPath :" + contextPath);
		// 가상주소
		String command = requestURI.substring(contextPath.length());
		System.out.println(" Mapping 주소 : " + command);

		Action action = null;
		ActionForward forward = null;
		// 가상주소 비교 페이지 이동
		// 해당 URL 주소로 올시 페이지 이동

		// 로그인 페이지로 이동처리
		if (command.equals("/goToLoginPage.do")) {
			System.out.println("로그인 페이지 이동 controller");

			forward = new ActionForward();
			forward.setPath("./login.jsp");
			forward.setRedirect(false);
		}

		// 로그인 처리
		else if (command.equals("/userLogin.do")) {
			System.out.println("로그인 처리 controller");
			action = new UserLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 로그아웃 처리
		} else if (command.equals("/UserLogout.do")) {
			System.out.println("로그아웃 처리 controller");
			action = new UserLogout();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 회원가입 처리
		} else if (command.equals("/userSignUp.do")) {
			System.out.println("회원가입 controller");

			action = new UserSignUpAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 아이디 찾기
		} else if (command.equals("/FindAccount.do")) {
			System.out.println("아이디 찾기 controller");

			action = new UserAccountFindAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*
			 * //해당 주소 이동을 모듈에서 처리함.(팝업에서 팝업창 띄우기라) //아이디 찾기 가져오기 페이지(팝업창) }else
			 * if(command.equals("/Find/goToAccAfter.do")) {
			 * System.out.println("아이디 찾기 가져오기 controller");
			 * 
			 * forward = new ActionForward();
			 * forward.setPath("./FindAccountAfter.jsp");
			 * forward.setRedirect(false); }
			 */

			// 비밀번호 찾기
		} else if (command.equals("/FindPass.do")) {
			System.out.println("비밀번호 찾기 controller");

			action = new UserPasswordFindAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// header에 MyPage로 페이지 이동
		} else if (command.equals("/goToMyPage.do")) {
			System.out.println("MyPage 페이지 이동 controller");

			action = new getMyInforAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// header에 about로 페이지 이동(비로그인, 일반 유저)
		} else if (command.equals("/goToAbout.do")) {
			System.out.println("About 페이지 이동(일반 유저, 비로그인) controller");

			action = new getAboutAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// About 페이지에서 관리자에게 메일 보내기
		} else if (command.equals("/SendMail.do")) {
			System.out.println("about페이지의 메일 보내기 controller");

			action = new SendMailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 유저 정보 불러오기
		} else if (command.equals("/MyinfoUpdate.do")) {
			System.out.println("Mypage 정보 수정 controller");

			action = new MyinfoUpdate();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 유저 정보 불러오기
		} else if (command.equals("/MyinfoUpdate.do")) {
			System.out.println("Mypage 정보 수정 controller");

			action = new MyinfoUpdate();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 유저 정보 수정하기
		} else if (command.equals("/MyinfoUpdateAction.do")) {
			System.out.println("Mypage 정보 수정 처리 controller");

			action = new MyinfoUpdateAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 탈퇴 정보 불러오기
		} else if (command.equals("/MyinfoDeletePopup.do")) {
			System.out.println("회원 탈퇴 정보 불러오기 controller");
			action = new MyinfoDeletePopup();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 회원탈퇴 페이지 실행
		} else if (command.equals("/MyinfoDelete.do")) {
			System.out.println("MyPage 삭제 팝업창 실행 controller");

			action = new MyinfoDeletePopup();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 회원탈퇴 구문 실행
		} else if (command.equals("/MyinfoDeleteAction.do")) {
			// new MemberDeleteAction
			action = new MyinfoDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Mypage 예약 취소 처리
		} else if (command.equals("/MyinfoBookCancel.do")) {
			action = new MyinfoBookCancel();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 비회원 호텔 승인 확인하기
		} else if (command.equals("/checkHotel.do")) {
			System.out.println("비회원 호텔 승인 확인 하기 controller");

			action = new checkHotelAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 회원 사진정보 바꾸기
		else if (command.equals("/ChangePfImage.do")) {

			action = new ChangeProfileImage();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 페이지 이동처리
		if (forward != null) { // 이동할 정보가 있으면
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get 호출");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post 호출");
		doProcess(request, response);
	}

}
