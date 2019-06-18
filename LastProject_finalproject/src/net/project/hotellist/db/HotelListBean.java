package net.project.hotellist.db;

public class HotelListBean {
	private int hotel_num;
	private String hotel_name;
	private int hotel_class;
	private String hotel_main_image;
	private String hotel_address;
	private int minPrice;
	
	private int hotel_option_no;
	private String hotel_option_name;
	private String hotel_option_title;
	private double review_star;

	public int getHotel_num() {
		return hotel_num;
	}
	public void setHotel_num(int hotel_num) {
		this.hotel_num = hotel_num;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public int getHotel_class() {
		return hotel_class;
	}
	public void setHotel_class(int hotel_class) {
		this.hotel_class = hotel_class;
	}
	public String getHotel_main_image() {
		return hotel_main_image;
	}
	public void setHotel_main_image(String hotel_main_image) {
		this.hotel_main_image = hotel_main_image;
	}
	
	public String getHotel_address() {
		return hotel_address;
	}
	public void setHotel_address(String hotel_address) {
		this.hotel_address = hotel_address;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getHotel_option_no() {
		return hotel_option_no;
	}
	public void setHotel_option_no(int hotel_option_no) {
		this.hotel_option_no = hotel_option_no;
	}
	public String getHotel_option_name() {
		return hotel_option_name;
	}
	public void setHotel_option_name(String hotel_option_name) {
		this.hotel_option_name = hotel_option_name;
	}
	public String getHotel_option_title() {
		return hotel_option_title;
	}
	public void setHotel_option_title(String hotel_option_title) {
		this.hotel_option_title = hotel_option_title;
	}
	public double getReview_star() {
		return review_star;
	}
	public void setReview_star(double review_star) {
		this.review_star = review_star;
	}
	
	
	
}
