package project.adminGetList.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGetListDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

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
	
	//승인 호텔 리스트에서 호텔 정보와 시설물 리스트, 사진 리스트 가져오기 메소드
	public AdminGetListBean AppAdminGetHotel(String hotel_no) throws Exception {

		AdminGetListBean AdGetList = null;
		
		try {
			conn = connect();
			sql = "SELECT HOTEL_NAME,HOTEL_INSTRODUCT FROM HOTEL_TABLE WHERE HOTEL_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hotel_no);
			rs = pstmt.executeQuery();
			
			//if? 호텔넘버에 해당 하는 호텔은 하나밖에 없으니까! while쓸필요가 없징!!
			if(rs.next()) {
				AdminGetListBean ab = new AdminGetListBean();
				ab.setHotel_name(rs.getString("HOTEL_NAME"));
				ab.setHotel_instroduct(rs.getString("HOTEL_INSTRODUCT"));
				
				//호텔의 정보 bean 객체에 가져오기
				AdGetList = ab;
				
				sql = "SELECT HOTEL_OPTION_NO FROM HOTEL_OPTION_CON_TABLE WHERE HOTEL_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, hotel_no);
				ResultSet rs1 = pstmt.executeQuery();
				
				
				sql = "SELECT HOTEL_PIC_NO,HOTEL_PIC FROM HOTEL_PIC_TABLE WHERE HOTEL_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, hotel_no);
				ResultSet rs2 = pstmt.executeQuery();
				
				//해당 호텔의 이미지 리스트 가져오기
					ArrayList<AdminGetListBean> AdHotelImageList = new ArrayList<>();
					
					while(rs2.next()) {
						AdminGetListBean ImageList = new AdminGetListBean();
						ImageList.setHotel_pic_no(rs2.getString("HOTEL_PIC_NO"));
						ImageList.setHotel_pic(rs2.getString("HOTEL_PIC"));
						AdHotelImageList.add(ImageList);
					}
				
					
					ab.setAdGetImages(AdHotelImageList);
					AdGetList = ab;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return AdGetList;
	}
	
	
	//해당 호텔의 방정보와 방 사진, 방 옵션 가져오기
	public ArrayList<AdminGetListBean> AppAdminRoom(String hotel_no) throws Exception{
		
		ArrayList<AdminGetListBean> AppAdminRoom = new ArrayList<>();
		
		try {
			conn = connect();
			
			sql = "SELECT * FROM ROOM_TYPE_TABLE WHERE HOTEL_NO = ?";		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hotel_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AdminGetListBean ab = new AdminGetListBean();
				ab.setRoom_type_no(rs.getString("ROOM_TYPE_NO"));
				ab.setHotel_no(rs.getString("HOTEL_NO"));
				ab.setRoom_basic_people(rs.getInt("ROOM_BASIC_PEOPLE"));
				ab.setRoom_max_people(rs.getInt("ROOM_MAX_PEOPLE"));
				ab.setRoom_info(rs.getString("ROOM_INFO"));
				ab.setRoom_price(rs.getInt("ROOM_PRICE"));
				ab.setRoom_name(rs.getString("ROOM_NAME"));				
				
				sql = "SELECT COUNT(ROOM_NO) AS COUNT FROM ROOM_TYPE_TABLE NATURAL JOIN ROOM_TABLE WHERE HOTEL_NO = ? AND ROOM_TYPE_NO = ? GROUP BY ROOM_TYPE_NO";		
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, hotel_no);
				pstmt.setString(2, rs.getString("ROOM_TYPE_NO"));
				ResultSet rs1 = pstmt.executeQuery();
				
				if(rs1.next()) {
					ab.setCount(rs1.getInt("COUNT"));
				}else {
					ab.setCount(0);
				}
				
				AppAdminRoom.add(ab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return AppAdminRoom;
	}
	
	//주소 가져오기 메소드
	public AdminGetListBean GetAddress(String hotel_num) throws Exception {
		AdminGetListBean GetAddress = null;
		
		try {
			conn = connect();
			sql = "SELECT HOTEL_ADDRESS FROM HOTEL_TABLE WHERE HOTEL_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hotel_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				AdminGetListBean ab = new AdminGetListBean();
				ab.setHotel_address(rs.getString("HOTEL_ADDRESS"));
				GetAddress = ab;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return GetAddress;
	}
	
	// 방 추가 옵션 가져오기 메소드
	public ArrayList<AdminGetListBean> AppRoomOption() throws Exception{
		ArrayList<AdminGetListBean> AppRoomOption = new ArrayList<>();
		
		try {
			conn = connect();
			sql = "SELECT * FROM ROOM_OPTION_TABLE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AdminGetListBean ab = new AdminGetListBean();
				ab.setRoom_option_no(rs.getString("ROOM_OPTION_NO"));
				ab.setRoom_type_no(rs.getString("ROOM_TYPE_NO"));
				ab.setRoom_option_name(rs.getString("ROOM_OPTION_NAME"));
				ab.setOption_add_price(rs.getInt("OPTION_ADD_PRICE"));
				AppRoomOption.add(ab);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return AppRoomOption;
	}
	
	//방의 이미지 리스트 저장하기 메소드
	
	public ArrayList<AdminGetListBean> AppRoomImages() throws Exception{
		ArrayList<AdminGetListBean> AppRoomImages = new ArrayList<>();
		
		try {
			conn = connect();
			sql = "SELECT ROOM_TYPE_NO,MAX(room_pic) AS ROOM_PIC1 FROM ROOM_PIC_TABLE GROUP BY ROOM_TYPE_NO";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AdminGetListBean ab = new AdminGetListBean();
				ab.setRoom_type_no(rs.getString("ROOM_TYPE_NO"));
				ab.setRoom_pic(rs.getString("ROOM_PIC1"));
				AppRoomImages.add(ab);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return AppRoomImages;
	}
	
	// 호텔 시설물 리스트 가져오기
	public ArrayList<AdminGetListBean> AppFacilityHotel(String hotel_no) throws Exception{
		ArrayList<AdminGetListBean> AppFacilityHotel = new ArrayList<>();
		
		try {
			conn = connect();
			sql = "SELECT HOTEL_OPTION_NO FROM HOTEL_OPTION_CON_TABLE WHERE HOTEL_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hotel_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				
				sql = "SELECT * FROM HOTEL_OPTION_TABLE WHERE HOTEL_OPTION_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rs.getString("HOTEL_OPTION_NO"));
				ResultSet rs1 = pstmt.executeQuery();
				
				while(rs1.next()) {
					AdminGetListBean ab = new AdminGetListBean();
					ab.setHotel_option_no(rs1.getString("HOTEL_OPTION_NO"));
					ab.setHotel_option_name(rs1.getString("HOTEL_OPTION_NAME"));
					ab.setHotel_option_title(rs1.getString("HOTEL_OPTION_TITLE"));
					AppFacilityHotel.add(ab);
				}
		
			}

			System.out.println("확인하기 값 : " + AppFacilityHotel.size());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return AppFacilityHotel;
	}
}
