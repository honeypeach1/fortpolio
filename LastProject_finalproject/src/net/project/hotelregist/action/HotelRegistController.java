package net.project.hotelregist.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.action.Action;
import project.action.ActionForward;

public class HotelRegistController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess �샇異�!");
	
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);

		
		String contextPath = request.getContextPath();
		System.out.println(contextPath);

		System.out.println("contextPath.length() : " + contextPath.length());
		String command = requestURI.substring(contextPath.length());
		System.out.println(" 가상 주소 : " + command);

		ActionForward forward = null;
		Action action = null;
		
		System.out.println("regis controller");
		
			if(command.equals("/regisAction.bo")){
				
				System.out.println("여기옴");
				action = new regisAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}else if(command.equals("/regisRoomAction.bo")){
				action = new regisRoomAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			

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
		System.out.println("Get 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post 호출");
		doProcess(request, response);
	}
	
}
