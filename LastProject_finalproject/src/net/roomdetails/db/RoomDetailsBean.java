package net.roomdetails.db;

import java.sql.Date;

public class RoomDetailsBean {
	
	// room_table : 객실 테이블
	private int room_no;
	private int hotel_no;
	private int room_info_no;
	private int room_number;
	private int room_max_people;
	private String room_name;
	private String room_info;
	private int room_price;
	private String room_price_str; // int형인 room_price를 String형태로(000,000) 가져오기 위한 변수
	
	// room_option_table : 객실 선택 옵션 테이블
	private int room_option_no;
	//private int room_no;
	private String room_option_name;
	private int option_add_price;
	
	// review_table : 리뷰 테이블
	private int review_no;
	private int booking_no;
	// private int hotel_no;
	private int user_no; 
	private int review_star;
	private String review_content;
	private Date review_input_date;
	
	// booking_table : 예약 테이블
	
	// user_table : 유저 테이블
	private String user_id;
	
	private String user_image;
	
// --------------------------------------------------------
	
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public int getHotel_no() {
		return hotel_no;
	}
	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}
	public int getRoom_info_no() {
		return room_info_no;
	}
	public void setRoom_info_no(int room_info_no) {
		this.room_info_no = room_info_no;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public int getRoom_max_people() {
		return room_max_people;
	}
	public void setRoom_max_people(int room_max_people) {
		this.room_max_people = room_max_people;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_info() {
		return room_info;
	}
	public void setRoom_info(String room_info) {
		this.room_info = room_info;
	}
	public int getRoom_price() {
		return room_price;
	}
	public void setRoom_price(int room_price) {
		this.room_price = room_price;
	}
	public String getRoom_price_str() {
		return room_price_str;
	}
	public void setRoom_price_str(String room_price_str) {
		this.room_price_str = room_price_str;
	}

	
// --------------------------------------------------------
	
	public int getRoom_option_no() {
		return room_option_no;
	}
	public void setRoom_option_no(int room_option_no) {
		this.room_option_no = room_option_no;
	}
	public String getRoom_option_name() {
		return room_option_name;
	}
	public void setRoom_option_name(String room_option_name) {
		this.room_option_name = room_option_name;
	}
	public int getOption_add_price() {
		return option_add_price;
	}
	public void setOption_add_price(int option_add_price) {
		this.option_add_price = option_add_price;
	}
	
// --------------------------------------------------------
	
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getReview_star() {
		return review_star;
	}
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Date getReview_input_date() {
		return review_input_date;
	}
	public void setReview_input_date(Date review_input_date) {
		this.review_input_date = review_input_date;
	}
	
// --------------------------------------------------------
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	
// --------------------------------------------------------
		
	
	
	
	
}
