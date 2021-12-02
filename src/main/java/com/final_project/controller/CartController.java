package com.final_project.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.ProductDAO;
import com.final_project.model.*;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // get information from form
        String action = req.getParameter("action");
        String url = req.getParameter("url"); // url of the page send request
        int product_id = Integer.parseInt(req.getParameter("product_id"));
        HttpSession session = req.getSession(false);

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
        // if (action.equals("+")) {
        // LineQuantity += 1;
        // }
        // if (action.equals("-")) {
        // LineQuantity -= 1;
        // }
        LineItem.setProduct(product);
        LineItem.setQuantity(LineQuantity);
        if (LineQuantity > 0) {
            cart.addItem(LineItem);
        }
        if (LineQuantity == 0 || action.equals("remove")) {
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
