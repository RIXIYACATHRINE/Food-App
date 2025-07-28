package com.foodApp.dao;
import java.util.List;

import com.foodApp.model.Menu;

public interface MenuDAO {
	void addMenu(Menu menu);
	void updateMenu(Menu menu);
	void deleteMenu(int menuid);
	Menu getMenuById(int menuid);
	List<Menu> getMenuByRestaurantId(int restaurantid);
	List<Menu> getAllMenu();
}