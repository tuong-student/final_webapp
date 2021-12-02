package com.final_project.business;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String action = request.getParameter("action");

            if (action.equals("deleteUser")) {
                String email = request.getParameter("email");

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
                request.getRequestDispatcher("/AdminServlet").forward(request, response);
            }
            if (action.equals("deleteProduct")) {
                int productID = Integer.parseInt(request.getParameter("productID"));

                Product product = new Product();
                product = ProductDAO.selectProduct(productID);

                PrintWriter out = response.getWriter();

                try {
                    ProductDAO.delete(product);
                    out.println("Delete success!!");
                } catch (Exception e) {
                    System.out.println("Delete fail!!");
                }

                request.getRequestDispatcher("/AdminServlet").forward(request, response);
            }
        } catch (Exception e) {
            // TODO: handle exception
            request.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
