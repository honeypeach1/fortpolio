package project.list.db;

import java.sql.Date;

/**
 * @author Jamey Aesthetic
 *
 */
public class ListBean {
	private int rownum;
	private int hotel_no;
	private String hotel_name;
	private String hotel_address;
	private String hotel_free_service;
	private int hotel_class;
	private String hotel_main_image;
	private String hotel_tel;
	private String approval;
	private int count;
	
	private int room_no;
	private int room_number;
	private int room_basic_people;
	private int room_max_people;
	private String room_info;
	private int room_price;
	private String room_type;
	private int discount_rate;
	private int discount_price;
	private String room_name;
	private String room_pic;
	
	private int hotel_pic_no;
	private String hotel_pic;
	
	private int hotel_option_no;
	private String hotel_option_name;
	private String hotel_option_title;
	
	private String hotel_insroduct;
	
	// User_Table
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_email;
	private String user_phone;
	private String user_name;
	
	// getUserInfo에 필요한 변수
	private int room_type_no; // 룸 타입 넘버

	private Date check_in;
	private Date check_out;
	private int day; // 총 몇 박 묵을지
		
	// 객실 옵션 정보
	private int room_option_no;
	private String room_option_name;
	private int option_add_price;
	
	// Book_Table
	private String booker_name;
	private int booking_no;
	private int booking_state;
	private int booking_total_people;
	private Date booking_date;
	private Date booking_enter_date;
	private Date booking_leave_date;
	
	private String hotel_instroduct;
	
	public String getBooker_name() {
		return booker_name;
	}
	public void setBooker_name(String booker_name) {
		this.booker_name = booker_name;
	}
	public Date getBooking_enter_date() {
		return booking_enter_date;
	}
	public void setBooking_enter_date(Date booking_enter_date) {
		this.booking_enter_date = booking_enter_date;
	}
	public Date getBooking_leave_date() {
		return booking_leave_date;
	}
	public void setBooking_leave_date(Date booking_leave_date) {
		this.booking_leave_date = booking_leave_date;
	}
	public Date getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	public int getBooking_total_people() {
		return booking_total_people;
	}
	public void setBooking_total_people(int booking_total_people) {
		this.booking_total_people = booking_total_people;
	}
	public int getBooking_state() {
		return booking_state;
	}
	public void setBooking_state(int booking_state) {
		this.booking_state = booking_state;
	}
	public int getRoom_type_no() {
		return room_type_no;
	}
	public void setRoom_type_no(int room_type_no) {
		this.room_type_no = room_type_no;
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
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
	public int getHotel_pic_no() {
		return hotel_pic_no;
	}
	public void setHotel_pic_no(int hotel_pic_no) {
		this.hotel_pic_no = hotel_pic_no;
	}
	public String getHotel_pic() {
		return hotel_pic;
	}
	public void setHotel_pic(String hotel_pic) {
		this.hotel_pic = hotel_pic;
	}
	public String getHotel_tel() {
		return hotel_tel;
	}
	public void setHotel_tel(String hotel_tel) {
		this.hotel_tel = hotel_tel;
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
	public String getHotel_main_image() {
		return hotel_main_image;
	}
	public void setHotel_main_image(String hotel_main_image) {
		this.hotel_main_image = hotel_main_image;
	}
	public String getHotel_instroduct() {
		return hotel_instroduct;
	}
	public void setHotel_instroduct(String hotel_instroduct) {
		this.hotel_instroduct = hotel_instroduct;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
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
	
	public int getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(int discount_price) {
		this.discount_price = discount_price;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_pic() {
		return room_pic;
	}
	public void setRoom_pic(String room_pic) {
		this.room_pic = room_pic;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRoom_basic_people() {
		return room_basic_people;
	}
	public void setRoom_basic_people(int room_basic_people) {
		this.room_basic_people = room_basic_people;
	}
	public String getRoom_info() {
		return room_info;
	}
	public void setRoom_info(String room_info) {
		this.room_info = room_info;
	}
	public int getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	public String getHotel_option_title() {
		return hotel_option_title;
	}
	public void setHotel_option_title(String hotel_option_title) {
		this.hotel_option_title = hotel_option_title;
	}
	public String getHotel_insroduct() {
		return hotel_insroduct;
	}
	public void setHotel_insroduct(String hotel_insroduct) {
		this.hotel_insroduct = hotel_insroduct;
	}
}
