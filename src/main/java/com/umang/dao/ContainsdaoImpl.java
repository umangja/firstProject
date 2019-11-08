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

import com.umang.model.Contains;

public class ContainsdaoImpl implements Containsdao{
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public ContainsdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public ContainsdaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void addcontain(Contains contain) {
		String sql = "insert into Contains values(?,?,?)";
		jdbcTemplate.update(sql,new Object[] {contain.getMedicine_id(),contain.getDrug_name(),contain.getPercentage()});
	}
	public int isExisting(int medicine_id, String drug_name) {
		String sql = "select count(*) as cnt from Contains where medicine_id=? and drug_name=?";
		int ans = jdbcTemplate.query(sql,new Object[] {medicine_id,drug_name},new ResultSetExtractor<Integer>() {
			
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {;
					return rs.getInt("cnt");
				}
				return 0;
			}
			
		});
		return ans;
	}
	
	
	public List<Contains> getDrugsForMedicine(int id) {
		String sql = "select drug_name,percentage from Contains where medicine_id=?";
		List<Contains> drugs = jdbcTemplate.query(sql,new Object[] {id},new ResultSetExtractor<List<Contains>>() {
			
			public List<Contains> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Contains> drugs = new ArrayList<Contains> ();
				while(rs.next()) {
					Contains drug = new Contains();
					drug.setDrug_name(rs.getString("drug_name"));
					drug.setPercentage(rs.getInt("percentage"));
					drugs.add(drug);
				}
				return drugs;
			}
			
		});
		return drugs;
	}
	
	
}
