package com.umang.dao;

import java.util.List;

import com.umang.model.Bill;

public interface Billdao {

	int addBillOfPartner(Bill bill);
	int addBillOfPatient(Bill bill);
	public List<Bill> getBills();
	List<Bill> getAllBillsFiltered(int total, int discount_offered, String datetime);
	List<Bill> getAllBillsFiltered(int total, int discount_offered, String datetime, String employee_issuing);
	List<Bill> getBillById(int bill_id);

}
