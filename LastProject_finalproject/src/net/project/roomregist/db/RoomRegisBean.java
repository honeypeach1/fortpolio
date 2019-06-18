package net.project.roomregist.db;

public class RoomRegisBean {
	private int hotel_no;
	private String room_name;
	private int room_basic_people;
	private int room_max_people;
	private String room_info;
	private int room_price;
	private String room_pic1;
	private String room_pic2;
	private String[] room_option_name;
	private int[] room_option_price;
	private String[] room_number;
	
	
	
	
	
	
	public String[] getRoom_number() {
		return room_number;
	}
	public void setRoom_number(String[] room_number) {
		this.room_number = room_number;
	}
	public String[] getRoom_option_name() {
		return room_option_name;
	}
	public void setRoom_option_name(String[] room_option_name) {
		this.room_option_name = room_option_name;
	}
	public int[] getRoom_option_price() {
		return room_option_price;
	}
	public void setRoom_option_price(int[] room_option_price) {
		this.room_option_price = room_option_price;
	}
	public int getHotel_no() {
		return hotel_no;
	}
	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
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
	public String getRoom_pic1() {
		return room_pic1;
	}
	public void setRoom_pic1(String room_pic1) {
		this.room_pic1 = room_pic1;
	}
	public String getRoom_pic2() {
		return room_pic2;
	}
	public void setRoom_pic2(String roon_pic2) {
		this.room_pic2 = roon_pic2;
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
	
	
}
