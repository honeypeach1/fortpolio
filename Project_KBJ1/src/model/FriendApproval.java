package model;

public class FriendApproval {
	private int fri_acc_num; //친구 신청 받는 번호
	private String approval;
	private int fri_app_num;	//친구 신청 하는 번호
	
	private String user_image;
	private String user_name;
	public int getFri_acc_num() {
		return fri_acc_num;
	}
	public void setFri_acc_num(int fri_acc_num) {
		this.fri_acc_num = fri_acc_num;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public int getFri_app_num() {
		return fri_app_num;
	}
	public void setFri_app_num(int fri_app_num) {
		this.fri_app_num = fri_app_num;
	}
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
