package com.foodApp.servlet;




import java.io.IOException;
import java.util.List;

import com.foodApp.daoimpl.RestaurantDAOImpl;
import com.foodApp.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("Hi from home servlet");
			
			RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
			
			List<Restaurant> allRestaurants = restaurantDAOImpl.getAllRestaurants();
			
			
//			for(Restaurant restaurant : allRestaurants) {
//				System.out.println(restaurant);
//			}
			
			req.setAttribute("allRestaurants", allRestaurants);
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
			
			requestDispatcher.forward(req, resp);
	}
}
