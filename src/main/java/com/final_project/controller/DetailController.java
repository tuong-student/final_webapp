package com.final_project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.ProductDAO;
import com.final_project.model.*;

@WebServlet("/DetailController")
public class DetailController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pId = Integer.parseInt(req.getParameter("product_id"));
        Product p = ProductDAO.selectProduct(pId);
        HttpSession session = req.getSession();
        session.setAttribute("p", p);

        getServletContext().getRequestDispatcher("/single-product.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
