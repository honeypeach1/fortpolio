package project.list.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.list.db.ListBean;
import project.list.db.ListDAO;

public class getRecentlyViewAction implements Action{

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		//////////////////////////////////////////////////////
		//쿠키 정보 가져오기
		Cookie[] cookies = request.getCookies();            // 요청정보로부터 쿠키를 가져온다.

		System.out.println("현재 설정된 쿠키의 개수 : " + cookies.length);    // 쿠키가 저장된 배열의 길이를 가져온다.

		for(int i = 0 ; i<cookies.length; i++){                                 // 쿠키 배열을 반복문으로 돌린다.
		System.out.println(i + "번째 쿠키 이름 : " + cookies[i].getName());    		// 쿠키의 이름을 가져온다.
		System.out.println(i + "번째 쿠키에 설정된 값 : " + cookies[i].getValue());		// 쿠키의 값을 가져온다.
		
		
		//쿠키에 저장된 값들중 호텔넘버 즉, 조회했을때 가져오는 호텔번호들만 가져오기
		if(cookies[i].getName().equals("roomInfor")) {
			String roomInfor = cookies[i].getValue();
		
			System.out.println("========== 쿠키로 가져오는 리스트 ==========");
			System.out.println("쿼리로 가져갈 호텔 넘버 값 : "+roomInfor);
			System.out.println("====================================");
			
			if(cookies[i].getName().equals("roomInfor1")) {
				String roomInfor1 = cookies[i].getValue();
				System.out.println("========== 쿠키로 가져오는 두번째 리스트 ==========");
				System.out.println("쿼리로 가져갈 호텔 넘버 값 : "+roomInfor);
				System.out.println("====================================");
			}
			
			ListDAO ldao = new ListDAO();
			
			
			//가져온 쿠키값으로 호텔 리스트 가져오기
			ArrayList<ListBean> recentlyHotel = ldao.recentlyHotel(roomInfor);
			ArrayList<ListBean> recentlyFacility = ldao.recentlyFacility(roomInfor);
			
			if(recentlyHotel != null) {
				System.out.println("RecentlyViewed 페이지 리스트 불러오기 성공");
				
				request.setAttribute("recentlyHotel", recentlyHotel);
				request.setAttribute("recentlyFacility", recentlyFacility);
				
				
				ActionForward forward = new ActionForward();
				forward.setPath("/RecentView.jsp");
				forward.setRedirect(false);
				return forward;
			}
		}
		}
			
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println(" alert('최근 조회상품이 없습니다.'); ");
		out.println(" history.back(); ");
		out.println("</script>");
		out.close();
		
		return null;
	}

}
