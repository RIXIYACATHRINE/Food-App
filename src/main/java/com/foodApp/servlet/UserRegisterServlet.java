package com.foodApp.servlet;

import java.io.IOException;

import com.foodApp.dao.UserDAO;
import com.foodApp.daoimpl.UserDAOImpl;
import com.foodApp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user-registration")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get parameters from the form
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phonenumber"); // fixed name match
        String role = req.getParameter("role");

        // Create user object
        User user = new User();
        user.setName(name);
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);

        // DAO operation
        UserDAO userDAO = new UserDAOImpl();
        if (userDAO.getUserByEmailId(email) != null) {
            req.setAttribute("error", "Email already registered.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        // Save user and redirect
        userDAO.addUser(user);

        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("user", user);
        resp.sendRedirect("home");
    }
}
