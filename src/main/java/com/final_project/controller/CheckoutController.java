package com.final_project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;

@WebServlet("/CheckoutController")
public class CheckoutController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String payment_method = request.getParameter("payment_method");
        String action = request.getParameter("action");
        if (action != null) {
            try {
                request.setAttribute("mail_action", "checkout");
                String email = request.getParameter("email");
                String myEmail = "demoshop271@gmail.com";
                int code = Integer.parseInt(request.getParameter("code"));
                JavaMailUtil.sendEmail(email, code, request);
                JavaMailUtil.sendEmail(myEmail, code, request);
                request.setAttribute("message",
                        "Send your payment code and money to this momo to complete the transaction || Shop momo: 0398149100 || Thanks you for your payment!!!");
                if (payment_method.equals("vnpay")) {
                    response.sendRedirect("https://sandbox.vnpayment.vn/tryitnow/Home/CreateOrder");
                    return;
                }
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            if (payment_method.equals("momo") || payment_method.equals("vnpay")) {
                int code = getRandomCode(1000, 9000);
                request.getSession().setAttribute("code", code);
                request.getSession().setAttribute("payment_method", payment_method);
            }
        }
        getServletContext().getRequestDispatcher("/checkagain.jsp").forward(request, response);
    }

    private int getRandomCode(int min, int max) {
        int range = (max - min) + 1;
        int code = (int) (Math.random() * range) + min;
        return code;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
