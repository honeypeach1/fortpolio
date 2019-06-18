package net.hotel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HotelDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비 연결 (디비 드라이버 설치 & 연결)
	public Connection getConnection() throws Exception {

		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/hotels");

		con = ds.getConnection();

		return con;
	}

	// 자원해제
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

	// ---------------------------------------------------------------------------------------------

	// getHotel(int hotel_no) : 호텔 번호로 호텔 정보 가져오기 - 정현
	public HotelBean getHotel(int hotel_no) {
		// 호텔 편의시설 새로 가져와야됨!
		HotelBean hb = null;

		try {
			con = getConnection();

			sql = "select * from hotel_table where hotel_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotel_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				hb = new HotelBean();

				hb.setHotel_no(hotel_no);
				hb.setHotel_name(rs.getString("hotel_name"));
				hb.setHotel_address(rs.getString("hotel_address"));
				hb.setHotel_class(rs.getInt("hotel_class"));
				hb.setHotel_instroduct(rs.getString("hotel_instroduct"));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return hb;
	}
	// getHotel(int hotel_no)

	// getSearchHotel(hotel_no, enter_date, leave_date, basic_people - 시작
	public HotelBean getSearchHotel(int hotel_no, String enter_date, String leave_date, int basic_people) {
		// 호텔 편의시설 새로 가져와야됨!
		HotelBean hb = null;

		try {
			con = getConnection();

			sql = "select * from hotel_table " + "where hotel_no=? " + "and ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotel_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				hb = new HotelBean();

				hb.setHotel_no(hotel_no);
				hb.setHotel_name(rs.getString("hotel_name"));
				hb.setHotel_address(rs.getString("hotel_address"));
				hb.setHotel_class(rs.getInt("hotel_class"));
				hb.setHotel_instroduct(rs.getString("hotel_instroduct"));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return hb;
	}

	// getSearchHotel(hotel_no, enter_date, leave_date, basic_people - 시작

	// getHotelOptionList(int hotel_no) : 호텔에 있는 편의시설 리스트 가져오기 - 정현
	public List getHotelOptionList(int hotel_no) {
		List optionList = new ArrayList();

		try {
			con = getConnection();

			sql = "select op.hotel_option_name, op.hotel_option_title from hotel_option_con_table con join hotel_option_table op on con.hotel_option_no=op.hotel_option_no where hotel_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotel_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HotelOptionBean hob = new HotelOptionBean();
				hob.setHotel_option_name(rs.getString("op.hotel_option_name"));
				hob.setHotel_option_title(rs.getString("op.hotel_option_title"));

				optionList.add(hob);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return optionList;
	}
	// getHotelOptionList(int hotel_no)

	// getHotelImages(int hotel_no) : 호텔 이미지 가져오기 - 정현
	public List getHotelImages(int hotel_no) {

		List hotelImageList = new ArrayList();

		try {
			con = getConnection();

			sql = "select hotel_pic from hotel_pic_table where hotel_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotel_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String str = rs.getString("hotel_pic");

				hotelImageList.add(str);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return hotelImageList;
	}
	// getHotelImages(int hotel_no)

}