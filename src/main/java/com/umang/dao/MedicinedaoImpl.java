package com.umang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.umang.model.Medicine;

public class MedicinedaoImpl implements Medicinedao {
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public MedicinedaoImpl() {
		
	}
	
	public MedicinedaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void addMedicine(Medicine med) {
		String sql = "INSERT INTO Medicines(name,company,price,in_stock,expiration_date,location_id,supplier_id) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] {med.getName(),med.getCompany(),med.getPrice(),med.getIn_stock(),med.getExpiration_date(),med.getLocation_id(),med.getSupplier_id()});
		
	}
	
	public List<Medicine> getAllMedicinesId() {
		String sql="select id,name from Medicines";
		List<Medicine> meds = jdbcTemplate.query(sql,new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {;
					Medicine med = new Medicine();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	public List<Medicine> getAllMedicine() {
		String sql="select * from Medicines";
		List<Medicine> meds = jdbcTemplate.query(sql,new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {;
					Medicine med = new Medicine();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setCompany(rs.getString("company"));
					med.setIn_stock(rs.getInt("in_stock"));
					med.setPrice(rs.getInt("price"));
					med.setExpiration_date(rs.getDate("expiration_date"));
					med.setSupplier_id(rs.getInt("supplier_id"));
					med.setLocation_id(rs.getInt("location_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	public List<Medicine> getMedicineById(int id) {
		String sql="select * from Medicines where id=?";
		List<Medicine> meds = jdbcTemplate.query(sql,new Object[] {id},new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {;
					Medicine med = new Medicine();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setCompany(rs.getString("company"));
					med.setIn_stock(rs.getInt("in_stock"));
					med.setPrice(rs.getInt("price"));
					med.setExpiration_date(rs.getDate("expiration_date"));
					med.setSupplier_id(rs.getInt("supplier_id"));
					med.setLocation_id(rs.getInt("location_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	public List<Medicine> getAllMedicineFiltered(int price, int in_stock, String expiration_date) {
		String sql="select * from Medicines where price<? and in_stock<? and expiration_date<?";
		List<Medicine> meds = jdbcTemplate.query(sql,new Object[] {price,in_stock,expiration_date},new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {;
					Medicine med = new Medicine();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setCompany(rs.getString("company"));
					med.setIn_stock(rs.getInt("in_stock"));
					med.setPrice(rs.getInt("price"));
					med.setExpiration_date(rs.getDate("expiration_date"));
					med.setSupplier_id(rs.getInt("supplier_id"));
					med.setLocation_id(rs.getInt("location_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	
	public List<Medicine> getAllMedicineFiltered(String company, int price, int in_stock, String expiration_date) {
		String sql="select * from Medicines where company=? and price<? and in_stock<? and expiration_date<?";
		List<Medicine> meds = jdbcTemplate.query(sql,new Object[] {company,price,in_stock,expiration_date},new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {;
					Medicine med = new Medicine();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setCompany(rs.getString("company"));
					med.setIn_stock(rs.getInt("in_stock"));
					med.setPrice(rs.getInt("price"));
					med.setExpiration_date(rs.getDate("expiration_date"));
					med.setSupplier_id(rs.getInt("supplier_id"));
					med.setLocation_id(rs.getInt("location_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	public void updateIn_stock(int medicine_id, int quantity) {
		String sql = "update Medicines set Medicines.in_stock = Medicines.in_stock-? where id=?";
		jdbcTemplate.update(sql,new Object[] {quantity,medicine_id});
	}
	
	public List<Medicine> getExpiredMedicineId() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		String sql = "select id,name from Medicines where expiration_date<? and in_stock>0";
		List<Medicine> meds = jdbcTemplate.query(sql,new Object[] {date},new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {
					Medicine med  = new Medicine();
					med.setId(rs.getInt("id"));
					med.setName(rs.getString("name"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	public void updateStockToZero(List<Medicine> medicine_id) {
		for(int i=0;i<medicine_id.size();i++)
		{
			int med_id = medicine_id.get(i).getId();
			String sql = "update Medicines set Medicines.in_stock = 0 where id=?";
			jdbcTemplate.update(sql,new Object[] {med_id});
		}		
	}
	
	
	public void updateExpiredMedicine() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		String sql = "update Medicines set Medicines.in_stock = 0 where expiration_date<?";
		getJdbcTemplate().update(sql,new Object[] {date});	
	}

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate ;
	}
	
	
	public List<Medicine> getMedicineByName(String med_name) {
		String sql="select * from Medicines where name=?";
		List<Medicine> meds = jdbcTemplate.query(sql,new Object[] {med_name},new ResultSetExtractor<List<Medicine> >() {
			
			public List<Medicine> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Medicine> meds = new ArrayList<Medicine> ();
				while(rs.next()) {;
					Medicine med = new Medicine();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setCompany(rs.getString("company"));
					med.setIn_stock(rs.getInt("in_stock"));
					med.setPrice(rs.getInt("price"));
					med.setExpiration_date(rs.getDate("expiration_date"));
					med.setSupplier_id(rs.getInt("supplier_id"));
					med.setLocation_id(rs.getInt("location_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	
	
	
	
	
	
	
	

}
