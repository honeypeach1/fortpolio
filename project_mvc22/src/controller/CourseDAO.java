package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Course;
import model.Information;

public class CourseDAO {

	DataSource dataSource;
	PreparedStatement pstmt = null;
	Connection conn = null;
	Course course = null;
	
	public CourseDAO() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static CourseDAO instance = new CourseDAO();
	
	public static CourseDAO getInstance(){
		return instance;
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

	private void connect() {
		// TODO Auto-generated method stub
		try {
				conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Course> AllList() {
		ArrayList<Course> dtos = new ArrayList<Course>();
		
		try {
			connect();
			String sql = "SELECT COURSE.*,STAY_PEOPLE.*, TO_CHAR(course.START_DATE,'YYYY-MM-DD') as START_DATE2, TO_CHAR(course.END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE, STAY_PEOPLE WHERE COURSE.STAY_NUM = STAY_PEOPLE.STAY_NUM";
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtos;
	}
	
	////HOLIDAY VISA
	public ArrayList<Course> holidayList() {
		
		ArrayList<Course> dtos = new ArrayList<Course>();
		
		try {
			connect();
			String sql = "SELECT COURSE_NUM,COURSE_NAME,COURSE_PRICE,COMMENTS,COURSE_IMAGE1,NATION,VISA,LONG_DATE,STAY_NAME,PEOPLE_NUM,TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE2,TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE NATURAL JOIN STAY_PEOPLE WHERE VISA = 'WORKING HOLIDAY'";
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtos;
	}
	
	//IMMIGRATION VISA
	public ArrayList<Course> stayList() {
		
		ArrayList<Course> dtos = new ArrayList<Course>();
		
		try {
			connect();
			String sql = "SELECT COURSE_NUM,COURSE_NAME,COURSE_PRICE,COMMENTS,COURSE_IMAGE1,NATION,VISA,LONG_DATE,STAY_NAME,PEOPLE_NUM,TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE2,TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE NATURAL JOIN STAY_PEOPLE WHERE VISA = 'IMMIGRATION'";
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtos;
	}
	
	////WORK VISA
	public ArrayList<Course> workList() {
		
		ArrayList<Course> dtos = new ArrayList<Course>();
		
		try {	
			connect();
			String sql = "SELECT COURSE_NUM,COURSE_NAME,COURSE_PRICE,COMMENTS,COURSE_IMAGE1,NATION,VISA,LONG_DATE,STAY_NAME,PEOPLE_NUM,TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE2,TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE NATURAL JOIN STAY_PEOPLE WHERE VISA = 'WORK'";
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtos;
	}
	
	////STUDENT VISA
	public ArrayList<Course> studentList() {
		
		ArrayList<Course> dtos = new ArrayList<Course>();

		try {
			connect();
			String sql = "SELECT COURSE_NUM,COURSE_NAME,COURSE_PRICE,COMMENTS,COURSE_IMAGE1,NATION,VISA,LONG_DATE,STAY_NAME,PEOPLE_NUM,TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE2,TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE NATURAL JOIN STAY_PEOPLE WHERE VISA = 'STUDENT'";
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtos;
	}

	public ArrayList<Course> shortList() {
		ArrayList<Course> dtos = new ArrayList<Course>();
		try {
			connect();
			String sql = "SELECT COURSE_NUM,COURSE_NAME,COURSE_PRICE,COMMENTS,COURSE_IMAGE1,NATION,VISA,LONG_DATE,STAY_NAME,PEOPLE_NUM,TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE2,TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE NATURAL JOIN STAY_PEOPLE WHERE VISA = 'SHORT TRAVEL'";
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dtos;
	}	

	public ArrayList<Course> longList() {
		
		ArrayList<Course> dtos = new ArrayList<Course>();
		
		try {
			connect();
			String sql = "SELECT COURSE_NUM,COURSE_NAME,COURSE_PRICE,COMMENTS,COURSE_IMAGE1,NATION,VISA,LONG_DATE,STAY_NAME,PEOPLE_NUM,TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE2,TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE NATURAL JOIN STAY_PEOPLE WHERE VISA = 'LONG TRAVEL'";
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtos;
	}	
	
	// ���� �˻�
	public ArrayList<Course> SearchPack(String visa, String nation) {
		
		ArrayList<Course> dtos = new ArrayList<Course>();
		
		try {	
			connect();
			String sql = "SELECT COURSE_NUM,COURSE_NAME,COURSE_PRICE,COMMENTS,COURSE_IMAGE1,NATION,VISA,LONG_DATE,STAY_NAME,PEOPLE_NUM,TO_CHAR(START_DATE,'YYYY-MM-DD') as START_DATE2,TO_CHAR(END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE NATURAL JOIN STAY_PEOPLE WHERE VISA = ? AND NATION = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, visa);
			pstmt.setString(2, nation);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				Course dto = new Course();
				dto.setCourse_num(resultSet.getString("COURSE_NUM"));
				dto.setCourse_name(resultSet.getString("COURSE_NAME"));
				dto.setCourse_price(resultSet.getString("COURSE_PRICE"));
				dto.setComments(resultSet.getString("COMMENTS"));
				dto.setCourse_image1(resultSet.getString("COURSE_IMAGE1"));
				dto.setNation(resultSet.getString("NATION"));
				dto.setVisa(resultSet.getString("VISA"));
				dto.setLong_date(resultSet.getString("LONG_DATE"));
				dto.setStay_name(resultSet.getString("STAY_NAME"));
				dto.setPeople_num(resultSet.getString("PEOPLE_NUM"));
				dto.setStart_date(resultSet.getString("START_DATE2"));
				dto.setEnd_date(resultSet.getString("END_DATE2"));
				dtos.add(dto);
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtos;
	}
	
	public boolean makeNewReservation(String course_num, String leave_num, String reser_date, String peoples)
	{
		boolean isRevSuccess = false;
		
		//TODO : �ߺ� ������ �ִ��� Ȯ���ϴ� �������� �ۼ��� ��
		
		String sql = "INSERT INTO RESERVATION VALUES (RESER_NUM.NEXTVAL, ?, ?, TO_DATE(?, 'MM/DD/YYYY'), ?)";
		
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(leave_num));
			pstmt.setInt(2, Integer.parseInt(course_num));
			pstmt.setString(3, reser_date);
			pstmt.setInt(4, Integer.parseInt(peoples));
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isRevSuccess = true;
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isRevSuccess;
	}
	
	//예약 테이블의 코스넘버 가져오기
	/*public Course GetCourseNum(String reser_num)
	{
		Course course = null;
		String sql = "select course_num from reservation where reser_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reser_num);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				course = new Course();
				course.setCourse_num(rs.getString("course_num"));
			} 
			disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return course;
	}*/
	//코스 상세 보기(이민, 학생, 워크, 워홀)
	public Course GetDetailInfor(String course_num)
	{
		Course course = null;
		String sql = "SELECT COURSE.*,STAY_PEOPLE.*, TO_CHAR(course.START_DATE,'YYYY-MM-DD') as START_DATE2, TO_CHAR(course.END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE, STAY_PEOPLE WHERE COURSE_NUM = ? AND COURSE.STAY_NUM = STAY_PEOPLE.STAY_NUM";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course_num);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Course dto= new Course();
				dto.setCourse_num(rs.getString("course_num"));
				dto.setCourse_name(rs.getString("course_name"));
				dto.setCourse_price(rs.getString("course_price"));
				dto.setComments(rs.getString("comments"));
				dto.setCourse_image1(rs.getString("course_image1"));
				dto.setCourse_image2(rs.getString("course_image2"));
				dto.setCourse_image3(rs.getString("course_image3"));
				dto.setCourse_image4(rs.getString("course_image4"));
				dto.setNation(rs.getString("nation"));
				dto.setVisa(rs.getString("visa"));
				dto.setLong_date(rs.getString("long_date"));
				dto.setStart_date(rs.getString("start_date2"));
				dto.setEnd_date(rs.getString("end_date2"));
				dto.setStay_name(rs.getString("stay_name"));
				dto.setStay_tel(rs.getString("stay_tel"));
				dto.setStay_email(rs.getString("stay_email"));
				dto.setPeople_num(rs.getString("people_num"));
				
				course = dto;
				
				//여기서 추가 코스 없을시 창 띄우게!!
				sql = "SELECT * FROM CATEGORY NATURAL JOIN INFORMATION WHERE COURSE_NUM = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, course_num);
				
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next())
				{
					ArrayList<Information> infos = new ArrayList<>();
					Information temp_info;
					
					do {
						temp_info = new Information();
						temp_info.setCategory_kind(rs2.getString("category_kind"));
						temp_info.setCategory_comments(rs2.getString("category_comments"));
						temp_info.setCategory_name(rs2.getString("category_name"));
						temp_info.setCategory_num(rs2.getString("category_num"));
						infos.add(temp_info);
					}while(rs2.next());
					
					dto.setInformation(infos);
					course = dto;
					}
				else {
					System.out.println("해당 페이지 없음");
					course = dto;
				}
				}
			
			disconnect();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return course;
	}
	
		//�ڽ� ������ ��������(�ܱ� �ڽ�)
				public Course GetShortTravel(String course_num)
				{
					Course course = null;
					String sql = "SELECT COURSE.*, STAY_PEOPLE.*,TO_CHAR(course.START_DATE,'YYYY-MM-DD') as START_DATE2, TO_CHAR(course.END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE, STAY_PEOPLE WHERE VISA = 'SHORT TRAVEL' AND COURSE.STAY_NUM = STAY_PEOPLE.STAY_NUM AND COURSE_NUM = ?";
					try {
						connect();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, course_num);
						
						ResultSet rs = pstmt.executeQuery();
						
						if (rs.next())
						{
							Course dto= new Course();
							dto.setCourse_num(rs.getString("course_num"));
							dto.setCourse_name(rs.getString("course_name"));
							dto.setCourse_price(rs.getString("course_price"));
							dto.setComments(rs.getString("comments"));
							dto.setCourse_image1(rs.getString("course_image1"));
							dto.setCourse_image2(rs.getString("course_image2"));
							dto.setCourse_image3(rs.getString("course_image3"));
							dto.setCourse_image4(rs.getString("course_image4"));
							dto.setNation(rs.getString("nation"));
							dto.setVisa(rs.getString("visa"));
							dto.setLong_date(rs.getString("long_date"));
							dto.setStart_date(rs.getString("start_date2"));
							dto.setEnd_date(rs.getString("end_date2"));
							dto.setStay_name(rs.getString("stay_name"));
							dto.setStay_tel(rs.getString("stay_tel"));
							dto.setStay_email(rs.getString("stay_email"));
							dto.setPeople_num(rs.getString("people_num"));

							course = dto;
							
							//여기서 추가 코스 없을시 창 띄우게!!
							sql = "SELECT * FROM CATEGORY NATURAL JOIN INFORMATION WHERE COURSE_NUM = ?";
							
							
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, course_num);
							
							ResultSet rs2 = pstmt.executeQuery();
							
							if (rs2.next())
							{
								ArrayList<Information> infos = new ArrayList<>();
								Information temp_info;
								
								do {
									temp_info = new Information();
									temp_info.setCategory_kind(rs2.getString("category_kind"));
									temp_info.setCategory_comments(rs2.getString("category_comments"));
									temp_info.setCategory_name(rs2.getString("category_name"));
									temp_info.setCategory_num(rs2.getString("category_num"));
									infos.add(temp_info);
								}while(rs2.next());
								
								dto.setInformation(infos);
								course = dto;
								}
							else {
								System.out.println("해당 페이지 없음");
								course = dto;
							}
							}
						
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return course;
				}
				//�ڽ� ������ ��������(��� �ڽ�)
				public Course GetLongTravel(String course_num)
				{
					Course course = null;
					String sql = "SELECT COURSE.*, STAY_PEOPLE.*,TO_CHAR(course.START_DATE,'YYYY-MM-DD') as START_DATE2, TO_CHAR(course.END_DATE,'YYYY-MM-DD') as END_DATE2 FROM COURSE, STAY_PEOPLE WHERE VISA = 'LONG TRAVEL' AND COURSE.STAY_NUM = STAY_PEOPLE.STAY_NUM AND COURSE_NUM = ?";
					try {
						connect();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, course_num);
						
						ResultSet rs = pstmt.executeQuery();
						
						if (rs.next())
						{
							Course dto= new Course();
							dto.setCourse_num(rs.getString("course_num"));
							dto.setCourse_name(rs.getString("course_name"));
							dto.setCourse_price(rs.getString("course_price"));
							dto.setComments(rs.getString("comments"));
							dto.setCourse_image1(rs.getString("course_image1"));
							dto.setCourse_image2(rs.getString("course_image2"));
							dto.setCourse_image3(rs.getString("course_image3"));
							dto.setCourse_image4(rs.getString("course_image4"));
							dto.setNation(rs.getString("nation"));
							dto.setVisa(rs.getString("visa"));
							dto.setLong_date(rs.getString("long_date"));
							dto.setStart_date(rs.getString("start_date2"));
							dto.setEnd_date(rs.getString("end_date2"));
							dto.setStay_name(rs.getString("stay_name"));
							dto.setStay_tel(rs.getString("stay_tel"));
							dto.setStay_email(rs.getString("stay_email"));
							dto.setPeople_num(rs.getString("people_num"));

							course = dto;
							
							//여기서 추가 코스 없을시 창 띄우게!!
							sql = "SELECT * FROM CATEGORY NATURAL JOIN INFORMATION WHERE COURSE_NUM = ?";
							
							
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, course_num);
							
							ResultSet rs2 = pstmt.executeQuery();
							
							if (rs2.next())
							{
								ArrayList<Information> infos = new ArrayList<>();
								Information temp_info;
								
								do {
									temp_info = new Information();
									temp_info.setCategory_kind(rs2.getString("category_kind"));
									temp_info.setCategory_comments(rs2.getString("category_comments"));
									temp_info.setCategory_name(rs2.getString("category_name"));
									temp_info.setCategory_num(rs2.getString("category_num"));
									infos.add(temp_info);
								}while(rs2.next());
								
								dto.setInformation(infos);
								course = dto;
								}
							else {
								System.out.println("해당 페이지 없음");
								course = dto;
							}
							}
						
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return course;
				}

	//�����ڽ� ���
	public boolean registCourse(Course course)
	{
		boolean isInserted = false;
		
		String sql = "INSERT INTO COURSE VALUES (REGIST_COURSE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'))";
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course.getStay_num());
			pstmt.setString(2, course.getCourse_name());
			pstmt.setString(3, course.getCourse_price());
			pstmt.setString(4, course.getLong_date());
			pstmt.setString(5, course.getPeople_num());
			pstmt.setString(6, course.getVisa());	
			pstmt.setString(7, course.getComments());
			pstmt.setString(8, course.getCourse_image1());
			pstmt.setString(9, course.getCourse_image2());
			pstmt.setString(10, course.getCourse_image3());
			pstmt.setString(11, course.getCourse_image4());
			pstmt.setString(12, course.getNation());
			pstmt.setString(13, course.getStart_date());
			pstmt.setString(14, course.getEnd_date());
			
			int result = pstmt.executeUpdate();
			
			System.out.println("Course has successfully inserted.");
			if (result > 0)
			{
				isInserted = true;
			}
			
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isInserted;
	}
	
	//코스 수정하기
		public boolean correctCourse(Course course)
		{
			boolean isInserted = false;

			String sql = "UPDATE COURSE SET"
					+ " COURSE_NAME = ?,"
					+ "NATION = ?,"
					+ "VISA = ?,"
					+ "COURSE_PRICE = ?,"
					+ "PEOPLE_NUM = ?,"
					+ "LONG_DATE = ?,"
					+ "START_DATE = TO_DATE(?,'YY-MM-DD'),"
					+ "END_DATE = TO_DATE(?,'YY-MM-DD'),"
					+ "COMMENTS = ?,"
					+ "COURSE_IMAGE1 = ?,"
					+ "COURSE_IMAGE2 = ?,"
					+ "COURSE_IMAGE3 = ?,"
					+ "COURSE_IMAGE4 = ?"
					+ " WHERE COURSE_NUM = ?";
			
			try {
				connect();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, course.getCourse_name());
				pstmt.setString(2, course.getNation());
				pstmt.setString(3, course.getVisa());	
				pstmt.setInt(4, Integer.parseInt(course.getCourse_price()));
				pstmt.setInt(5, Integer.parseInt(course.getPeople_num()));
				pstmt.setString(6, course.getLong_date());
				pstmt.setString(7, course.getStart_date());
				pstmt.setString(8, course.getEnd_date());
				pstmt.setString(9, course.getComments());
				pstmt.setString(10, course.getCourse_image1());
				pstmt.setString(11, course.getCourse_image2());
				pstmt.setString(12, course.getCourse_image3());
				pstmt.setString(13, course.getCourse_image4());
				pstmt.setInt(14, Integer.parseInt(course.getCourse_num()));
				int result = pstmt.executeUpdate();
				
				System.out.println("Course has successfully corrected.");
				if (result > 0)
				{
					isInserted = true;
				}
				
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return isInserted;
		}
		
				//��ǰ ������ ���� ������ �ڽ� �ҷ�����
	public Course getStaycourse(String stay_num) {
		String sql = "SELECT * FROM COURSE WHERE STAY_NUM = ?";
		
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stay_num);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				course = new Course();				
				course.setCourse_num(rs.getString("course_num"));
				course.setStay_num(rs.getString("stay_num"));
				course.setNation(rs.getString("NATION"));
				course.setCourse_name(rs.getString("course_name"));
				course.setStay_tel(rs.getString("stay_tel"));
				course.setStay_email(rs.getString("stay_email"));
				course.setStay_name(rs.getString("stay_name"));
				course.setCourse_price(rs.getString("COURSE_PRICE"));
				course.setLong_date(rs.getString("long_date"));
				course.setCourse_image1(rs.getString("course_image1"));
				course.setCourse_image2(rs.getString("course_image2"));
				course.setCourse_image3(rs.getString("course_image3"));
				course.setCourse_image4(rs.getString("course_image4"));
				course.setVisa(rs.getString("visa"));
				course.setComments(rs.getString("COMMENTS"));
				course.setPeople_num(rs.getString("PEOPLES"));
				course.setStay_id(rs.getString("STAY_ID"));
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return course;
	}
	
	//�������� ����
		public ArrayList<Course> CheckList(String leave_num)
		{
			ArrayList<Course> reser = new ArrayList<Course>();
			
			try {
				connect();
				String sql = "select leave_num,reser_num,course_num,TO_CHAR(reser_date,'YYYY-MM-DD') as reser_date,peoples,course_image1,visa,stay_tel,stay_id from reservation natural join course natural join stay_people where leave_num = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, leave_num);
				
				ResultSet rs = pstmt.executeQuery();

				while (rs.next())
				{
					Course reser_info = new Course();
					reser_info = new Course();
					reser_info.setLeave_num(rs.getString("leave_num"));
					reser_info.setReser_num(rs.getString("reser_num"));
					reser_info.setCourse_num(rs.getString("course_num"));
					reser_info.setReser_date(rs.getString("reser_date"));
					reser_info.setPeoples(rs.getString("peoples"));
					reser_info.setCourse_image1(rs.getString("course_image1"));
					reser_info.setVisa(rs.getString("visa"));
					reser_info.setStay_tel(rs.getString("stay_tel"));
					reser_info.setStay_id(rs.getString("stay_id"));
					reser.add(reser_info);
					}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return reser;
		}
		
		//예약 날짜 불러오기 j query로 처리해야할 값(DetailInfor)
		public ArrayList<String> GetReserDate(String course_num)
		{
			ArrayList<String> reserdate = new ArrayList<String>();
			
			try {
				connect();
				String sql = "SELECT RESER_DATE FROM RESERVATION WHERE COURSE_NUM = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, course_num);
				
				ResultSet rs = pstmt.executeQuery();

				while (rs.next())
				{
					reserdate.add("\"" + rs.getString("RESER_DATE") + "\"");
				}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return reserdate;
		}

		//예약 날짜 불러오기 j query로 처리해야할 값(TravelInfor)
				public ArrayList<String> GetTravelReserDate(String course_num)
				{
					ArrayList<String> resertraveldate = new ArrayList<String>();
					
					try {
						connect();
						String sql = "SELECT RESER_DATE,LONG_DATE FROM COURSE NATURAL JOIN RESERVATION WHERE COURSE_NUM = ?";
					
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, course_num);
						
						ResultSet rs = pstmt.executeQuery();

						while (rs.next())
						{
							resertraveldate.add(rs.getString("LONG_DATE"));
						}
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return resertraveldate;
				}
		
		//예약 취소
		public boolean CancelReser(String reser_num)
		{
			boolean reserdelete = false;
			
			try {
				connect();
				String sql = "delete from reservation where reser_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(reser_num));
				
				int result = pstmt.executeUpdate();
				
				if (result > 0)
				{
					reserdelete = true;
					System.out.println("예약이 취소가 완료되었습니다.");
				}
				else
				{
					System.out.println("예약 취소 실패.");
				}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return reserdelete;
		}
		
		//판매자 코스 리스트 불러오기
				public ArrayList<Course> CourseList(String stay_num)
				{
					ArrayList<Course> lists = new ArrayList<Course>();
					
					try {
						connect();
						String sql = "select course_num,course_image1,visa,course_name,TO_CHAR(start_date,'YYYY-MM-DD') as start_date,TO_CHAR(end_date,'YYYY-MM-DD') as end_date,people_num from course natural join stay_people where stay_num = ?";
					
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, stay_num);
						
						ResultSet rs = pstmt.executeQuery();

						while (rs.next())
						{
							Course courseList = new Course();
							courseList = new Course();
							courseList.setCourse_num(rs.getString("course_num"));
							courseList.setCourse_image1(rs.getString("course_image1"));
							courseList.setVisa(rs.getString("visa"));
							courseList.setCourse_name(rs.getString("course_name"));
							courseList.setStart_date(rs.getString("start_date"));
							courseList.setEnd_date(rs.getString("end_date"));
							courseList.setPeople_num(rs.getString("people_num"));
							lists.add(courseList);
							}
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return lists;
				}
				
				//체류자 넘버로 코스 번호 가져오기
				public ArrayList<Course> GetCourseNum(String stay_num)
				{
					ArrayList<Course> lists = new ArrayList<Course>();
					
					try {
						connect();
						String sql = "select course_num from course where stay_num = ?";
					
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, stay_num);
						
						ResultSet rs = pstmt.executeQuery();

						while (rs.next())
						{
							Course courseList = new Course();
							courseList = new Course();
							courseList.setStay_num(rs.getString("stay_num"));
							lists.add(courseList);
							}
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return lists;
				}
				
				//에약자 리스트 불러오기
				public ArrayList<Course> ReserList(String stay_num)
				{
					ArrayList<Course> reserlists = new ArrayList<Course>();
					
					try {
						connect();
						String sql = "SELECT * FROM LEAVE_PEOPLE NATURAL JOIN (SELECT * FROM RESERVATION NATURAL JOIN COURSE WHERE STAY_NUM = ?)";
					
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, stay_num);
						
						ResultSet rs = pstmt.executeQuery();

						while (rs.next())
						{
							Course reserList = new Course();
							reserList.setReser_num(rs.getString("RESER_NUM"));
							reserList.setLeave_id(rs.getString("LEAVE_ID"));
							reserList.setLeave_tel(rs.getString("LEAVE_TEL"));
							reserList.setLeave_name(rs.getString("LEAVE_NAME"));
							reserList.setReser_date(rs.getString("RESER_DATE"));
							reserlists.add(reserList);
							}
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return reserlists;
				}
				
				//판매자 추가코스 리스트 불러오기
				public ArrayList<Course> addList(String course_num)
				{
					ArrayList<Course> addlists = new ArrayList<Course>();
					
					try {
						connect();
						String sql = "select * from information natural join category where course_num = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1,course_num);
						
						ResultSet rs = pstmt.executeQuery();
						
						while (rs.next()) {
								Course addlist = new Course();
								addlist = new Course();
								addlist.setCategory_num(rs.getString("category_num"));
								addlist.setCategory_kind(rs.getString("category_kind"));
								addlist.setCategory_comments(rs.getString("category_comments"));
								addlist.setCategory_name(rs.getString("category_name"));
								addlist.setCourse_num(rs.getString("course_num"));
								addlists.add(addlist);
						}
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return addlists;
			}
				
				//추가 정보 등록하기
				public boolean insertInfor(Course course)
				{
					boolean insertInfor = false;
					
					try {
						connect();
						String sql = "INSERT INTO INFORMATION VALUES (INFOR_NUM.NEXTVAL, ?, ?, ?)";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, course.getCategory_kind()); 
						pstmt.setString(2, course.getCategory_name());
						pstmt.setString(3, course.getCategory_comments());
						int result = pstmt.executeUpdate();
						
						System.out.println("Information Table has successfully inserted.");

						if (result > 0)
						{	
							sql = "INSERT INTO CATEGORY VALUES (?, INFOR_NUM.CURRVAL)";
							
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, course.getCourse_num());
							
							result = pstmt.executeUpdate();
							
							if (result > 0)
								insertInfor = true;
						}
						
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return insertInfor;
				}
				
				//예약 삭제(판매자 마이페이지에서)
				public boolean deleteReserNum(String reser_num) {
					boolean reserdelete = false;
					
					try {
						connect();
					String sql = "delete from reservation where reser_num = ?";

						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, reser_num);
						

						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, Integer.parseInt(reser_num));
						
						int result = pstmt.executeUpdate();
						
						if (result > 0)
						{
							reserdelete = true;
							System.out.println("예약이 취소가 완료되었습니다.");
						}
						else
						{
							System.out.println("예약 취소 실패.");
						}
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return reserdelete;
				}
				
				//코스 삭제(판매자 마이페이지의 리스트에서)
				public boolean deleteCourse(String course_num)
				{
					boolean deletecourse = false;
					
					try {
						connect();
					String sql = "delete from category where course_num = ?";
					

						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, course_num);
						System.out.println("카테고리 테이블 "+course_num);
						
						
						sql = "delete from course where course_num = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, course_num);
						System.out.println("코스 테이블 " +course_num);
						int result = pstmt.executeUpdate();
						
						if (result > 0)
						{
							deletecourse = true;
							System.out.println("코스가 성공적으로 삭제 되었습니다.");
						}
						else
						{
							System.out.println("코스 삭제 실패.");
						}
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return deletecourse;
				}
				
				//추가 정보 삭제하기(판매자 코스관리에서 추가정보 삭제시)
				public boolean CancelInfor(String category_num)
				{
				boolean infordelete = false;
				try {
					connect();
					String sql1 = "delete from information where category_num = ?";
					String sql = "delete from category where category_num = ?";

					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1, category_num);
				
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, category_num);
					
					int result = pstmt.executeUpdate();
					
					if (result > 0)
					{
						infordelete = true;
						System.out.println("추가 정보가 성공적으로 삭제 되었습니다.");
					}
					else
					{
						System.out.println("추가 정보가 삭제에 실패했습니다.");
					}
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return infordelete;
				}
				
				//추가 정보 수정하기(판매자 코스관리에서 추가정보 수정시)
				public boolean correctInfor(Course course)
				{
				boolean inforcorrect = false;

				String sql = "UPDATE INFORMATION SET"
						+ " CATEGORY_KIND = ?,"
						+ "CATEGORY_NAME = ?,"
						+ "CATEGORY_COMMENTS = ? "
						+" WHERE CATEGORY_NUM = ?";

					try {
						connect();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, course.getCategory_kind());
						pstmt.setString(2, course.getCategory_name());
						pstmt.setString(3, course.getCategory_comments());	
						pstmt.setInt(5, Integer.parseInt(course.getCategory_num()));
						int result = pstmt.executeUpdate();
						
						System.out.println("Course has successfully corrected.");
						if (result > 0)
						{
							inforcorrect = true;
						}
						
						disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return inforcorrect;
				}				
}