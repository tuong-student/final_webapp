package com.final_project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.ProductDAO;
import com.final_project.model.*;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // get information from form
        String url = req.getParameter("url"); // url of the page send request
        String action = req.getParameter("action");
        int product_id = Integer.parseInt(req.getParameter("product_id"));
        HttpSession session = req.getSession();

        // creat object
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        LineItem LineItem = new LineItem();
        Product product = ProductDAO.selectProduct(product_id);

        // business
        // if input quantity is invalid, set it is 1
        int LineQuantity;
        try {
            LineQuantity = Integer.parseInt(req.getParameter("line_quantity"));
            if (LineQuantity < 0) {
                LineQuantity = 1;
            }
        } catch (NumberFormatException e) {
            LineQuantity = 1;
        }

        // creat LineItem and add if action == add, else delete
        LineItem.setProduct(product);
        LineItem.setQuantity(LineQuantity);
        if (action.equals("add")) {
            cart.addItem(LineItem);
        }
        if (action.equals("delete")) {
            cart.removeItem(LineItem);
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect(url);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
