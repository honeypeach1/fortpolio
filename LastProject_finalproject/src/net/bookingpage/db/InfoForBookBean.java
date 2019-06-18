package net.bookingpage.db;

import java.sql.Date;

public class InfoForBookBean {

	private int user_no;
	private String user_id;
	private int room_type_no;
	private int booking_total_people;
	private Date check_in;
	private Date check_out;
	private String booker_name;
	private String booker_phone;
	private String booker_email;
	private String[] room_option_no;
	private float total_price;

	// --------------------------------------------------------------

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int type;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getRoom_type_no() {
		return room_type_no;
	}

	public void setRoom_type_no(int room_type_no) {
		this.room_type_no = room_type_no;
	}

	public int getBooking_total_people() {
		return booking_total_people;
	}

	public void setBooking_total_people(int booking_total_people) {
		this.booking_total_people = booking_total_people;
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

	public String getBooker_name() {
		return booker_name;
	}

	public void setBooker_name(String booker_name) {
		this.booker_name = booker_name;
	}

	public String getBooker_phone() {
		return booker_phone;
	}

	public void setBooker_phone(String booker_phone) {
		this.booker_phone = booker_phone;
	}

	public String getBooker_email() {
		return booker_email;
	}

	public void setBooker_email(String booker_email) {
		this.booker_email = booker_email;
	}

	public String[] getRoom_option_no() {
		return room_option_no;
	}

	public void setRoom_option_no(String[] room_option_no) {
		this.room_option_no = room_option_no;
	}

}