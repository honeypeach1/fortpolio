package net.project.hotellist.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HotelsController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URI 
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : "+requestURI);
		String contextPath = request.getContextPath();
		System.out.println(" contextPath :"+contextPath);
		String command = requestURI.substring(contextPath.length());
		System.out.println(" command : "+command);
				
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/goToListPageSearch.hdo")){
			System.out.println("goToListPageSearch.hdo controller");
			
			action = new getHotelSearhListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/ajaxdataInfo.hdo")){
			System.out.println("ù��° Ajax �̵� controller");
			action = new ajaxdataInfoAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/ajaxdataFac.hdo")){
			System.out.println("�ι�° Ajax �̵� controller");
			action = new ajaxdataFacAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// ������ �̵� ó��
		if(forward != null){
			if(forward.isRedirect()){
					response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dis 
					= request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				}
			}
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}
