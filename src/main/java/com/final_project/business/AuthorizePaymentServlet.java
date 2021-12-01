package com.final_project.business;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.model.*;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/AuthorizePaymentServlet")
public class AuthorizePaymentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        // if user not login, return to login page
        if (user == null) {
            resp.sendRedirect("login.html");
            return;
        }

        double shipping = 0;
        double subtotal = cart.getTotal();
        double total = cart.getTotal();
        double tax = 0;

        OrderDetail orderDetail = new OrderDetail(total, shipping, subtotal, tax);

        PaymentService paymentService = new PaymentService();
        try {
            String approvalLink = paymentService.authorizePayment(orderDetail, req);
            resp.sendRedirect(approvalLink);
        } catch (PayPalRESTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }

    }

}
