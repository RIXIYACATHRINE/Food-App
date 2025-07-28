package com.foodApp.dao;
import java.util.List;

import com.foodApp.model.OrderItem;

public interface OrderItemDAO {
	void addOrderItem(OrderItem orderitem);
	void updateOrderItem(OrderItem orderitem);
	void deleteOrderItem(int orderitemid);
	OrderItem getOrderItemById(int orderitemid);
	List<OrderItem> getAllOrderItems();
}