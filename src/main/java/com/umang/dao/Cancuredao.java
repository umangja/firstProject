package com.umang.dao;

import java.util.List;

import com.umang.model.Cancure;
import com.umang.model.Disease;
import com.umang.model.Medicine;

public interface Cancuredao {

	void addCancure(Cancure cancure);

	int isExisting(String parameter, String parameter2);

	List<Disease> getDiseasesForMedicine(int id);

	List<Medicine> getMedicineThatCanCure(int med_id);

}
