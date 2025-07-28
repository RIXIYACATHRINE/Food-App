package com.foodApp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.foodApp.dao.UserDAO;
import com.foodApp.model.User;
import com.foodApp.util.DBConnection;

public class UserDAOImpl implements UserDAO{

	private final String INSERT_USERS = "INSERT into `user` (`name`, `userName`, `password`, `email`, `phoneNumber`, `address`, `role`, `createdDate`, `lastLoginDate`) values (?,?,?,?,?,?,?,?,?)";
	private final String UPDATE_USERS = "UPDATE `user` SET `name` = ?, `userName` = ?, `password` = ?, `email` = ?, `phoneNumber` = ?, `address` = ?, `role` = ? WHERE `userId` = ?";
	private final String GET_USER_BY_ID = "SELECT * from `user` WHERE `userId` = ?";
	private final String GET_USER_BY_EMAIL_ID = "SELECT * from `user` WHERE `email` = ?";
	private final String GET_ALL_USERS = "SELECT * from `user`";
	private final String DELETE_USERS = "DELETE from `user` WHERE `userId` = ?";

	@Override
	public void addUser(User user) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USERS);)
		{
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUserName());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhoneNumber());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			prepareStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			prepareStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

			int executeUpdate = prepareStatement.executeUpdate();

			System.out.println(executeUpdate);
			
			System.out.println("A new user has been added to the database...");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User user) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_USERS);)
		{
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUserName());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhoneNumber());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			prepareStatement.setInt(8, user.getUserId());
			int executeUpdate = prepareStatement.executeUpdate();

			System.out.println(executeUpdate);
			
			System.out.println("A new user data has been updated in the database...");
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteUser(int userId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USERS);)
		{
			prepareStatement.setInt(1, userId);

			int executeUpdate = prepareStatement.executeUpdate();

			System.out.println(executeUpdate);
			
			System.out.println("Succesfuuly user data has been deleted in the database...");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {

		List<User> listusers = new ArrayList<User>();
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_USERS);)
		{
			ResultSet resultSet = prepareStatement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String username = resultSet.getString("userName");
				String password = resultSet.getString("password");
				String email = resultSet.getString("email");
				String phonenumber = resultSet.getString("phoneNumber");
				String address = resultSet.getString("address");
				String role = resultSet.getString("role");
				Timestamp createddate = resultSet.getTimestamp("createdDate");
				Timestamp lastlogindate = resultSet.getTimestamp("lastLoginLate");

				User user = new User(id, name, username, password, email, phonenumber, address, role, createddate, lastlogindate);
				listusers.add(user);
				
				System.out.println("Succesfully fecth the all users from the database...");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listusers;
	}

	@Override
	public User getUserById(int userId) {

		User user = null;

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_ID);)
		{
			prepareStatement.setInt(1, userId);

			ResultSet resultSet = prepareStatement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String username = resultSet.getString("userName");
				String password = resultSet.getString("password");
				String email = resultSet.getString("email");
				String phonenumber = resultSet.getString("phoneNumber");
				String address = resultSet.getString("address");
				String role = resultSet.getString("role");
				Timestamp createddate = resultSet.getTimestamp("createdDate");
				Timestamp lastlogindate = resultSet.getTimestamp("lastLoginDate");

				user = new User(id, name, username, password, email, phonenumber, address, role, createddate, lastlogindate);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUserByEmailId(String email) {
		
		User user = null;

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_EMAIL_ID);)
		{
			prepareStatement.setString(1, email);

			ResultSet res = prepareStatement.executeQuery();

			while(res.next()) {
				int id = res.getInt("userId");
				String name = res.getString("name");
				String username = res.getString("userName");
				String password = res.getString("password");
				String emailId = res.getString("email");
				String phonenumber = res.getString("phoneNumber");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createddate = res.getTimestamp("createdDate");
				Timestamp lastlogindate = res.getTimestamp("lastLoginDate");

				user = new User(id, name, username, password, emailId, phonenumber, address, role, createddate, lastlogindate);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
}