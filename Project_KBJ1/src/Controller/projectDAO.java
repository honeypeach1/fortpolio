package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.omg.CORBA.COMM_FAILURE;

import javafx.animation.Timeline;
import model.CommentTable;
import model.FriendApproval;
import model.TimeLine;
import model.UserProfile;
import model.UserTable;
import oracle.security.o5logon.a;

public class projectDAO {
	
	DataSource dataSource;
	PreparedStatement pstmt = null;
	Connection conn = null;

	private static projectDAO instance = new projectDAO();
	
	//DB연결
		private void connect() {
			// TODO Auto-generated method stub
			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
				conn = dataSource.getConnection();
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	//DB끊기
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
	
	
	//로그인 하기 메서드
	public UserTable checkUser(String id, String pw) throws ClassNotFoundException
	{
		UserTable usertable = null;
		
		try {
			connect();
			String sql = "SELECT *FROM USER_TABLE WHERE USER_ID = ? AND USER_PW = ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				usertable = new UserTable();
				usertable.setUser_num(rs.getString("USER_NUM"));
				usertable.setUser_id(rs.getString("USER_ID"));
				usertable.setUser_pw(rs.getString("USER_PW"));
				usertable.setUser_name(rs.getString("USER_NAME"));
				usertable.setUser_image(rs.getString("USER_IMAGE"));
				System.out.println("[projectDAO.java.checkUser()] User " + id + " has log in.");
				System.out.println();
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return usertable;
	}
	
	//이하 아래 method생성 및 불러오기
	public UserTable InforPage(String user_num) throws ClassNotFoundException
	{
		UserTable usertable = null;

		try {
			connect();
			String sql = "SELECT USER_NUM,USER_ID,USER_PW,USER_NAME,USER_IMAGE,NATION_NAME,USER_TEL,"
					   + "USER_EMAIL,TO_CHAR(BORN_DATE,'YYYY-MM-DD') AS BORN_DATE2,USER_GENDER"
					   + " FROM USER_TABLE NATURAL JOIN NATION_TABLE WHERE USER_NUM = ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				usertable = new UserTable();
				usertable.setUser_num(rs.getString("USER_NUM"));
				usertable.setUser_id(rs.getString("USER_ID"));
				usertable.setUser_pw(rs.getString("USER_PW"));
				usertable.setUser_name(rs.getString("USER_NAME"));
				usertable.setUser_image(rs.getString("USER_IMAGE"));	
				usertable.setNation_name(rs.getString("NATION_NAME"));
				usertable.setUser_tel(rs.getString("USER_TEL"));
				usertable.setUser_email(rs.getString("USER_EMAIL"));
				usertable.setBorn_date(rs.getString("BORN_DATE2"));
				usertable.setUser_gender(rs.getString("USER_GENDER"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return usertable;
	}
	
	// 이하 아래 method생성 및 세부 정보 불러오기
	public UserProfile addInfor(String user_num) throws ClassNotFoundException {
		UserProfile userprof = null;

		try {
			connect();
			String sql = "SELECT ELEMENT_SCHOOL_NAME,MIDDLE_SCHOOL_NAME,HIGH_SCHOOL_NAME,UNIVERSITY_NAME,"
					+ "JOB_NAME,PRO_ADDRESS,SUBSTR(PRO_ADDRESS,1,10)||'...' AS PRO_ADDRESS1,PRO_MARRIAGE,PRO_IMAGE1"
					+ " FROM USER_PROFILE WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				userprof = new UserProfile();
				userprof.setElement_school_name(rs.getString("ELEMENT_SCHOOL_NAME"));
				userprof.setMiddle_school_name(rs.getString("MIDDLE_SCHOOL_NAME"));
				userprof.setHigh_school_name(rs.getString("HIGH_SCHOOL_NAME"));
				userprof.setUniversity_name(rs.getString("UNIVERSITY_NAME"));
				userprof.setJob_name(rs.getString("JOB_NAME"));
				userprof.setPro_address(rs.getString("PRO_ADDRESS"));
				userprof.setPro_address(rs.getString("PRO_ADDRESS1"));
				userprof.setPro_marriage(rs.getString("PRO_MARRIAGE"));
				userprof.setPro_image1(rs.getString("PRO_IMAGE1"));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return userprof;
	}

	//친구 리스트 가져오기 메서드
	//TODO 1일 경우 친구, 0일 경우 대기 목록임.
	public ArrayList<UserTable> friendList(String my_num)
	{
		ArrayList<UserTable> getFriendList = new ArrayList<>();
		
		try {
			connect();
			String sql = "SELECT A.USER_NUM AS USER_NUM1,A.USER_ID AS USER_ID1,A.USER_NAME AS USER_NAME1,A.USER_IMAGE AS USER_IMAGE1,B.APPROVAL AS APPROVAL1" + 
					" FROM USER_TABLE A JOIN FRIEND_APPROVAL B" + 
					" ON A.USER_NUM = B.FRI_APP_NUM" + 
					" WHERE B.FRI_ACC_NUM = ? AND B.APPROVAL = 1"; //1은 친구		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, my_num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserTable dto = new UserTable();
				dto.setUser_num(rs.getString("USER_NUM1"));
				dto.setUser_id(rs.getString("USER_ID1"));
				dto.setUser_name(rs.getString("USER_NAME1"));
				dto.setUser_image(rs.getString("USER_IMAGE1"));
				dto.setApproval(rs.getString("APPROVAL1"));
				getFriendList.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return getFriendList;
	}
	
	//친구 신청하기 메서드(신청값이라 0으로 초기화)
	public boolean addfriend(String friends_num,String my_num) {
		boolean addfriend = false;

		try {
			connect();
			String sql = "INSERT INTO FRIEND_APPROVAL VALUES(?,0,?)"; //첫번째 친구의 번호(외래키), 두번째 친구신청하는 사람의 번호(즉, 내 번호)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, friends_num);
			pstmt.setString(2, my_num);
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				addfriend = true;
			}
			System.out.println("친구 신청 메서드 성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return addfriend;
	}
	
	//나의 타임라인 가져오기 메서드
	public ArrayList<TimeLine> TimeLines(String user_num){
		ArrayList<TimeLine> getTimeLine = new ArrayList<>();
		
		try {
			connect();
			
				String sql = "SELECT A.TIME_LINE_NUM AS TIME_LINE_NUM2,A.CONDITION_NUM AS CONDITION_NUM2,B.USER_NUM AS USER_NUM2,A.TIME_LINE_COMMENTS AS TIME_LINE_COMMENTS2,"
						+ " A.TIME_LINE_TIME AS TIME_LINE_TIME2,A.TIME_LINE_IMAGE AS TIME_LINE_IMAGE2,B.USER_ID AS USER_ID2,B.USER_IMAGE AS USER_IMAGE2,A.UPLOAD_NUM AS UPLOAD_NUM2,C.CONDITION_NAME AS CONDITION_NAME2" + 
						" FROM TIME_LINE A JOIN USER_TABLE B" + 
						" ON A.UPLOAD_NUM = B.USER_NUM INNER JOIN CONDITION_TABLE C ON A.CONDITION_NUM = C.CONDITION_NUM" + 
						" WHERE A.USER_NUM = ? ORDER BY TIME_LINE_NUM DESC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_num);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					TimeLine dto = new TimeLine();
					dto.setTime_line_num(rs.getInt("TIME_LINE_NUM2"));
					dto.setCondition_num(rs.getInt("CONDITION_NUM2"));
					dto.setUser_num(rs.getInt("USER_NUM2"));
					dto.setTime_line_comments(rs.getString("TIME_LINE_COMMENTS2"));
					dto.setTime_line_time(rs.getString("TIME_LINE_TIME2"));
					dto.setTime_line_image(rs.getString("TIME_LINE_IMAGE2"));
					dto.setUser_id(rs.getString("USER_ID2"));
					dto.setUser_image(rs.getString("USER_IMAGE2"));
					dto.setUpload_num(rs.getInt("UPLOAD_NUM2"));
					dto.setCondition_name(rs.getString("CONDITION_NAME2"));
					getTimeLine.add(dto);
					
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return getTimeLine;
	}
	
	//댓글 가져오기 메서드
	public ArrayList<CommentTable> Getcomm(String user_num){
		ArrayList<CommentTable> getComment = new ArrayList<>();
		
		try {
			connect();
			
				String sql = "SELECT TIME_LINE_NUM FROM TIME_LINE WHERE USER_NUM = ? ORDER BY TIME_LINE_NUM";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_num);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					System.out.println("해당 글 넘버  : "+rs.getInt("TIME_LINE_NUM"));
					
					sql = "SELECT A.USER_NUM AS USER_NUM1, A.USER_IMAGE AS USER_IMAGE1, A.USER_NAME AS USER_NAME1, B.COMMENT_NUM AS COMMENT_NUM1, B.COMMENT_TIME AS COMMENT_TIME1, B.COMMENTS AS COMMENTS1, B.TIME_LINE_NUM AS TIME_LINE_NUM1"
							+ " FROM USER_TABLE A JOIN COMMENT_TABLE B ON A.USER_NUM = B.USER_NUM WHERE TIME_LINE_NUM = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rs.getInt("TIME_LINE_NUM"));
					ResultSet rs1 = pstmt.executeQuery();

						int count = 0;
						while(rs1.next()){
							CommentTable dtos = new CommentTable();
							dtos.setUser_num(rs1.getInt("USER_NUM1"));
							dtos.setUser_image(rs1.getString("USER_IMAGE1"));
							dtos.setUser_name(rs1.getString("USER_NAME1"));
							dtos.setComment_num(rs1.getInt("COMMENT_NUM1"));
							dtos.setComment_time(rs1.getString("COMMENT_TIME1"));
							dtos.setComments(rs1.getString("COMMENTS1"));
							dtos.setTime_line_num(rs1.getInt("TIME_LINE_NUM1"));
							count++;
							getComment.add(dtos);
							
							System.out.println("유저 번호 : " + dtos.getUser_num());
							System.out.println("댓글 번호 : " + dtos.getComment_num());
							System.out.println("댓글 쓴 유저 이미지 : "+dtos.getUser_image());
							System.out.println("댓글 내용 : " + dtos.getComments());
							System.out.println("댓글 쓴 시간 : " + dtos.getComment_time());
							System.out.println("댓글 갯수 : " + count);
						}
						System.out.println("-----------------");
						System.out.println("댓글 갯수 / " + count + " /");
						System.out.println("-----------------");
	 				}
					System.out.println("댓글 수 = " + getComment.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return getComment; 
		}
	
	//글목록 가져오기
	//가장 최신에 했던 메서드 답에 근접함
	/*public ArrayList<TimeLine> TimeLines(String user_num){
		ArrayList<TimeLine> getTimeLine = new ArrayList<>();
		ArrayList<CommentTable> getComment = new ArrayList<>();
		
		try {
			connect();
			
				String sql = "SELECT A.TIME_LINE_NUM AS TIME_LINE_NUM2,A.CONDITION_NUM AS CONDITION_NUM2,B.USER_NUM AS USER_NUM2,A.TIME_LINE_COMMENTS AS TIME_LINE_COMMENTS2,"
						+ " A.TIME_LINE_TIME AS TIME_LINE_TIME2,A.TIME_LINE_IMAGE AS TIME_LINE_IMAGE2,B.USER_ID AS USER_ID2,B.USER_IMAGE AS USER_IMAGE2,A.UPLOAD_NUM AS UPLOAD_NUM2,C.CONDITION_NAME AS CONDITION_NAME2" + 
						" FROM TIME_LINE A JOIN USER_TABLE B" + 
						" ON A.UPLOAD_NUM = B.USER_NUM INNER JOIN CONDITION_TABLE C ON A.CONDITION_NUM = C.CONDITION_NUM" + 
						" WHERE A.USER_NUM = ? ORDER BY TIME_LINE_NUM DESC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_num);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					TimeLine dto = new TimeLine();
					dto.setTime_line_num(rs.getInt("TIME_LINE_NUM2"));
					dto.setCondition_num(rs.getInt("CONDITION_NUM2"));
					dto.setUser_num(rs.getInt("USER_NUM2"));
					dto.setTime_line_comments(rs.getString("TIME_LINE_COMMENTS2"));
					dto.setTime_line_time(rs.getString("TIME_LINE_TIME2"));
					dto.setTime_line_image(rs.getString("TIME_LINE_IMAGE2"));
					dto.setUser_id(rs.getString("USER_ID2"));
					dto.setUser_image(rs.getString("USER_IMAGE2"));
					dto.setUpload_num(rs.getInt("UPLOAD_NUM2"));
					dto.setCondition_name(rs.getString("CONDITION_NAME2"));
					getTimeLine.add(dto);
					
					System.out.println("해당 글 넘버  : "+dto.getTime_line_num());
					
					sql = "SELECT A.USER_NUM AS USER_NUM1, A.USER_IMAGE AS USER_IMAGE1, A.USER_NAME AS USER_NAME1, B.COMMENT_NUM AS COMMENT_NUM1, B.COMMENT_TIME AS COMMENT_TIME1, B.COMMENTS AS COMMENTS1"
							+ " FROM USER_TABLE A JOIN COMMENT_TABLE B ON A.USER_NUM = B.USER_NUM WHERE TIME_LINE_NUM = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, dto.getTime_line_num());
					ResultSet rs1 = pstmt.executeQuery();
					
					
					
				if(rs1.next())
				{
					int count = 0;
					while(rs1.next()){
						
						CommentTable dtos = new CommentTable();
						dtos.setUser_num(rs1.getInt("USER_NUM1"));
						dtos.setUser_image(rs1.getString("USER_IMAGE1"));
						dtos.setUser_name(rs1.getString("USER_NAME1"));
						dtos.setComment_num(rs1.getInt("COMMENT_NUM1"));
						dtos.setComment_time(rs1.getString("COMMENT_TIME1"));
						dtos.setComments(rs1.getString("COMMENTS1"));
						count++;
						getComment.add(dtos);
						
						
						System.out.println("댓글 번호 : " + dtos.getComment_num());
						System.out.println("해당 글에 가져오는 댓글 값 : " + dto.getCommentTable());
						System.out.println("댓글 번호 : " + dto.getComment_num());
						System.out.println("댓글 쓴 유저 이미지 : "+dto.getUser_image());
						System.out.println("댓글 내용 : " + dto.getComments());
						System.out.println("댓글 쓴 시간 : " + dto.getComment_time());
						System.out.println("댓글 갯수 : " + count);
					}
					System.out.println("-----------------");
					System.out.println("댓글 갯수 / " + count + " /");
					System.out.println("-----------------");
					//dto.setCommentTable(getComment);
					//getTimeLine.add(dto);
				
				}else {
						System.out.println("댓글이 없습니다.");
					}
 				}
				System.out.println("---------------------------");
				System.out.println("해당 유저의 가져오는 타임라인 갯수 = "+getTimeLine.size() + " / 해당 유저의 댓글 수 = " + getComment.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return getTimeLine; 
	}*/
	
	//친구 페이지에서 보이는 타임라인
	public ArrayList<TimeLine> TimeLinesFri(String user_num){
		ArrayList<TimeLine> getTimeLine = new ArrayList<>();
		
		try {
			connect();
			
		    String sql = "SELECT A.TIME_LINE_NUM AS TIME_LINE_NUM2,A.CONDITION_NUM AS CONDITION_NUM2,B.USER_NUM AS USER_NUM2,A.TIME_LINE_COMMENTS AS TIME_LINE_COMMENTS2,"
						+ " A.TIME_LINE_TIME AS TIME_LINE_TIME2,A.TIME_LINE_IMAGE AS TIME_LINE_IMAGE2,B.USER_ID AS USER_ID2,B.USER_IMAGE AS USER_IMAGE2,A.UPLOAD_NUM AS UPLOAD_NUM2,C.CONDITION_NAME AS CONDITION_NAME2" + 
						" FROM TIME_LINE A JOIN USER_TABLE B" + 
						" ON A.UPLOAD_NUM = B.USER_NUM INNER JOIN CONDITION_TABLE C ON A.CONDITION_NUM = C.CONDITION_NUM" + 
						" WHERE A.USER_NUM = ? AND A.UPLOAD_NUM IN (SELECT UPLOAD_NUM FROM TIME_LINE WHERE USER_NUM = ?) ORDER BY TIME_LINE_NUM DESC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_num);
				pstmt.setString(2, user_num);
				ResultSet rs1 = pstmt.executeQuery(); //select 쿼리 실행문
				
				while (rs1.next()) {
					TimeLine dto = new TimeLine();
					dto.setTime_line_num(rs1.getInt("TIME_LINE_NUM2"));
					dto.setCondition_num(rs1.getInt("CONDITION_NUM2"));
					dto.setUser_num(rs1.getInt("USER_NUM2"));
					dto.setTime_line_comments(rs1.getString("TIME_LINE_COMMENTS2"));
					dto.setTime_line_time(rs1.getString("TIME_LINE_TIME2"));
					dto.setTime_line_image(rs1.getString("TIME_LINE_IMAGE2"));
					dto.setUser_id(rs1.getString("USER_ID2"));
					dto.setUser_image(rs1.getString("USER_IMAGE2"));
					dto.setUpload_num(rs1.getInt("UPLOAD_NUM2"));
					dto.setCondition_name(rs1.getString("CONDITION_NAME2"));
					getTimeLine.add(dto);
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return getTimeLine; 
	}
	
	//친구페이지에서 댓글 가져오기 메서드
		public ArrayList<CommentTable> GetcommFri(String user_num){
			ArrayList<CommentTable> getComment = new ArrayList<>();
			
			try {
				connect();
				
					String sql = "SELECT TIME_LINE_NUM FROM TIME_LINE WHERE USER_NUM = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user_num);
					ResultSet rs = pstmt.executeQuery();
					
					while (rs.next()) {
						System.out.println("해당 글 넘버  : "+rs.getInt("TIME_LINE_NUM"));
						
						sql = "SELECT A.USER_NUM AS USER_NUM1, A.USER_IMAGE AS USER_IMAGE1, A.USER_NAME AS USER_NAME1, B.COMMENT_NUM AS COMMENT_NUM1, B.COMMENT_TIME AS COMMENT_TIME1, B.COMMENTS AS COMMENTS1, B.TIME_LINE_NUM AS TIME_LINE_NUM1"
								+ " FROM USER_TABLE A JOIN COMMENT_TABLE B ON A.USER_NUM = B.USER_NUM WHERE TIME_LINE_NUM = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, rs.getInt("TIME_LINE_NUM"));
						ResultSet rs1 = pstmt.executeQuery();

							int count = 0;
							while(rs1.next()){
								CommentTable dtos = new CommentTable();
								dtos.setUser_num(rs1.getInt("USER_NUM1"));
								dtos.setUser_image(rs1.getString("USER_IMAGE1"));
								dtos.setUser_name(rs1.getString("USER_NAME1"));
								dtos.setComment_num(rs1.getInt("COMMENT_NUM1"));
								dtos.setComment_time(rs1.getString("COMMENT_TIME1"));
								dtos.setComments(rs1.getString("COMMENTS1"));
								dtos.setTime_line_num(rs1.getInt("TIME_LINE_NUM1"));
								count++;
								getComment.add(dtos);
								
								System.out.println("유저 번호 : " + dtos.getUser_num());
								System.out.println("댓글 번호 : " + dtos.getComment_num());
								System.out.println("댓글 쓴 유저 이미지 : "+dtos.getUser_image());
								System.out.println("댓글 내용 : " + dtos.getComments());
								System.out.println("댓글 쓴 시간 : " + dtos.getComment_time());
								System.out.println("댓글 갯수 : " + count);
							}
							System.out.println("-----------------");
							System.out.println("댓글 갯수 / " + count + " /");
							System.out.println("-----------------");
		 				}
						System.out.println("댓글 수 = " + getComment.size());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					disconnect();
				}
				return getComment; 
			}
	
	//회원가입 메서드
	public boolean CreateUser(UserTable user)
	{
		boolean SignUp =false;
			
		try {
			connect();
			String sql = "INSERT INTO USER_TABLE VALUES(USER_TAB.NEXTVAL,?,?,?,?,?,TO_DATE(?, 'MM/DD/YYYY'),?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getNation_num());
			pstmt.setString(2, user.getUser_id());
			pstmt.setString(3, user.getUser_pw());
			pstmt.setString(4, user.getUser_tel());
			pstmt.setString(5, user.getUser_email());
			pstmt.setString(6, user.getBorn_date());
			pstmt.setString(7, user.getUser_gender());
			pstmt.setString(8, user.getUser_name());
			pstmt.setString(9, user.getUser_image());
			int result = pstmt.executeUpdate();
			
			System.out.println("값이 성공적으로 insert 되었습니다.");
			if (result > 0)
			{
				SignUp = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return SignUp;
	}
	
	//TODO 검색 결과 출력 하는 리스트 메서드
	public ArrayList<UserTable> searchInfor(String infor){
		
		ArrayList<UserTable> dtos = new ArrayList<>();
		
		try {
			connect();
			String sql = "SELECT USER_NUM,USER_NAME,USER_ID,USER_TEL,USER_EMAIL,"
			+"TO_CHAR(BORN_DATE,'YY\"년\"MM\"월\"DD\"일\"') as bornDate,USER_GENDER,USER_IMAGE,NATION_NAME "
			+" FROM USER_TABLE NATURAL JOIN NATION_TABLE WHERE USER_NAME = ? OR USER_ID = ? OR USER_GENDER = ? OR NATION_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, infor);
			pstmt.setString(2, infor);
			pstmt.setString(3, infor);
			pstmt.setString(4, infor);
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				UserTable dto = new UserTable();
				dto.setUser_num(resultSet.getString("USER_NUM"));
				dto.setUser_name(resultSet.getString("USER_NAME"));
				dto.setUser_id(resultSet.getString("USER_ID"));
				dto.setUser_tel(resultSet.getString("USER_TEL"));
				dto.setUser_email(resultSet.getString("USER_EMAIL"));
				dto.setBorn_date(resultSet.getString("bornDate"));
				dto.setUser_gender(resultSet.getString("USER_GENDER"));
				dto.setUser_image(resultSet.getString("USER_IMAGE"));
				dto.setNation_name(resultSet.getString("NATION_NAME"));
				dtos.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dtos;
	}
	
	//나의 타임 라인 등록
	public boolean registLine(TimeLine time) {
		
		boolean registTimeLine = false;
		
		try {
			connect();

			String sql = "INSERT INTO TIME_LINE VALUES(TIME_LINE_TAB.NEXTVAL,?,?,?,to_char(sysdate,'yyyy.mm.dd hh24:mi'),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, time.getCondition_num());
			pstmt.setInt(2, time.getUser_num());	//session값 가져오기
			pstmt.setString(3, time.getTime_line_comments());
			pstmt.setString(4, time.getTime_line_image());
			pstmt.setInt(5, time.getUpload_num());		//나의 타임라인에 글을 남기는 것이므로 똑같은 session값 주면 됨
			int result = pstmt.executeUpdate();
			
			System.out.println("타임 라인 등록이 완료 되었습니다.");
			if (result > 0)
			{
				registTimeLine = true;
			}	
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return registTimeLine;
	}
	
	//친구 타임 라인 등록
	public boolean registLinefri(TimeLine times) {
		
		boolean registLineFri = false;
		
		try {
			connect();

			String sql = "INSERT INTO TIME_LINE VALUES(TIME_LINE_TAB.NEXTVAL,?,?,?,to_char(sysdate,'yyyy.mm.dd hh24:mi'),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, times.getCondition_num());
			pstmt.setInt(2, times.getUser_num());	//타임라인 남기는 친구 번호
			pstmt.setString(3, times.getTime_line_comments());
			pstmt.setString(4, times.getTime_line_image());
			pstmt.setInt(5, times.getUpload_num());  //글쓰는 유저 번호
			int result = pstmt.executeUpdate();
			
			System.out.println("친구의 타임 라인 등록이 완료 되었습니다.");
			if (result > 0)
			{
				registLineFri = true;
			}	
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return registLineFri;
	}
	
	//타임 라인 삭제
	//TODO 만약 댓글이 달려있으면 CASCADE 처리하게끔
	public boolean timeLineDelete(String timeNum) {
		
		boolean timeLineDel = false;
		
		try {
		connect();
			
		String sql = "DELETE FROM TIME_LINE WHERE TIME_LINE_NUM = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, timeNum);
		int result = pstmt.executeUpdate();
		System.out.println("타임 라인 삭제가 완료 되었습니다.");
		
		if (result > 0)
		{
			timeLineDel = true;
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		
		return timeLineDel;
	}
	
	//유저 상세정보 보는것 메서드
	public UserProfile getProfile(String user_num){
		UserProfile proFile = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM USER_PROFILE NATURAL JOIN USER_TABLE WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				proFile = new UserProfile();
				proFile.setPro_image1(rs.getString("pro_image1"));
				proFile.setElement_school_name(rs.getString("element_school_name"));
				proFile.setMiddle_school_name(rs.getString("middle_school_name"));
				proFile.setHigh_school_name(rs.getString("high_school_name"));
				proFile.setUniversity_name(rs.getString("university_name"));
				proFile.setJob_name(rs.getString("job_name"));
				proFile.setPro_address(rs.getString("pro_address"));
				proFile.setPro_marriage(rs.getString("pro_marriage"));
				proFile.setUser_name(rs.getString("user_name"));
				proFile.setUser_image(rs.getString("user_image"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return proFile;
	}
	
	// header에 count(*) 친구 신청 들어온 목록 가져오기(빨간 글자)
	public int countFriend(String user_num){
		int countFriend = 0;
		
		try {
			connect();
			String sql = "SELECT COUNT(*) FROM FRIEND_APPROVAL WHERE FRI_ACC_NUM = ? AND APPROVAL = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				countFriend = rs.getInt(1);
				System.out.println(user_num + " 번호의 메서드 와일문 안의 "+rs.getInt("COUNT(*)"));
			}
			System.out.println("메서드 상의 카운트 값 : " + countFriend);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return countFriend;
	}
	
	// header 페이지에서 친구 신청 들어온 목록 가져오기
	// 나의 유저 번호를 가지고 신청한 친구 번호를 먼저 SELECT한다.
	// 그런뒤에 나온 친구번호를 기반으로 친구 정보를 가져온다.
	public ArrayList<FriendApproval> getFriend(String user_num) {
		ArrayList<FriendApproval> friendApproval = new ArrayList<>();
		
		try {
			connect();
			String sql = "SELECT A.FRI_ACC_NUM as FRI_ACC_NUM, A.FRI_APP_NUM as FRI_APP_NUM, B.USER_NAME as USER_NAME,"
					+ " A.APPROVAL as APPROVAL, B.USER_IMAGE as USER_IMAGE"
					+ " FROM FRIEND_APPROVAL A JOIN USER_TABLE B ON A.FRI_APP_NUM = B.USER_NUM"
					+ " WHERE A.FRI_ACC_NUM = ? AND A.APPROVAL = 0";		//체크값 0은 신청상태임.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FriendApproval dto = new FriendApproval();
				dto.setFri_acc_num(rs.getInt("FRI_ACC_NUM"));
				dto.setFri_app_num(rs.getInt("FRI_APP_NUM"));
				dto.setUser_name(rs.getString("USER_NAME"));
				dto.setApproval(rs.getString("APPROVAL"));
				dto.setUser_image(rs.getString("USER_IMAGE"));
				friendApproval.add(dto);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return friendApproval;
	}
	
	//친구 승인하기 메서드
	public boolean approval(String my_num, String friend_num) {
		boolean Approval = false;

		try {
			connect();
			String sql = "UPDATE FRIEND_APPROVAL SET APPROVAL = 1 WHERE FRI_ACC_NUM = ? AND FRI_APP_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, my_num);
			pstmt.setString(2, friend_num);
			int result = pstmt.executeUpdate();
			System.out.println("첫단계 성공");
			
			if(result > 0) {
				sql = "INSERT INTO FRIEND_APPROVAL VALUES(?,1,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, friend_num);
				pstmt.setString(2, my_num);
				int result1 = pstmt.executeUpdate();
				System.out.println("이단계 성공");
				
				if(result1 > 0) {
					Approval = true;
				}
			}
			System.out.println("값 : "+Approval);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return Approval;
	}
	
	//친구 거절하기 메서드
	public boolean deny(String my_num, String friend_num) {
		boolean Deny = false;
		
		try {
			connect();
			String sql = "DELETE FROM FRIEND_APPROVAL WHERE FRI_ACC_NUM = ? AND FRI_APP_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, my_num);
			pstmt.setString(2, friend_num);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				Deny = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return Deny;
	}
	
	//유저 메인정보 수정 체크하기
	public int userCheck(String user_num, String user_name, String user_pw) {
		int usercheck = 0;
		
		try {
			connect();
			
			//디비에서 해당 유저의 이름과 비밀번호 가져오기
			String sql = "SELECT USER_NAME, USER_PW FROM USER_TABLE WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			ResultSet rs = pstmt.executeQuery();

			// 디비에서 가져온 값과 유저가 입력한 아이디 체크하기
			if (rs.next()) {
				if (rs.getString("USER_NAME").equals(user_name) && rs.getString("USER_PW").equals(user_pw)) {
						System.out.println("입력한 이름과 비밀번호가 일치합니다.");
						
						usercheck = 1;
				} else {
					System.out.println("입력한 이름이 일치하지 않습니다.");
					
					usercheck = 2;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return usercheck;
	}
	
	//유저 메인 정보 변경하기 메서드
	public boolean chMainInfor(String user_name,String user_pw,String user_gender,String user_tel,String user_email,
							   String nation_num,String born_date,String user_num) {
		
		boolean changeMainInfor = false;
		
		try {
			connect();
			
			String sql = "UPDATE USER_TABLE SET USER_NAME = ?, USER_PW = ?, USER_GENDER = ?, USER_TEL = ?,"
					+ " USER_EMAIL = ?, NATION_NUM = ?, BORN_DATE = TO_DATE(?, 'MM/DD/YYYY') WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_pw);
			pstmt.setString(3, user_gender);
			pstmt.setString(4, user_tel);
			pstmt.setString(5, user_email);
			pstmt.setString(6, nation_num);
			pstmt.setString(7, born_date);
			pstmt.setString(8, user_num);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				changeMainInfor = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return changeMainInfor;
	}
	
	//유저 세부 정보 변경 메서드
	public boolean chSubInfor(String element_name,String middle_name,String high_name,String univ_name,String job_name,
						      String user_address,String marriage,String user_num) {
		
		boolean changeSubInfor = false;
		
		try {
			connect();
			
			String sql = "UPDATE USER_PROFILE SET ELEMENT_SCHOOL_NAME = ?, MIDDLE_SCHOOL_NAME = ?, HIGH_SCHOOL_NAME = ?,"
					+ " UNIVERSITY_NAME = ?, JOB_NAME = ?, PRO_ADDRESS = ?, PRO_MARRIAGE = ? WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, element_name);
			pstmt.setString(2, middle_name);
			pstmt.setString(3, high_name);
			pstmt.setString(4, univ_name);
			pstmt.setString(5, job_name);
			pstmt.setString(6, user_address);
			pstmt.setString(7, marriage);
			pstmt.setString(8, user_num);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				changeSubInfor = true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return changeSubInfor;
	}

	//유저 세부 정보 입력 메서드
	public boolean chSubUpload(String user_num,String element_name, String middle_name, String high_name, String univ_name,
			String job_name, String user_address, String marriage) {

		boolean uploadSubInfor = false;

		try {
			connect();

			String sql = "INSERT INTO USER_PROFILE(PROFILE_NUM,USER_NUM,ELEMENT_SCHOOL_NAME,MIDDLE_SCHOOL_NAME,HIGH_SCHOOL_NAME,UNIVERSITY_NAME,JOB_NAME,PRO_ADDRESS,PRO_MARRIAGE)"
					+ " VALUES(USER_PROF_TAB.NEXTVAL,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			pstmt.setString(2, element_name);
			pstmt.setString(3, middle_name);
			pstmt.setString(4, high_name);
			pstmt.setString(5, univ_name);
			pstmt.setString(6, job_name);
			pstmt.setString(7, user_address);
			pstmt.setString(8, marriage);
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				uploadSubInfor = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return uploadSubInfor;
	}
	
	//메인 이미지 변경하기 메서드
	public boolean mainImage(String image_file, String user_num) {
		boolean MainImage = false;
		
		try {
			connect();

			String sql = "UPDATE USER_TABLE SET USER_IMAGE = ? WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image_file);
			pstmt.setString(2, user_num);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				MainImage = true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return MainImage;
	}
	
	//처음 세부 이미지 등록 메서드
	public boolean subImage(String image_file,String user_num) {
		boolean subImage = false;
		
		try {
			connect();
			System.out.println(user_num + "/" + image_file);
			String sql = "UPDATE USER_PROFILE SET PRO_IMAGE1 = ? WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image_file);
			pstmt.setString(2, user_num);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				subImage = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return subImage;
	}
	
	//inforpage, searchinfor, friendinfor 페이지에서 서브 이미지 유무 판단하는 메서드
	public String chImg(String user_num) {
		String chImg = null;
		
		try {
			connect();
			
			String sql = "SELECT PRO_IMAGE1 FROM USER_PROFILE WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				chImg = rs.getString("PRO_IMAGE1");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return chImg;
	}
	
	//댓글달기 메서드
	public boolean registComm(CommentTable comment) {
		boolean registComm = false;
		
		try {
			connect();
			
			String sql = "INSERT INTO COMMENT_TABLE VALUES (COMMENT_TAB.NEXTVAL,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getTime_line_num());
			pstmt.setInt(2, comment.getUser_num());
			pstmt.setString(3, comment.getComments());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				registComm = true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return registComm;
	}
	
	//댓글 가져오기 메서드
	public ArrayList<CommentTable> getComment(TimeLine getTimeLine){
		ArrayList<CommentTable> getComment = new ArrayList<>();

		try {
			connect();
			
			String sql = "SELECT A.USER_NUM AS USER_NUM1, A.USER_IMAGE AS USER_IMAGE1, A.USER_NAME AS USER_NAME1, B.COMMENT_NUM AS COMMENT_NUM1, B.COMMENT_TIME AS COMMENT_TIME1, B.COMMENTS AS COMMENTS1"
					+ " FROM USER_TABLE A JOIN COMMENT_TABLE B ON A.USER_NUM = B.USER_NUM WHERE TIME_LINE_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getTimeLine.getTime_line_num());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentTable dto = new CommentTable();
				dto.setUser_num(rs.getInt("USER_NUM1"));
				dto.setUser_image(rs.getString("USER_IMAGE1"));
				dto.setUser_name(rs.getString("USER_NAME1"));
				dto.setComment_num(rs.getInt("COMMENT_NUM1"));
				dto.setComment_time(rs.getString("COMMENT_TIME1"));
				dto.setComments(rs.getString("COMMENTS1"));
				getComment.add(dto);
			
				System.out.println("댓글 번호 : " + dto.getComment_num());
			}
				System.out.println("댓글 가져오기 성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return getComment;
	}
	
	//아이디 체크
	public boolean ckid(String ck_id) {
		boolean ckid = false;
		
		try {
			connect();
			
			String sql = "SELECT USER_ID FROM USER_TABLE WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ck_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {	//값이 존재함
				System.out.println("해당 아이디가 존재합니다.");
				ckid = true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return ckid;
	}
	
	//비밀번호 체크하기
	public int ckpw(String user_num, String user_pw) {
		int ckpw = 0;
		
		try {
			connect();
			
			String sql = "SELECT USER_PW FROM USER_TABLE WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getString("USER_PW").equals(user_pw)) {
					System.out.println("해당 비밀번호 일치 회원탈퇴 진행 하기");
					ckpw = 2;
				} else {
					System.out.println("해당 비밀번호 불일치 진행 불가");
					ckpw = 1;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return ckpw;
	}
	
	//`
	public boolean deleteRegist(String user_num) {
		boolean deleteregist = false;
		
		try {
			connect();
			
			String sql = "DELETE FROM USER_TABLE WHERE USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			pstmt.executeUpdate();
			System.out.println("회원 탈퇴");
			
			//친구 요청 삭제
			sql = "DELETE FROM FRIEND_APPROVAL WHERE FRI_APP_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_num);
			pstmt.executeUpdate();
			System.out.println("친구 요청 삭제");
			
			System.out.println("계정 삭제 성공");
			deleteregist = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return deleteregist;
	}
	
	public boolean delComm(String comment_num,String user_num) {
		boolean delComm = false;
		
		try {
			connect();
			
			String sql = "DELETE FROM COMMENT_TABLE WHERE COMMENT_NUM = ? AND USER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment_num);
			pstmt.setString(2, user_num);
			pstmt.executeUpdate();
			
			System.out.println("메서드 댓글 삭제 성공");
			delComm = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return delComm;
	}
	
	public int delFriend(String my_num, String friend_num) {
		int delFriend = 0;
		
		try {
			connect();
			
			String sql = "DELETE FROM FRIEND_APPROVAL WHERE FRI_ACC_NUM = ? AND FRI_APP_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, my_num);
			pstmt.setString(2, friend_num);
			pstmt.executeUpdate();
			System.out.println("내 기준 친구 삭제 성공");
			
			sql = "DELETE FROM FRIEND_APPROVAL WHERE FRI_ACC_NUM = ? AND FRI_APP_NUM = ?";
			conn.prepareStatement(sql);
			pstmt.setString(1, friend_num);
			pstmt.setString(2, my_num);
			pstmt.executeUpdate();
			System.out.println("친구 기준 삭제 성공");
			System.out.println();
			
			System.out.println("친구 삭제 메서드 성공");
			delFriend = 1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return delFriend;
	}
		
}
