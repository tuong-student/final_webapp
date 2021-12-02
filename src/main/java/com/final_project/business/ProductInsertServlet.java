package com.final_project.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.final_project.data.*;
import com.final_project.model.*;

@WebServlet("/ProductInsertServlet")
// Fix lỗi form có chứa enctype="multipart/form-data"
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
        * 100)
public class ProductInsertServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("price"));
        PrintWriter out = response.getWriter();

        try {
            Product p = new Product();
            p.setName(name);
            p.setDescription(description);
            p.setQuantity(quantity);
            p.setPrice(price);
            p.setImageName(uploadFile(request, response));
            ProductDAO.insert(p);
            out.println("success");
            response.sendRedirect("product_input.html");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
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
}
