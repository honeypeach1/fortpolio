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

import controller.LeavePeopleDAO;
import model.LeavePeople;

public class LeavePeopleDAO {
	
	public static final int LEAVE_NONEXISTENT  = 0;
	public static final int LEAVE_EXISTENT = 1;
	public static final int LEAVE_JOIN_FAIL = 0;
	public static final int LEAVE_JOIN_SUCCESS = 1;
	public static final int LEAVE_LOGIN_PW_NO_GOOD = 0;
	public static final int LEAVE_LOGIN_SUCCESS = 1;
	public static final int LEAVE_LOGIN_IS_NOT = -1;
	
	DataSource dataSource;
		
	private static LeavePeopleDAO instance = new LeavePeopleDAO();
	
	Connection conn = null;
	PreparedStatement pstmt = null;

	/*private final String DB_URL = "jdbc:oracle:thin://@localhost:1521/xe";
	private final String DB_USER = "jamey";
	private final String DB_PW = "0000";*/
	
	public static LeavePeopleDAO getInstance(){
		return instance;
	}
	
	public void connect() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
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
	
	public void disconnect() throws SQLException{
		if (pstmt != null){
			pstmt.close();
			pstmt = null;
		}
		
		if (conn != null){
			conn.close();
			conn = null;
		}
	}
	public int insertLeave(LeavePeople dto) {
		int ri = 0;

		String query = "insert into LEAVE_PEOPLE values (LEAVE_PEO.NEXTVAL,?,?,?,?,2,?,?)"; //LEAVE PEOPLE 2
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getLeave_id());
			pstmt.setString(2, dto.getLeave_password());
			pstmt.setString(3, dto.getLeave_name());
			pstmt.setString(4, dto.getLeave_tel());
			pstmt.setString(5, dto.getLeave_email());
			pstmt.setString(6, dto.getLeave_image());
			pstmt.executeUpdate();
			ri = LeavePeopleDAO.LEAVE_JOIN_SUCCESS;
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
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select LEAVE_ID from LEAVE_PEOPLE where LEAVE_ID = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()){
				ri = LeavePeopleDAO.LEAVE_EXISTENT;
			} else {
				ri = LeavePeopleDAO.LEAVE_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select LEAVE_PASSWORD from 	LEAVE_PEOPLE where LEAVE_PASSWORD = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					ri = LeavePeopleDAO.LEAVE_LOGIN_SUCCESS;
				} else {
					ri = LeavePeopleDAO.LEAVE_LOGIN_PW_NO_GOOD;
				}
			} else {
				ri = LeavePeopleDAO.LEAVE_LOGIN_IS_NOT;	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public LeavePeople getLeave(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from LEAVE_PEOPLE where LEAVE_ID = ?";
		LeavePeople dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
	
				dto = new LeavePeople();
				dto.setLeave_id(set.getString("id")); ///jsp page
				dto.setLeave_password(set.getString("password"));
				dto.setLeave_name(set.getString("name"));
				dto.setLeave_tel(set.getString("tel"));
				dto.setGrade(set.getString("grade"));
				dto.setLeave_email(set.getString("email"));
				dto.setLeave_image(set.getString("image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
	public int updateLeave(LeavePeople dto) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update LEAVE_PEOPLE set LEAVE_PASSWORD = ?, LEAVE_NAME = ? where LEAVE_ID = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getLeave_password());
			pstmt.setString(2, dto.getLeave_name());
			pstmt.setString(3, dto.getLeave_id());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	public LeavePeople loginleave(String id,String pw) throws ClassNotFoundException {
		
		LeavePeople LeavePeople = null;
		try {	
			connect();
			String sql = "SELECT * FROM LEAVE_PEOPLE WHERE LEAVE_ID = ? AND LEAVE_PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				LeavePeople = new LeavePeople();
				LeavePeople.setLeave_id(rs.getString("LEAVE_ID"));
				LeavePeople.setLeave_password(rs.getString("LEAVE_PASSWORD"));
				LeavePeople.setLeave_name(rs.getString("LEAVE_NAME"));
				LeavePeople.setLeave_tel(rs.getString("LEAVE_TEL"));
				LeavePeople.setLeave_num(rs.getString("LEAVE_NUM"));
				LeavePeople.setGrade(rs.getString("GRADE"));
				
				System.out.println("[LeavePeopleDAO.java.loginLeave()] User " + id + " has log in.");
				System.out.println();
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return LeavePeople;
	}
	
	public boolean CreateLeave(String leave_id,String leave_pw,String leave_nm,String leave_tel, String leave_email, String leave_image) throws ClassNotFoundException {

		boolean InsertLeave = false;
		
		try {	
			connect();
			String sql = "INSERT INTO LEAVE_PEOPLE VALUES(LEAVE_PEO.NEXTVAL,?,?,?,?,2,?,?)";  //LEAVEPEOPLE GRADE 2
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, leave_id);
			pstmt.setString(2, leave_pw);
			pstmt.setString(3, leave_nm);
			pstmt.setString(4, leave_tel);
			pstmt.setString(5, leave_email);
			pstmt.setString(6, leave_image);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0)
			{
				InsertLeave = true;
				System.out.println("출국자 데이터 insert 성공");
			}
			else
			{
				System.out.println("출국자 데이터 insert 실패");
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return InsertLeave;
	}
	
	public LeavePeople LeaveInfor(String leave_num) throws ClassNotFoundException
	{
		LeavePeople LeavePeople = null;
		
		try {
			connect();
			String sql = "SELECT * FROM LEAVE_PEOPLE WHERE LEAVE_NUM = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, leave_num);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
			LeavePeople dto = new LeavePeople();
			dto.setLeave_tel(rs.getString("LEAVE_TEL"));
			dto.setLeave_name(rs.getString("LEAVE_NAME"));
			dto.setLeave_id(rs.getString("LEAVE_ID"));
			dto.setLeave_email(rs.getString("LEAVE_EMAIL"));
			dto.setLeave_image(rs.getString("LEAVE_IMAGE"));
			LeavePeople = dto;
			}
			disconnect();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return LeavePeople;
	}
	
	public boolean correctLeav(LeavePeople leavepeople) throws ClassNotFoundException
	{
		boolean correctleave = false;
		
		String sql = "UPDATE LEAVE_PEOPLE SET"
				+ " LEAVE_PASSWORD = ?,"
				+ "LEAVE_NAME = ?,"
				+ "LEAVE_TEL = ?, "
				+ "LEAVE_EMAIL = ?, "
				+ "LEAVE_IMAGE = ?"
				+ " WHERE LEAVE_NUM = ?";
		
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, leavepeople.getLeave_password());
			pstmt.setString(2, leavepeople.getLeave_name());
			pstmt.setString(3, leavepeople.getLeave_tel());	
			pstmt.setString(4, leavepeople.getLeave_email());
			pstmt.setString(5, leavepeople.getLeave_image());	
			pstmt.setInt(6, Integer.parseInt(leavepeople.getLeave_num()));
			int result = pstmt.executeUpdate();
			
			System.out.println("Leave people information has successfully corrected.");
			
			if(result > 0)
			{
				correctleave = true;
				System.out.println("LEAVE_PEOPLE 마이 페이지 수정 성공");
			}
			
			disconnect();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return correctleave;
	}
	
	public boolean acccountDelete (String leave_num) throws ClassNotFoundException
	{
		boolean acccountdelete = false;
		
		String sql = "DELETE FROM LEAVE_PEOPLE WHERE LEAVE_NUM = ?";
		
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,leave_num);
			int result = pstmt.executeUpdate();
			
			System.out.println("Leave people has successfully deleted.");
			
			if(result > 0)
			{
				acccountdelete = true;
				System.out.println("LEAVE_PEOPLE 계정 탈퇴 성공");
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return acccountdelete;
	}
}
