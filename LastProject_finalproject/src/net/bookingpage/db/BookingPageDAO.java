package net.bookingpage.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookingPageDAO {

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

	// getUserInfo() : 개인정보 불러오기(이름, 전화번호, 이메일) - 시작
	public BookingPageBean getUserInfo(int user_no) {

		BookingPageBean bpb = null;

		try {
			con = getConnection();
			System.out.println("getUserInfo(user_no) DB 연결 완료");

			/*
			 * select substring(user_name,1,1), substring(user_name,2),
			 * user_email, user_phone from user_table where user_no=?;
			 */

			sql = "select user_name, user_email, user_phone " + "from user_table where user_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				bpb = new BookingPageBean();

				bpb.setUser_name(rs.getString("user_name"));
				bpb.setUser_email(rs.getString("user_email"));
				bpb.setUser_phone(rs.getString("user_phone"));
				bpb.setUser_no(user_no);

			}
			System.out.println("user_no: " + user_no + "에 해당하는 개인정보 BookingPageBean에 저장 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("getUserInfo(user_id) 자원해제 완료");
		}

		return bpb;
	}
	// getUserInfo() : 개인정보 불러오기(이름, 전화번호, 이메일) - 끝

	// getHotelInfo(room_type_no) : 내가 예약할 호텔 정보 불러오기 - 시작
	public BookingPageBean getHotelInfo(int room_type_no, Date check_in, Date check_out) {
		BookingPageBean bpb = null;

		try {
			con = getConnection();

			sql = "select h.hotel_name, h.hotel_main_image, r.room_name, r.room_price "
					+ "from room_type_table r join hotel_table h " + "on r.hotel_no = h.hotel_no "
					+ "where r.room_type_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, room_type_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bpb = new BookingPageBean();

				bpb.setHotel_name(rs.getString("h.hotel_name"));
				bpb.setHotel_main_image(rs.getString("h.hotel_main_image"));
				bpb.setRoom_name(rs.getString("r.room_name"));
				bpb.setRoom_price(rs.getInt("r.room_price"));
				bpb.setRoom_type_no(room_type_no);

				bpb.setCheck_in(check_in);
				bpb.setCheck_out(check_out);

				sql = "select datediff(?, ?) as day";

				pstmt = con.prepareStatement(sql);
				pstmt.setDate(1, check_out);
				pstmt.setDate(2, check_in);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					bpb.setDay(rs.getInt("day"));
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return bpb;
	}
	// getHotelInfo(room_type_no) : 내가 예약할 호텔 정보 불러오기 - 끝

	// getRoomOptionList(room_op_no_array) : 유저가 선택한 객실 선택옵션 불러오기 - 시작
	public List getRoomOptionList(String[] room_op_no_array) {

		List getRoomOptionList = new ArrayList();

		try {
			con = getConnection();
			System.out.println("getRoomOptionList(room_op_no_array) DB 연결 완료");

			for (int i = 0; i < room_op_no_array.length; i++) {

				sql = "select room_option_no, room_option_name, option_add_price " + "from room_option_table "
						+ "where room_option_no = ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, room_op_no_array[i]);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					BookingPageBean bpb = new BookingPageBean();

					bpb.setRoom_option_no(rs.getInt("room_option_no"));
					bpb.setRoom_option_name(rs.getString("room_option_name"));
					bpb.setOption_add_price(rs.getInt("option_add_price"));
					System.out.println(
							"★★★★★" + rs.getInt("room_option_no") + " | " + rs.getString("room_option_name") + "");
					getRoomOptionList.add(bpb);
				}
			} // for문 종료

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("getRoomOptionList(room_op_no_array) 자원해제 완료");
		}

		return getRoomOptionList;
	}
	// getRoomOptionList() : 유저가 선택한 객실 선택옵션 불러오기 - 끝

	// bookRoom() : 객실 예약하기 - 시작
	public void bookRoom(InfoForBookBean ifbbean) {
		// String user_id, int room_type_no, int booking_total_people, Date
		// check_in, Date check_out, int type
		int booking_no;
		int room_no;
		int user_no;
		String[] room_option_no;

		try {
			con = getConnection();
			System.out.println("bookRoom() DB 연결 완료");

			// booking_no 결정하기
			sql = "select MAX(booking_no) from booking_table";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				booking_no = rs.getInt(1) + 1;
			} else {
				booking_no = 1;
			}

			// room_no 결정하기
			/*
			 * select room_no, room_number from room_table where room_type_no=1
			 * and room_no not in (select room_no from booking_table where
			 * (('2019-07-01' between booking_enter_date and booking_leave_date)
			 * or ('2019-07-04' between booking_enter_date and
			 * booking_leave_date)) and (booking_state != 1 or booking_state !=
			 * 2)) order by room_no asc limit 1;
			 */
			sql = "select room_no, room_number " + "from room_table " + "where room_type_no=? " + "and room_no not in "
					+ "(select room_no " + "from booking_table "
					+ "where ((? between booking_enter_date and booking_leave_date) or (? between booking_enter_date and booking_leave_date)) "
					+ "and (booking_state != 1 or booking_state != 2)) " + "order by room_no asc " + "limit 1";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, ifbbean.getRoom_type_no());
			pstmt.setDate(2, ifbbean.getCheck_in());
			pstmt.setDate(3, ifbbean.getCheck_out());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				room_no = rs.getInt(1);
			} else {
				room_no = 0;
				System.out.println("room 없음");
				return;
			}

			// user_no 구하기
			/*
			 * sql = "select user_no from user_table where user_id=?";
			 * 
			 * pstmt = con.prepareStatement(sql);
			 * 
			 * pstmt.setString(1, ifbbean.getUser_id());
			 * 
			 * rs = pstmt.executeQuery();
			 * 
			 * if(rs.next()){ user_no = rs.getInt("user_no"); }else{ user_no =
			 * 0; System.out.println("user 없음"); return; }
			 */

			// insert하기
			// <booking_table column>
			// booking_no | user_no | room_type_no | room_no |
			// booking_total_people |
			// booking_date | booking_enter_date | booking_leave_date |
			// booking_state |
			// booker_email | booker_phone | booker_name

			sql = "insert into booking_table "
					+ "value (?,?,?,?,?,now(),DATE_FORMAT(DATE_ADD(?,interval 15 hour),'%Y-%c-%d %T'),DATE_FORMAT(DATE_ADD(?,interval 11 hour),'%Y-%c-%d %T'),?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, booking_no);
			pstmt.setInt(2, ifbbean.getUser_no());
			pstmt.setInt(3, ifbbean.getRoom_type_no());
			pstmt.setInt(4, room_no);
			pstmt.setInt(5, ifbbean.getBooking_total_people());
			pstmt.setDate(6, ifbbean.getCheck_in());
			pstmt.setDate(7, ifbbean.getCheck_out());
			if (ifbbean.getType() == 1) {
				pstmt.setInt(8, 1); // booking_state = 예약:1, 결제완료:2, 환불:3,
									// 숙박완료:4, 리뷰작성:5
			} else if (ifbbean.getType() == 2) {
				pstmt.setInt(8, 2);
			}
			pstmt.setString(9, ifbbean.getBooker_email());
			pstmt.setString(10, ifbbean.getBooker_phone());
			pstmt.setString(11, ifbbean.getBooker_name());

			pstmt.executeUpdate();
			System.out.println("booking_table에 insert 완료");

			if (ifbbean.getRoom_option_no()[0] == null) {

				room_option_no = ifbbean.getRoom_option_no();

				for (int i = 0; i < room_option_no.length; i++) {
					// check_option_table에 내가 선택한 옵션 정보 넣기
					sql = "insert into check_option_table " + "value (?,?)";

					pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, booking_no);
					pstmt.setInt(2, Integer.parseInt(room_option_no[i]));

					pstmt.executeUpdate();
					System.out.println("선택된 room_option - insert 완료");
				}
			} else {
				System.out.println("선택된 room_option 없음");
			}

			// payment_table 에 총 가격 insert 하기
			sql = "insert into payment_table(booking_no, total_price) value(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, booking_no);
			pstmt.setFloat(2, ifbbean.getTotal_price());
			pstmt.executeUpdate();
			System.out.println("booking_no: " + booking_no + ", totalPrice : " + ifbbean.getTotal_price()
					+ " - payment_table에 insert 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			System.out.println("bookRoom() 자원해제 완료");
		}

	}
	// bookRoom() : 객실 예약하기 - 끝

}