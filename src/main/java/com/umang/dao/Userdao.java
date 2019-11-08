package com.umang.dao;

import java.util.List;

import javax.validation.Valid;

import com.umang.model.User;

public interface Userdao {
	
	public void registerEmp(User user);
	public void delete(String username);
	public User getUser(String username);
	public List<User> getAllEmp();
	public List<User> getEmpSortedByIncomeAndJoining();
	public List<User> getEmpSortedByIncome();
	public List<User> getEmpSortedByJoining();
	public void updateEmpHimself(User user);
	public void setAddress(String name, int add_id);
	public List<User> getCurrentEmp(String username);
	public List<User> getCurrentUser(String username);
	public void updateEmp(User user);
	public void registerUser(@Valid User user);
	public int checkEmailAndUsername(String parameter, String parameter2);
	public void updateToken(String parameter, String tokken);
	public User getUserByToken(String token);
	public void updatePassword(String username, String encode);

}
