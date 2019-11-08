package com.umang.model;

public class Address {
	private int id;
	private int shop_no;
	private int house_no;
	private String colony_name;
	private String city;
	private String state;
	private int pincode;
	
	public Address() {
		super();
	}
	
	public Address(int id) {
		super();
		this.id=id;
	}
	
	public int getHouse_no() {
		return house_no;
	}
	public String getCity() {
		return city;
	}
	public String getColony_name() {
		return colony_name;
	}
	public int getId() {
		return id;
	}
	public int getPincode() {
		return pincode;
	}
	public int getShop_no() {
		return shop_no;
	}
	public String getState() {
		return state;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setColony_name(String colony_name) {
		this.colony_name = colony_name;
	}
	
	public void setHouse_no(int house_no) {
		this.house_no = house_no;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public void setShop_no(int shop_no) {
		this.shop_no = shop_no;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
}