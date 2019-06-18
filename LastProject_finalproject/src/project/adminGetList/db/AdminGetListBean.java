package project.adminGetList.db;

import java.util.ArrayList;

public class AdminGetListBean {
	
	//호텔 리스트
	private String hotel_no;
	private String hotel_name;
	private String hotel_address;
	private String hotel_class;
	private String hotel_main_image;
	private String hotel_information;
	private String hotel_tel;
	private String approval;
	private String hotel_pic;
	private String hotel_pic_no;

	//편의시설
	private String hotel_option_no;
	private String hotel_option_image;
	private String hotel_option_name;
	private String hotel_option_title;
	
	//객실
	private String room_type_no;
	private String room_no;
	private String room_number;
	private int room_basic_people;
	private int room_max_people;
	private String room_name;
	private String room_info;
	private int room_price;
	private String room_type;
	private String hotel_instroduct;
	
	//객실 사진
	private String room_pic_no;
	private String room_pic;
	
	private String room_option_no;
	private String room_option_name;
	private int option_add_price;
	
	//Count처리
	private int count;
	
	//시설물 리스트 객체
	private ArrayList<AdminGetListBean> AdGetList;

	//호텔 이미지 리스트 객체
	private ArrayList<AdminGetListBean> AdGetImages;
	
	public String getHotel_no() {
		return hotel_no;
	}
	public void setHotel_no(String hotel_no) {
		this.hotel_no = hotel_no;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getHotel_address() {
		return hotel_address;
	}
	public void setHotel_address(String hotel_address) {
		this.hotel_address = hotel_address;
	}
	public String getHotel_class() {
		return hotel_class;
	}
	public void setHotel_class(String hotel_class) {
		this.hotel_class = hotel_class;
	}
	public String getHotel_main_image() {
		return hotel_main_image;
	}
	public void setHotel_main_image(String hotel_main_image) {
		this.hotel_main_image = hotel_main_image;
	}
	public String getHotel_information() {
		return hotel_information;
	}
	public void setHotel_information(String hotel_information) {
		this.hotel_information = hotel_information;
	}
	public String getHotel_tel() {
		return hotel_tel;
	}
	public void setHotel_tel(String hotel_tel) {
		this.hotel_tel = hotel_tel;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getHotel_option_no() {
		return hotel_option_no;
	}
	public void setHotel_option_no(String hotel_option_no) {
		this.hotel_option_no = hotel_option_no;
	}
	public String getHotel_option_image() {
		return hotel_option_image;
	}
	public void setHotel_option_image(String hotel_option_image) {
		this.hotel_option_image = hotel_option_image;
	}
	public String getHotel_option_name() {
		return hotel_option_name;
	}
	public void setHotel_option_name(String hotel_option_name) {
		this.hotel_option_name = hotel_option_name;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getRoom_number() {
		return room_number;
	}
	public void setRoom_number(String room_number) {
		this.room_number = room_number;
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
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getRoom_option_no() {
		return room_option_no;
	}
	public void setRoom_option_no(String room_option_no) {
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
	public ArrayList<AdminGetListBean> getAdGetList() {
		return AdGetList;
	}
	public void setAdGetList(ArrayList<AdminGetListBean> adGetList) {
		AdGetList = adGetList;
	}
	public ArrayList<AdminGetListBean> getAdGetImages() {
		return AdGetImages;
	}
	public void setAdGetImages(ArrayList<AdminGetListBean> adGetImages) {
		AdGetImages = adGetImages;
	}
	public String getHotel_pic() {
		return hotel_pic;
	}
	public void setHotel_pic(String hotel_pic) {
		this.hotel_pic = hotel_pic;
	}
	public String getHotel_pic_no() {
		return hotel_pic_no;
	}
	public void setHotel_pic_no(String hotel_pic_no) {
		this.hotel_pic_no = hotel_pic_no;
	}
	public String getRoom_type_no() {
		return room_type_no;
	}
	public void setRoom_type_no(String room_type_no) {
		this.room_type_no = room_type_no;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getRoom_pic_no() {
		return room_pic_no;
	}
	public void setRoom_pic_no(String room_pic_no) {
		this.room_pic_no = room_pic_no;
	}
	public String getRoom_pic() {
		return room_pic;
	}
	public void setRoom_pic(String room_pic) {
		this.room_pic = room_pic;
	}
	public String getHotel_instroduct() {
		return hotel_instroduct;
	}
	public void setHotel_instroduct(String hotel_instroduct) {
		this.hotel_instroduct = hotel_instroduct;
	}
	public String getHotel_option_title() {
		return hotel_option_title;
	}
	public void setHotel_option_title(String hotel_option_title) {
		this.hotel_option_title = hotel_option_title;
	}
}
