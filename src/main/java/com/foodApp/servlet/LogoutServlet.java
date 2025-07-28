package com.foodApp.servlet;



import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Invalidate the existing session if one exists
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Optionally, you can add a logout success message as a request attribute
        req.setAttribute("message", "You have been logged out successfully.");

        // Redirect to login page (or home page, as per your application flow)
        resp.sendRedirect("login.jsp");
    }
}