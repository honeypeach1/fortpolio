package net.roomdetails.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bookingpage.action.getBookingPaymentInfoAction;
import project.action.ActionForward;
import project.action.Action;

public class RoomDetailsFrontController extends HttpServlet{
	
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
		
		if(command.equals("/RoomDetails.rd")){ // 상품등록 페이지 -> roomDetail.jsp
			action = new RoomListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/ReviewWriteAction.rd")){ // 리뷰 쓰기
			action = new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/ReviewDeleteAction.rd")){ // 리뷰 삭제
			action = new ReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getBookingPaymentInfoAction.rd")){ // 결제 페이지
			action = new getBookingPaymentInfoAction();
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
