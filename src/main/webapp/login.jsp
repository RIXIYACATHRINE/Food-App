<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>BiteBuddy | Login</title>
    <link rel="icon" href="images/logo.jpeg">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@600;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="css/login.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    
</head>
<body>
    <div class="login-header">
        <img src="images/logo.jpeg" class="login-logo" alt="Foodie Logo">
        <div class="login-title">BiteBuddy</div>
    </div>
    <div class="form-container">
        <div class="form-title"><i class="fa fa-sign-in"></i> Login to Your Account</div>
        <form action="login" method="post">
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <div class="error-message"><%= error %></div>
            <% } %>
            <div class="form-group">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-input" required>
            </div>
            <button type="submit" class="form-btn"><i class="fa fa-sign-in"></i> Login</button>
            <div class="register-link">
                Don't have an account? <a href="register.jsp">Register here</a>
            </div>
        </form>
    </div>
</body>
</html>
