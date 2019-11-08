package com.umang.dao;

import java.util.List;

import com.umang.model.User;

public interface Managesdao {
	public void addManager(String manager,String emp);
	public User getManager(String emp);
	public List< User > getManagesEmp(String manager);

}
