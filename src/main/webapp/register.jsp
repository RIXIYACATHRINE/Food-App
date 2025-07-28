<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>BiteBuddy | Register</title>
    <link rel="icon" href="images/logo.jpeg">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@600;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="form-container">
        <div class="form-title"><i class="fa fa-user-plus"></i> Register</div>
        <form action="user-registration" method="post">
            <%
                String error = (String) request.getAttribute("error");
                String success = (String) request.getAttribute("success");
                if (error != null) {
            %>
                <div class="error-message"><%= error %></div>
            <% } else if (success != null) { %>
                <div class="success-message"><%= success %></div>
            <% } %>
            <div class="form-group">
                <label class="form-label" for="name">Name</label>
                <input type="text" name="name" id="name" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="username">Username</label>
                <input type="text" name="username" id="username" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="email">Email</label>
                <input type="email" name="email" id="email" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="password">Password</label>
                <input type="password" name="password" id="password" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="phonenumber">Phone Number</label>
                <input type="text" name="phonenumber" id="phonenumber" class="form-input" required pattern="[0-9]{10,}">
            </div>
            <div class="form-group">
                <label class="form-label" for="address">Address</label>
                <input type="text" name="address" id="address" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="role">Select Role</label>
                <select name="role" id="role" class="form-select" required>
                    <option value="Customer">Customer</option>
                    <option value="RestaurantAdmin">Restaurant Admin</option>
                    <option value="DeliveryAgent">Delivery Agent</option>
                    <option value="SuperAdmin">Super Admin</option>
                </select>
            </div>
            <button type="submit" class="form-btn"><i class="fa fa-user-plus"></i> Register</button>
        </form>
        <div class="form-alt-link">
            Already have an account? <a href="login.jsp">Login</a>
        </div>
    </div>
</body>
</html>
