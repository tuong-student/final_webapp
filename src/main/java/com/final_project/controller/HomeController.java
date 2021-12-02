package com.final_project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.model.*;
import com.final_project.data.*;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = ProductDAO.selectAllProduct();
            Product p = products.get(0);
            HttpSession session = request.getSession();
            session.setAttribute("product_list", products);
            session.setAttribute("p", p);// default single_product
            response.sendRedirect("index.html");
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
