package project.list.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListBean;
import project.list.db.ListDAO;

public class getHotDealAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//DAO 객체 생성
		ListDAO ldao = new ListDAO();
		
		//hot deal 리스트
		ArrayList<ListBean> getHotDeal = ldao.getHotDeal();
		
		//hot deal의 이미지 리스트
		ArrayList<ListBean> getHotDealImage = ldao.getHotDealImage();
		
		if(getHotDeal != null) {
			System.out.println("HOT DEAL 리스트 가져오기 성공");
			
			request.setAttribute("getHotDeal", getHotDeal);
			request.setAttribute("getHotDealImage", getHotDealImage);
		}else {
			System.out.println("HOT DEAL 리스트 가져오기 실패");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/HotDeal.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
