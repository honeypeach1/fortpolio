package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import controller.StayPeopleDAO;
import model.StayPeople;

public class StayPeopleDAO {
	
	public static final int STAY_NONEXISTENT  = 0;
	public static final int STAY_EXISTENT = 1;
	public static final int STAY_JOIN_FAIL = 0;
	public static final int STAY_JOIN_SUCCESS = 1;
	public static final int STAY_LOGIN_PW_NO_GOOD = 0;
	public static final int STAY_LOGIN_SUCCESS = 1;
	public static final int STAY_LOGIN_IS_NOT = -1;
	
	DataSource dataSource;
		
	private static StayPeopleDAO instance = new StayPeopleDAO();
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	/*
	private final String DB_URL = "jdbc:oracle:thin://@localhost:1521/xe";
	private final String DB_USER = "jamey";
	private final String DB_PW = "0000";
	*/


	public void connect() throws ClassNotFoundException, SQLException{
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conn = dataSource.getConnection();
		//conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
	}
	
	private void disconnect() {
		// TODO Auto-generated method stub
		if (pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt = null;
		}
		
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;

		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int insertStay(StayPeople dto) {
		int ri = 0;
		
		String query = "insert into STAY_PEOPLE values (STAY_PEO.NEXTVAL,?,?,?,?,?)"; //STAY PEOPLE 1

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getStay_id());
			pstmt.setString(2, dto.getStay_password());
			pstmt.setString(3, dto.getStay_email());
			pstmt.setString(4, dto.getStay_tel());
			pstmt.setString(5, dto.getStay_name());
			pstmt.setString(6,"1");
			pstmt.executeUpdate();
			ri = StayPeopleDAO.STAY_JOIN_SUCCESS;
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		
		ResultSet set = null;
		String query = "select STAY_ID from STAY_PEOPLE where STAY_ID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()){
				ri = StayPeopleDAO.STAY_EXISTENT;
				
			} else {
				ri = StayPeopleDAO.STAY_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dbPw;

		ResultSet set = null;
		String query = "select STAY_PASSWORD from STAY_PEOPLE where STAY_PASSWORD = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					ri = StayPeopleDAO.STAY_LOGIN_SUCCESS;
				} else {
					ri = StayPeopleDAO.STAY_LOGIN_PW_NO_GOOD;
				}
			} else {
				ri = StayPeopleDAO.STAY_LOGIN_IS_NOT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public StayPeople getStay(String id) {
		ResultSet set = null;
		String query = "select * from STAY_PEOPLE where STAY_ID = ?";
		StayPeople dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {

				dto = new StayPeople();
				dto.setStay_id(set.getString("id")); ///jsp page
				dto.setStay_password(set.getString("password"));
				dto.setStay_name(set.getString("gender"));
				dto.setStay_tel(set.getString("tel"));
				dto.setStay_email(set.getString("email"));
				dto.setGrade(set.getString("grade"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
	//회원 정보 수정
	public int updateStay(StayPeople dto) {
		int ri = 0;
		
		String query = "update STAY_PEOPLE set STAY_PASSWORD = ?, STAY_NAME = ? where STAY_ID = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getStay_password());
			pstmt.setString(2, dto.getStay_name());
			pstmt.setString(3, dto.getStay_id());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	//로그인
	public StayPeople loginStay(String id,String pw) throws ClassNotFoundException
	{
		StayPeople StayPeople = null;
		try {	
			connect();
			
			String sql = "SELECT * FROM STAY_PEOPLE WHERE STAY_ID = ? AND STAY_PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			/*pstmt.setString(3, grade);*/
			
			/*System.out.println("grade" + "등급이외다.");*/
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				StayPeople = new StayPeople();
				StayPeople.setStay_num(rs.getString("STAY_NUM"));
				StayPeople.setStay_id(rs.getString("STAY_ID"));
				StayPeople.setStay_password(rs.getString("STAY_PASSWORD"));
				StayPeople.setStay_email(rs.getString("STAY_EMAIL"));
				StayPeople.setStay_tel(rs.getString("STAY_TEL"));
				StayPeople.setStay_name(rs.getString("STAY_NAME"));
				StayPeople.setGrade(rs.getString("GRADE"));
				
				System.out.println("[StayPeopleDAO.java.loginStay()] User " + id + " has log in.");
				System.out.println();
			}
			disconnect();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return StayPeople;
	}
	
	public boolean CreateStay(String stay_id,String stay_pw,String stay_email,String stay_tel,String stay_nm) throws ClassNotFoundException {

		boolean InsertStay = false;
		
		try {	
			connect();
			String sql = "INSERT INTO STAY_PEOPLE VALUES(STAY_PEO.NEXTVAL,?,?,?,?,?,1)";  //STAYPEOPLE GRADE 1
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stay_id);
			pstmt.setString(2, stay_pw);
			pstmt.setString(3, stay_email);
			pstmt.setString(4, stay_tel);
			pstmt.setString(5, stay_nm);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0)
			{
				InsertStay = true;
				System.out.println("체류자 데이터 insert 성공");
			}
			else
			{
				System.out.println("체류자 데이터 insert 실패");
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return InsertStay;
	}
}
