<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.foodApp.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>BiteBuddy | Account</title>
    <link rel="icon" href="images/logo.jpeg">
    <link rel="stylesheet" href="css/account.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700;900&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    
</head>
<body>
    <div class="account-container">
        <div class="account-title"><i class="fa fa-user"></i> My Account</div>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
        %>
            <div style="text-align:center;font-size:1.15em;color:#888;">Please <a href="login.jsp">login</a> to view your account.</div>
        <% } else { %>
            <table class="order-table">
                <tr>
                    <th>Name</th>
                    <td><%= user.getName() %></td>
                </tr>
                <tr>
                    <th>UserName</th>
                    <td><%= user.getUserName() %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= user.getEmail() %></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><%= user.getAddress() %></td>
                </tr>
                <tr>
                    <th>Phone Number</th>
                    <td><%= user.getPhoneNumber() %></td>
                </tr>
                <tr>
                    <th>Role</th>
                    <td><%= user.getRole() %></td>
                </tr>
            </table>
        <% } %>
    </div>
</body>
</html>