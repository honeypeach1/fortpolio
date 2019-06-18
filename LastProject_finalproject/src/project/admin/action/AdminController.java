package project.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;
import project.list.action.getAllHotelAction;
import project.user.action.getAdminAction;

/**
 * Servlet implementation class UserController
 */
/*@WebServlet("/AdminController")*/
public class AdminController extends HttpServlet {
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
				
				
				//관리자 호텔 정보 헤더 클릭시 이동 매핑 주소
				if(command.equals("/adHotelInfor.ad")) {
					System.out.println("관리자 Hotel Infor 페이지 이동 controller");
					
					action = new AdminHotelInforAction();
		
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//header에 about로 페이지 이동(관리자)
				}else if(command.equals("/adminAbout.ad")) {
					System.out.println("About 페이지 이동(관리자) controller");
						
					action = new getAdminAction();
						
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//header에 Approval Hotel로 페이지 이동(관리자)
				}else if(command.equals("/adAppHotelInfor.ad")) {
					System.out.println("Approval Hotel 페이지로 이동(관리자) controller");
					
					action = new getAppHotelAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				//About페이지에서 관리자 정보 바꾸기
				}else if(command.equals("/ChangeAdminInfor.ad")) {
					System.out.println("About 페이지에서 관리자 정보(코멘트) 바꾸기 controller");
					
					action = new ChangeAdminInforAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				//About페이지에서 관리자 외 정보들 바꾸기	
				}else if(command.equals("/ChangeAdminInfors.ad")) {
					System.out.println("About 페이지에서 관리자 정보(주소,이메일,전화번호) 바꾸기 controller");
					
					action = new ChangeAdminInforsAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//About페이지에서 관리자의 사진 정보 바꾸기
				}else if(command.equals("/ChangeAdminImage.ad")) {
					System.out.println("About 페이지에서 관리자 사진 바꾸기 controller");
					
					action = new ChangeAdminImageAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//header의 Approval Hotel탭에서 호텔 승인하기
				}else if(command.equals("/AppHotel.ad")) {
					System.out.println("Approval Hotel탭에서 호텔 승인하기 controller");
					
					action = new AppHotelAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//header의 Approval Hotel탭에서 호텔 승인 거절하기
				}else if(command.equals("/DenyHotel.ad")) {
					System.out.println("Approval Hotel탭에서 호텔 승인 거절 하기 controller");
					
					action = new DenyHotelAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//header의 All Hotel탭에서 호텔 삭제 하기
				}else if(command.equals("/adHotelInforRemove.ad")) {
					System.out.println("All Hotel탭에서 해당 호텔 삭제 하기 controller");
					
					action = new HotelInforRemoveAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				//Approval Hotel의 호텔 세부정보 보기
				}else if(command.equals("/AppHotelRoomDetails.ad")) {
					System.out.println("Approval Hotel탭에서 호텔의 세부 정보 보기 controller");
					
					action = new AppHotelRoomAction();
					
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
