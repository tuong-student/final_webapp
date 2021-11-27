package com.final_project.business;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.JavaMailUtil;

@WebServlet("/JavaEmailServlet")
public class JavaEmailServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reception = req.getParameter("email");
        try {
            int code = getRandomCode(1000, 9000);
            JavaMailUtil.sendEmail(reception, code);
            req.getSession().setAttribute("code", code);
            getServletContext().getRequestDispatcher("/reset_code.html").forward(req, resp);
        } catch (MessagingException e) {
            System.out.println("JavaEmailServlet fail!!!");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    protected int getRandomCode(int min, int max) {
        int range = (max - min) + 1;
        int code = (int) (Math.random() * range) + min;
        return code;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
