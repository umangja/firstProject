package com.umang.model;

public class Orders {
	
	int id,is_completed,bill_no,ordered_by;
	String dateAndTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIs_completed() {
		return is_completed;
	}
	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
	public int getBill_no() {
		return bill_no;
	}
	public void setBill_no(int bill_no) {
		this.bill_no = bill_no;
	}
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public int getOrdered_by() {
		return ordered_by;
	}
	
	public void setOrdered_by(int ordered_by) {
		this.ordered_by = ordered_by;
	}
	
	
	
	

}
