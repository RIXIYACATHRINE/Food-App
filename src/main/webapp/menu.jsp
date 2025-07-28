<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>BiteBuddy | Menu</title>
    <link rel="icon" href="images/logo.jpeg">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@600;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="css/menu.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">

</head>
<body>
    <div class="header">
        <div class="header-title"><i class="fa fa-utensils"></i> BiteBuddy </div>
        <div class="header-sub">Discover & Order Delicious Food</div>
    </div>
    <div class="menu-list">
        <%
            java.util.List<com.foodApp.model.Menu> menuList = (java.util.List<com.foodApp.model.Menu>)request.getAttribute("menuByRestaurantId");
            Integer restaurantId = null;
            if (menuList != null && !menuList.isEmpty()) {
                restaurantId = menuList.get(0).getRestaurantId();
                for (com.foodApp.model.Menu menu : menuList) {
        %>
        <div class="menu-card">
            <img class="menu-image" src="<%= menu.getImagePath() %>" alt="<%= menu.getItemName() %>">
            <div class="menu-title"><i class="fa fa-utensils"></i> <%= menu.getItemName() %></div>
            <div class="menu-desc"><%= menu.getDescription() %></div>
            <div class="menu-meta">
                <span class="menu-price"><i class="fa fa-inr"></i> <%= menu.getPrice() %></span>
                <span class="menu-rating"><i class="fa fa-star"></i> <%= menu.getRatings() %></span>
            </div>
            <div class="menu-availability" style="color:<%= menu.getIsAvailable().equalsIgnoreCase("yes") ? "#2ecc40" : "#d10000" %>;background:<%= menu.getIsAvailable().equalsIgnoreCase("yes") ? "#e9f8ef" : "#ffeaea" %>;">
                <i class="fa fa-check-circle"></i> <%= menu.getIsAvailable().equalsIgnoreCase("yes") ? "Available" : "Not Available" %>
            </div>
            <form action="cart" method="post" style="margin-top:1em;">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                <input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId() %>">
                <div class="quantity-selector">
                    <button type="button" class="quantity-btn" onclick="var q=this.parentNode.querySelector('.quantity-input'); if(q.value>1) q.value--;">-</button>
                    <input type="number" min="1" name="quantity" value="1" class="quantity-input" readonly>
                    <button type="button" class="quantity-btn" onclick="var q=this.parentNode.querySelector('.quantity-input'); q.value++;">+</button>
                </div>
                <button type="submit" class="add-cart-btn"><i class="fa fa-cart-plus"></i> Add to Cart</button>
            </form>
        </div>
        <%
                }
            } else {
        %>
            <div style="grid-column:1/-1;text-align:center;font-size:1.15em;color:#888;">No menu items found.</div>
        <% } %>
    </div>
    <script>
        // Prevent form submission on +/- button click
        document.querySelectorAll('.quantity-btn').forEach(function(btn){
            btn.addEventListener('click', function(e){
                e.preventDefault();
            });
        });
    </script>
</body>
</html>