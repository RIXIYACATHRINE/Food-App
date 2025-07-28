<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>BiteBuddy | Home</title>
    <link rel="icon" href="images/food App.jpeg">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700;900&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/home.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">

</head>
<body>
    <!-- Hero Section -->
    <section class="hero-section">
        <div class="hero-details">
            <h1>Order Your Favourite Food Online</h1>
            <p>Delicious meals delivered fast from top rated restaurants near you.<br>Explore, order, and enjoy!</p>
            <form class="search-bar" action="SearchServlet" method="get">
                <input type="text" name="searchQuery" placeholder="Search for restaurants, cuisines, or dishes..." autocomplete="off">
                <button type="submit"><i class="fa fa-search"></i> Search</button>
            </form>
        </div>
        <div class="hero-img">
            <img src="images/logo.jpeg" alt="Delicious Food">
        </div>
    </section>

    <!-- Featured Restaurants -->
    <h2 class="section-title-blue"><i class="fa fa-fire"></i> Featured Restaurants</h2>

    <div class="restaurant-list">
        <%
            java.util.List<com.foodApp.model.Restaurant> allRestaurants = (java.util.List<com.foodApp.model.Restaurant>) request.getAttribute("allRestaurants");
            if (allRestaurants != null && !allRestaurants.isEmpty()) {
                for (com.foodApp.model.Restaurant res : allRestaurants) {
        %>
        <div class="restaurant-card">
            <img class="restaurant-image" src="<%= res.getImagePath() %>" alt="<%= res.getName() %>">
            <div class="restaurant-info">
                <div class="restaurant-title"><i class="fa fa-cutlery"></i> <%= res.getName() %></div>
                <div class="restaurant-cuisine"><i class="fa fa-leaf"></i> <%= res.getCusineType() %></div>
                <div class="restaurant-rating"><i class="fa fa-star"></i> <%= res.getRating() %></div>
                <div class="restaurant-meta"><i class="fa fa-clock"></i> <%= res.getDeliveryTime() %> | 
                    <i class="fa fa-map-marker"></i> <%= res.getAddress() %>
                </div><br>
                <a href="menu?restaurantId=<%= res.getRestaurantId() %>" class="add-cart-btn">
                    <i class="fa fa-list-alt"></i> View Menu
                </a>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div style="grid-column: 1/-1; text-align: center; font-size: 1.2em; color: #888;">
            No restaurants found.
        </div>
        <% } %>
    </div>
</body>
</html>