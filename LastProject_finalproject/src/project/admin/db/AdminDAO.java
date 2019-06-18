package project.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
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
	
	//about페이지에서 관리자 테이블 정보 가져오기 메소드
	public ArrayList<AdminBean> aboutInfor() throws Exception {
		ArrayList<AdminBean> aboutInfor = new ArrayList<>();
		
		try {
			conn = connect();
			
			sql = "SELECT * FROM ADMIN_TABLE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				AdminBean ab = new AdminBean();
				ab.setAdmin_infor(rs.getString("ADMIN_INFOR"));
				ab.setAdmin_image(rs.getString("ADMIN_IMAGE"));
				ab.setAdmin_tel(rs.getString("ADMIN_TEL"));
				ab.setAdmin_address(rs.getString("ADMIN_ADDRESS"));
				ab.setAdmin_email(rs.getString("ADMIN_EMAIL"));
				aboutInfor.add(ab);
			}
			
			System.out.println("about페이지 메소드 불러오기 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return aboutInfor;
	}
	
	
	
	
	/*
	 * 
	 * 	관리자 테이블에 read only로 되어있음.
	 *  일반 insert로는 데이터를 변경할 수 없음.
	 *  
	 *  그래서 read write로 풀어서 데이터를 변경하고 다시 read only로 변경하는 작업을 해주어야함.
	 * 
	 *  why? 왜 read only로 select절만 가능하게 하는가?
	 *  해당 테이블은 데이터 로우수가 한개만 되어야 하기 때문에 특수하게 데이터 insert를 막기 위한 작업으로 read only로 변경해 놓았음.
	 * 
	 */
	// 관리자 테이블에서 관리자 정보(코멘트) 변경하기 메소드
	public boolean ChangeAdminInfor(String admin_infor) throws Exception {
		boolean ChangeAdminInfor = false;
		
		try {
			conn = connect();
			
			sql = "ALTER TABLE ADMIN_TABLE READ WRITE";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "UPDATE ADMIN_TABLE SET ADMIN_INFOR = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin_infor);
			pstmt.executeUpdate();
			
			sql = "ALTER TABLE ADMIN_TABLE READ ONLY";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			System.out.println("about 페이지 정보(코멘트) 변경 완료");
			ChangeAdminInfor = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return ChangeAdminInfor;
	}
	
	// 관리자 테이블에서 관리자 정보(주소,이메일, 전화번호) 변경하기 메소드
	public boolean ChangeAdminInfors(String admin_address,String admin_tel,String admin_email) throws Exception {
		boolean ChangeAdminInfors = false;
		
		try {
			conn = connect();
			
			sql = "ALTER TABLE ADMIN_TABLE READ WRITE";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "UPDATE ADMIN_TABLE SET ADMIN_ADDRESS = ?, ADMIN_TEL = ?, ADMIN_EMAIL = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin_address);
			pstmt.setString(2, admin_tel);
			pstmt.setString(3, admin_email);
			pstmt.executeUpdate();
			
			sql = "ALTER TABLE ADMIN_TABLE READ ONLY";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			System.out.println("about 페이지 정보(주소, 이메일, 전화번호) 변경 완료");
			ChangeAdminInfors = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return ChangeAdminInfors;
	}
	
	//about 페이지에서 관리자 이미지 변경하기 메소드
	public boolean ChangeAdminImage(String admin_image) throws Exception {
		boolean ChangeAdminImage = false;
		
		try {
			conn = connect();
					
			sql = "UPDATE ADMIN_TABLE SET ADMIN_IMAGE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin_image);
			pstmt.executeUpdate();
			
			System.out.println("about 페이지에서 관리자 이미지 변경 성공");
			ChangeAdminImage = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return ChangeAdminImage;
	}
}
