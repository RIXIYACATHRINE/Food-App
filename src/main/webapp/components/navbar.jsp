<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.foodApp.model.User" %>
<%
    String userName = null;
    if (session != null && session.getAttribute("user") != null) {
        User user = (User) session.getAttribute("user");
        userName = user.getName();
    }
%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<nav class="navbar">
    <div class="navbar-left">
        <a href="home" class="nav-logo">
            <img src="images/logo.jpeg" alt="Foodie" class="logo-img">
            <span class="app-title">BiteBuddy</span>
        </a>
    </div>
    <div class="navbar-right">
        <% if (userName == null) { %>
            <a href="home"><i class="fa fa-home"></i> Home</a>
            <a href="login.jsp"><i class="fa fa-sign-in"></i> Login</a>
            <a href="register.jsp"><i class="fa fa-user-plus"></i> Register</a>
        <% } else { %>
            <a href="home"><i class="fa fa-home"></i> Home</a>
            <a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Cart</a>
            <a href="home"><i class="fa fa-cutlery"></i> Restaurants</a>
            <a href="orderHistory"><i class="fa fa-list"></i> Orders</a>
            <a href="account.jsp"><i class="fa fa-user"></i> Account</a>
            <a href="logout"><i class="fa fa-sign-out"></i> Logout</a>
            <span class="welcome-text">Hi, <%= userName %></span>
        <% } %>
    </div>
</nav>

<style>
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.6em 2.5em;
    border-radius: 0 0 1.1em 1.1em;
    position: sticky;
    top: 0;
    z-index: 1000;
    background: linear-gradient(90deg, #ff6a00 60%, #ff944d 100%);
    box-shadow: 0 2px 10px rgba(255,106,0,0.12);
}
.nav-logo {
    display:flex;
    align-items:center;
    text-decoration:none;
}
.logo-img {
    width:38px;
    height:38px;
    border-radius:50%;
    margin-right:0.7em;
    box-shadow:0 2px 6px rgba(255,106,0,0.15);
}
.app-title {
    color:#fff;
    font-size:1.5em;
    font-weight:700;
    letter-spacing:1px;
}
.navbar-right a {
    margin: 0 0.7em;
    font-size: 1.07em;
    font-weight: 500;
    padding: 0.3em 1em;
    border-radius: 1.3em;
    transition: background 0.17s, color 0.17s;
    text-decoration: none;
    color: #fff;
}
.navbar-right a:hover, .navbar-right a:focus {
    background: #fff;
    color: #ff6a00 !important;
    text-decoration: none;
}
.welcome-text {
    font-size: 1.05em;
    color: #fff1e0;
    font-weight:500;
    margin-left:1.2em;
}
@media(max-width: 700px) {
    .navbar {
        flex-direction: column;
        align-items: flex-start;
        padding: 0.7em 0.8em;
    }
    .navbar-right {
        margin-top: 0.6em;
    }
    .nav-logo .app-title {
        font-size: 1.1em;
    }
}
</style>
