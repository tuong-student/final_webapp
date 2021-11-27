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
        req.setAttribute("p", p);
        PrintWriter w = resp.getWriter();
        w.println(p.getName());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
