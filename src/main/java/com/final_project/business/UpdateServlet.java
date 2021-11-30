package com.final_project.business;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/UpdateServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
        * 100)
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try {

            int productID = Integer.parseInt(request.getParameter("productID"));
            Product product = new Product();
            product = ProductDAO.selectProduct(productID);
            String action = request.getParameter("action");
            if (action.equals("product_update")) {
                request.getSession().setAttribute("needUpdatePro", product);
                getServletContext().getRequestDispatcher("/product_update.jsp").forward(request,
                        response);
            }

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int price = Integer.parseInt(request.getParameter("price"));
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setImageName(uploadFile(request, response));
            ProductDAO.update(product);
            System.out.println("Update success!!");

            request.getRequestDispatcher("/AdminServlet").forward(request, response);
        } catch (Exception e) {
            // TODO: handle exception
            out.println("Update fail!!");
            out.println(e);
        }

    }

    private String uploadFile(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String fileName = "";
        PrintWriter out = response.getWriter();
        try {
            Part filePart = request.getPart("photo");

            // fileName: picture-001.jpg
            fileName = getFileName(filePart);

            // applicationPath:
            // C:\Users\Lonely\Documents\NetBeansProjects\Shop_Bonfire\build\web
            String applicationPath = request.getServletContext().getRealPath("");
            out.println("applicationPath: " + applicationPath);
            // File.separator: \
            String basePath = applicationPath + File.separator + "img" + File.separator;

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println(e);
                fileName = "Fail1";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception e) {
            out.println(e);
            fileName = "Fail2";
        }
        return fileName;
    }

    // get Part file name
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}