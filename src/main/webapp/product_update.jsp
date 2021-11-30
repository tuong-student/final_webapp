<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

            <!DOCTYPE html>

            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="css/style_input.css">
                <title>Document</title>
            </head>

            <body>
                <form action="UpdateServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="updateProduct">
                    <input type="hidden" name="productID" value="${needUpdatePro.getID()}">
                    <div id="box">
                        <div id="title">
                            <h1>Adding Product</h1>
                        </div>

                        <div class="info" id="info">
                            <div class="infobox">
                                <label>Name </label>
                                <input type="text" name="name" placeholder="Input product name">
                            </div>

                            <div class="infobox">
                                <label>Price</label>
                                <input type="number" name="price" placeholder="Input product price">
                            </div>

                            <div class="infobox">
                                <label>Quantity</label>
                                <input type="number" name="quantity" placeholder="Input product quatity">
                            </div>

                            <div class="infobox">
                                <label>Description</label>
                                <input id="description" type="text" name="description" placeholder="Input product description">
                            </div>
                            <div class="infobox">
                                <label>Photo</label>
                                <input type="file" name="photo" placeholder="Input product photo">
                            </div>
                        </div>  
                        <button type="submit"> Update </button>
                    </div> 
                </form>
            </body>

            </html>