package com.foodApp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.dao.OrderDAO;
import com.foodApp.model.Order;
import com.foodApp.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private final String INSERT_ORDER = "INSERT into `order` (`restaurantId`, `userId`, `orderDate`, `totalAmount`, `status`, `paymentMode`) values (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_ORDER = "UPDATE `order` SET `restaurantId` = ?, `userId` = ?, `orderDate` = ?, `totalAmount` = ?, `status` = ?, `paymentMode` = ? WHERE `orderId` = ?";
    private final String GET_ORDER_BY_ID = "SELECT * from `order` WHERE `orderId` = ?";
    private final String GET_ALL_ORDERS = "SELECT * from `order`";
    private final String DELETE_ORDER = "DELETE from `order` WHERE `orderId` = ?";
    private final String GET_ORDERS_BY_USER_ID = "SELECT * from `order` WHERE `userId` = ? ORDER BY `orderDate` DESC";

    @Override
    public int addOrder(Order order) {
        int orderId = 0;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);) {
            prepareStatement.setInt(1, order.getRestaurantId());
            prepareStatement.setInt(2, order.getUserId());
            prepareStatement.setTimestamp(3, order.getOrderDate());
            prepareStatement.setInt(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPaymentMode());

            int i = prepareStatement.executeUpdate();

            if (i == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            ResultSet id = prepareStatement.getGeneratedKeys();

            if (id.next()) {
                orderId = id.getInt(1);
            }

            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDER);) {
            prepareStatement.setInt(1, order.getRestaurantId());
            prepareStatement.setInt(2, order.getUserId());
            prepareStatement.setTimestamp(3, order.getOrderDate());
            prepareStatement.setInt(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPaymentMode());
            prepareStatement.setInt(7, order.getOrderId());

            int i = prepareStatement.executeUpdate();

            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDER);) {
            prepareStatement.setInt(1, orderId);
            int i = prepareStatement.executeUpdate();
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDER_BY_ID);) {
            prepareStatement.setInt(1, orderId);
            ResultSet res = prepareStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("orderId");
                int restaurantid = res.getInt("restaurantId");
                int userid = res.getInt("userId");
                Timestamp orderdate = res.getTimestamp("orderDate");
                int totalamount = res.getInt("totalAmount");
                String status = res.getString("status");
                String paymentmode = res.getString("paymentMode");
                order = new Order(id, restaurantid, userid, orderdate, totalamount, status, paymentmode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_ORDERS);) {
            ResultSet res = prepareStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("orderId");
                int restaurantid = res.getInt("restaurantId");
                int userid = res.getInt("userId");
                Timestamp orderdate = res.getTimestamp("orderDate");
                int totalamount = res.getInt("totalAmount");
                String status = res.getString("status");
                String paymentmode = res.getString("paymentMode");
                Order ord = new Order(id, restaurantid, userid, orderdate, totalamount, status, paymentmode);
                orders.add(ord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<Order>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDERS_BY_USER_ID);) {
            prepareStatement.setInt(1, userId);
            ResultSet res = prepareStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("orderId");
                int restaurantid = res.getInt("restaurantId");
                int userid = res.getInt("userId");
                Timestamp orderdate = res.getTimestamp("orderDate");
                int totalamount = res.getInt("totalAmount");
                String status = res.getString("status");
                String paymentmode = res.getString("paymentMode");
                Order ord = new Order(id, restaurantid, userid, orderdate, totalamount, status, paymentmode);
                orders.add(ord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}