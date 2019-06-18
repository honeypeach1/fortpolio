package model;

import java.util.ArrayList;

public class Course {
	private String course_num;
	private String stay_num;
	private String course_name;
	private String course_price;
	private String long_date;
	private String people_num;
	private String visa;
	private String comments;
	private String course_image1;
	private String course_image2;
	private String course_image3;
	private String course_image4;
	private String nation;
	private String start_date;
	private String end_date;
	
	//join���� ������ ����
	private String stay_name;
	private String stay_tel;
	private String stay_id;
	private String stay_email;
	private String category_num;
	private String category_kind;
	private String category_name;
	private String category_comments;
	
	private String reser_num;
	private String leave_num;
	private String leave_id;
	private String leave_email;
	private String leave_tel;
	private String leave_name;
	private String reser_date;
	private String peoples;
	
	private ArrayList<Information> information;
	private ArrayList<String> reserdate; 
	private ArrayList<String> traveldate;
	
	public ArrayList<String> getTraveldate() {
		return traveldate;
	}
	public void setTraveldate(ArrayList<String> traveldate) {
		this.traveldate = traveldate;
	}
	public ArrayList<String> getReserdate() {
		return reserdate;
	}
	public void setReserdate(ArrayList<String> reserdate) {
		this.reserdate = reserdate;
	}
	public String getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(String leave_id) {
		this.leave_id = leave_id;
	}
	public String getLeave_email() {
		return leave_email;
	}
	public void setLeave_email(String leave_email) {
		this.leave_email = leave_email;
	}
	public String getLeave_tel() {
		return leave_tel;
	}
	public void setLeave_tel(String leave_tel) {
		this.leave_tel = leave_tel;
	}
	public String getLeave_name() {
		return leave_name;
	}
	public void setLeave_name(String leave_name) {
		this.leave_name = leave_name;
	}
	public String getReser_num() {
		return reser_num;
	}
	public void setReser_num(String reser_num) {
		this.reser_num = reser_num;
	}
	public String getLeave_num() {
		return leave_num;
	}
	public void setLeave_num(String leave_num) {
		this.leave_num = leave_num;
	}
	public String getReser_date() {
		return reser_date;
	}
	public void setReser_date(String reser_date) {
		this.reser_date = reser_date;
	}
	public String getPeoples() {
		return peoples;
	}
	public void setPeoples(String peoples) {
		this.peoples = peoples;
	}
	public ArrayList<Information> getInformation() {
		return information;
	}
	public void setInformation(ArrayList<Information> information) {
		this.information = information;
	}
	public String getCourse_num() {
		return course_num;
	}
	public void setCourse_num(String course_num) {
		this.course_num = course_num;
	}
	public String getStay_num() {
		return stay_num;
	}
	public void setStay_num(String stay_num) {
		this.stay_num = stay_num;
	}
	public void setCategory_num(String category_num) {
		this.category_num = category_num;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_price() {
		return course_price;
	}
	public void setCourse_price(String course_price) {
		this.course_price = course_price;
	}
	public String getLong_date() {
		return long_date;
	}
	public void setLong_date(String long_date) {
		this.long_date = long_date;
	}
	public String getPeople_num() {
		return people_num;
	}
	public void setPeople_num(String people_num) {
		this.people_num = people_num;
	}
	public String getVisa() {
		return visa;
	}
	public void setVisa(String visa) {
		this.visa = visa;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCourse_image1() {
		return course_image1;
	}
	public void setCourse_image1(String course_image1) {
		this.course_image1 = course_image1;
	}
	public String getCourse_image2() {
		return course_image2;
	}
	public void setCourse_image2(String course_image2) {
		this.course_image2 = course_image2;
	}
	public String getCourse_image3() {
		return course_image3;
	}
	public void setCourse_image3(String course_image3) {
		this.course_image3 = course_image3;
	}
	public String getCourse_image4() {
		return course_image4;
	}
	public void setCourse_image4(String course_image4) {
		this.course_image4 = course_image4;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getStay_name() {
		return stay_name;
	}
	public void setStay_name(String stay_name) {
		this.stay_name = stay_name;
	}
	public String getStay_tel() {
		return stay_tel;
	}
	public void setStay_tel(String stay_tel) {
		this.stay_tel = stay_tel;
	}
	public String getStay_id() {
		return stay_id;
	}
	public void setStay_id(String stay_id) {
		this.stay_id = stay_id;
	}
	public String getStay_email() {
		return stay_email;
	}
	public void setStay_email(String stay_email) {
		this.stay_email = stay_email;
	}
	public String getCategory_num() {
		return category_num;
	}
	public String getCategory_kind() {
		return category_kind;
	}
	public void setCategory_kind(String category_kind) {
		this.category_kind = category_kind;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_comments() {
		return category_comments;
	}
	public void setCategory_comments(String category_comments) {
		this.category_comments = category_comments;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
