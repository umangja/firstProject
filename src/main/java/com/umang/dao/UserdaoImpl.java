package com.umang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.umang.model.User;

@Repository("Userdao")
public class UserdaoImpl implements Userdao {

	@Autowired
	DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserdaoImpl() {
		
	}
	
	public UserdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public void delete(String username) {
		String sql = "update Users set status=0 WHERE username=? and status=1";
		jdbcTemplate.update(sql,username);
	}
	
	
	public User getUser(String username) {
		String sql = "SELECT * FROM Users WHERE status=1 and username='"+username+"'";
		return jdbcTemplate.query(sql,new ResultSetExtractor<User>() {
		
		public User extractData(ResultSet rs) throws SQLException,DataAccessException{
			if(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			return null;
		}
		
	});
		}
	
	public void registerEmp(User user) {
		 String sql = "INSERT INTO Users(username, password,firstname,lastname,gender,DOB,post,income,joining_date,email) VALUES (?,?,?,?,?,?,?,?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),user.getPassword(),user.getFirstname(),user.getLastname(),user.getGender(),user.getDOB(),user.getPost(),user.getIncome(),user.getJoining_date(),user.getEmail()});
		 sql = "INSERT INTO user_roles(user,role) VALUES(?,?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),"ROLE_EMP"});
		
	}
	
	public void registerUser(User user) {
		 String sql = "INSERT INTO Users(username, password,firstname,lastname,gender,DOB,post,income,joining_date) VALUES (?,?,?,?,?,?,?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),user.getPassword(),user.getFirstname(),user.getLastname(),user.getGender(),user.getDOB(),user.getPost(),user.getIncome(),user.getJoining_date()});
		 sql = "INSERT INTO user_roles(user,role) VALUES(?,?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),"ROLE_USER"});
		
	}
	
	public List<User> getAllEmp() {
		String sql = "select username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at from Users,user_roles where Users.username=user_roles.user and role=? and status=1";
		@SuppressWarnings("unchecked")
		List<User> users =jdbcTemplate.query(sql,new Object[] {"ROLE_EMP"}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> ();
				while(rs.next()) {
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
	
	
	public List<User> getEmpSortedByIncome() {
		String sql = "select username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at from Users,user_roles where Users.username=user_roles.user and role=? and status=1 order by income desc";
		@SuppressWarnings("unchecked")
		List<User> users =jdbcTemplate.query(sql,new Object[] {"ROLE_EMP"}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> ();
				while(rs.next()) {
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
	
	public List<User> getEmpSortedByIncomeAndJoining() {
		String sql = "select username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at from Users,user_roles where Users.username=user_roles.user and role=? and and status=1 order by income,joining_date";
		@SuppressWarnings("unchecked")
		List<User> users =jdbcTemplate.query(sql,new Object[] {"ROLE_EMP"}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> ();
				while(rs.next()) {
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
	
	public List<User> getEmpSortedByJoining() {
		String sql = "select username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at from Users,user_roles where Users.username=user_roles.user and role=? and and status=1 order by joining_date ";
		@SuppressWarnings("unchecked")
		List<User> users =jdbcTemplate.query(sql,new Object[] {"ROLE_EMP"}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> ();
				while(rs.next()) {
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
	
	
	
	public void updateEmpHimself(User user) {
		 String sql = "UPDATE Users set firstname=?,lastname=?,gender=?,DOB=? where username=? and status=1"; 
		 jdbcTemplate.update(sql,new Object[] {user.getFirstname(),user.getLastname(),user.getGender(),user.getDOB(),user.getUsername()});		
	}
	
	public void setAddress(String name, int add_id) {
		String sql = "update Users set lives_at = ? where username=? and status=1";
		jdbcTemplate.update(sql,new Object[] {add_id,name});
	}
	
	
	public List<User> getCurrentEmp(String username) {
		String sql = "select username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at from Users,user_roles where Users.username=? and Users.username=user_roles.user and (role=? or role=?)";
		List<User> users =jdbcTemplate.query(sql,new Object[] {username,"ROLE_EMP","ROLE_ADMIN"}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> ();
				while(rs.next()) {
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
	
	
	public List<User> getCurrentUser(String username) {
		String sql = "select username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at from Users,user_roles where Users.username=? and Users.username=user_roles.user and role=?";
		List<User> users =jdbcTemplate.query(sql,new Object[] {username,"ROLE_USER"}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> ();
				while(rs.next()) {
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
	
	
	public void updateEmp(User user) {
		 String sql = "update Users set firstname=? , lastname=? ,DOB =?, post=?,income=?,joining_date=? where username=? and status=1";
		 jdbcTemplate.update(sql,new Object[] {user.getFirstname(),user.getLastname(),user.getDOB(),user.getPost(),user.getIncome(),user.getJoining_date(),user.getUsername()});
	}
	
	
	
	@Override
	public int checkEmailAndUsername(String parameter, String parameter2) {
		String sql = "select username from Users where username=? and email=? and status=1";
		@SuppressWarnings("unchecked")
		
		List<Integer> something =jdbcTemplate.query(sql,new Object[] {parameter2,parameter}, new ResultSetExtractor< List<Integer> >() {
			
			public List<Integer> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Integer> users = new ArrayList<Integer> ();
				while(rs.next()) {					
					users.add(1);
				}
				return users;
			}
			
		});
		return something.size();
	}
	
	
	@Override
	public void updateToken(String parameter, String tokken) {
		String sql = "update Users set token=? where username=? and status=1";
		 jdbcTemplate.update(sql,new Object[] {tokken,parameter});
	}
	
	
	@Override
	public User getUserByToken(String token) {
		String sql = "select username,firstname,lastname,gender,DOB,post,income,joining_date,lives_at from Users where token=?";
		List<User> users =jdbcTemplate.query(sql,new Object[] {token}, new ResultSetExtractor< List<User> >() {
			
			public List<User> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<User> users = new ArrayList<User> ();
				while(rs.next()) {
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
		if(users!=null && users.size()>0)
		{
			return users.get(0);
		}
		else
		{
			return null;
		}
	
		
	}
	
	@Override
	public void updatePassword(String username, String encode) {
		String sql = "update Users set password=?,token=123 where username=? and status=1";
		 jdbcTemplate.update(sql,new Object[] {encode,username});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
