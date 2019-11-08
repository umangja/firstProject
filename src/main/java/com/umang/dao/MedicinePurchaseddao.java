package com.umang.dao;

import java.util.List;

import com.umang.model.Medicine_purchased;

public interface MedicinePurchaseddao {

	void add(List<Medicine_purchased> medicinePurchased);

	List<Medicine_purchased> getMedicinesForBill(int bill_id);

}
