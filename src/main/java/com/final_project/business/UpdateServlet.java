package com.final_project.business;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/UpdateServlet")

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        int productID = Integer.parseInt(request.getParameter("productID"));
        Product product =new Product();
        product = ProductDAO.selectProduct(productID);
        
        PrintWriter out = response.getWriter();
        String name = request.getParameter("pname");
        String description = request.getParameter("pdescription");
        int quantity = Integer.parseInt(request.getParameter("pquantity"));
        float price = Float.parseFloat(request.getParameter("pprice"));

        try{
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);

            ProductDAO.update(product); 
            out.println("Update success!!"); 
        }catch (Exception e) {
            System.out.println("Update fail!!");
        }

        request.getRequestDispatcher("/AdminServlet").forward(request, response);
    }
    
    
    
}
