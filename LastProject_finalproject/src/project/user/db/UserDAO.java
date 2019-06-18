package project.user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class UserDAO {
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

	// 로그인 할때 아이디, 비밀번호 체크 메서드
	public int idCheck(String id, String pw) throws Exception {
		// 초기값
		int check = 0;

		try {
			conn = connect();
			sql = "SELECT USER_PW FROM USER_TABLE WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pw.equals(rs.getString("USER_PW"))) {
					System.out.println("아이디 비밀번호 일치");
					check = 2;
				} else {
					System.out.println("비밀번호 불일치");
					check = 1;
				}
			} else {
				System.out.println("아이디가 불일치");
				check = 0;
			}

			System.out.println("체크값 : " + check);
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return check;
	}

	// 로그인 성공시 세션값 가져오기
	public UserBean getUserSession(String id, String pw) throws Exception {
		UserBean userbean = null;

		try {
			conn = connect();

			sql = "SELECT USER_NO,USER_NAME FROM USER_TABLE WHERE USER_ID = ? AND USER_PW = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userbean = new UserBean();
				userbean.setUser_no(rs.getInt("USER_NO"));
				userbean.setUser_name(rs.getString("USER_NAME"));

				System.out.println("유저 넘버, 유저 이름 가져오기 성공");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return userbean;
	}

	// 회원가입하기 메소드
	// 0번 초기값
	// 1번 아이디 중복
	// 2번 회원가입 성공
	public int SignUpUser(UserBean ub) throws Exception {
		int SignUp = 0;

		try {
			conn = connect();

			sql = "SELECT USER_ID FROM USER_TABLE WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ub.getUser_id());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("아이디 중복입니다.");
				SignUp = 1;
			} else {
				sql = "INSERT INTO USER_TABLE(user_id,user_pw,user_email,user_phone,user_name,user_image) VALUES(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ub.getUser_id());
				pstmt.setString(2, ub.getUser_pw());
				pstmt.setString(3, ub.getUser_email());
				pstmt.setString(4, ub.getUser_phone());
				pstmt.setString(5, ub.getUser_name());
				pstmt.setString(6, ub.getUser_image());
				pstmt.executeUpdate();

				System.out.println("회원가입 메서드 실행 성공");
				SignUp = 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return SignUp;
	}

	// 회원 아이디 찾기 메소드
	public String FindAcc(String user_name, String user_email) throws Exception {
		String user_id = null;

		try {
			conn = connect();
			sql = "SELECT USER_ID FROM USER_TABLE WHERE USER_NAME = ? AND USER_EMAIL = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user_id = rs.getString("USER_ID");

				System.out.println("회원 아이디 찾기 메소드 성공");
				System.out.println("메소드에서 찾은 유저 아이디 : " + user_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return user_id;
	}

	// 비밀번호 찾기에서 해당 정보가 맞는지 아닌지 체크하기 메소드
	public boolean ckPass(String user_id, String user_name, String user_email) throws Exception {
		boolean ckPass = false;
		try {
			conn = connect();

			sql = "SELECT USER_PW FROM USER_TABLE WHERE USER_ID = ? AND USER_NAME = ? AND USER_EMAIL = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_email);
			rs = pstmt.executeQuery();

			// 비밀번호값이 존재할때(즉, 일치된 정보가 있을때)
			if (rs.next()) {

				System.out.println("일치된 정보 존재(메소드)");
				ckPass = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return ckPass;
	}

	// 임시비밀번호 랜덤값 받아와서 업데이트 시키기
	public boolean UpdatePass(String temPass, String user_id, String user_email) throws Exception {
		boolean UpdateTempPass = false;

		try {
			conn = connect();

			// 아이디와 이메일로 해당 유저 넘버 가져오기
			sql = "SELECT USER_NO FROM USER_TABLE WHERE USER_ID = ? AND USER_EMAIL = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_email);
			rs = pstmt.executeQuery();

			// 해당 넘버값 가져오기
			if (rs.next()) {
				sql = "UPDATE USER_TABLE SET USER_PW = ? WHERE USER_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, temPass);
				pstmt.setInt(2, rs.getInt("USER_NO"));
				pstmt.executeUpdate();

				System.out.println("메서드 단의 임시 비밀번호 수정 성공");

				UpdateTempPass = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return UpdateTempPass;
	}

	// 호텔 체크하기
	public int checkHotel(String hotel_name, String hotel_tel) throws Exception {
		int checkHotel = 3;

		try {
			conn = connect();
			sql = "SELECT APPROVAL FROM HOTEL_TABLE WHERE HOTEL_NAME = ? AND HOTEL_TEL = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hotel_name);
			pstmt.setString(2, hotel_tel);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("조회정보가 있음.");
				System.out.println("APPROVAL -> " + rs.getString("APPROVAL"));

				if (rs.getString("APPROVAL").equals("0")) {
					System.out.println("승인 대기 상태");

					checkHotel = 1;
				} else {
					System.out.println("승인 완료");

					checkHotel = 2;
				}
			} else {
				System.out.println("조회 정보가 없음");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return checkHotel;
	}

	// 유저 정보 가져오기
	public UserBean getUserinfo(String user_num) throws Exception {
		UserBean ub = null;

		try {
			conn = connect();
			sql = "SELECT * FROM USER_TABLE WHERE USER_NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ub = new UserBean();
				ub.setUser_no(rs.getInt("USER_NO"));
				ub.setUser_id(rs.getString("USER_ID"));
				ub.setUser_pw(rs.getString("USER_PW"));
				ub.setUser_email(rs.getString("USER_EMAIL"));
				ub.setUser_phone(rs.getString("USER_PHONE"));
				ub.setUser_name(rs.getString("USER_NAME"));
				ub.setUser_image(rs.getString("USER_IMAGE"));
			}
			System.out.println("회원 정보 불러오기 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return ub;
	}

	// 유저 정보 수정하기
	public int updateUserinfo(UserBean ub) {
		int check = -1;

		try {
			System.out.println("유저 정보 업데이트 DAO 불러오기 완료");
			conn = connect();

			sql = "SELECT USER_ID FROM USER_TABLE WHERE USER_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ub.getUser_no());

			rs = pstmt.executeQuery();
			System.out.println("입력한 정보 확인(아이디, 번호) == " + ub.getUser_id() + ", " + ub.getUser_no());
			if (rs.next()) {
				if (ub.getUser_id().equals(rs.getString("USER_ID"))) {
					sql = "UPDATE USER_TABLE SET USER_PW = ?, USER_NAME = ?, USER_EMAIL = ?, USER_PHONE = ? WHERE USER_ID = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, ub.getUser_pw());
					pstmt.setString(2, ub.getUser_name());
					pstmt.setString(3, ub.getUser_email());
					pstmt.setString(4, ub.getUser_phone());
					pstmt.setString(5, ub.getUser_id());

					pstmt.executeUpdate();
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return check;
	}

	// 회원 탈퇴 페이지 정보 가져오기
	public UserBean getDUserinfo(int user_num) {
		UserBean ub = null;

		try {
			conn = connect();

			sql = "SELECT * FROM USER_TABLE WHERE USER_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ub = new UserBean();
				ub.setUser_id(rs.getString("USER_ID"));
				ub.setUser_pw(rs.getString("USER_PW"));
			}
			System.out.println("탈퇴 ID 불러오기 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return ub;
	}

	// 회원 탈퇴하기
	public int deleteUser(String user_id, String user_pw) {
		int check = -1;

		try {
			conn = connect();
			sql = "SELECT USER_PW FROM USER_TABLE WHERE USER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (user_pw.equals(rs.getString("USER_PW"))) {
					sql = "DELETE FROM USER_TABLE WHERE USER_ID = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user_id);
					pstmt.executeUpdate();
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return check;
	}

	// 예약 취소 메소드
	public int BookCancel(int booking_no, String user_num) {
		UserBean ub = new UserBean();
		System.out.println("예약취소 DAO 현재 예약 항목 번호 : " + booking_no);
		System.out.println("예약취소 DAO 현재 유저 번호 : " + user_num);

		int check = 0;
		try {
			conn = connect();
			sql = "SELECT booking_state FROM booking_table WHERE booking_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booking_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "UPDATE booking_table SET booking_state = ? WHERE booking_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 3);
				pstmt.setInt(2, booking_no);
				pstmt.executeUpdate();

				check = 1;
				System.out.println("예약 취소 DAO 정상 작동 -> " + check);

			} else {
				check = 0;
				System.out.println("예약 취소 DAO 이상 발생 -> " + check);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	public int changeImage(UserBean ub, String user_num) {
		int check = 0;
		System.out.println("프사 변경 DAO 현재 유저 번호 : " + user_num);

		try {
			conn = connect();

			sql = "SELECT user_image FROM user_table WHERE user_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			rs = pstmt.executeQuery();

			System.out.println("바꾸게 될 사진 이미지 : " + ub.getUser_image());
			if (rs.next()) {
				sql = "UPDATE user_table SET user_image = ? WHERE user_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ub.getUser_image());
				pstmt.setString(2, user_num);
				pstmt.executeUpdate();

				check = 1;
				System.out.println("프사 변경 DAO 정상 작동 : " + check);
			} else {
				check = 0;
				System.out.println("프사 변경 DAO 오류 발생 : " + check);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return check;
	}
}
