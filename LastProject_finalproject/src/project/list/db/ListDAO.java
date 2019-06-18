package project.list.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ListDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	String rowSql = "";

	// 디비 연결 (디비 드라이버 설치 & 연결)
	public Connection connect() throws Exception {

		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/hotels");

		conn = ds.getConnection();

		return conn;
	}

	// 자원해제
	public void disconnect() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

		//HOTELS 페이지 등록된 모든 호텔(승인된 호텔, 1번) 정보 가져오기
		public ArrayList<ListBean> getAllList() throws Exception {
			ArrayList<ListBean> getList = new ArrayList<>();
			
			try {
				conn = connect();
				rowSql = "set @rownum:=0";
				pstmt = conn.prepareStatement(rowSql);
				rs = pstmt.executeQuery();
				
				sql = "SELECT @rownum:=@rownum+1 as rnum , hotel.hotel_no, hotel.hotel_name, hotel.hotel_address, hotel.hotel_class, hotel.hotel_main_image, hotel.hotel_instroduct, hotel.hotel_tel "
						+ "FROM(SELECT * FROM hotel_table WHERE approval = 1 ORDER BY hotel_class DESC) hotel ";//승인된 호텔 1번
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ListBean hb = new ListBean();
					hb.setRownum(rs.getInt("rnum"));
					hb.setHotel_no(rs.getInt("HOTEL_NO"));
					hb.setHotel_name(rs.getString("HOTEL_NAME"));
					hb.setHotel_address(rs.getString("HOTEL_ADDRESS"));
					hb.setHotel_class(rs.getInt("HOTEL_CLASS"));
					hb.setHotel_main_image(rs.getString("HOTEL_MAIN_IMAGE"));
					hb.setHotel_instroduct(rs.getString("HOTEL_INSTRODUCT"));
					hb.setHotel_tel(rs.getString("HOTEL_TEL"));
					getList.add(hb);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return getList;
		}
		
	//메인 페이지 호텔 인기 top3가져오기
	public ArrayList<ListBean> getPopularList() throws Exception {
		ArrayList<ListBean> getList = new ArrayList<>();
		
		try {
			conn = connect();
			rowSql = "set @rownum:=0";
			pstmt = conn.prepareStatement(rowSql);
			rs = pstmt.executeQuery();
			
			sql = "SELECT @rownum:=@rownum+1 as rnum , hotel.hotel_no, hotel.hotel_name, hotel.hotel_address, hotel.hotel_class, hotel.hotel_main_image, hotel.hotel_instroduct, hotel.hotel_tel "
					+ "FROM(SELECT * FROM hotel_table WHERE approval = 1 ORDER BY hotel_class DESC) hotel "
					+ "limit 3";//승인된 호텔 1번
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ListBean hb = new ListBean();
				hb.setHotel_no(rs.getInt("HOTEL_NO"));
				hb.setHotel_name(rs.getString("HOTEL_NAME"));
				hb.setHotel_address(rs.getString("HOTEL_ADDRESS"));
				hb.setHotel_class(rs.getInt("HOTEL_CLASS"));
				hb.setHotel_main_image(rs.getString("HOTEL_MAIN_IMAGE"));
				hb.setHotel_instroduct(rs.getString("HOTEL_INSTRODUCT"));
				hb.setHotel_tel(rs.getString("HOTEL_TEL"));
				getList.add(hb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return getList;
	}

	// 호텔 편의 시설 정보 가져오기 메소드
	public ArrayList<ListBean> getFacilityList() throws Exception {
		ArrayList<ListBean> getFacilityList = new ArrayList<>();
		try {
			conn = connect();

			sql = "SELECT HOTEL_OPTION_NO,HOTEL_OPTION_NAME,HOTEL_OPTION_TITLE,HOTEL_NO FROM HOTEL_OPTION_TABLE NATURAL JOIN HOTEL_OPTION_CON_TABLE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ListBean hb = new ListBean();
				hb.setHotel_option_no(rs.getInt("HOTEL_OPTION_NO"));
				hb.setHotel_option_name(rs.getString("HOTEL_OPTION_NAME"));
				hb.setHotel_option_title(rs.getString("HOTEL_OPTION_TITLE"));
				hb.setHotel_no(rs.getInt("HOTEL_NO"));
				getFacilityList.add(hb);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return getFacilityList;
	}
	
	//Hot Deal 페이지 정보 가져오기 메소드
	public ArrayList<ListBean> getHotDeal() throws Exception {
		ArrayList<ListBean> getHotDeal = new ArrayList<>();
		try {
			conn = connect();
			
			rowSql="set @rownum:=0";
			
			pstmt = conn.prepareStatement(rowSql);
			rs = pstmt.executeQuery();
			
			sql = "SELECT @rownum:=@rownum+1 as rnum, hotel.HOTEL_NAME, hotel.ROOM_TYPE_NO, hotel.HOTEL_NO, hotel.ROOM_BASIC_PEOPLE, hotel.ROOM_MAX_PEOPLE, hotel.ROOM_INFO, hotel.ROOM_PRICE, hotel.ROOM_NAME, hotel.DISCOUNT, hotel.discount_percent "
					+ "FROM (SELECT ht.hotel_name,rt.*,(rt.room_price*((100-dct.discount_percent)/100)) AS discount, dct.discount_percent "
					+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
					+ "JOIN discount_room_table dct ON (rt.room_type_no = dct.room_type_no) "
					+ "WHERE ht.approval = 1 "
					+ "ORDER BY dct.discount_percent DESC) hotel";
			 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ListBean lb = new ListBean();
				lb.setRownum(rs.getInt("rnum"));
				lb.setHotel_name(rs.getString("HOTEL_NAME"));
				lb.setRoom_type_no(rs.getInt("ROOM_TYPE_NO"));
				lb.setHotel_no(rs.getInt("HOTEL_NO"));
				lb.setRoom_basic_people(rs.getInt("ROOM_BASIC_PEOPLE"));
				lb.setRoom_max_people(rs.getInt("ROOM_MAX_PEOPLE"));
				lb.setRoom_info(rs.getString("ROOM_INFO"));
				lb.setRoom_price(rs.getInt("ROOM_PRICE"));
				lb.setRoom_name(rs.getString("ROOM_NAME"));
				lb.setDiscount_price(rs.getInt("DISCOUNT"));
				lb.setDiscount_rate(rs.getInt("discount_percent"));
				getHotDeal.add(lb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return getHotDeal;
	}
	
	//HOT DEAL 이미지 가져오기
	public ArrayList<ListBean> getHotDealImage() throws Exception{
		ArrayList<ListBean> getHotDealImage = new ArrayList<>();
		
		try {
			conn = connect();
			
			sql = "SELECT ROOM_TYPE_NO,MIN(ROOM_PIC) AS ROOM_PIC FROM ROOM_PIC_TABLE GROUP BY ROOM_TYPE_NO";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ListBean lb = new ListBean();
				lb.setRoom_type_no(rs.getInt("ROOM_TYPE_NO"));
				lb.setRoom_pic(rs.getString("ROOM_PIC"));
				getHotDealImage.add(lb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return getHotDealImage;
	}
	
	
	//RecentlyViewed 페이지에 리스트 가져오기
	public ArrayList<ListBean> recentlyHotel(String roomInfor) throws Exception{
		ArrayList<ListBean> recentlyHotel = new ArrayList<>();
		
		try {
			conn = connect();
			sql = "SELECT * FROM HOTEL_TABLE WHERE HOTEL_NO = ? AND APPROVAL = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomInfor);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ListBean hb = new ListBean();
				hb.setHotel_no(rs.getInt("HOTEL_NO"));
				hb.setHotel_name(rs.getString("HOTEL_NAME"));
				hb.setHotel_address(rs.getString("HOTEL_ADDRESS"));
				hb.setHotel_class(rs.getInt("HOTEL_CLASS"));
				hb.setHotel_main_image(rs.getString("HOTEL_MAIN_IMAGE"));
				hb.setHotel_instroduct(rs.getString("HOTEL_INSTRODUCT"));
				hb.setHotel_tel(rs.getString("HOTEL_TEL"));
				recentlyHotel.add(hb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return recentlyHotel;
	}
	
	//RecentlyViewed 페이지의 시설물 정보 가져오기 리스트
	// 호텔 편의 시설 정보 가져오기 메소드
		public ArrayList<ListBean> recentlyFacility(String roomInfor) throws Exception {
			ArrayList<ListBean> recentlyFacility = new ArrayList<>();
			try {
				conn = connect();

				sql = "SELECT HOTEL_OPTION_NO,HOTEL_OPTION_NAME,HOTEL_NO FROM HOTEL_OPTION_TABLE NATURAL JOIN HOTEL_OPTION_CON_TABLE WHERE HOTEL_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, roomInfor);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ListBean hb = new ListBean();
					hb.setHotel_option_no(rs.getInt("HOTEL_OPTION_NO"));
					hb.setHotel_option_name(rs.getString("HOTEL_OPTION_NAME"));
					hb.setHotel_no(rs.getInt("HOTEL_NO"));
					recentlyFacility.add(hb);
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return recentlyFacility;
		}
		
		
		//승인 대기 호텔 가져오기 메소드(승인 대기 APPROVAL = 0)
		public ArrayList<ListBean> AppHotel() throws Exception{
			ArrayList<ListBean> AppHotel = new ArrayList<>();
				
			try {
				conn = connect();
				rowSql = "set @rownum:=0";
				pstmt = conn.prepareStatement(rowSql);
				rs = pstmt.executeQuery();
				
				sql = "SELECT @rownum:=@rownum+1 as rnum , hotel.hotel_no, hotel.hotel_name, hotel.hotel_address, hotel.hotel_class, hotel.hotel_main_image, hotel.hotel_instroduct, hotel.hotel_tel "
						+ "FROM (SELECT * FROM hotel_table ORDER BY hotel_class DESC) hotel "
						+ "WHERE approval = 0";	//0번 승인 대기중인 호텔
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {				
					ListBean lb = new ListBean();
					lb.setRownum(rs.getInt("rnum"));
					lb.setHotel_no(rs.getInt("hotel_no"));
					lb.setHotel_name(rs.getString("hotel_name"));
					lb.setHotel_address(rs.getString("hotel_address"));
					lb.setHotel_class(rs.getInt("hotel_class"));
					lb.setHotel_main_image(rs.getString("hotel_main_image"));
					lb.setHotel_instroduct(rs.getString("hotel_instroduct"));
					lb.setHotel_tel(rs.getString("hotel_tel"));
					
					sql = "SELECT COUNT(*) AS COUNT FROM HOTEL_TABLE NATURAL JOIN ROOM_TYPE_TABLE NATURAL JOIN ROOM_TABLE WHERE HOTEL_NO = ? GROUP BY HOTEL_NO";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rs.getInt("HOTEL_NO"));
					ResultSet rs1 = pstmt.executeQuery();
					
					if(rs1.next()) {
						lb.setCount(rs1.getInt("COUNT"));
					}else{
						lb.setCount(0);
					}
					
					AppHotel.add(lb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return AppHotel;
		}
		
		//호텔 승인하기 동작 메소드(APPROVAL = 1로 변경하기)
		public boolean ApprovalHotel(String hotel_no) throws Exception {
			boolean ApprovalHotel = false;
			
			try {
				conn = connect();
				sql = "UPDATE HOTEL_TABLE SET APPROVAL = 1 WHERE HOTEL_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, hotel_no);
				pstmt.executeUpdate();
				
				ApprovalHotel = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return ApprovalHotel;
		}
		
		
		/*
		 * 
		 *    반드시 지우는 테이블의 관계를 가지는 테이블에 cascade를 설정해놓아야함!!!!
		 *    지워지는 테이블의 하위테이블도 포함(즉, 외래키를 가지는 모든 테이블에 cascade 종속 삭제를 걸어줘야 테이블이 삭제가 됨)
		 * 
		 * 
		 * */
		//호텔 승인 삭제 하기 메소드(오라클은 캐스캐이딩 지원함. 그래서 호텔에 딸린 하위 테이블의 값도 삭제가 가능함.)
		public int DenyHotel(int hotel_no) throws Exception {
			int DenyHotel = 0;
			
			try {
				conn = connect();
				sql = "DELETE FROM hotel_table WHERE hotel_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, hotel_no);
				pstmt.executeUpdate();
				
				DenyHotel = 1;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return DenyHotel;
		}
		
		// 예약된 호텔 가져오기 메소드 : 내가 예약한거를 MyPagr에 보여주기 위한 메서드
		public ArrayList<ListBean> getBookList(String user_num) throws Exception{
			ArrayList<ListBean> getBookList = new ArrayList<>();
			
			
			try {
				conn = connect();
				sql = "SELECT h.hotel_no, u.user_no, b.booking_no, b.booking_state, b.booking_total_people, b.booking_date, b.booking_enter_date, b.booking_leave_date, r.room_number, "
						+ "h.hotel_name, h.hotel_address, h.hotel_main_image, t.room_price "
						+ "FROM booking_table b JOIN room_table r "
						+ "ON b.room_no = r.room_no "
						+ "JOIN room_type_table t " 
						+ "ON r.room_type_no = t.room_type_no "
						+ "JOIN hotel_table h "
						+ "ON t.hotel_no = h.hotel_no "
						+ "JOIN user_table u "
						+ "ON b.user_no = u.user_no "
						+ "WHERE u.user_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_num);
				rs = pstmt.executeQuery();
				// 쿼리문에서 h.hotel_information 삭제
				while(rs.next()){
					ListBean lb = new ListBean();
					
					System.out.println("lb 호텔 번호 :"+rs.getInt("h.hotel_no"));
					lb.setBooking_state(rs.getInt("b.booking_state"));
					lb.setBooking_total_people(rs.getInt("b.booking_total_people"));
					lb.setBooking_date(rs.getDate("b.booking_date"));
					lb.setBooking_enter_date(rs.getDate("b.booking_enter_date"));
					lb.setBooking_leave_date(rs.getDate("b.booking_leave_date"));
					lb.setRoom_number(rs.getInt("r.room_number"));
//					lb.setRoom_name(rs.getString("t.room_name"));
					lb.setHotel_name(rs.getString("h.hotel_name"));
					lb.setHotel_address(rs.getString("h.hotel_address"));
					lb.setHotel_main_image(rs.getString("h.hotel_main_image"));
					lb.setRoom_price(rs.getInt("t.room_price"));
//					lb.setHotel_instroduct(rs.getString("h.hotel_instroduct"));
					lb.setBooking_no(rs.getInt("b.booking_no"));
					getBookList.add(lb);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconnect();
			}
				
			
			return getBookList;
		}
		
		// 예약된 호텔의 부가옵션 받아오기 리스트
		public ArrayList<ListBean> getBookOptionList(String user_num) throws Exception {
			ArrayList<ListBean> getBookOptionList = new ArrayList<>();
			
			try {
				conn = connect();
				
				sql = "SELECT b.user_no, o.room_type_no, o.room_option_name, o.option_add_price\r\n" + 
						"FROM booking_table b JOIN room_table r\r\n" + 
						"ON b.room_no = r.room_no\r\n" + 
						"JOIN room_option_table o\r\n" + 
						"ON r.room_type_no = o.room_type_no\r\n" + 
						"JOIN user_table u\r\n" + 
						"ON b.user_no = u.user_no \r\n" + 
						"WHERE b.user_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_num);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					ListBean lb = new ListBean();
					
					lb.setUser_no(rs.getInt("b.user_no"));
					lb.setRoom_option_name(rs.getString("o.room_option_name"));
					lb.setOption_add_price(rs.getInt("o.option_add_price"));
					
					getBookOptionList.add(lb);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			
			return getBookOptionList;
		}
		// 예약된 호텔의 부가옵션 받아오기 리스트(끝)
}
