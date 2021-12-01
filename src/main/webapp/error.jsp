<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="style.css">
    <title>Error page</title>
</head>

<body>
    <div class="content_area">
        <h1 class="title error">OPP! Some thing went wrong</h1>
        <p class="title error_message">${errorMessage}</p>
        <a href="cart.jsp">
            <button class="return_btn btn">Return</button>
        </a>
    </div>
</body>

</html>