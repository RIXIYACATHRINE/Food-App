package com.foodApp.dao;
import java.util.List;

import com.foodApp.model.Restaurant;

public interface RestaurantDAO {
	void addRestaurant(Restaurant restaurant);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantid);
	Restaurant getRestaurantById(int restaurantid);
	List<Restaurant> getAllRestaurants();
	List<Restaurant> searchRestaurants(String query);	
}