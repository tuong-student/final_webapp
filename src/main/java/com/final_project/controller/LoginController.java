package com.final_project.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String action = req.getParameter("action");

            if (action.equals("login")) {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String message = "Enter your email!!";
                // defaul url
                String url = "/index.html";
                // if email exist then check if password is correct
                if (UserDAO.emailExists(email)) {
                    if (UserDAO.passwordExists(email, password)) {
                        User user = new User();
                        user = UserDAO.selectUser(email);
                        req.getSession().setAttribute("user", user);
                        if (email.equals("demoshop271@gmail.com")) {
                            resp.sendRedirect("AdminServlet");
                            return;
                        }
                        req.getSession().setAttribute("user", user);
                    } else {
                        System.out.println("Wrong password");
                        message = "Wrong password!";
                        url = "/login.jsp";
                    }
                } else {
                    message = "Email has not been registered. Please Signup!";
                    url = "/login.jsp";
                }
                req.setAttribute("message", message);
                getServletContext().getRequestDispatcher(url).forward(req, resp);

            }
            if (action.equals("signup")) {
                // get password and re_password from form
                String password = req.getParameter("password");
                String re_password = req.getParameter("re_password");

                String url = "/signup.jsp";
                // if password and re_password is the same, go to EmailInsertServlet
                if (password.equals(re_password)) {
                    url = "/EmailInsertServlet";
                    req.getRequestDispatcher(url).forward(req, resp);
                    return;
                } else {
                    // is the password and re_password not the same, re_input the information
                    String message = "password not same";
                    req.setAttribute("message", message);
                }
                getServletContext().getRequestDispatcher(url).forward(req, resp);
            }
            if (action.equals("forgot_password")) {
                String url = "/JavaEmailServlet";
                req.getRequestDispatcher(url).forward(req, resp);
            }
            if (action.equals("reset_code")) {
                // get information form the form
                String otp = req.getParameter("otp");
                String code = req.getSession().getAttribute("code").toString();
                String url = "/reset_password.html";
                // if the otp is the same as the code, go to the reset_password page
                if (otp.equals(code)) {
                    getServletContext().getRequestDispatcher(url).forward(req, resp);
                } else {
                    // else re_input the code
                    url = "/reset_code.html";
                    getServletContext().getRequestDispatcher(url).forward(req, resp);
                }
            }
            if (action.equals("reset_password")) {
                // get the information
                String password = req.getParameter("password");
                String re_password = req.getParameter("re_password");
                String url = "/reset_password.jsp";
                // check password and re_password
                if (password.equals(re_password)) {
                    // go to the EmailInsertServlet to check the information
                    url = "/EmailInsertServlet";
                } else {
                    // is the password and re_password not the same, re_input the information
                    String message = "password not same";
                    req.setAttribute("message", message);
                }
                getServletContext().getRequestDispatcher(url).forward(req, resp);
            }

        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            System.out.println(e);
            System.out.println("LoginController Fail!!!");
            out.println("LoginController Faill!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
}
