package com.umang.dao;

import com.umang.model.Address;

public interface Addressdao {
	
	public Address getAddressById(int id);

	public int updateAddress(Address add);
	
}
