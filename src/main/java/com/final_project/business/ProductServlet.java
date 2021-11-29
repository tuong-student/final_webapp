package com.final_project.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.*;
import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet 
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {

        PrintWriter out = response.getWriter();  
        String name = request.getParameter("pname");
        String description = request.getParameter("pdescription");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));

        try 
        {
            Product p = new Product();
            p.setName(name);
            p.setDescription(description);
            p.setQuantity(quantity);
            p.setPrice(price);
            ProductDAO.insert(p);
            out.println("success");

        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            out.println("ProductServlet Fail!!");
        }
        
        request.getRequestDispatcher("/AdminServlet").forward(request, response);
    }
    

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
        doGet(request, response);
    }
}
