package com.umang.dao;

import java.util.List;

import com.umang.model.Partner;
import com.umang.model.Patient;
import com.umang.model.Supplier;

public interface Supplierdao {

	public List<Supplier> getAllSup();

	public int addSupplier(Supplier sup);

	public List<Supplier> getSupplierForMedicine(int medicine_id);

	public void setAddress(String parameter, int add_id);


}
