package com.foodApp.servlet;


import java.io.IOException;
import java.util.List;

import com.foodApp.daoimpl.MenuDAOImpl;
import com.foodApp.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hi from menu servelt...");
		
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		
		List<Menu> menuByRestaurantId = menuDAOImpl.getMenuByRestaurantId(restaurantId);
		
		
//		for(Menu menu : menuByRestaurantId) {
//			 System.out.println(menu);
//		}
			
		req.setAttribute("menuByRestaurantId", menuByRestaurantId);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("menu.jsp");
			
		requestDispatcher.forward(req, resp);
	}
}