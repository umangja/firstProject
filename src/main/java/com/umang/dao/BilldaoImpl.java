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

import com.umang.model.Bill;

public class BilldaoImpl implements Billdao {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public BilldaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public BilldaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public int addBillOfPartner(Bill bill) {
		String sql = "insert into Bills(total,payment_mode,transaction_id,discount_offered,employee_issuing,supplied_to) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] {bill.getTotal(),bill.getPayment_mode(),bill.getTransaction_id(),bill.getDiscount_offered(),bill.getEmployee_issuing(),bill.getSupplied_to()});
		sql = "select id from Bills where total=? and payment_mode=? and transaction_id=? and discount_offered=? and employee_issuing=? and supplied_to=?";
		int id = jdbcTemplate.query(sql,new Object[] {bill.getTotal(),bill.getPayment_mode(),bill.getTransaction_id(),bill.getDiscount_offered(),bill.getEmployee_issuing(),bill.getSupplied_to()}, new ResultSetExtractor<Integer>() {
			
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {
					return rs.getInt("id");
				}
				return null;
			}
			
		});
		return id;
	}
	
	public int addBillOfPatient(Bill bill) {
		String sql = "insert into Bills(total,payment_mode,transaction_id,discount_offered,employee_issuing,purchased_by) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] {bill.getTotal(),bill.getPayment_mode(),bill.getTransaction_id(),bill.getDiscount_offered(),bill.getEmployee_issuing(),bill.getPurchased_by()});
		sql = "select id from Bills where total=? and payment_mode=? and transaction_id=? and discount_offered=? and employee_issuing=? and purchased_by=?";
		int id = jdbcTemplate.query(sql,new Object[] {bill.getTotal(),bill.getPayment_mode(),bill.getTransaction_id(),bill.getDiscount_offered(),bill.getEmployee_issuing(),bill.getPurchased_by()}, new ResultSetExtractor<Integer>() {
			
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {
					return rs.getInt("id");
				}
				return null;
			}
			
		});
		return id;
	}
	
	public List<Bill> getBills() {
		String sql = "select * from Bills";
		List<Bill> id = jdbcTemplate.query(sql, new ResultSetExtractor<List<Bill>>() {
			
			public List<Bill> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Bill> bills = new ArrayList<Bill> ();
				while(rs.next()) {
					Bill bill = new Bill();
					bill.setDatetime(rs.getDate("dateAndTime"));
					bill.setEmployee_issuing(rs.getString("employee_issuing"));
					bill.setDiscount_offered(rs.getInt("discount_offered"));
					bill.setId(rs.getInt("id"));
					bill.setPayment_mode(rs.getString("payment_mode"));
					bill.setTotal(rs.getInt("total"));
					bill.setPurchased_by(rs.getInt("purchased_by"));
					bill.setSupplied_to(rs.getInt("supplied_to"));
					bill.setTransaction_id(rs.getInt("transaction_id"));
					bills.add(bill);
				}
				return bills;
			}
			
		});
		return id;
	}
	
	
	public List<Bill> getAllBillsFiltered(int total, int discount_offered, String datetime) {
		String sql = "select * from Bills where total>? and discount_offered>? and dateAndTime<?";
		List<Bill> id = jdbcTemplate.query(sql, new Object[] {total,discount_offered,datetime}, new ResultSetExtractor<List<Bill>>() {
			
			public List<Bill> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Bill> bills = new ArrayList<Bill> ();
				while(rs.next()) {
					Bill bill = new Bill();
					bill.setDatetime(rs.getDate("dateAndTime"));
					bill.setEmployee_issuing(rs.getString("employee_issuing"));
					bill.setDiscount_offered(rs.getInt("discount_offered"));
					bill.setId(rs.getInt("id"));
					bill.setPayment_mode(rs.getString("payment_mode"));
					bill.setTotal(rs.getInt("total"));
					bill.setPurchased_by(rs.getInt("purchased_by"));
					bill.setSupplied_to(rs.getInt("supplied_to"));
					bill.setTransaction_id(rs.getInt("transaction_id"));
					bills.add(bill);
				}
				return bills;
			}
			
		});
		return id;
		}
	
	public List<Bill> getAllBillsFiltered(int total, int discount_offered, String datetime, String employee_issuing) {
		String sql = "select * from Bills where total>? and discount_offered>? and dateAndtime<? and employee_issuing=?";
		List<Bill> id = jdbcTemplate.query(sql, new Object[] {total,discount_offered,datetime,employee_issuing}, new ResultSetExtractor<List<Bill>>() {
			
			public List<Bill> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Bill> bills = new ArrayList<Bill> ();
				while(rs.next()) {
					Bill bill = new Bill();
					bill.setDatetime(rs.getDate("dateAndTime"));
					bill.setEmployee_issuing(rs.getString("employee_issuing"));
					bill.setDiscount_offered(rs.getInt("discount_offered"));
					bill.setId(rs.getInt("id"));
					bill.setPayment_mode(rs.getString("payment_mode"));
					bill.setTotal(rs.getInt("total"));
					bill.setPurchased_by(rs.getInt("purchased_by"));
					bill.setSupplied_to(rs.getInt("supplied_to"));
					bill.setTransaction_id(rs.getInt("transaction_id"));
					bills.add(bill);
				}
				return bills;
			}
			
		});
		return id;
	}
	
	public List<Bill> getBillById(int bill_id) {
		String sql = "select * from Bills where id=?";
		List<Bill> id = jdbcTemplate.query(sql, new Object[] {bill_id},new ResultSetExtractor<List<Bill>>() {
			
			public List<Bill> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Bill> bills = new ArrayList<Bill> ();
				while(rs.next()) {
					Bill bill = new Bill();
					bill.setDatetime(rs.getDate("dateAndTime"));
					bill.setEmployee_issuing(rs.getString("employee_issuing"));
					bill.setDiscount_offered(rs.getInt("discount_offered"));
					bill.setId(rs.getInt("id"));
					bill.setPayment_mode(rs.getString("payment_mode"));
					bill.setTotal(rs.getInt("total"));
					bill.setPurchased_by(rs.getInt("purchased_by"));
					bill.setSupplied_to(rs.getInt("supplied_to"));
					bill.setTransaction_id(rs.getInt("transaction_id"));
					bills.add(bill);
				}
				return bills;
			}
			
		});
		return id;
	}
	
	

	
	

}
