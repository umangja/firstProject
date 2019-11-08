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

import com.umang.model.Orders;
import com.umang.model.Supplier;

public class OrdersdaoImpl implements Ordersdao {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public OrdersdaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public OrdersdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public int add(Orders orders) {
		String sql = "INSERT INTO Orders(ordered_by) values(?)";
		jdbcTemplate.update(sql,new Object[] {orders.getOrdered_by()});
		sql = "SELECT id FROM Orders ORDER BY id DESC LIMIT 1";
		int id = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
			
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {
					return rs.getInt("id");
				}
				return 0;
			}
			
		});
		return id;
	}
	
	@Override
	public List<Orders> getIncompleteOrders() {
		String sql = "select * from Orders where is_completed=0";
		List<Orders> sups =jdbcTemplate.query(sql,new ResultSetExtractor< List<Orders> >() {
			
			public List<Orders> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Orders> sups = new ArrayList<Orders> ();
				while(rs.next()) {
					Orders sup = new Orders();
					sup.setId(rs.getInt("id"));
					sup.setDateAndTime(rs.getString("dateAndTime"));
					sup.setIs_completed(rs.getInt("is_completed"));
					sup.setOrdered_by(rs.getInt("ordered_by"));
					sups.add(sup);
				}
				return sups;
			}
			
		});
		return sups;
	}
	
	

	
	
	

}
