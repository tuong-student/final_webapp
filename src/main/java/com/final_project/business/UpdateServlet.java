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
        
        // int productID = Integer.parseInt(request.getParameter("productID"));
        // Product product =new Product();
        // product = ProductDAO.selectProduct(productID);
        
        PrintWriter out = response.getWriter();
        String name = request.getParameter("pname");
        String description = request.getParameter("pdescription");
     
        try{
            int quantity = Integer.parseInt(request.getParameter("pquantity"));
        }
        catch (Exception e){
            out.println("quan fall");
        }
        try{
             float price = Float.parseFloat(request.getParameter("pprice"));
        }
        catch (Exception e){
            out.println("price fall");
        }
        
        
        // PrintWriter out = response.getWriter();

        // try{
        //     ProductDAO.update(product); 
        //     out.println("Update success!!"); 
        // }catch (Exception e) {
        //     System.out.println("Update fail!!");
        // }

        // request.getRequestDispatcher("/AdminServlet").forward(request, response);
    }
    
    
    
}
