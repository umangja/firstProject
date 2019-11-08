package com.umang.model;

public class Medicine_purchased {
	
	int medicine_id,bill_id,quantity;
	String name;
	public Medicine_purchased() {
		// TODO Auto-generated constructor stub
	}
	public int getBill_id() {
		return bill_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public int getMedicine_id() {
		return medicine_id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	
	public void setMedicine_id(int medicine_id) {
		this.medicine_id = medicine_id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
