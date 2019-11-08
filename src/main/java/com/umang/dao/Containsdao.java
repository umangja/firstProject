package com.umang.dao;

import java.util.List;

import com.umang.model.Contains;

public interface Containsdao {

	int isExisting(int medicine_id, String drug_name);

	void addcontain(Contains contain);

	List<Contains> getDrugsForMedicine(int id);


}
