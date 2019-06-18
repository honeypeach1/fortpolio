package net.hotel.db;

public class HotelBean {

	private int hotel_no;
	private String hotel_name;
	private String hotel_address;
	private String hotel_free_service;
	private int hotel_class;
	private String hotel_instroduct;
	
	
	public String getHotel_instroduct() {
		return hotel_instroduct;
	}
	public void setHotel_instroduct(String hotel_instroduct) {
		this.hotel_instroduct = hotel_instroduct;
	}
	public int getHotel_no() {
		return hotel_no;
	}
	public void setHotel_no(int hotel_no) {
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
	public String getHotel_free_service() {
		return hotel_free_service;
	}
	public void setHotel_free_service(String hotel_free_service) {
		this.hotel_free_service = hotel_free_service;
	}
	public int getHotel_class() {
		return hotel_class;
	}
	public void setHotel_class(int hotel_class) {
		this.hotel_class = hotel_class;
	}


}
