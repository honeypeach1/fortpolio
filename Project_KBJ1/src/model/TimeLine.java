package model;

import java.util.ArrayList;

public class TimeLine{
	private int time_line_num;
	private int condition_num;
	private int user_num;
	private String time_line_comments;
	private String time_line_time;
	private String time_line_image;
	private int upload_num;
	
	private String user_id;
	private String condition_name;
	
	private String user_image;
	
	private ArrayList<CommentTable> CommentTable;
	
	
	private int comment_num;
	private String comments;
	private String comment_time;
	private String user_name;
	
	
	public int getTime_line_num() {
		return time_line_num;
	}
	public void setTime_line_num(int time_line_num) {
		this.time_line_num = time_line_num;
	}
	public int getCondition_num() {
		return condition_num;
	}
	public void setCondition_num(int condition_num) {
		this.condition_num = condition_num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	
	public String getTime_line_comments() {
		return time_line_comments;
	}
	public void setTime_line_comments(String time_line_comments) {
		this.time_line_comments = time_line_comments;
	}
	public String getTime_line_time() {
		return time_line_time;
	}
	public void setTime_line_time(String time_line_time) {
		this.time_line_time = time_line_time;
	}
	public String getTime_line_image() {
		return time_line_image;
	}
	public void setTime_line_image(String time_line_image) {
		this.time_line_image = time_line_image;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCondition_name() {
		return condition_name;
	}
	public void setCondition_name(String condition_name) {
		this.condition_name = condition_name;
	}
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	public int getUpload_num() {
		return upload_num;
	}
	public void setUpload_num(int upload_num) {
		this.upload_num = upload_num;
	}
	public ArrayList<CommentTable> getCommentTable() {
		return CommentTable;
	}
	public void setCommentTable(ArrayList<CommentTable> commentTable) {
		CommentTable = commentTable;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
