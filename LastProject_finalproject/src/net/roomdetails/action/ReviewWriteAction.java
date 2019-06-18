package net.roomdetails.action;

import project.action.Action;
import project.action.ActionForward;

import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.roomdetails.db.RoomDetailsBean;
import net.roomdetails.db.RoomDetailsDAO;

public class ReviewWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ReviewWriteAction_execute!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		 HttpSession session = request.getSession();
		  String user_num = session.getAttribute("user_num").toString();
		  int user_no = Integer.parseInt(user_num);
		
		// < 필요한 값 처리 >
		// hotel_no 받아오기 *현재는 1로 임의 설정
		int hotel_no = Integer.parseInt(request.getParameter("hotel_no"));
		Date check_in = Date.valueOf(request.getParameter("check_in"));
		Date check_out = Date.valueOf(request.getParameter("check_out"));
		int basic_people = Integer.parseInt(request.getParameter("people_num"));
		
		
		
		// user_id, user_no 받아오기 *현재는 Parameter값으로 받아옴 -> 추 후 session값 받아오는것으로 수정할 것
/*		String user_id = request.getParameter("user_id");
		int user_no = 1; // session으로 받아올 것!!!
*/		
		// RoomDetailsBean 객체 생성
		RoomDetailsBean rdb = new RoomDetailsBean();
		
		rdb.setReview_star(Integer.parseInt(request.getParameter("review_star")));
		rdb.setReview_content(request.getParameter("review_content"));
		
		// RoomDatailsDAO 객체 생성
		RoomDetailsDAO rddao = new RoomDetailsDAO();
		
		// 리뷰 쓰기 메서드
		rddao.insertReview(rdb, user_no, hotel_no);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./RoomDetails.rd?hotel_no="+hotel_no+"&check_in="+check_in+"&check_out="+check_out+"&people_num="+basic_people);
		forward.setRedirect(true);
		return forward;
		
	}
	

}
