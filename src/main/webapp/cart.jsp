<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.foodApp.model.Cart" %>
<%@ page import="com.foodApp.model.CartItem" %>
<%@ page import="com.foodApp.model.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <title>BiteBuddy | Cart</title>
    <link rel="icon" href="images/logo.jpeg">
    <link rel="stylesheet" href="css/cart.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700;900&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    
</head>
<body>
    <div class="cart-container">
        <div class="cart-title"><i class="fa fa-shopping-cart"></i> Your Cart</div>
        <%
            Cart cart = (Cart)session.getAttribute("cart");
            if (cart != null && !cart.getItems().isEmpty()) {
        %>
        <form action="cart" method="post">
            <table class="cart-table">
                <tr>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th></th>
                </tr>
                <%
                    for (CartItem item : cart.getItems().values()) {
                %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><i class="fa fa-inr"></i> <%= item.getPrice() %></td>
                    <td>
                        <form action="cart" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <input type="number" min="1" name="quantity" value="<%= item.getQuantity() %>" onchange="this.form.submit()">
                        </form>
                    </td>
                    <td><i class="fa fa-inr"></i> <%= item.getTotalPrice() %></td>
                    <td>
                        <form action="cart" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <button type="submit" class="remove-btn" title="Remove"><i class="fa fa-trash"></i></button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
        </form>
        <div class="cart-footer">
            <span>Grand Total: <i class="fa fa-inr"></i> <%= cart.getGrandTotal() %></span>
            <form action="checkout" method="post" style="display:inline;">
                <select name="payment" class="form-select">
                    <option value="COD">Cash on Delivery</option>
                    <option value="Card">Card</option>
                    <option value="UPI">UPI</option>
                </select>
                <button type="submit" class="checkout-btn"><i class="fa fa-credit-card"></i> Checkout</button>
            </form>
        </div>
        <% } else { %>
            <div class="cart-empty">
                Your cart is empty. <a href="home">Browse restaurants</a>
            </div>
        <% } %>
    </div>
</body>
</html>