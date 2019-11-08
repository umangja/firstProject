package com.umang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.umang.model.Address;

public class AddressdaoImpl implements Addressdao {
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public AddressdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public AddressdaoImpl() {
	}

	public Address getAddressById(int id) {
			String sql = "select * from Address where id=?";
			return jdbcTemplate.query(sql,new Object[] {id},new ResultSetExtractor<Address>() {
				
				public Address extractData(ResultSet rs) throws SQLException,DataAccessException{
					if(rs.next()) {
						Address add = new Address();
						add.setCity(rs.getString("city"));
						add.setColony_name(rs.getString("colony_name"));
						add.setHouse_no(rs.getInt("house_no"));
						add.setPincode(rs.getInt("pincode"));
						add.setShop_no(rs.getInt("shop_no"));
						add.setState(rs.getString("state"));
						return add;
					}
					return null;
				}
				
			});
	}
	
	public int updateAddress(Address add) {
		String sql = "INSERT INTO Address(shop_no,house_no,colony_name,city,state,pincode) values(?,?,?,?,?,?)"; 
		jdbcTemplate.update(sql,new Object[] {add.getShop_no(),add.getHouse_no(),add.getColony_name(),add.getCity(),add.getState(),add.getPincode()});		
		sql = "select id from Address where house_no=? and shop_no=? and colony_name=? and city=? and state=? and pincode=?";
		int add_id = jdbcTemplate.query(sql,new Object[] {add.getHouse_no(),add.getShop_no(), add.getColony_name(),add.getCity(),add.getState(),add.getPincode()},new ResultSetExtractor<Integer>() {
			
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {;
					return rs.getInt("id");
				}
				return null;
			}
			
		});
		return add_id;
	}
	
	
	


}
