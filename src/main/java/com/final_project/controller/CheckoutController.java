package com.final_project.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/CheckoutController")
public class CheckoutController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String payment_method = request.getParameter("payment_method");
        String action = request.getParameter("action");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            getServletContext().getRequestDispatcher("/login.html").forward(request, response);
            return;
        }

        if (action != null) {
            try {

                request.setAttribute("mail_action", "checkout");
                String email = request.getParameter("email");
                double money = Double.parseDouble(request.getParameter("money"));
                // check information
                if (email.equals(user.getemail()) && money == cart.getTotal()) {
                    // do nothing
                } else {
                    String message = "Wrong input!!";
                    request.setAttribute("messageer", message);
                    if (payment_method.equals("paypal")) {
                        request.getRequestDispatcher("/checkagainPaypal.jsp").forward(request, response);
                        return;
                    } else {
                        request.getRequestDispatcher("/checkagain.jsp").forward(request, response);
                        return;
                    }
                }
                String myEmail = "demoshop271@gmail.com";
                int code = Integer.parseInt(request.getParameter("code"));
                JavaMailUtil.sendEmail(email, code, request);
                JavaMailUtil.sendEmail(myEmail, code, request);
                if (payment_method.equals("paypal")) {
                    String paymentId = request.getParameter("paymentId");
                    String PayerID = request.getParameter("PayerID");
                    PaymentService paymentService = new PaymentService();
                    Payment payment = paymentService.executePayment(paymentId, PayerID);

                    request.setAttribute("message", "Thank you for your payment !!");
                    request.getRequestDispatcher("checkagainPaypal.jsp").forward(request, response);
                    return;
                }
                if (payment_method.equals("momo")) {
                    request.setAttribute("message",
                            "Send your payment code and money to this momo to complete the transaction || Shop momo: 0398149100 || Thanks you for your payment!!!");
                }
                if (payment_method.equals("vnpay")) {
                    response.sendRedirect("https://sandbox.vnpayment.vn/tryitnow/Home/CreateOrder");
                    return;
                }
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (PayPalRESTException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                request.setAttribute("errorMessage", "Could not execute payment");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } else {
            if (payment_method.equals("momo") || payment_method.equals("vnpay")) {
                int code = getRandomCode(1000, 9000);
                request.getSession().setAttribute("code", code);
                request.getSession().setAttribute("payment_method", payment_method);
            }
            if (payment_method.equals("paypal")) {
                String baseURL = request.getParameter("url");
                int code = getRandomCode(1000, 9000);
                request.getSession().setAttribute("code", code);
                request.getSession().setAttribute("baseURL", baseURL);
                request.getSession().setAttribute("payment_method", payment_method);
                request.getRequestDispatcher("AuthorizePaymentServlet").forward(request, response);
                return;
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
