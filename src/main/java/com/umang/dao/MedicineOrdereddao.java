package com.umang.dao;

import java.util.List;

import com.umang.model.Medicine_ordered;

public interface MedicineOrdereddao  {

	void add(List<Medicine_ordered> medicineOrdered);

	List<Medicine_ordered> getMedicinesForOrder(int order_id);

	void updateToComplete(int order_id);

}
