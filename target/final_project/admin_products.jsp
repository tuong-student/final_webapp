<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Admin_page</title>
</head>

<body>
    <div class="web_container">
        <div class="header">
            <img class="header_img" src="https://vietadsgroup.vn/Uploads/files/lo-go-la-gi.png" alt="shop-logo">
            <p class="shop-name">Final Shop</p>
        </div>
        <div class="container">
            <div class="sider">
                <ul class="sider_functions">
                    <a class="link" href="#">
                        <li class="sider_function">View your site</li>
                    </a>
                    <a class="link" href="admin_customers.jsp">
                        <li class="sider_function">View all customers</li>
                    </a>
                </ul>
            </div>
            <div class="main_content">
                <ul class="items">
                    <div class="item_lables-4">
                        <div class="lable-4">Name</div>
                        <div class="lable-4">Price</div>
                        <div class="lable-4">Quantity</div>
                    </div>
                    <c:forEach var="product" items="${product_list}">
                        <li class="item">
                            <img class="item_img" src="https://product.hstatic.net/1000096703/product/1_5d443ecd807c47238a1630dca196d6be_master.jpg" alt="">
                            <div class="text_container">
                                <div class="item_text-4">${product.getName()}</div>
                                <div class="item_text-4">${product.getPrice()}</div>
                                <div class="item_text-4">${product.getQuantity()}</div>
                                
                                <form action="${pageContext.request.contextPath}/DeleteServlet" method="post">
                                    <input type="hidden" name="action" value="deleteProduct">
                                    <input type="hidden" name="productID" value="${product.getID()}">
                                    <button class="deleteButton" title="submit">Delete</button>
                                </form>

                                <button class="updateButton"  onclick="document.location='product_update.html'">Update Product</button>
                                
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <button type="button" onclick="document.location='product_input.html'" class="item_add">Add Product</button>
            </div>
        </div>
    </div>
</body>

</html>