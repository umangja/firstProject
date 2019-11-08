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

import com.umang.model.Feedback;

public class FeedbackdaoImpl implements Feedbackdao {
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public FeedbackdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public FeedbackdaoImpl() {
	}
	
	@Override
	public void add(Feedback fb) {
		String sql = "insert into Feedbacks(partner_id,feedback) values(?,?)";
		jdbcTemplate.update(sql,new Object[] {fb.getPartner_id(),fb.getFeedback()});	
	}
	
	@Override
	public List<Feedback> getAllIncompletedFeedback() {
		String sql = "select * from Feedbacks where is_completed=0";
		List<Feedback> fbs = jdbcTemplate.query(sql,new Object[] {},new ResultSetExtractor< List<Feedback> >() {
			
			public List<Feedback> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Feedback> meds = new ArrayList<Feedback> ();
				while(rs.next()) {;
				    Feedback med = new Feedback();
					med.setFeedback(rs.getString("feedback"));
					med.setId(rs.getInt("id"));
					med.setPartner_id(rs.getInt("partner_id"));
					meds.add(med);
				}
				return meds;
			}
			
		});
		return fbs;
	}
	
	@Override
	public void setComplete(int id) {
		String sql = "update Feedbacks set is_completed=1 where id=?";
		jdbcTemplate.update(sql,new Object[] {id});	
		
	}
	
	
	


}
