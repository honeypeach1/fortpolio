package model;

public class Reservation {
	private String reser_num;
	private String leave_num;
	private String course_num;
	private String reser_date;
	private String peoples;
	
	private String stay_tel;
	
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
	public String getCourse_num() {
		return course_num;
	}
	public void setCourse_num(String course_num) {
		this.course_num = course_num;
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
	public String getStay_tel() {
		return stay_tel;
	}
	public void setStay_tel(String stay_tel) {
		this.stay_tel = stay_tel;
	}
}
