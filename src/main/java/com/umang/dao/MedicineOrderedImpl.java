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

import com.umang.model.Medicine_ordered;
import com.umang.model.Medicine_purchased;

public class MedicineOrderedImpl implements MedicineOrdereddao {
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public MedicineOrderedImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MedicineOrderedImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public void add(List<Medicine_ordered> medicineOrdered) {
		for(int i=0;i<medicineOrdered.size();i++)
		{
			Medicine_ordered med = medicineOrdered.get(0);
			String sql = "insert into Medicine_ordered(medicine_id,order_id,quantity) values(?,?,?)";
			jdbcTemplate.update(sql,new Object[] {med.getMedicine_id(),med.getOrder_id(),med.getQuantity()});
			
		}
		
	}
	
	@Override
	public List<Medicine_ordered> getMedicinesForOrder(int order_id) {
		String sql = "select Medicine_ordered.order_id as order_id,Medicine_ordered.quantity as quantity,Medicines.name as name,Medicines.id as med_id from  Medicine_ordered,Medicines where Medicine_ordered.order_id=? and Medicines.id=Medicine_ordered.medicine_id";
		List<Medicine_ordered> meds = jdbcTemplate.query(sql,new Object[] {order_id},new ResultSetExtractor< List<Medicine_ordered> >() {
			
			public List<Medicine_ordered> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine_ordered> meds = new ArrayList<Medicine_ordered> ();
				while(rs.next()) {;
					Medicine_ordered med = new Medicine_ordered();
					med.setOrder_id(rs.getInt("order_id"));
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
	
	@Override
	public void updateToComplete(int order_id) {
		String sql = "update Orders set is_completed=1 where id=?";
		jdbcTemplate.update(sql,new Object[] {order_id});
		return ;
	}
	
	
	
	

}
