package com.foodApp.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.foodApp.dao.OrderDAO;
import com.foodApp.daoimpl.OrderDAOImpl;
import com.foodApp.daoimpl.OrderItemDAOImpl;
import com.foodApp.model.Cart;
import com.foodApp.model.CartItem;
import com.foodApp.model.Order;
import com.foodApp.model.OrderItem;
import com.foodApp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	
	private OrderDAO orderDAO;
	
	@Override
	public void init() throws ServletException {
		orderDAO = new OrderDAOImpl();
	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		

		
		if(cart!=null && user!=null && !cart.getItems().isEmpty()) {
			
			String paymentmode = req.getParameter("payment");
			String address = (String)session.getAttribute("userAddress");
			
			

			Order order = new Order();
			order.setUserId(user.getUserId());
			order.setRestaurantId( (int)session.getAttribute("restaurantId") );
			order.setOrderDate(new Timestamp(System.currentTimeMillis()));
			order.setPaymentMode(paymentmode);
			order.setStatus("Confirmed");
			order.setTotalAmount((int)cart.getGrandTotal());
			
			
		
			int totalAmount=0;
			for(CartItem item : cart.getItems().values())
			{
				totalAmount += item.getPrice() * item.getQuantity();
			}
			
			order.setTotalAmount(totalAmount);
			
			
			
			int orderid = orderDAO.addOrder(order);
			
			for(CartItem item : cart.getItems().values()) 
			{
				int itemId = item.getItemId();
				int quantity = item.getQuantity();
				int totalPrice = (int)item.getTotalPrice();
				
				OrderItem orderitem = new OrderItem(orderid, itemId, quantity, totalPrice);
				
				OrderItemDAOImpl orderI;
				orderI = new OrderItemDAOImpl();
				orderI.addOrderItem(orderitem);
			}
			
			session.removeAttribute("cart"); 
			session.setAttribute("order", order);
			
			resp.sendRedirect("order_confirmation.jsp");
		}
		else {
			resp.sendRedirect("cart.jsp"); 
		}
	}
}