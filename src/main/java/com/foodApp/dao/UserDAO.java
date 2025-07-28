package com.foodApp.dao;
import java.util.List;

import com.foodApp.model.User;

public interface UserDAO {
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(int userid);
	User getUserById(int userid);
	User getUserByEmailId(String emailid);
	List<User> getAllUsers();
}