package com.final_project.business;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@WebServlet("/UpdateServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024* 100)
public class UpdateServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        int productID = Integer.parseInt(request.getParameter("productID"));
        Product product = ProductDAO.selectProduct(productID);
        
        String action = request.getParameter("action");
        if (action.equals("product_update"))
        {   
            response.sendRedirect("product_update.html");
        }
        
        else{    
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
    
        
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
