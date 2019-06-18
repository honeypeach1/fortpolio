package net.bookingpage.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.roomdetails.action.ReviewDeleteAction;
import net.roomdetails.action.ReviewWriteAction;
import net.roomdetails.action.RoomListAction;
import project.action.Action;
import project.action.ActionForward;

public class BookingFrontController extends HttpServlet{
	
	// doProcess(request,response) 시작
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("RoomDetailsFrontController doProcess!");
		
		// 가상주소 체크
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : "+requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println(" contextPath : "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println(" command : "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		
		// --- 가상주소 비교 시작 ---
		
		if(command.equals("/BookingAction.bp")){
			action = new BookingAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(command.equals("/BookAndPaymentAction.bp")){ // 결제 로직
			action = new BookAndPaymentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// --- 가상주소 비교  끝 ---
		
		
		// 페이지 이동 처리
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
	}
	// doProcess(request,response) 끝
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}
