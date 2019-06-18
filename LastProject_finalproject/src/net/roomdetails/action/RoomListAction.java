package net.roomdetails.action;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.hotel.db.HotelBean;
import net.hotel.db.HotelDAO;
import net.roomdetails.db.RoomDetailsDAO;
import project.action.Action;
import project.action.ActionForward;

public class RoomListAction implements Action {

 @Override
 public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
  // TODO Auto-generated method stub

  System.out.println("RoomListAction_execute!");
  // < 필요한 값 처리 >

  // hotel_no 받아오기 *현재는 1로 임의 설정
  int hotel_no = Integer.parseInt(request.getParameter("hotel_no"));
  String hotel_num = request.getParameter("hotel_no");
  
//최근 본 상품 조회하기 위해 쿠키값 생성

		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("roomInfor")) {
				if (hotel_num != cookies[i].getValue()) {
					Cookie info1 = new Cookie("roomInfor1", hotel_num);

					info1.setMaxAge(60 * 60); // 1시간
					info1.setMaxAge(-1); // 메모리에 저장
					info1.setPath("/");
				}
			}
		}

		Cookie info = new Cookie("roomInfor", hotel_num);

		info.setMaxAge(60 * 60); // 1시간
		info.setMaxAge(-1); // 메모리에 저장
		info.setPath("/");

		response.addCookie(info);

		//////////////////////////////////////////////////////////////
  
  
  // < DB 처리 > - 성환
  // 디비 처리 객체 생성
  RoomDetailsDAO rddao = new RoomDetailsDAO();
  

  int user_no;
  String user_num=null;
  HttpSession session = request.getSession();
  String user_image=null;
  
  if (session.getAttribute("user_num") == null) {
   user_no = 0;
  } else {
   
   user_num = session.getAttribute("user_num").toString();
   user_no = Integer.parseInt(user_num);
   user_image = rddao.getUserImage(user_no);
  }

  // 체크인 체크아웃 날짜 임의로 가져온것 - 정현
  /*
   * java.sql.Date check_in = rddao.getDateIn(); java.sql.Date check_out =
   * rddao.getDateOut();
   */
  // String check_in = request.getParameter("check_in");
  // String check_out = request.getParameter("check_out");
  // check_in, check_out, 값 가져오기
  Date check_in;
  Date check_out;

  if (request.getParameter("check_in").equals("")) {
   check_in = new Date(System.currentTimeMillis());
  } else {
   check_in = Date.valueOf(request.getParameter("check_in"));
  }


  if (request.getParameter("check_out").equals("")) {
   check_out = new Date(System.currentTimeMillis() + (long) (1000 * 60 * 60 * 24));
  } else {
   check_out = Date.valueOf(request.getParameter("check_out"));
  }

  // 기본 인원값 받아오기
  int basic_people;

  if (request.getParameter("people_num").equals("")) {
   basic_people = 1;
  } else {
   basic_people = Integer.parseInt(request.getParameter("people_num"));
  }

  // < DB 처리 > - 성환
  // 디비 처리 객체 생성
  /*RoomDetailsDAO rddao = new RoomDetailsDAO();*/

  // 객실 리스트를 가져오는 메서드 생성
  // List roomDetailsList = rddao.getRoomDetailsList(hotel_no);

  /*
   * ==================추가(객실 리스트 가져오기->되면 위에 줄 지우기)==================== -
   * 정현
   */
  List roomTypeList = rddao.getRoomTypeList(hotel_no, check_in, check_out, basic_people);

  // 룸 이미지 가져오기 - 정현
  HashMap roomImageList = rddao.getRoomImage(hotel_no);

  // 현재 호텔에서 숙박한적이 있는지 확인하는 메서드 생성 - 성환
  boolean writeReview;

  if (user_num == null) {
   writeReview = false;
  } else {
   writeReview = rddao.checkBookingState(hotel_no, user_no);
  }

  ////////////////////////////////////////////////// 리뷰 리뷰 - 시작

  // 리뷰 한번에 10개씩 보여주기
  String reviewNum;

  if (request.getParameter("reviewNum") == null) {
   reviewNum = "2";
  } else {
   reviewNum = request.getParameter("reviewNum");
  }
  System.out.println("reviewNum = " + reviewNum);
  int reviewNumInt = Integer.parseInt(reviewNum);

  // 리뷰 리스트를 가져오는 메서드 생성 - 성환
  List reviewList = rddao.getReviewList(hotel_no, reviewNumInt);

  ////////////////////////////////////////////////// 리뷰 리뷰 - 끝

  ///////////////// - 정현
  // 호텔 관련 디비 처리 객체
  HotelDAO hdao = new HotelDAO();

  // 해당 호텔의 정보 가져오기
  HotelBean hb = new HotelBean();
  hb = hdao.getHotel(hotel_no);

  // 호텔의 편의시설 가져오기
  List optionList = hdao.getHotelOptionList(hotel_no);

  // 호텔 사진 가져오기
  List hotelImageList = hdao.getHotelImages(hotel_no);

  /*
   * // ***** id값 임의 처리 나중에 sessin으로 고칠 것! request.setAttribute("user_id",
   * user_id);
   */

  // 정보 저장 request객체 - 성환
  // request.setAttribute("roomDetailsList", roomDetailsList); // 나중에 삭제
  request.setAttribute("reviewList", reviewList);
  request.setAttribute("writeReview", writeReview);
  request.setAttribute("hotel_no", hotel_no);
  request.setAttribute("check_in", check_in);
  request.setAttribute("check_out", check_out);
  request.setAttribute("people_num", basic_people);
  request.setAttribute("reviewNum", reviewNum);

  ///////////////// - 정현
  request.setAttribute("roomTypeList", roomTypeList);
  request.setAttribute("roomImageList", roomImageList);
  request.setAttribute("hb", hb);
  request.setAttribute("optionList", optionList);
  request.setAttribute("hotelImageList", hotelImageList);
  request.setAttribute("user_image", user_image);

  // 페이지이동
  ActionForward forward = new ActionForward();
  forward.setPath("./roomDetail.jsp");
  forward.setRedirect(false);

  return forward;
 }

}