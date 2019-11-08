package com.umang.model;

import java.util.Date;
import java.util.List;

public class Bill {
	int id,total,transaction_id,discount_offered,supplied_to,purchased_by;
	String payment_mode,employee_issuing;
	Date datetime;
	List<Integer> medicines;
	
	public int getId() {
		return id;
	}
	
	public Bill() {
		// TODO Auto-generated constructor stub
	}
	
	public Date getDatetime() {
		return datetime;
	}
	
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	
	public List<Integer> getMedicines() {
		return medicines;
	}
	
	public void setMedicines(List<Integer> medicines) {
		this.medicines = medicines;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getDiscount_offered() {
		return discount_offered;
	}
	public void setDiscount_offered(int discount_offered) {
		this.discount_offered = discount_offered;
	}
	public int getSupplied_to() {
		return supplied_to;
	}
	public void setSupplied_to(int supplied_to) {
		this.supplied_to = supplied_to;
	}
	public int getPurchased_by() {
		return purchased_by;
	}
	public void setPurchased_by(int purchased_by) {
		this.purchased_by = purchased_by;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getEmployee_issuing() {
		return employee_issuing;
	}
	public void setEmployee_issuing(String employee_issuing) {
		this.employee_issuing = employee_issuing;
	}

	
	

}
