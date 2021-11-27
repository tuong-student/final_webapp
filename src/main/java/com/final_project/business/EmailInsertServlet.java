package com.final_project.business;

import java.io.*;
import javax.servlet.*;
import javax.servlet.GenericServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/EmailInsertServlet")
public class EmailInsertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String url = "/trang_chu.html";
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String action = req.getParameter("action");
            String message = "Enter your email!!";

            if (action.equals("signup")) {
                if (UserDAO.emailExists(email)) {
                    message = "Email has already been taken.";
                    url = "/signup.jsp";
                } else {
                    User user = new User();
                    user.setemail(email);
                    user.setname(name);
                    user.setpassword(password);

                    UserDAO.insert(user);
                    req.getSession().setAttribute("user", user);
                }
            }
            if (action.equals("reset_password")) {
                User user = UserDAO.selectUser(email);
                user.setpassword(password);

                UserDAO.update(user);
                url = "/login.jsp";
                message = "Your password has been change \n Please login again";
                req.getSession().setAttribute("user", user);
            }
            req.setAttribute("message", message);
            getServletContext().getRequestDispatcher(url).forward(req, resp);
        } catch (Exception e) {
            System.out.println("Insert Servlet fail!!!");
            System.out.println(e);
        }
    }

}
