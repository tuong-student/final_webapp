package com.final_project.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = UserDAO.selectAllUsers();
            List<Product> products = ProductDAO.selectAllProduct();
            HttpSession session = req.getSession();
            session.setAttribute("user_list", users);
            session.setAttribute("product_list", products);
            String action = req.getParameter("action");
            if (action == null) {
                action = "Adminpage";
            }
            if (action.equals("Adminpage")) {
                resp.sendRedirect("admin_products.jsp");
            } else {
                if (action.equals("deleteUser")) {
                    resp.sendRedirect("admin_customers.jsp");
                }
                if (action.equals("updateProduct")) {
                    resp.sendRedirect("admin_products.jsp");
                }
                if (action.equals("deleteProduct")) {
                    resp.sendRedirect("admin_products.jsp");
                }
                if (action.equals("addProduct")) {
                    resp.sendRedirect("admin_products.jsp");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
