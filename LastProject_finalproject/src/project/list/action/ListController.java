package project.list.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.user.action.UserPasswordFindAction;

/**
 * Servlet implementation class UserController
 */
/*@WebServlet("/ListController")*/
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 가상 주소 처리
		
		
				/*
				 * 
				 * 
				 *  controller 공통 사항
				 * 
				 * 
				 * */
		
		
				// URI 
				String requestURI = request.getRequestURI();
				System.out.println(" requestURI : "+requestURI);
				// 프로젝트명
				String contextPath = request.getContextPath();
				System.out.println(" contextPath :"+contextPath);
				// 가상주소
				String command = requestURI.substring(contextPath.length());
				System.out.println(" Mapping 주소 : "+command);
				
				Action action = null;
				ActionForward forward = null;
				// 가상주소 비교 페이지 이동
				//해당 URL 주소로 올시 페이지 이동
				
			
				//메인 페이지 이동시
				if(command.equals("/goToMainPage.li")) {
					System.out.println("메인 페이지 이동 controller");
					
					//리스트 가져오기 모듈
					action = new getMainListAction();
					
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//header에 rooms로 페이지 이동
					}else if (command.equals("/goToHotel.li")) {
					System.out.println("Rooms 페이지 이동 controller");
		
					action = new getAllHotelAction();
		
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				//header에 hot deal로 페이지 이동
				}else if(command.equals("/HotDeal.li")) {
					System.out.println("hot deal 페이지 이동 controller");
					
					action = new getHotDealAction();
					
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				//header에 recently view로 페이지 이동
				}else if(command.equals("/goToRecentlyView.li")) {
					System.out.println("recently view 페이지 이동 controller");
					
					//최근 조회를 볼려면 로그인이 되어있어야 함.
					//그래서 비로그인일때는 접근을 막아야 함.
					
					action = new getRecentlyViewAction();
					
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				
				// 페이지 이동처리 
				if(forward != null){ //이동할 정보가 있으면
					if(forward.isRedirect()){
						response.sendRedirect(forward.getPath());
					}else{
						RequestDispatcher dis =
								request.getRequestDispatcher(forward.getPath());
						dis.forward(request, response);						
					}
				}
				
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 호출");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 호출");
		doProcess(request, response);
	}

}
