package project.list.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListDAO;
import project.list.db.ListBean;

public class getMainListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ListDAO ldao = new ListDAO();
		
		//top 3 리스트 불러오기 메소드
		ArrayList<ListBean> getList = ldao.getPopularList();
		
		//top 3의 편의 시설 리스트 불러오기 메소드
		ArrayList<ListBean> getFacilityList = ldao.getFacilityList();
		
		
		if(getList != null) {
			System.out.println("메인 TOP3 리스트 모듈 : 메소드 불러오기 성공");
			
			//해당 리스트 속성 생성
			request.setAttribute("getList", getList);
			request.setAttribute("getFacilityList", getFacilityList);
		}else {
			System.out.println("메인 TOP3 리스트 모듈 : 메소드 불러오기 실패");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/mainPage.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
