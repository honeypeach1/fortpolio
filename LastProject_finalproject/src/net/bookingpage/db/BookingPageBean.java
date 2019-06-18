package net.bookingpage.db;

import java.sql.Date;

public class BookingPageBean {
	
	// getUserInfo에 필요한 변수
	private String user_name;
	private String user_fstName; // 이름
	private String user_lstName; // 성
	private String user_email;
	private String user_phone;
	private int user_no;
	
	// 호텔 정보
	private String hotel_name; // 호텔 이름
	private String hotel_main_image; // 호텔 메인 이미지

	private int room_type_no; // 룸 타입 넘버
	private String room_name; // 룸 타입 이름	
	private int room_price; // 룸 가격
	
	
	private Date check_in;
	private Date check_out;
	private int day; // 총 몇 박 묵을지
	
	
	// 객실 옵션 정보
	private int room_option_no;
	private String room_option_name;
	private int option_add_price;
	
	
//------------------------------------------------
	
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
	public int getRoom_price() {
		return room_price;
	}
	public void setRoom_price(int room_price) {
		this.room_price = room_price;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public Date getCheck_in() {
		return check_in;
	}
	public void setCheck_in(Date check_in) {
		this.check_in = check_in;
	}
	public Date getCheck_out() {
		return check_out;
	}
	public void setCheck_out(Date check_out) {
		this.check_out = check_out;
	}
	public String getHotel_main_image() {
		return hotel_main_image;
	}
	public void setHotel_main_image(String hotel_main_image) {
		this.hotel_main_image = hotel_main_image;
	}
	
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public int getRoom_type_no() {
		return room_type_no;
	}
	public void setRoom_type_no(int room_type_no) {
		this.room_type_no = room_type_no;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_fstName() {
		return user_fstName;
	}
	public void setUser_fstName(String user_fstName) {
		this.user_fstName = user_fstName;
	}
	public String getUser_lstName() {
		return user_lstName;
	}
	public void setUser_lstName(String user_lstName) {
		this.user_lstName = user_lstName;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public int getRoom_option_no() {
		return room_option_no;
	}
	public void setRoom_option_no(int room_option_no) {
		this.room_option_no = room_option_no;
	}

}
