package net.project.hotelregist.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HotelRegisDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	public Connection getConnection() throws Exception{
		Context init = new InitialContext(); 
		
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/hotels");
		
		con = ds.getConnection();
		
		return con; 
	}
	
	public void closeDB(){
		try{
			if(con != null){ con.close(); }
			if(pstmt != null){ pstmt.close(); }
			if(rs != null){ rs.close(); }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertHotel(HotelRegisBean hb){
		System.out.println("여기는 dao 입니다");
		int num = 0;

		try {
			con = getConnection();
			
			// 게시판 글번호 계산하기
			sql = "select max(hotel_no) from hotel_table";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			System.out.println("글번호 (num) = " + num);
			System.out.println("글번호"+num);
			
			
			
			// sql �옉�꽦 & pstmt 媛앹껜 �깮�꽦
			sql ="insert into hotel_table(hotel_no,hotel_name,hotel_address,hotel_class,hotel_main_image,hotel_instroduct,hotel_tel,approval)"
					+ "values(?,?,?,?,?,?,?,0)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, hb.getHotel_name());
			pstmt.setString(3, hb.getHotel_address());
			pstmt.setInt(4, hb.getHotel_class());
			pstmt.setString(5, hb.getHotel_pic1());
			pstmt.setString(6, hb.getHotel_instroduct());
			pstmt.setString(7, hb.getHotel_tel());
		
			System.out.println("실행완료");		
			pstmt.executeUpdate();
			System.out.println("hotel_table 데이터 저장 완료");
			
			
			//여기가 호텔옵션 넣는 코드
			
			
			int [] hotel_option_no = hb.getHotel_option_no();
			for(int i=0;i<hotel_option_no.length;i++){
			
			sql="insert into hotel_option_con_table(hotel_no,hotel_option_no) values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, hotel_option_no[i]);
			System.out.println("호텔 옵션 값 확인"+hotel_option_no[i]);
			pstmt.executeUpdate();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
				int pic_num = 0 ;
			
				sql = "select max(hotel_pic_no) from hotel_pic_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					pic_num = rs.getInt(1) + 1;
				}
				System.out.println("사진번호 (num) = " + pic_num);
				System.out.println("사진번호"+pic_num);
			
				sql="insert into hotel_pic_table(hotel_pic_no,hotel_no,hotel_pic) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pic_num);
				pstmt.setInt(2, num);
				pstmt.setString(3, hb.getHotel_pic1());
				pstmt.executeUpdate();
				System.out.println("hotel_pic 데이터 저장 완료");
				
				
				 pic_num = 0 ;
				
				sql = "select max(hotel_pic_no) from hotel_pic_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					pic_num = rs.getInt(1) + 1;
				}
				System.out.println("사진번호 (num) = " + pic_num);
				System.out.println("사진번호"+pic_num);
				sql="insert into hotel_pic_table(hotel_pic_no,hotel_no,hotel_pic) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pic_num);
				pstmt.setInt(2, num);
				pstmt.setString(3, hb.getHotel_pic2());
				pstmt.executeUpdate();
				System.out.println("hotel_pic 데이터 저장 완료");
				
				
				 pic_num = 0 ;
				
				sql = "select max(hotel_pic_no) from hotel_pic_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					pic_num = rs.getInt(1) + 1;
				}
				System.out.println("사진번호 (num) = " + pic_num);
				System.out.println("사진번호"+pic_num);
				sql="insert into hotel_pic_table(hotel_pic_no,hotel_no,hotel_pic) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pic_num);
				pstmt.setInt(2, num);
				pstmt.setString(3, hb.getHotel_pic3());
				pstmt.executeUpdate();
				System.out.println("hotel_pic 데이터 저장 완료");
				
				
				 pic_num = 0 ;
				
				sql = "select max(hotel_pic_no) from hotel_pic_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					pic_num = rs.getInt(1) + 1;
				}
				System.out.println("사진번호 (num) = " + pic_num);
				System.out.println("사진번호"+pic_num);
				sql="insert into hotel_pic_table(hotel_pic_no,hotel_no,hotel_pic) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pic_num);
				pstmt.setInt(2, num);
				pstmt.setString(3, hb.getHotel_pic4());
				pstmt.executeUpdate();
				System.out.println("hotel_pic 데이터 저장 완료");
				
				
				 pic_num = 0 ;
				
				sql = "select max(hotel_pic_no) from hotel_pic_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					pic_num = rs.getInt(1) + 1;
				}
				System.out.println("사진번호 (num) = " + pic_num);
				System.out.println("사진번호"+pic_num);
				sql="insert into hotel_pic_table(hotel_pic_no,hotel_no,hotel_pic) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pic_num);
				pstmt.setInt(2, num);
				pstmt.setString(3, hb.getHotel_pic5());
				pstmt.executeUpdate();
				System.out.println("hotel_pic 데이터 저장 완료");
				
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
	
		return num;
	}
	
	
}
