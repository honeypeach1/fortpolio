package net.roomdetails.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.roomdetails.db.RoomTypeBean;

public class RoomDetailsDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// getConnection() : 디비 연결 (디비 드라이버 설치 & 연결)
	public Connection getConnection() throws Exception {

		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/hotels");

		con = ds.getConnection();

		return con;
	}
	// getConnection() : 디비 연결 (디비 드라이버 설치 & 연결) 끝

	// closeDB() : 자원 해제
	public void closeDB() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// closeDB() : 자원 해제 끝

	// -------------------------------------------------------------------------

	// getRoomTypeList : 호텔별로 방 종류 가져오기 - 시작 - 정현
	public List getRoomTypeList(int hotel_no, Date check_in, Date check_out, int basic_people) {
		List roomTypeList = new ArrayList();

		try {
			con = getConnection();

			/*
			 * sql =
			 * "select room_type_no, hotel_no, room_name, room_basic_people, room_max_people, room_info, room_price "
			 * +"from room_type_table " +" where hotel_no=?";
			 */
			// , FORMAT(room_price,0) sql문에 추가하기

			/*
			 * <workbench 용> select t.room_type_no, t.hotel_no, t.room_name,
			 * t.room_basic_people, t.room_max_people, t.room_info,
			 * t.room_price, count(*) as totalRoom from room_type_table t join
			 * room_table r on t.room_type_no=r.room_type_no where hotel_no=1
			 * and room_basic_people >= 5 group by t.room_type_no;
			 */

			sql = "select t.room_type_no, t.hotel_no, t.room_name, t.room_basic_people, t.room_max_people, t.room_info, t.room_price, FORMAT(t.room_price, 0), count(*) as totalRoom "
					+ "from room_type_table t join room_table r " + "on t.room_type_no=r.room_type_no "
					+ "where hotel_no=? " + "and room_basic_people >= ? " + "group by t.room_type_no";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotel_no);
			pstmt.setInt(2, basic_people);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomTypeBean rtb = new RoomTypeBean();

				rtb.setRoom_type_no(rs.getInt("t.room_type_no"));
				rtb.setHotel_no(rs.getInt("t.hotel_no"));
				rtb.setRoom_name(rs.getString("t.room_name"));
				rtb.setRoom_basic_people(rs.getInt("t.room_basic_people"));
				rtb.setRoom_max_people(rs.getInt("t.room_max_people"));
				rtb.setRoom_info(rs.getString("t.room_info"));
				rtb.setRoom_price(rs.getInt("t.room_price"));
				rtb.setRoom_price_str(rs.getString("FORMAT(t.room_price, 0)"));

				// =============================================================================

				ResultSet rs2 = null;

				System.out.println("=================<1>");
				System.out.println(rs.getInt("t.room_type_no") + "에 해당하는 전체 방 갯수 : " + rs.getInt("totalRoom"));

				/*
				 * select count(*) as reservedRoom from booking_table where
				 * (((DATE_FORMAT(DATE_ADD(?,interval 15 hour),'%Y-%c-%d %T')
				 * between booking_enter_date and booking_leave_date) or
				 * (DATE_FORMAT(DATE_ADD(?,interval 11 hour),'%Y-%c-%d %T')
				 * between booking_enter_date and booking_leave_date)) and
				 * room_type_no=1 and (booking_state=2 or booking_state=1);
				 */

				sql = "select count(*) as reservedRoom " + "from booking_table "
						+ "where ((DATE_FORMAT(DATE_ADD(?,interval 15 hour),'%Y-%c-%d %T') between booking_enter_date and booking_leave_date) or (DATE_FORMAT(DATE_ADD(?,interval 11 hour),'%Y-%c-%d %T') between booking_enter_date and booking_leave_date)) "
						+ "and room_type_no=? " + "and (booking_state=2 or booking_state=1)";

				pstmt = con.prepareStatement(sql);
				pstmt.setDate(1, check_in);
				pstmt.setDate(2, check_out);
				pstmt.setInt(3, rs.getInt("t.room_type_no"));

				rs2 = pstmt.executeQuery();

				if (rs2.next()) {

					System.out.println("=================<1>");
					System.out.println(rs.getInt("t.room_type_no") + "에 해당하는 예약된 방 갯수 : " + rs2.getInt("reservedRoom"));

					int left_room = rs.getInt("totalRoom") - rs2.getInt("reservedRoom");

					System.out.println("전체방 - 예약된 방(leftRoom) : " + left_room);

					rtb.setLeft_room(left_room);
				}

				roomTypeList.add(rtb);
				System.out.println("<1> roomTypeList에 room_type_no: " + rs.getInt("room_type_no") + "에 해당하는 정보 저장 완료");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return roomTypeList;
	}
	// getRoomTypeList : 호텔별로 방 종류 가져오기 - 끝

	// getRoomOptionList(room_type_no) : 호텔의 룸 별로 선택 옵션 List 불러오기 - 시작 - 성환
	public List getRoomOptionList(int room_type_no) {

		List roomOptionList = new ArrayList();

		try {
			con = getConnection();
			System.out.println("getRoomOptionList(room_no) DB 연결 완료");

			// sql = "select * from room_option_table where room_no=?";
			sql = "select * from room_option_table where room_type_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, room_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomTypeBean rtb = new RoomTypeBean();

				rtb.setRoom_option_no(rs.getInt("room_option_no"));
				rtb.setRoom_type_no(rs.getInt("room_type_no"));
				// rtb.setRoom_no(rs.getInt("room_no"));
				rtb.setRoom_option_name(rs.getString("room_option_name"));
				rtb.setOption_add_price(rs.getInt("option_add_price"));

				roomOptionList.add(rtb);
				System.out
						.println("roomOptionList에 room_option_no: " + rs.getInt("room_option_no") + "에 해당하는 정보 저장 완료");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("getRoomOptionList(room_no) 자원해제 완료");
		}

		return roomOptionList;
	}
	// getRoomOptionList(room_no) : 호텔의 룸 별로 옵션 List 불러오기 - 끝

	// --< 리뷰 관련 메서드 > -시작
	// ---------------------------------------------------------------------------

	// getReviewList(hotel_no) : 리뷰 불러오기 - 시작 - 성환
	public List getReviewList(int hotel_no, int reviewNumInt) {

		List reviewList = new ArrayList();

		try {
			con = getConnection();
			System.out.println("getReviewList(hotel_no) DB 연결 완료");

			/*
			 * select review.review_no, review.review_star,
			 * review.review_content, review.review_input_date, user.user_id,
			 * user.user_no from review_table review join booking_table booking
			 * on review.booking_no = booking.booking_no join user_table user on
			 * booking.user_no = user.user_no where review.hotel_no=1 order by
			 * review.review_input_date desc limit 10;
			 */

			sql = "select review.review_no, review.review_star, review.review_content, review.review_input_date, user.user_id, user.user_no, user.user_image "
					+ "from review_table review " + "join booking_table booking "
					+ "on review.booking_no = booking.booking_no " + "join user_table user "
					+ "on booking.user_no = user.user_no " + "where review.hotel_no=? "
					+ "order by review.review_input_date desc " + "limit ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotel_no);
			pstmt.setInt(2, reviewNumInt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomDetailsBean rdb = new RoomDetailsBean();

				rdb.setReview_no(rs.getInt("review.review_no"));
				rdb.setReview_star(rs.getInt("review.review_star"));
				rdb.setReview_content(rs.getString("review.review_content"));
				rdb.setReview_input_date(rs.getDate("review.review_input_date"));
				rdb.setUser_id(rs.getString("user.user_id"));
				rdb.setUser_no(rs.getInt("user.user_no"));
				rdb.setUser_image(rs.getString("user.user_image"));

				System.out.println("**************DAO user_image : " + rs.getString("user.user_image"));

				reviewList.add(rdb);

			}
			System.out.println("reviewList에  review 정보 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("getReviewList(hotel_no) 자원해제 완료");
		}

		return reviewList;
	}
	// getReviewList(hotel_no) : 리뷰 불러오기 - 끝

	// checkBookingState(hotel_no, user_no) : 현재 호텔에서 숙박한적이 있는지 확인하는 메서드 - 시작 -
	// 성환
	public boolean checkBookingState(int hotel_no, int user_no) {

		/*
		 * 현재 호텔에서 결제완료한 예약(booking_state=2)이 있는 경우에만 리뷰를 쓸 수 있게 하였습니다. 또한 가장
		 * 숙박을 완료한 날짜로 부터 60일이 지난 경우에는 리뷰를 쓸 수 없게 하였습니다. 리뷰를 쓰면 booking_state는
		 * 5(리뷰작성 상태)로 변경됩니다.
		 * 
		 * 예시) <booking_table> (참고) booking_state = 예약:1, 결제완료:2, 환불:3, 체크아웃
		 * 지남:4, 리뷰작성:5 bk_no | user_no | room_no | ... | bk_leave_date |
		 * bk_state 1 1 1 2019-04-01 4 2 1 2 2019-04-08 3 3 1 3 2019-04-26 4 4 1
		 * 2 2019-04-20 4
		 * 
		 * 1. booking_state가 4이면서 체크아웃 한지 60일이 지나지 않은 튜플 중 가장 오래된 튜플을 불러옴 (이 경우
		 * booking_no가 1인 튜플) -> 해당 튜플이 없을 경우 페이지에서 리뷰쓰기 부분이 나타나지 않음 -> 해당 튜플이
		 * 있을 경우 리뷰쓰기 부분이 나타나며 리뷰를 쓰게 되면 해당 예약의 booking_state가 5(리뷰작성 상태)로 변경
		 * 
		 */

		// 리뷰를 쓸 수 있는지 체크하는 변수
		boolean writeReview = false;

		try {
			con = getConnection();
			System.out.println("checkBookingState(hotel_no, user_no) DB 연결 완료");

			/*
			 * select book.booking_no, book.booking_state,
			 * book.booking_leave_date,
			 * 60-(to_days(now())-to_days(book.booking_leave_date))
			 * remaining_days from booking_table book join user_table user on
			 * book.user_no = user.user_no join room_table room on book.room_no
			 * = room.room_no join room_type_table rtype on room.room_type_no =
			 * rtype.room_type_no join hotel_table hotel on rtype.hotel_no =
			 * hotel.hotel_no where user.user_no=1 and hotel.hotel_no=1 and
			 * book.booking_state=2 and
			 * 60-(to_days(now())-to_days(book.booking_leave_date)) >= 0 order
			 * by book.booking_leave_date asc limit 1;
			 */

			sql = "select book.booking_no, book.booking_state, book.booking_leave_date, 60-(to_days(now())-to_days(book.booking_leave_date)) remaining_days "
					+ "from booking_table book " + "join user_table user " + "on book.user_no = user.user_no "
					+ "join room_table room " + "on book.room_no = room.room_no " + "join room_type_table rtype "
					+ "on room.room_type_no = rtype.room_type_no " + "join hotel_table hotel "
					+ "on rtype.hotel_no = hotel.hotel_no " + "where user.user_no=? " + "and hotel.hotel_no=? "
					+ "and booking_state=2 " + "and 60-(to_days(now())-to_days(book.booking_leave_date)) >= 0 "
					+ "order by book.booking_leave_date asc " + "limit 1";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, hotel_no);
			rs = pstmt.executeQuery();

			if (rs.next() == true) {
				System.out.println("remaining_days: " + rs.getInt("remaining_days"));
				writeReview = true;
			}
			System.out.println("writeReview : " + writeReview);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("checkBookingState(hotel_no, user_no) 자원해제 완료");
		}

		return writeReview;
	}
	// checkBookingState(hotel_no, user_no) : 현재 호텔에서 숙박한적이 있는지 확인하는 메서드 - 끝

	// insertReview(rdb) : 리뷰 쓰기 - 시작 - 성환
	public void insertReview(RoomDetailsBean rdb, int user_no, int hotel_no) {

		int review_no = 0;
		int getReview_no = 0;

		try {
			con = getConnection();
			System.out.println("insertReview(rdb) DB 연결 완료");

			// review_no(review_table의 pk값) 계산하기
			sql = "select max(review_no) from review_table";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				review_no = rs.getInt("max(review_no)") + 1;
			}

			// 리뷰 insert
			// 결제 후 체크아웃 날짜로 부터 60일 지나기 전의 예약목록 중 가장 오래된 예약 상태를 리뷰작성(5) 상태로 변경하기

			/*
			 * 결제 후 체크아웃 날짜로 부터 60일 지나기 전의 예약목록 중 가장 오래된 예약 목록의 bookin_no 가져오기
			 * select book.booking_no, book.booking_state,
			 * book.booking_leave_date,
			 * 60-(to_days(now())-to_days(book.booking_leave_date))
			 * remaining_days from booking_table book join user_table user on
			 * book.user_no = user.user_no join room_table room on book.room_no
			 * = room.room_no join room_type_table rtype on room.room_type_no =
			 * rtype.room_type_no join hotel_table hotel on rtype.hotel_no =
			 * hotel.hotel_no where user.user_no=1 and hotel.hotel_no=1 and
			 * booking_state=4 and
			 * 60-(to_days(now())-to_days(book.booking_leave_date)) >= 0 order
			 * by book.booking_leave_date asc limit 1;
			 */

			sql = "select book.booking_no, book.booking_state, book.booking_leave_date, 60-(to_days(now())-to_days(book.booking_leave_date)) remaining_days "
					+ "from booking_table book " + "join user_table user " + "on book.user_no = user.user_no "
					+ "join room_table room " + "on book.room_no = room.room_no " + "join room_type_table rtype "
					+ "on room.room_type_no = rtype.room_type_no " + "join hotel_table hotel "
					+ "on rtype.hotel_no = hotel.hotel_no " + "where user.user_no=? " + "and hotel.hotel_no=? "
					+ "and booking_state=2 " + "and 60-(to_days(now())-to_days(book.booking_leave_date)) >= 0 "
					+ "order by book.booking_leave_date asc " + "limit 1";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, hotel_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				getReview_no = rs.getInt("book.booking_no");

				// 리뷰 insert 하기
				sql = "insert into review_table(review_no, booking_no,hotel_no, review_star, review_content, review_input_date) "
						+ "values(?,?,?,?,?,now())";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, review_no);
				pstmt.setInt(2, getReview_no);
				pstmt.setInt(3, hotel_no);
				pstmt.setInt(4, rdb.getReview_star());
				pstmt.setString(5, rdb.getReview_content());

				pstmt.executeUpdate();
				System.out.println("review_no : " + review_no + "에 해당하는 리뷰 insert 완료");

				// 가져온 bookin_no에 해당하는 booking_state를 2(결제완료 상태)에서 5(리뷰작성 상태)로
				// 변경
				sql = "update booking_table " + "set booking_state = 5 " + "where booking_no=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, getReview_no);

				pstmt.executeUpdate();
				System.out.println("review_no: " + getReview_no + "에 해당하는 booking_state 2 -> 5 변경 완료");

			} else {
				System.out.println("getReview_no 없음. 메소드 종료");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("insertReview(rdb) 자원해제 완료");
		}
	}
	// insertReview(rdb) : 리뷰 쓰기 - 끝

	// deleteReview() : 리뷰 삭제 - 시작 - 성환
	public int deleteReview(int user_no, String user_pw, int review_no) {

		int checkDltRv = -1;
		// -1: id가 없을 경우, 0: 비밀번호 불일치, 1: 비밀번호 일치->리뷰 삭제

		try {
			con = getConnection();
			System.out.println("deleteReview(user_no, user_pw, review_no) DB 연결 완료");

			// 입력한 user_pw 일치하는지 확인하기
			sql = "select user_pw from user_table where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (user_pw.equals(rs.getString("user_pw"))) { // 비밀번호가 일치하는 경우

					sql = "delete from review_table where review_no=?";

					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, review_no);
					pstmt.executeUpdate();

					checkDltRv = 1;

				} else { // 비밀번호 불일치
					checkDltRv = 0;
				}
			}
			System.out.println("checkDltRv : " + checkDltRv + " (-1:id 없음, 0:pw불일치, 1:pw일치->리뷰 삭제)");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("deleteReview(user_no, user_pw, review_no) 자원해제 완료");
		}

		return checkDltRv;
	}
	// deleteReview() : 리뷰 삭제 - 시작

	// --< 리뷰 관련 메서드 > - 끝
	// ---------------------------------------------------------------------------

	////////////////////////////////////////////////////////////// 정현

	// getLeftRoom(room_name) : 룸 종류별로 남는 방 가져오기 - 시작 => 혹시 디비 바뀌면 룸 정보 가져오는 부분에
	// 추가하면됨
	/*
	 * public List getLeftRoom(int hotel_no, Date check_in, Date check_out){
	 * List leftRoomList = new ArrayList();
	 * System.out.println("checkin : "+check_in);
	 * System.out.println("checkout : "+check_out);
	 * 
	 * try { con = getConnection();
	 * 
	 * // 룸 타입에 해당하는 전체 방 갯수 sql =
	 * "select r.room_type_no, count(*) as totalRoom " +
	 * "from room_table r join room_type_table t " +
	 * "on r.room_type_no=t.room_type_no " + "where hotel_no=? " +
	 * "group by r.room_type_no";
	 * 
	 * pstmt = con.prepareStatement(sql); pstmt.setInt(1, hotel_no); rs =
	 * pstmt.executeQuery();
	 * 
	 * 
	 * while(rs.next()){ ResultSet rs2 = null;
	 * 
	 * System.out.println("=================<1>");
	 * System.out.println(rs.getInt("r.room_type_no")+"에 해당하는 전체 방 갯수 : "+rs.
	 * getInt("totalRoom"));
	 * 
	 * 
	 * sql = "select room_type_no, count(*) as reservedRoom " +
	 * "from booking_table " +
	 * "where (booking_enter_date between ? and ?) and booking_state=2 and room_type_no=?"
	 * ;
	 * 
	 * pstmt = con.prepareStatement(sql); pstmt.setDate(1, check_in);
	 * pstmt.setDate(2, check_out); pstmt.setInt(3,
	 * rs.getInt("r.room_type_no"));
	 * 
	 * rs2 = pstmt.executeQuery();
	 * 
	 * if(rs2.next()){ RoomTypeBean rtb = new RoomTypeBean();
	 * System.out.println("=================<1>");
	 * System.out.println(rs.getInt("r.room_type_no")+"에 해당하는 예약된 방 갯수 : "+rs2.
	 * getInt("reservedRoom"));
	 * 
	 * 
	 * int left_room = rs.getInt("totalRoom")-rs2.getInt("reservedRoom");
	 * 
	 * System.out.println("전체방 - 예약된 방(leftRoom) : "+ left_room);
	 * 
	 * rtb.setRoom_type_no(rs.getInt("room_type_no"));
	 * rtb.setLeft_room(left_room);
	 * 
	 * leftRoomList.add(rtb); }
	 * 
	 * }
	 * 
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { closeDB(); }
	 * 
	 * 
	 * 
	 * return leftRoomList; }
	 */

	// getLeftRoom(room_name) : 룸 종류별로 남는 방 가져오기 - 끝

	/* ====================객실 이미지 가져오기========================= */
	// getRoomImage(room_type_no) : 객실 이미지 가져오기 - 시작 - 정현
	public HashMap getRoomImage(int hotel_no) {
		// List roomImageList = new ArrayList();
		HashMap roomImageList = new HashMap();

		try {

			con = getConnection();

			sql = "select room_type_no " + "from room_type_table " + "where hotel_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotel_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("room_type_no") + "에 해당하는 이미지 가져오기");

				ResultSet rs2 = null;
				List roomImage = new ArrayList();

				sql = "select * " + "from room_pic_table " + "where room_type_no=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("room_type_no"));
				rs2 = pstmt.executeQuery();

				while (rs2.next()) {
					System.out.println(rs2.getString("room_pic") + "추가!");

					roomImage.add(rs2.getString("room_pic"));
				}

				roomImageList.put(rs.getInt("room_type_no"), roomImage);
				System.out.println("맵에 추가");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return roomImageList;
	}

	// getRoomImage(room_type_no) : 객실 이미지 가져오기 - 끝

	// 임시 체크인 날짜가져오기 - 정현
	public Date getDateIn() {
		Date d = null;

		try {
			con = getConnection();

			sql = "select booking_enter_date " + "from booking_table " + "where booking_no=3";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				d = rs.getDate("booking_enter_date");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return d;
	}

	// 임시 체크아웃 날짜가져오기 - 정현
	public Date getDateOut() {
		Date d = null;

		try {
			con = getConnection();

			sql = "select booking_leave_date " + "from booking_table " + "where booking_no=3";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				d = rs.getDate("booking_leave_date");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return d;
	}

	public String getUserImage(int user_no) {
		String user_image = null;
		try {
			con = getConnection();

			sql = "select user_image " + "from user_table " + "where user_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("RoomDetailsDAO.java getUserImage : " + rs.getString("user_image"));
				user_image = rs.getString("user_image");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_image;
	}

	// 객실 이미지 불러오기
	/*
	 * public List getRoomImage2(int room_type_no){
	 * 
	 * List getRoomImg = null;
	 * 
	 * try { con = getConnection();
	 * System.out.println("getRoomImage() DB 연결 완료");
	 * 
	 * sql = "select room_pic from room_pic_table where room_type_no=?";
	 * 
	 * pstmt = con.prepareStatement(sql); pstmt.setInt(1, room_type_no); rs =
	 * pstmt.executeQuery();
	 * 
	 * getRoomImg = new ArrayList();
	 * 
	 * while(rs.next()){
	 * 
	 * 
	 * 
	 * getRoomImg.add(rs.getString("room_pic"));
	 * 
	 * System.out.println("[JSH-DAO]"+rs.getString("room_pic")+"추가 완료!!"); }
	 * 
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { closeDB(); }
	 * 
	 * return getRoomImg; }
	 */

} // RoomDetailsDAO - 끝
