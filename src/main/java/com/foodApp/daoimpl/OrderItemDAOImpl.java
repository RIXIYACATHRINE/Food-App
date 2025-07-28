package com.foodApp.daoimpl;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.dao.OrderItemDAO;
import com.foodApp.model.OrderItem;
import com.foodApp.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO{

	private final String INSERT_ORDER_ITEM= "INSERT into `orderitem` (`orderId`, `menuId`, `quantity`, `totalAmount`) values (?, ?, ?, ?)";
	private final String UPDATE_ORDER_ITEM = "UPDATE `orderitem` SET `orderId` = ?, `menuId` = ?, `quantity` = ?, `totalAmount` = ? WHERE `orderItemId` = ?";
	private final String GET_ORDERITEM_BY_ID = "SELECT * from `orderitem` WHERE `orderItemId` = ?";
	private final String GET_ALL_ORDER_ITEMS = "SELECT * from `orderitem`";
	private final String DELETE_ORDER_ITEM = "DELETE from `orderitem` WHERE `orderItemId` = ?";

	@Override
	public void addOrderItem(OrderItem orderitem) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDER_ITEM);)
		{

			prepareStatement.setInt(1, orderitem.getOrderId());
			prepareStatement.setInt(2, orderitem.getMenuId());
			prepareStatement.setInt(3, orderitem.getQuantity());
			prepareStatement.setInt(4, orderitem.getTotalAmount());

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateOrderItem(OrderItem orderitem) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDER_ITEM);)
		{

			prepareStatement.setInt(1, orderitem.getOrderId());
			prepareStatement.setInt(2, orderitem.getMenuId());
			prepareStatement.setInt(3, orderitem.getQuantity());
			prepareStatement.setInt(4, orderitem.getTotalAmount());
			prepareStatement.setInt(5, orderitem.getOrderItemId());

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int orderItemId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDER_ITEM);)
		{

			prepareStatement.setInt(1, orderItemId);

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItemById(int orderItemId) {
		
		OrderItem orderitem = null;
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDERITEM_BY_ID);)
		{

			prepareStatement.setInt(1, orderItemId);

			ResultSet res = prepareStatement.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("orderItemId");
				int orderid = res.getInt("orderId");
				int menuid = res.getInt("menuId");
				int quantity = res.getInt("quantity");
				int totalamount = res.getInt("totalAmount");
				
				orderitem = new OrderItem(id, orderid, menuid, quantity, totalamount);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return orderitem;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		
		List<OrderItem> orderitems = new ArrayList<OrderItem>();
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_ORDER_ITEMS);)
		{
			ResultSet res = prepareStatement.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("orderItemId");
				int orderid = res.getInt("orderId");
				int menuid = res.getInt("menuId");
				int quantity = res.getInt("quantity");
				int totalamount = res.getInt("totalAmount");
				
				OrderItem ord_item = new OrderItem(id, orderid, menuid, quantity, totalamount);
				orderitems.add(ord_item);
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return orderitems;
	}

}