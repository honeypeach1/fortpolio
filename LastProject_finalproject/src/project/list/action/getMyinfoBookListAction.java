package project.list.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListBean;
import project.list.db.ListDAO;

public class getMyinfoBookListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★execute");
		
		// 로그인 세션 유지
		HttpSession session = request.getSession();
		String user_num = session.getAttribute("user_num").toString();
		System.out.println("getMyinfoBookList with no. " + user_num);
		String user_name = request.getParameter("user_name");
		System.out.println("예약 리스트 받아오기 정보 확인_아이디 : "+user_name);
		
		
		
		
		ListDAO ldao = new ListDAO();
		ListBean lb = new ListBean();
		
		
		// 예약 페이지 정보 불러오기
		ArrayList<ListBean> getBookList = ldao.getBookList(user_num);
		// 예약 페이지 부가옵션 정보 불러오기

		ArrayList<ListBean> getBookOptionList = ldao.getBookOptionList(user_num);
		
		if(getBookList != null){
			System.out.println("예약 페이지 리스트 불러오기 성공");
			
			request.setAttribute("getBookList", getBookList);
			request.setAttribute("getBookOptionList", getBookOptionList);
		} else {
			System.out.println("예약 페이지 리스트 불러오기 실패");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/goToMyPage.do");
		forward.setRedirect(false);
		return forward;

	}

}
