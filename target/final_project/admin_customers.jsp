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
                    <a class="link" href="admin_products.jsp">
                        <li class="sider_function">View all products</li>
                    </a>
                </ul>
            </div>
            <div class="main_content">
                <ul class="items">
                    <div class="item_lables">
                        <div class="lable">User ID</div>
                        <div class="lable">User name</div>
                        <div class="lable">Email</div>
                    </div>
                    <c:forEach var="user" items="${user_list}">
                        <li class="item">
                            <form action="DeleteServlet" method="post">
                            <input type="hidden" name="action" value="deleteUser">
                            <input type="hidden" name="email" value="${user.getemail()}">
                            <div class="text_container">
                                <div class="item_text item_name">${user.getID()}</div>
                                <div class="item_text item_type">${user.getname()}</div>
                                <div class="item_text item_price">${user.getemail()}</div>
                                <button class="deleteButton" title="submit">Delete</button>
                            </div>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>

</html>