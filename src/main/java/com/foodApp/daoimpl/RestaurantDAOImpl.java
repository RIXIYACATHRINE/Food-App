package com.foodApp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.dao.RestaurantDAO;
import com.foodApp.model.Restaurant;
import com.foodApp.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO{

	private final String INSERT_RESTAURANT = "INSERT into `restaurant` (`name`, `address`, `phoneNumber`, `cusineType`, `deliveryTime`, `admineUserId`, `rating`, `isActive`, `imagePath`) values (?, ?, ?, ? , ? , ? , ? , ? , ?)";
	private final String UPDATE_RESTAURANT = "UPDATE `restaurant` SET `name` = ?, `address` = ?, `phoneNumber` = ?, `cusineType` = ?, `deliveryTime` = ?, `admineUserId` = ?, `rating` = ?, `isActive` = ?, `imagePath` = ? WHERE `restaurantId` =?";
	private final String GET_RESTAURANT_BY_ID = "SELECT * from `restaurant` WHERE `restaurantId` = ?";
	private final String GET_ALL_RESTAURANTS = "SELECT * from `restaurant`";
	private final String DELETE_RESTAURANT = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";
	private final String SEARCH_RESTAURANTS = "SELECT * FROM `restaurant` WHERE LOWER(`name`) LIKE ? OR LOWER(`address`) LIKE ? OR LOWER(`cusineType`) LIKE ?";
	
	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_RESTAURANT);)
		{
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhoneNumber());
			prepareStatement.setString(4, restaurant.getCusineType());
			prepareStatement.setString(5, restaurant.getDeliveryTime());
			prepareStatement.setInt(6, restaurant.getAdmineUserId());
			prepareStatement.setString(7, restaurant.getRating());
			prepareStatement.setString(8, restaurant.getIsActive());
			prepareStatement.setString(9, restaurant.getImagePath());
			int executeUpdate = prepareStatement.executeUpdate();
			
			System.out.println(executeUpdate);
			System.out.println("succesfully add the restaurant to the database.....");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_RESTAURANT);)
		{
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhoneNumber());
			prepareStatement.setString(4, restaurant.getCusineType());
			prepareStatement.setString(5, restaurant.getDeliveryTime());
			prepareStatement.setInt(6, restaurant.getAdmineUserId());
			prepareStatement.setString(7, restaurant.getRating());
			prepareStatement.setString(8, restaurant.getIsActive());
			prepareStatement.setString(9, restaurant.getImagePath());
			prepareStatement.setInt(10, restaurant.getRestaurantId());
			
			int executeUpdate = prepareStatement.executeUpdate();
			
			System.out.println(executeUpdate);
			System.out.println("succesfully update the restaurant in the database.....");
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_RESTAURANT);)
		{
			prepareStatement.setInt(1, restaurantId);
			
			int executeUpdate = prepareStatement.executeUpdate();
			
			System.out.println(executeUpdate);
			System.out.println("succesfully delete the restaurant data in the database.....");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) {
		
		Restaurant restaurant = null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_RESTAURANT_BY_ID);)
		{
			prepareStatement.setInt(1, restaurantId);
			
			ResultSet resultSet = prepareStatement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt("restaurantId");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String phonenumber = resultSet.getString("phoneNumber");
				String cusinetype = resultSet.getString("cusineType");
				String deliverytime = resultSet.getString("deliveryTime");
				int admineuserid = resultSet.getInt("admineUserId");
				String rating = resultSet.getString("rating");
				String isactive = resultSet.getString("isActive");
				String imagepath = resultSet.getString("imagePath");
				
				restaurant = new Restaurant(id, name, address, phonenumber, cusinetype, deliverytime, admineuserid, rating, isactive, imagepath);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return restaurant;
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_RESTAURANTS);)
		{
			ResultSet resultSet = prepareStatement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt("restaurantId");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String phonenumber = resultSet.getString("phoneNumber");
				String cusinetype = resultSet.getString("cusineType");
				String deliverytime = resultSet.getString("deliveryTime");
				int admineuserid = resultSet.getInt("admineUserId");
				String rating = resultSet.getString("rating");
				String isactive = resultSet.getString("isActive");
				String imagepath = resultSet.getString("imagePath");
				
				Restaurant restaurant= new Restaurant(id, name, address, phonenumber, cusinetype, deliverytime, admineuserid, rating, isactive, imagepath);
				restaurants.add(restaurant);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> searchRestaurants(String query) {
		
		List<Restaurant> results = new ArrayList<>();
	    String searchTerm = "%" + query.toLowerCase() + "%";
	    
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement prepareStatement = connection.prepareStatement(SEARCH_RESTAURANTS)) {
	        
	        prepareStatement.setString(1, searchTerm);
	        prepareStatement.setString(2, searchTerm);
	        prepareStatement.setString(3, searchTerm);
	        
	        ResultSet res = prepareStatement.executeQuery();

	        while(res.next()) {
	            int id = res.getInt("restaurantId");
	            String name = res.getString("name");
	            String address = res.getString("address");
	            String phonenumber = res.getString("phoneNumber");
	            String cusinetype = res.getString("cusineType");
	            String deliverytime = res.getString("deliveryTime");
	            int admineuserid = res.getInt("admineUserId");
	            String rating = res.getString("rating");
	            String isactive = res.getString("isActive");
	            String imagepath = res.getString("imagePath");
	            
	            Restaurant r = new Restaurant(id, name, address, phonenumber, cusinetype, deliverytime, admineuserid, rating, isactive, imagepath);
	            results.add(r);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
		return results;
	}
}