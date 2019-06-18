package net.roomdetails.db;

public class RoomTypeBean {
	
	// room_table : 객실 테이블
	private int room_type_no;
	private int hotel_no;
	private String room_name;
	private int room_basic_people;
	private int room_max_people;
	private String room_info;
	private int room_price;
	private String room_price_str; // int형인 room_price를 String형태로(000,000) 가져오기 위한 변수
	
	// room_option_table : 객실 선택 옵션 테이블
	private int room_option_no;
	// private int room_type_no; // 위에 선언되어있음
	private String room_option_name;
	private int option_add_price;
	
	// room_pic_table : 객실 이미지 테이블
	private int room_pic_no;
	// private int room_type_no; // 위에 선언되어있음
	private String room_pic;
	
	// 빈 방
	private int left_room;
	
	
	
	

	public int getLeft_room() {
		return left_room;
	}
	public void setLeft_room(int left_room) {
		this.left_room = left_room;
	}
	public int getRoom_pic_no() {
		return room_pic_no;
	}
	public void setRoom_pic_no(int room_pic_no) {
		this.room_pic_no = room_pic_no;
	}
	public String getRoom_pic() {
		return room_pic;
	}
	public void setRoom_pic(String room_pic) {
		this.room_pic = room_pic;
	}
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
	public int getRoom_type_no() {
		return room_type_no;
	}
	public void setRoom_type_no(int room_type_no) {
		this.room_type_no = room_type_no;
	}
	public int getHotel_no() {
		return hotel_no;
	}
	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getRoom_basic_people() {
		return room_basic_people;
	}
	public void setRoom_basic_people(int room_basic_people) {
		this.room_basic_people = room_basic_people;
	}
	public int getRoom_max_people() {
		return room_max_people;
	}
	public void setRoom_max_people(int room_max_people) {
		this.room_max_people = room_max_people;
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
	

	
	
	
	
}
