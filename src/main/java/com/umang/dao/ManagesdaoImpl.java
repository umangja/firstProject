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

import com.umang.model.User;

public class ManagesdaoImpl implements Managesdao{
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public ManagesdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public ManagesdaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void addManager(String manager, String emp) {
		 String sql = "INSERT INTO Manages(manager_id,emp_id) VALUES (?,?)";
		 jdbcTemplate.update(sql,new Object[] {manager,emp});
	}
	
	public User getManager(String emp) {
		String sql = "SELECT username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at FROM Manages,Users WHERE emp_id=? and manager_id=username";

		User user =jdbcTemplate.query(sql,new Object[] {emp}, new ResultSetExtractor< User >() {
			
			public User extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					user.setGender(rs.getString("gender"));
					user.setDOB(rs.getDate("DOB"));
					user.setJoining_date(rs.getDate("joining_date"));
					user.setPost(rs.getString("post"));
					user.setLives_at(rs.getInt("lives_at"));
					user.setIncome(rs.getInt("income"));
					return user;
				}
				return null;
			}
			
		});
		
		return user;
	}
	
	
	public List<User> getManagesEmp(String manager) {
		String sql = "SELECT username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at FROM Manages,Users where manager_id=? and emp_id=username";
		
		List<User> users =jdbcTemplate.query(sql,new Object[] {manager}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> (); 
				if(rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					user.setGender(rs.getString("gender"));
					user.setDOB(rs.getDate("DOB"));
					user.setJoining_date(rs.getDate("joining_date"));
					user.setPost(rs.getString("post"));
					user.setLives_at(rs.getInt("lives_at"));
					user.setIncome(rs.getInt("income"));
					users.add(user);
				}
				return users;
			}
			
		});
		
		return users;
	}
	
	
	
	

}
