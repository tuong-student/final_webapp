package com.final_project.business;

import java.io.*;
import javax.servlet.*;
import javax.servlet.GenericServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user = UserDAO.selectUser(email);

        PrintWriter out = response.getWriter();
        try {
            UserDAO.delete(user);
            out.println("Delete success!!");
        } catch (Exception e) {
            out.println("Delete fail!!");
            out.println(user.getemail());
            out.println(user.getpassword());
        }

    }
}
