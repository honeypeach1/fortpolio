package net.project.roomregist.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class RoomRegisDAO {

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
		
		public void insertRoom(RoomRegisBean rb){
			System.out.println("여기는 room dao 입니다");
			

			try {
				con = getConnection();
				int num = 0;
				
				
				// 게시판 글번호 계산하기
				sql = "select max(room_type_no) from room_type_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					num = rs.getInt(1) + 1;
				}
				System.out.println("방 타입 번호 (num) = " + num);
				
				
				
				
				// sql �옉�꽦 & pstmt 媛앹껜 �깮�꽦
				sql ="insert into room_type_table(room_type_no,hotel_no,room_name,room_basic_people,room_max_people,room_info,room_price)"
						+ "values(?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setInt(2, rb.getHotel_no()); // 호텔 넘버 가져와야함.
				pstmt.setString(3, rb.getRoom_name());
				pstmt.setInt(4, rb.getRoom_basic_people());
				pstmt.setInt(5, rb.getRoom_max_people());
				pstmt.setString(6, rb.getRoom_info());
				pstmt.setInt(7, rb.getRoom_price());
				
				
				System.out.println("실행완료");
				// sql �떎�뻾			
				pstmt.executeUpdate();
				System.out.println("room_type_table 데이터 저장 완료");
				
				
				// ********************** 여기는 방 번호, 방 옵션 저장
				
				
				
				
				
				
				
				
				/*pstmt.setInt(7, rb.getRoom_price());*/
				
				for(int i=0; i<rb.getRoom_number().length;i++){ // for문을 돌리지를 못함
					
					System.out.println("여기는 실행되나요? 제발");
				int room_no=0;
				
				sql = "select max(room_no) from room_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					room_no = rs.getInt(1) + 1;
				}
				System.out.println("방 번호 (num) = " + room_no);
				
				
				sql="insert into room_table(room_no,room_type_no,room_number) "
						+ "values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, room_no);
				pstmt.setInt(2, num);
				pstmt.setString(3, rb.getRoom_number()[i]);
				pstmt.executeUpdate();
				
				
				// ********************** 여기까지 방 번호, 방 옵션 저장
				
				
				
				
				
				
				
				
				
				// ************************ 여기서부터 방 옵션 저장
				for(int j=0; j<rb.getRoom_option_name().length;j++){
					
				
				int room_option_no=0;
				
				sql = "select max(room_option_no) from room_option_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					room_option_no = rs.getInt(1) + 1;
				}
				
				
				System.out.println("방 옵션 번호 (num) = " + room_option_no);
				
				
				sql ="insert into room_option_table(room_option_no,room_type_no,room_option_name,option_add_price) "
						+ "values(?,?,?,?)";
				
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, room_option_no);
				pstmt.setInt(2, num);
				pstmt.setString(3, rb.getRoom_option_name()[j]);
				pstmt.setInt(4, rb.getRoom_option_price()[j]);
				pstmt.executeUpdate();
				
				// ********************** 방 옵션 저장
				System.out.println("방 옵션 출력 완료");
				}// 옵션 for문 끝
				System.out.println("방 옵션 for문 출력완료");
				}// 방번호 for문 끝
				
				
				
				
				
				
				
				
				
				
				
				
				
					int pic_num = 0 ;
				
					sql = "select max(room_pic_no) from room_pic_table";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();

					if (rs.next()) {
						pic_num = rs.getInt(1) + 1;
					}
					System.out.println("사진번호 (num) = " + pic_num);
					System.out.println("사진번호"+pic_num);
				
					// 여기부분에서 자꾸 오류가 나는데 우짜누
					
					
					sql="insert into room_pic_table(room_pic_no,room_type_no,room_pic) values(?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, pic_num);
					pstmt.setInt(2, num);
					pstmt.setString(3, rb.getRoom_pic1());
					pstmt.executeUpdate();
					System.out.println("room_pic 데이터 저장 완료");
					
					pic_num = 0 ;
					
					sql = "select max(room_pic_no) from room_pic_table";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();

					if (rs.next()) {
						pic_num = rs.getInt(1) + 1;
					}
					System.out.println("사진번호 (num) = " + pic_num);
					System.out.println("사진번호"+pic_num);
				
					sql="insert into room_pic_table(room_pic_no,room_type_no,room_pic) values(?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, pic_num);
					pstmt.setInt(2, num);
					pstmt.setString(3, rb.getRoom_pic1());
					pstmt.executeUpdate();
					System.out.println("room_pic 데이터 저장 완료");
					
					
					 
					
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		
		
		}
		
		
	}



