package com.umang.dao;


import java.util.List;

import com.umang.model.Orders;

public interface Ordersdao {

	public int add(Orders orders);

	public List<Orders> getIncompleteOrders();
}
