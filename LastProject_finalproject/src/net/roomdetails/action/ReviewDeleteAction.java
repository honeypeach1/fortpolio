package net.roomdetails.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.roomdetails.db.RoomDetailsDAO;
import project.action.Action;
import project.action.ActionForward;

public class ReviewDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ReviewDeleteAction_execute!!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
	
		 HttpSession session = request.getSession();
		  String user_num = session.getAttribute("user_num").toString();
		  int user_no = Integer.parseInt(user_num);

		// 파라미터값 받아오기
		/*String user_id = request.getParameter("user_id");*/
		/*int user_no = 1;*/ //세션으로 박아오기
		  int hotel_no = Integer.parseInt(request.getParameter("hotel_no"));
		  Date check_in = Date.valueOf(request.getParameter("check_in"));
		  Date check_out = Date.valueOf(request.getParameter("check_out"));
		  int basic_people = Integer.parseInt(request.getParameter("people_num"));
		String user_pw = request.getParameter("user_pw");
		int review_no = Integer.parseInt(request.getParameter("review_no"));
		
		// RoomDetailsDAO 객체 생성
		RoomDetailsDAO rddao = new RoomDetailsDAO();
		
		// deleteReview(user_id, user_pw, review_no) : 리뷰 삭제 메서드
		int checkDltRv = rddao.deleteReview(user_no, user_pw, review_no);
		
		if(checkDltRv == 0){
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println(" <script> ");
			out.println(" alert('비밀번호 오류'); ");
			out.println(" history.back(); ");
			out.println(" </script> ");
			out.close();
			
		}else if(checkDltRv == -1){
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println(" <script> ");
			out.println(" alert('id 없음 : 잘못된 접근입니다.'); ");
			out.println(" history.back(); ");
			out.println(" </script> ");
			out.close();
			
		}
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println(" <script> ");
		out.println(" alert('리뷰 삭제 완료'); ");
		// **** 뒤에 호텔정보 값이 있어야 겠지 -> 추 후 수정할 것
		out.println(" location.href='./RoomDetails.rd?hotel_no="+hotel_no+"&check_in="+check_in+"&check_out="+check_out+"&people_num="+basic_people+"#review'; ");
		out.println(" </script> ");
		out.close();
		
		
		return null;
	}
	
	
}
