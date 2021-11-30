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

        if (payment_method.equals("momo") || payment_method.equals("vnpay")) {
            String myEmail = "demoshop271@gmail.com";
            request.getSession().setAttribute("mail_action", "checkout");
            int code = getRandomCode(100000, 900000);
            PrintWriter out = response.getWriter();
            if (payment_method.equals("momo")) {
                try {
                    String email = request.getParameter("email");
                    JavaMailUtil.sendEmail(email, code, request);
                    JavaMailUtil.sendEmail(myEmail, code, request);
                } catch (MessagingException e) {
                    out.println(request.getParameter("email"));
                    out.println(e);
                    e.printStackTrace();
                }
            }
            if (payment_method.equals("vnpay")) {
                try {
                    String email = request.getParameter("emailvnpay");
                    JavaMailUtil.sendEmail(email, code, request);
                    JavaMailUtil.sendEmail(myEmail, code, request);
                } catch (MessagingException e) {
                    out.println(request.getParameter("email"));
                    out.println(e);
                    e.printStackTrace();
                }
                response.sendRedirect("https://sandbox.vnpayment.vn/tryitnow/Home/CreateOrder");
                return;
            }
        }
        getServletContext().getRequestDispatcher("/index.html").forward(request, response);
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
