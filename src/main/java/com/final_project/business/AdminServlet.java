package com.final_project.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

public class AdminServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserDAO.selectAllUsers();
        List<Product> products = ProductDAO.selectAllProduct();
        HttpSession session = req.getSession();
        session.setAttribute("user_list", users);
        session.setAttribute("product_list", products);

        getServletContext().getRequestDispatcher("/admin_products.jsp").forward(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
