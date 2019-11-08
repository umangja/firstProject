package com.umang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.umang.model.Partner;


public class PartnerdaoImpl implements Partnerdao{
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public PartnerdaoImpl() {
		
	}
	
	public PartnerdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void addPartner(Partner partner) {
		String sql = "insert into Partners(name,phone_no,address_id) values(?,?,?)";
		jdbcTemplate.update(sql,new Object[] {partner.getName(),partner.getPhone_no(),partner.getAddress_id()});
	}
	
	public List<Partner> getAll() {
		String sql = "select * from Partners";
		List<Partner> meds = jdbcTemplate.query(sql,new ResultSetExtractor<List<Partner> >() {
			
			public List<Partner> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Partner> meds = new ArrayList<Partner> ();
				while(rs.next()) {;
					Partner med = new Partner();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setPhone_no(rs.getString("phone_no"));
					med.setAddress_id(rs.getInt("address_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	public List<Partner> getPartnerById(int patient_id) {
		String sql = "select * from Partners where id = ?";
		List<Partner> meds = jdbcTemplate.query(sql,new Object[] {patient_id},new ResultSetExtractor<List<Partner> >() {
			
			public List<Partner> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Partner> meds = new ArrayList<Partner> ();
				while(rs.next()) {;
					Partner med = new Partner();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setPhone_no(rs.getString("phone_no"));
					med.setAddress_id(rs.getInt("address_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	public List<Partner> getMostValuablePartnersSorted() {
	    Calendar c = Calendar.getInstance();  
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    Date reqired_date = c.getTime();
		String sql = "select * from Partners where id in (select supplied_to from Bills where supplied_to!='NULL' and dateAndTime>=? group by supplied_to order by sum(total) desc)";
		List<Partner> meds = jdbcTemplate.query(sql,new Object[] {reqired_date},new ResultSetExtractor<List<Partner> >() {
			
			public List<Partner> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Partner> meds = new ArrayList<Partner> ();
				while(rs.next()) {;
					Partner med = new Partner();
					med.setName(rs.getString("name"));
					med.setId(rs.getInt("id"));
					med.setPhone_no(rs.getString("phone_no"));
					med.setAddress_id(rs.getInt("address_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
	
	
	public List<Integer> getTotalBillOfPartner(int id) {
		String sql = "select sum(total) as total from Bills where supplied_to=? group by supplied_to";
		List<Integer> meds = jdbcTemplate.query(sql,new Object[] {id},new ResultSetExtractor<List<Integer> >() {
			
			public List<Integer> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Integer> meds = new ArrayList<Integer> ();
				while(rs.next()) {;
					meds.add(rs.getInt("total"));
				}
				return meds;
			}
			
		});
		return meds;
	}
	
	
}
