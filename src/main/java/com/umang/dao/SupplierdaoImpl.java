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

import com.umang.dao.Supplierdao;
import com.umang.model.Supplier;
import com.umang.model.User;

public class SupplierdaoImpl implements Supplierdao{
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public SupplierdaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public SupplierdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public int addSupplier(Supplier sup) {
		String sql = "insert into Suppliers(name,phone_no) values(?,?)";
		jdbcTemplate.update(sql,new Object[] {sup.getName(),sup.getPhone_no()});
		sql = "select id from Suppliers where name=? and phone_no=?";
		Integer id = jdbcTemplate.query(sql,new Object[] {sup.getName(),sup.getPhone_no()},new ResultSetExtractor< Integer >() {
			
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {
					return rs.getInt("id");
				}
				return null;
			}
			
		});
		return id;
		
	}
	
	public List<Supplier> getAllSup() {
		String sql = "select * from Suppliers ";
		List<Supplier> sups =jdbcTemplate.query(sql, new ResultSetExtractor< List<Supplier> >() {
			
			public List<Supplier> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Supplier> sups = new ArrayList<Supplier> ();
				while(rs.next()) {
					Supplier sup = new Supplier();
					sup.setAddress_id(rs.getInt("address_id"));
					sup.setId(rs.getInt("id"));
					sup.setName(rs.getString("name"));
					sup.setPhone_no(rs.getString("phone_no"));
					sups.add(sup);
				}
				return sups;
			}
			
		});
		return sups;
	}
	
	public List<Supplier> getSupplierForMedicine(int medicine_id) {
		System.out.println(medicine_id);
		String sql = "select Suppliers.id,Suppliers.address_id,Suppliers.name,Suppliers.phone_no from Medicines,Suppliers where Medicines.supplier_id=Suppliers.id and Medicines.id=? ";
		List<Supplier> sups =jdbcTemplate.query(sql, new Object[] {medicine_id},new ResultSetExtractor< List<Supplier> >() {
			
			public List<Supplier> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Supplier> sups = new ArrayList<Supplier> ();
				while(rs.next()) {
					System.out.println("fuck");
					Supplier sup = new Supplier();
					sup.setAddress_id(rs.getInt("address_id"));
					sup.setId(rs.getInt("id"));
					sup.setName(rs.getString("name"));
					sup.setPhone_no(rs.getString("phone_no"));
					sups.add(sup);
				}
				return sups;
			}
			
		});
		return sups;
	}
	
	public void setAddress(String parameter, int add_id) {
		String sql = "update Suppliers set address_id = ? where id=?";
		jdbcTemplate.update(sql,new Object[] {add_id,parameter});
	}
	
	
	
	
}
