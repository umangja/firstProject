package com.umang.dao;

import java.util.List;

import com.umang.model.Partner;
import com.umang.model.Patient;

public interface Partnerdao {

	void addPartner(Partner partner);

	public List<Partner> getAll();

	List<Partner> getPartnerById(int patient_id);

	List<Partner> getMostValuablePartnersSorted();

	public List<Integer> getTotalBillOfPartner(int id);
}
