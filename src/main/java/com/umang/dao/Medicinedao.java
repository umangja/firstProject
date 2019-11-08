package com.umang.dao;

import java.util.List;

import com.umang.model.Medicine;

public interface Medicinedao {

	public void addMedicine(Medicine med);

	public List<Medicine> getAllMedicinesId();

	public List<Medicine> getAllMedicine();

	public List<Medicine> getAllMedicineFiltered(String company, int price, int in_stock, String expiration_date);

	public List<Medicine> getAllMedicineFiltered(int price, int in_stock, String expiration_date);

	public List<Medicine> getMedicineById(int medicine_id);

	public void updateIn_stock(int medicine_id, int quantity);

	public List<Medicine> getExpiredMedicineId();

	public void updateStockToZero(List<Medicine> medicine_id);

	public List<Medicine> getMedicineByName(String med_name);

}
