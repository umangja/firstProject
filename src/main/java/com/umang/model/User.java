package com.umang.model;

import java.sql.Date;

public class User {
	
	private String username,password,firstname,lastname,gender,post,mpassword,email;
	private int income;
	private Date DOB,joining_date;
	private int lives_at,enabled;
	private int status;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	public String getMpassword() {
		return mpassword;
	}
	
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
	public int getEnabled() {
		return enabled;
	}
	
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	
	public User() {
		super();
	}
	
	public User(String username,String password) {
		super();
		this.password = password;
		this.username = username;
	}
	
	
	public Date getDOB() {
		return DOB;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getGender() {
		return gender;
	}
	
	
	public int getIncome() {
		return income;
	}
	
	
	public Date getJoining_date() {
		return joining_date;
	}
	
	
	public String getLastname() {
		return lastname;
	}
	
	
	public int getLives_at() {
		return lives_at;
	}
	
	
	
	public String getPost() {
		return post;
	}
	
	
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setIncome(int income) {
		this.income = income;
	}
	
	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setLives_at(int lives_at) {
		this.lives_at = lives_at;
	}
	
	public void setPost(String post) {
		this.post = post;
	}
	

	
	
	
	public String getPassword() {
		return password;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
