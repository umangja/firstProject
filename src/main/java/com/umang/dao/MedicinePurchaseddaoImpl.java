package com.umang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.umang.model.Medicine_purchased;
import com.umang.model.Partner;

public class MedicinePurchaseddaoImpl implements MedicinePurchaseddao{
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public MedicinePurchaseddaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MedicinePurchaseddaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void add(List<Medicine_purchased> medicinePurchased) {
		for(int i=0;i<medicinePurchased.size();i++)
		{
			Medicine_purchased med = medicinePurchased.get(i);
			String sql = "insert into Medicine_purchased values(?,?,?)";
			jdbcTemplate.update(sql,new Object[] {med.getMedicine_id(),med.getBill_id(),med.getQuantity()});
			
		}
		
	}
	
	public List<Medicine_purchased> getMedicinesForBill(int bill_id) {
		String sql = "select Medicine_purchased.bill_id as bill_id,Medicine_purchased.quantity as quantity,Medicines.name as name,Medicines.id as med_id from  Medicine_purchased,Medicines where Medicine_purchased.bill_id=? and Medicines.id=Medicine_purchased.medicine_id";
		List<Medicine_purchased> meds = jdbcTemplate.query(sql,new Object[] {bill_id},new ResultSetExtractor< List<Medicine_purchased> >() {
			
			public List<Medicine_purchased> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine_purchased> meds = new ArrayList<Medicine_purchased> ();
				while(rs.next()) {;
					Medicine_purchased med = new Medicine_purchased();
					med.setBill_id(rs.getInt("bill_id"));
					med.setName(rs.getString("name"));
					med.setQuantity(rs.getInt("quantity"));
					med.setMedicine_id(rs.getInt("med_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	

}
