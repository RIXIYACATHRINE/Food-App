package com.foodApp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.dao.MenuDAO;
import com.foodApp.model.Menu;
import com.foodApp.util.DBConnection;

public class MenuDAOImpl implements MenuDAO{

	private final String INSERT_MENU = "INSERT into `menu` (`restaurantId`, `itemName`, `description`, `price`, `isAvailable`, `ratings`, `imagePath`) values (?, ?, ?, ?, ?, ?, ?)";
	private final String UPDATE_MENU = "UPDATE `menu` SET `restaurantId` = ?, `itemName` = ?, `description` = ?, `price` = ?, `isAvailable` = ?, `ratings` = ?, `imagePath` = ? WHERE `menuId` = ?";
	private final String DELETE_MENU = "DELETE FROM `menu` WHERE `menuId` = ?";
	private final String GET_MENU_BY_MENU_ID = "SELECT * from `menu` WHERE `menuId` = ?";
	private final String GET_MENU_BY_RESTAURANT_ID = "SELECT * from `menu` WHERE `restaurantId` = ?";
	private final String GET_ALL_MENU = "SELECT * from `menu`";
	
	
	@Override
	public void addMenu(Menu menu) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_MENU);)
		{
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setString(3, menu.getDescription());
			prepareStatement.setInt(4, menu.getPrice());
			prepareStatement.setString(5, menu.getIsAvailable());
			prepareStatement.setFloat(6, menu.getRatings());
			prepareStatement.setString(7, menu.getImagePath());
			
			int i = prepareStatement.executeUpdate();
			
			System.out.println(i);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateMenu(Menu menu) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_MENU);)
		{
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setString(3, menu.getDescription());
			prepareStatement.setInt(4, menu.getPrice());
			prepareStatement.setString(5, menu.getIsAvailable());
			prepareStatement.setFloat(6, menu.getRatings());
			prepareStatement.setString(7, menu.getImagePath());
			prepareStatement.setInt(8, menu.getMenuId());
			int i = prepareStatement.executeUpdate();
			
			System.out.println(i);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int menuId) {
	
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_MENU);)
		{
			prepareStatement.setInt(1, menuId);
			
			int i = prepareStatement.executeUpdate();
			
			System.out.println(i);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {
		
		List<Menu> menu = new ArrayList<Menu>();
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_MENU_BY_RESTAURANT_ID);)
		{
			prepareStatement.setInt(1, restaurantId);
			
			ResultSet res = prepareStatement.executeQuery();
			
			while(res.next()) {
				int menuId = res.getInt("menuId");
				int id = res.getInt("restaurantId");
				String itemName = res.getString("itemName");
				String description = res.getString("description");
				int price = res.getInt("price");
				String isAvailable = res.getString("isAvailable");
				float ratings = res.getFloat("ratings");
				String imagePath = res.getString("imagePath");
				
				Menu m = new Menu(menuId, id, itemName, description, price, isAvailable, ratings, imagePath);
				menu.add(m);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public Menu getMenuById(int menuId) {
		
		Menu menu = null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_MENU_BY_MENU_ID);)
		{
			prepareStatement.setInt(1, menuId);
			
			ResultSet res = prepareStatement.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("menuId");
				int restaurantid = res.getInt("restaurantId");
				String itemname = res.getString("itemName");
				String description = res.getString("description");
				int price = res.getInt("price");
				String isavailable = res.getString("isAvailable");
				float ratings = res.getFloat("ratings");
				String imagepath = res.getString("imagePath");
				
				menu = new Menu(id, restaurantid, itemname, description, price, isavailable, ratings, imagepath);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public List<Menu> getAllMenu() {
		
		List<Menu> menu = new ArrayList<Menu>();
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_MENU);)
		{			
			ResultSet res = prepareStatement.executeQuery();
			
			while(res.next()) {
				int menuid = res.getInt("menuId");
				int restaurantid = res.getInt("restaurantId");
				String itemname = res.getString("itemName");
				String description = res.getString("description");
				int price = res.getInt("price");
				String isavailable = res.getString("isAvailable");
				float ratings = res.getFloat("ratings");
				String imagepath = res.getString("imagePath");
				
				Menu m = new Menu(menuid, restaurantid, itemname, description, price, isavailable, ratings, imagepath);
				menu.add(m);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
		return menu;
	}
}