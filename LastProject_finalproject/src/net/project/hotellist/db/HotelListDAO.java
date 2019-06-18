package net.project.hotellist.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HotelListDAO {
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
	
	/*// ȣ�� �⺻���� ��������(�ּұݾױ���)
	public List getHotelInfoList(){
		List hotelNoList = new ArrayList();
		List hotelInfoList = new ArrayList();
		try {
			con = getConnection();
			
			sql = "SELECT hotel_no FROM hotel_table"; // ���߿� ȣ�� �ּҳ� �̸��� where�� �߰�
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				hotelNoList.add(rs.getInt("hotel_no"));
			}
			
			for(int i=0;i<hotelNoList.size();i++){
				sql = "SELECT ht.hotel_no, ht.hotel_name, ht.hotel_class, ht.hotel_main_image, MIN(rt.room_price) room_price "
					+ "FROM hotel_table ht JOIN room_table rt ON (ht.hotel_no = rt.hotel_no) "
					+ "WHERE ht.hotel_no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (int)hotelNoList.get(i));
				rs = pstmt.executeQuery();
			
				if(rs.next()){
					HotelListBean hlb = new HotelListBean();
					
					hlb.setHotel_num(rs.getInt("hotel_no"));
					hlb.setHotel_name(rs.getString("hotel_name"));
					hlb.setHotel_class(rs.getInt("hotel_class"));
					hlb.setHotel_main_image(rs.getString("hotel_main_image"));
					hlb.setMinPrice(rs.getInt("room_price"));
					hotelInfoList.add(hlb);
				}
			}
			
			System.out.println("ȣ�� ���� ���� �Ϸ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return hotelInfoList;
	}
	
	
	// ȣ���� ���ǽü� ����
	public List getHotelFacList(){
		List hotelNoList = new ArrayList();
		List hotelFacList = new ArrayList();
		
		try {
			con = getConnection();
			
			sql = "SELECT hotel_no FROM hotel_table"; // ���߿� ȣ�� �ּҳ� �̸��� where�� �߰�
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				hotelNoList.add(rs.getInt("hotel_no"));
			}
			for(int i=0;i<hotelNoList.size();i++){
				sql = "SELECT hoct.hotel_no, hoct.hotel_option_no, hot.hotel_option_name, hot.hotel_option_title "
					+ "FROM hotel_option_con_table hoct JOIN hotel_option_table hot ON (hoct.hotel_option_no = hot.hotel_option_no) "
					+ "WHERE hoct.hotel_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (int)hotelNoList.get(i));
				rs = pstmt.executeQuery();
				while(rs.next()){
					HotelListBean hlb = new HotelListBean();
					hlb.setHotel_num(rs.getInt("hotel_no"));
					hlb.setHotel_option_no(rs.getInt("hotel_option_no"));
					hlb.setHotel_option_name(rs.getString("hotel_option_name"));
					hlb.setHotel_option_title(rs.getString("hotel_option_title"));
				
					hotelFacList.add(hlb);
				}
			}
			System.out.println("ȣ�� ���ǽü� ���� �Ϸ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return hotelFacList;
	}
	*/
	//////////////////////////////////////////////////////////////////////////////
	// �˻��Ҷ��� �޼ҵ�
	
	// getHotelInfoList(input_text)
	public List getHotelInfoList(String input_text, int people_num){
		List hotelNoList = new ArrayList();
		List hotelInfoList = new ArrayList();
		
		try {
			con = getConnection();
			
			sql = "SELECT ht.hotel_no "
					+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
					+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
					+ "AND ht.approval = 1 "
					+ "GROUP BY ht.hotel_no "
					+ "HAVING MIN(rt.room_max_people) >= ? "; // ȣ�� �ּҳ� �̸��� where�� �߰�
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+input_text+"%");
			pstmt.setString(2, "%"+input_text+"%");
			pstmt.setInt(3, people_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				hotelNoList.add(rs.getInt("hotel_no"));
			}
			
			for(int i=0;i<hotelNoList.size();i++){
				sql = "SELECT ht.hotel_no, ht.hotel_name, ht.hotel_class, ht.hotel_main_image, ht.hotel_address, MIN(rt.room_price) room_price, ROUND(AVG(rvt.review_star),1) review_star "
					+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
					+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
					+ "WHERE ht.hotel_no = ? "
					+ "group by ht.hotel_no, ht.hotel_name, ht.hotel_class, ht.hotel_main_image, ht.hotel_address";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (int)hotelNoList.get(i));
				System.out.println(hotelNoList.get(i));
				rs = pstmt.executeQuery();
			
				if(rs.next()){
					HotelListBean hlb = new HotelListBean();
					
					hlb.setHotel_num(rs.getInt("hotel_no"));
					hlb.setHotel_name(rs.getString("hotel_name"));
					hlb.setHotel_class(rs.getInt("hotel_class"));
					hlb.setHotel_main_image(rs.getString("hotel_main_image"));
					hlb.setHotel_address(rs.getString("hotel_address"));
					hlb.setMinPrice(rs.getInt("room_price"));
					hlb.setReview_star(rs.getDouble("review_star"));
					hotelInfoList.add(hlb);
				}
			}
			
			System.out.println("ȣ�� ���� ���� �Ϸ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return hotelInfoList;
	}
	// getHotelInfoList(input_text)
	
	
	// getHotelFacList(input_text)
	public List getHotelFacList(String input_text, int people_num){
		List hotelNoList = new ArrayList();
		List hotelFacList = new ArrayList();
		
		try {
			con = getConnection();
			
			sql = "SELECT ht.hotel_no "
					+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
					+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
					+ "AND ht.approval = 1 "
					+ "GROUP BY ht.hotel_no "
					+ "HAVING MIN(rt.room_max_people) >= ? "; // ���߿� ȣ�� �ּҳ� �̸��� where�� �߰�
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+input_text+"%");
			pstmt.setString(2, "%"+input_text+"%");
			pstmt.setInt(3, people_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				hotelNoList.add(rs.getInt("hotel_no"));
			}
			for(int i=0;i<hotelNoList.size();i++){
				sql = "SELECT hoct.hotel_no, hoct.hotel_option_no, hot.hotel_option_name, hot.hotel_option_title "
					+ "FROM hotel_option_con_table hoct JOIN hotel_option_table hot ON (hoct.hotel_option_no = hot.hotel_option_no) "
					+ "WHERE hoct.hotel_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (int)hotelNoList.get(i));
				rs = pstmt.executeQuery();
				while(rs.next()){
					HotelListBean hlb = new HotelListBean();
					hlb.setHotel_num(rs.getInt("hotel_no"));
					hlb.setHotel_option_no(rs.getInt("hotel_option_no"));
					hlb.setHotel_option_name(rs.getString("hotel_option_name"));
					hlb.setHotel_option_title(rs.getString("hotel_option_title"));
				
					hotelFacList.add(hlb);
				}
			}
			System.out.println("ȣ�� ���ǽü� ���� �Ϸ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return hotelFacList;
	}
	// getHotelFacList(input_text)
	
	
	// getAjaxHotelInfoList(input_text, min, max, star)
	public JSONArray getAjaxHotelInfoList(String input_text, int min, int max, String star, String review_score, String fac_checkNum, int people_num) { // ajax ���� JSON���� �ѱ��
		List hotelNoList = new ArrayList();
		JSONArray hotelInfoList = new JSONArray();
		
		try {
			con = getConnection();
			if(fac_checkNum == ""){ // ���ǽü�üũ ��������
				if(review_score == ""){ // ������ ������
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setInt(4, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setInt(4, max);
							pstmt.setInt(5, people_num);
						}
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setInt(5, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setInt(6, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
						sql = "SELECT ht.hotel_no "
								+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
								+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
								+ "AND ht.approval = 1 "
								+ "AND ht.hotel_class BETWEEN ? AND ? "
								+ "GROUP BY ht.hotel_no "
								+ "HAVING MIN(rt.room_price) >= ? "
								+ "AND MIN(rt.room_max_people) >= ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+input_text+"%");
						pstmt.setString(2, "%"+input_text+"%");
						pstmt.setInt(3, strMin);
						pstmt.setInt(4, strMax);
						pstmt.setInt(5, min);
						pstmt.setInt(6, people_num);
						}else {
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setInt(7, people_num);
						}
					}	
				}else { // ������ 1�� �̻� �϶�
					double[] review = new double[review_score.length()];
					double reviewMin=5;
					
					for(int i=0;i<review_score.length();i++){
						if(Double.parseDouble(review_score.charAt(i)+"") == 1)
							review[i] = 4.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 2)
							review[i] = 4.0;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 3)
							review[i] = 3.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 4)
							review[i] = 3.0;
					}
					for(int i=0;i<review_score.length();i++){
						if(review[i]<reviewMin){
							reviewMin = review[i];
						}
					}
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setDouble(4, reviewMin);
							pstmt.setInt(5, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setInt(4, max);
							pstmt.setDouble(5, reviewMin);
							pstmt.setInt(6, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							System.out.println("DAO������ "+Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setDouble(5, reviewMin);
							pstmt.setInt(6, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, min);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setDouble(7, reviewMin);
							pstmt.setInt(8, people_num);
						}
						
					}	
				}
			}else if(fac_checkNum.length() == 1){ // ���ǽü� üũ �ϳ��� ������
				int fac_num = Integer.parseInt(fac_checkNum);
				if(review_score == ""){ // ������ ������
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setInt(5, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setInt(6, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setInt(6, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setInt(7, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, fac_num);
							pstmt.setInt(6, min);
							pstmt.setInt(7, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, fac_num);
							pstmt.setInt(6, min);
							pstmt.setInt(7, max);
							pstmt.setInt(8, people_num);
						}
						
					}	
				}else { // ������ 1�� �̻� �϶�
					double[] review = new double[review_score.length()];
					double reviewMin=5;
					
					for(int i=0;i<review_score.length();i++){
						if(Double.parseDouble(review_score.charAt(i)+"") == 1)
							review[i] = 4.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 2)
							review[i] = 4.0;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 3)
							review[i] = 3.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 4)
							review[i] = 3.0;
					}
					for(int i=0;i<review_score.length();i++){
						if(review[i]<reviewMin){
							reviewMin = review[i];
						}
					}
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setDouble(5, reviewMin);
							pstmt.setInt(6, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setDouble(7, reviewMin);
							pstmt.setInt(8, people_num);
						}
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, fac_num);
							pstmt.setInt(6, min);
							pstmt.setDouble(7, reviewMin);
							pstmt.setInt(8, people_num);
						}else{
						
						sql = "SELECT ht.hotel_no "
								+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
								+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
								+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
								+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
								+ "AND ht.approval = 1 "
								+ "AND ht.hotel_class BETWEEN ? AND ? "
								+ "AND hoct.hotel_option_no=? "
								+ "GROUP BY ht.hotel_no "
								+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
								+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
								+ "AND MIN(rt.room_max_people) >= ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+input_text+"%");
						pstmt.setString(2, "%"+input_text+"%");
						pstmt.setInt(3, strMin);
						pstmt.setInt(4, strMax);
						pstmt.setInt(5, fac_num);
						pstmt.setInt(6, min);
						pstmt.setInt(7, max);
						pstmt.setDouble(8, reviewMin);
						pstmt.setInt(9, people_num);
						}
					}	
				}
			}else if(fac_checkNum.length() >=2 ){ // ���� �ü� üũ ������ ������
				int[] facList = new int[fac_checkNum.length()];
				String facsqlJoin= "";// join�� �߰���ų ����
				String facsqlAnd = "";//where���� �߰���ų ����
				int count=0;
				for(int i=1;i<fac_checkNum.length();i++){
					facsqlJoin +="JOIN hotel_option_con_table hoct"+i+" ON (hoct0.hotel_no = hoct"+i+".hotel_no) ";
					System.out.println(facsqlJoin);
				}
				for(int i=0;i<fac_checkNum.length();i++){
					facList[i] = Integer.parseInt(fac_checkNum.charAt(i)+"");
					System.out.println(facList[i]);
					facsqlAnd += "AND hoct"+i+".hotel_option_no = ? ";
					System.out.println(facsqlAnd);
					count++;
					System.out.println(count);
				}
				if(review_score == ""){ // ������ ������
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
								System.out.println(facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setInt(4+count, people_num);
							
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setInt(4+count, max);
							pstmt.setInt(5+count, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setInt(5+count, people_num);
							
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setInt(5+count, max);
							pstmt.setInt(6+count, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setInt(6+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setInt(6+count, max);
							pstmt.setInt(7+count, people_num);
						}
						
					}	
				}else { // ������ 1�� �̻� �϶�
					double[] review = new double[review_score.length()];
					double reviewMin=5;
					
					for(int i=0;i<review_score.length();i++){
						if(Double.parseDouble(review_score.charAt(i)+"") == 1)
							review[i] = 4.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 2)
							review[i] = 4.0;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 3)
							review[i] = 3.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 4)
							review[i] = 3.0;
					}
					for(int i=0;i<review_score.length();i++){
						if(review[i]<reviewMin){
							reviewMin = review[i];
						}
					}
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setDouble(4+count, reviewMin);
							pstmt.setInt(5+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setInt(4+count, max);
							pstmt.setDouble(5+count, reviewMin);
							pstmt.setInt(6+count, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setDouble(5+count, reviewMin);
							pstmt.setInt(6+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setInt(5+count, max);
							pstmt.setDouble(6+count, reviewMin);
							pstmt.setInt(7+count, people_num);
						}
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setDouble(6+count, reviewMin);
							pstmt.setInt(7+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setInt(6+count, max);
							pstmt.setDouble(7+count, reviewMin);
							pstmt.setInt(8+count, people_num);
						}
					}	
				}
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				hotelNoList.add(rs.getInt("hotel_no"));
			}	
			
			
			for(int i=0;i<hotelNoList.size();i++){
				sql = "SELECT ht.hotel_no, ht.hotel_name, ht.hotel_class, ht.hotel_main_image, MIN(rt.room_price) room_price, ROUND(AVG(rvt.review_star),1) review_star "
					+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
					+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
					+ "WHERE ht.hotel_no = ? "
					+ "group by ht.hotel_no, ht.hotel_name, ht.hotel_class, ht.hotel_main_image";
						/*+ "AND WHERE ht.hotel_no IN (SELECT hotel_no FROM hotel_table WHERE hotel_name LIKE ? OR hotel_address LIKE ?) "
						+ "group by ht.hotel_no, ht.hotel_name, ht.hotel_class, ht.hotel_main_image";*/
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, (int)hotelNoList.get(i));
				rs = pstmt.executeQuery();
				if(rs.next()){
					JSONObject obj = new JSONObject();
					obj.put("hotel_no", rs.getInt("hotel_no"));
					obj.put("hotel_name", rs.getString("hotel_name"));
					obj.put("hotel_class", rs.getInt("hotel_class"));
					obj.put("hotel_main_image", rs.getString("hotel_main_image"));
					obj.put("room_price", rs.getInt("room_price"));
					obj.put("review_star", rs.getDouble("review_star"));
					hotelInfoList.add(obj);
				}
				
			}
			System.out.println("�ش� ���� ����� ȣ�� ���� ���� �Ϸ�");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return hotelInfoList;
	}
	// getAjaxHotelInfoList(String input_text, String min, String max)
	
	// getAjaxHotelFacList(input_text,min,max, star)
	public JSONArray getAjaxHotelFacList(String input_text, int min, int max, String star, String review_score, String fac_checkNum, int people_num){
		List hotelNoList = new ArrayList();
		JSONArray hotelFacList = new JSONArray();
		
		try {
			con = getConnection();
			if(fac_checkNum == ""){ // ���ǽü�üũ ��������
				if(review_score == ""){ // ������ ������
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setInt(4, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setInt(4, max);
							pstmt.setInt(5, people_num);
						}
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setInt(5, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setInt(6, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
						sql = "SELECT ht.hotel_no "
								+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
								+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
								+ "AND ht.approval = 1 "
								+ "AND ht.hotel_class BETWEEN ? AND ? "
								+ "GROUP BY ht.hotel_no "
								+ "HAVING MIN(rt.room_price) >= ? "
								+ "AND MIN(rt.room_max_people) >= ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+input_text+"%");
						pstmt.setString(2, "%"+input_text+"%");
						pstmt.setInt(3, strMin);
						pstmt.setInt(4, strMax);
						pstmt.setInt(5, min);
						pstmt.setInt(6, people_num);
						}else {
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setInt(7, people_num);
						}
					}	
				}else { // ������ 1�� �̻� �϶�
					double[] review = new double[review_score.length()];
					double reviewMin=5;
					
					for(int i=0;i<review_score.length();i++){
						if(Double.parseDouble(review_score.charAt(i)+"") == 1)
							review[i] = 4.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 2)
							review[i] = 4.0;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 3)
							review[i] = 3.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 4)
							review[i] = 3.0;
					}
					for(int i=0;i<review_score.length();i++){
						if(review[i]<reviewMin){
							reviewMin = review[i];
						}
					}
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setDouble(4, reviewMin);
							pstmt.setInt(5, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, min);
							pstmt.setInt(4, max);
							pstmt.setDouble(5, reviewMin);
							pstmt.setInt(6, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							System.out.println("DAO������ "+Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setDouble(5, reviewMin);
							pstmt.setInt(6, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, min);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setDouble(7, reviewMin);
							pstmt.setInt(8, people_num);
						}
						
					}	
				}
			}else if(fac_checkNum.length() == 1){ // ���ǽü� üũ �ϳ��� ������
				int fac_num = Integer.parseInt(fac_checkNum);
				if(review_score == ""){ // ������ ������
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setInt(5, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setInt(6, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setInt(6, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setInt(7, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, fac_num);
							pstmt.setInt(6, min);
							pstmt.setInt(7, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, fac_num);
							pstmt.setInt(6, min);
							pstmt.setInt(7, max);
							pstmt.setInt(8, people_num);
						}
						
					}	
				}else { // ������ 1�� �̻� �϶�
					double[] review = new double[review_score.length()];
					double reviewMin=5;
					
					for(int i=0;i<review_score.length();i++){
						if(Double.parseDouble(review_score.charAt(i)+"") == 1)
							review[i] = 4.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 2)
							review[i] = 4.0;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 3)
							review[i] = 3.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 4)
							review[i] = 3.0;
					}
					for(int i=0;i<review_score.length();i++){
						if(review[i]<reviewMin){
							reviewMin = review[i];
						}
					}
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setDouble(5, reviewMin);
							pstmt.setInt(6, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, fac_num);
							pstmt.setInt(4, min);
							pstmt.setInt(5, max);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setDouble(6, reviewMin);
							pstmt.setInt(7, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							pstmt.setInt(4, fac_num);
							pstmt.setInt(5, min);
							pstmt.setInt(6, max);
							pstmt.setDouble(7, reviewMin);
							pstmt.setInt(8, people_num);
						}
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ "AND hoct.hotel_option_no=? "
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							pstmt.setInt(5, fac_num);
							pstmt.setInt(6, min);
							pstmt.setDouble(7, reviewMin);
							pstmt.setInt(8, people_num);
						}else{
						
						sql = "SELECT ht.hotel_no "
								+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
								+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
								+ "JOIN hotel_option_con_table hoct ON (rvt.hotel_no = hoct.hotel_no) "
								+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
								+ "AND ht.approval = 1 "
								+ "AND ht.hotel_class BETWEEN ? AND ? "
								+ "AND hoct.hotel_option_no=? "
								+ "GROUP BY ht.hotel_no "
								+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
								+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
								+ "AND MIN(rt.room_max_people) >= ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+input_text+"%");
						pstmt.setString(2, "%"+input_text+"%");
						pstmt.setInt(3, strMin);
						pstmt.setInt(4, strMax);
						pstmt.setInt(5, fac_num);
						pstmt.setInt(6, min);
						pstmt.setInt(7, max);
						pstmt.setDouble(8, reviewMin);
						pstmt.setInt(9, people_num);
						}
					}	
				}
			}else if(fac_checkNum.length() >=2 ){ // ���� �ü� üũ ������ ������
				int[] facList = new int[fac_checkNum.length()];
				String facsqlJoin= "";// join�� �߰���ų ����
				String facsqlAnd = "";//where���� �߰���ų ����
				int count=0;
				for(int i=1;i<fac_checkNum.length();i++){
					facsqlJoin +="JOIN hotel_option_con_table hoct"+i+" ON (hoct0.hotel_no = hoct"+i+".hotel_no) ";
					System.out.println(facsqlJoin);
				}
				for(int i=0;i<fac_checkNum.length();i++){
					facList[i] = Integer.parseInt(fac_checkNum.charAt(i)+"");
					System.out.println(facList[i]);
					facsqlAnd += "AND hoct"+i+".hotel_option_no = ? ";
					System.out.println(facsqlAnd);
					count++;
					System.out.println(count);
				}
				if(review_score == ""){ // ������ ������
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
								System.out.println(facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setInt(4+count, people_num);
							
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setInt(4+count, max);
							pstmt.setInt(5+count, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setInt(5+count, people_num);
							
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setInt(5+count, max);
							pstmt.setInt(6+count, people_num);
						}
						
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setInt(6+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setInt(6+count, max);
							pstmt.setInt(7+count, people_num);
						}
						
					}	
				}else { // ������ 1�� �̻� �϶�
					double[] review = new double[review_score.length()];
					double reviewMin=5;
					
					for(int i=0;i<review_score.length();i++){
						if(Double.parseDouble(review_score.charAt(i)+"") == 1)
							review[i] = 4.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 2)
							review[i] = 4.0;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 3)
							review[i] = 3.5;
						else if(Double.parseDouble(review_score.charAt(i)+"") == 4)
							review[i] = 3.0;
					}
					for(int i=0;i<review_score.length();i++){
						if(review[i]<reviewMin){
							reviewMin = review[i];
						}
					}
					if(star == ""){//��� üũ ��������
						if(max == 300000){// ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setDouble(4+count, reviewMin);
							pstmt.setInt(5+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							for(int i=0;i<count;i++){
								pstmt.setInt(3+i, facList[i]);
							}
							pstmt.setInt(3+count, min);
							pstmt.setInt(4+count, max);
							pstmt.setDouble(5+count, reviewMin);
							pstmt.setInt(6+count, people_num);
						}
						
					}else if(star.length()==1){ // ��� �ϳ��� ���ý�
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setDouble(5+count, reviewMin);
							pstmt.setInt(6+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class = ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, Integer.parseInt(star));
							for(int i=0;i<count;i++){
								pstmt.setInt(4+i, facList[i]);
							}
							pstmt.setInt(4+count, min);
							pstmt.setInt(5+count, max);
							pstmt.setDouble(6+count, reviewMin);
							pstmt.setInt(7+count, people_num);
						}
					}else { // ��� ������ ���ý�
						int[] str = new int[star.length()];
						int strMin=6;
						int strMax=0;
						
						for(int i=0;i<star.length();i++){
							str[i] = Integer.parseInt(star.charAt(i)+"");
						}
						for(int i=0;i<star.length();i++){
							if(str[i] > strMax){
								strMax = str[i];
							}else if(str[i]<strMin){
								strMin = str[i];
							}
						}
						if(max == 300000){ // ���� 30���� �̻��϶�
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) >= ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setDouble(6+count, reviewMin);
							pstmt.setInt(7+count, people_num);
						}else{
							sql = "SELECT ht.hotel_no "
									+ "FROM hotel_table ht JOIN room_type_table rt ON (ht.hotel_no = rt.hotel_no) "
									+ "JOIN review_table rvt ON (rt.hotel_no = rvt.hotel_no) "
									+ "JOIN hotel_option_con_table hoct0 ON (rvt.hotel_no = hoct0.hotel_no) "
									+ facsqlJoin
									+ "WHERE (hotel_name LIKE ? OR hotel_address LIKE ?) "
									+ "AND ht.approval = 1 "
									+ "AND ht.hotel_class BETWEEN ? AND ? "
									+ facsqlAnd
									+ "GROUP BY ht.hotel_no "
									+ "HAVING MIN(rt.room_price) BETWEEN ? AND ? "
									+ "AND ROUND(AVG(rvt.review_star),1) >= ? "
									+ "AND MIN(rt.room_max_people) >= ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+input_text+"%");
							pstmt.setString(2, "%"+input_text+"%");
							pstmt.setInt(3, strMin);
							pstmt.setInt(4, strMax);
							for(int i=0;i<count;i++){
								pstmt.setInt(5+i, facList[i]);
							}
							pstmt.setInt(5+count, min);
							pstmt.setInt(6+count, max);
							pstmt.setDouble(7+count, reviewMin);
							pstmt.setInt(8+count, people_num);
						}
					}	
				}
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				hotelNoList.add(rs.getInt("hotel_no"));
			}	
			
			for(int i=0;i<hotelNoList.size();i++){
				sql = "SELECT hoct.hotel_no, hoct.hotel_option_no, hot.hotel_option_name, hot.hotel_option_title "
						+ "FROM hotel_option_con_table hoct JOIN hotel_option_table hot ON (hoct.hotel_option_no = hot.hotel_option_no) "
						+ "WHERE hoct.hotel_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (int)hotelNoList.get(i));
				rs = pstmt.executeQuery();
				while(rs.next()){
					JSONObject obj = new JSONObject();
					obj.put("hotel_no", rs.getInt("hotel_no"));
					obj.put("hotel_option_no", rs.getInt("hotel_option_no"));
					obj.put("hotel_option_name", rs.getString("hotel_option_name"));
					obj.put("hotel_option_title", rs.getString("hotel_option_title"));
					hotelFacList.add(obj);
				}
			}
			System.out.println("����� ��� ȣ�� ���ǽü� ���� �Ϸ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return hotelFacList;
	}
	// getAjaxHotelFacList(input_text,min,max)

}
