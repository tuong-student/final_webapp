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
        List<Product> products = ProductDAO.selectAllProduct();
        HttpSession session = request.getSession();
        session.setAttribute("product_list", products);
        response.sendRedirect("index.html");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
