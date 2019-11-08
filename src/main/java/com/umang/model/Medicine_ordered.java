package com.umang.model;

public class Medicine_ordered {
 int medicine_id,quantity,order_id;
 String name;
 
 public String getName() {
	return name;
}
 
 public void setName(String name) {
	this.name = name;
}
 
 

public int getMedicine_id() {
	return medicine_id;
}

public void setMedicine_id(int medicine_id) {
	this.medicine_id = medicine_id;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public int getOrder_id() {
	return order_id;
}

public void setOrder_id(int order_id) {
	this.order_id = order_id;
}
 
}
