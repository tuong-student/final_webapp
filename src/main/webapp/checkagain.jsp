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
                <title>Checkout again</title>
            </head>

            <body>
                <div class="web_container">
                    <div class="content_area">
                        <h1 class="title">Please Check Again and input your information</h1>
                        <form action="CheckoutController" class="info_form">
                            <input type="hidden" name="action" value="accept_payment">
                            <input type="hidden" name="payment_method" value="${payment_method}">
                            <div class="info_input">
                                <div class="input_area">
                                    <div class="small_area">
                                        <div>
                                            <h3>Input your name:</h3>
                                            <input class="form_input base" type="text" name="name" required>
                                        </div>
                                        <div>
                                            <h3>Input your email address:</h3>
                                            <input class="form_input base" type="email" name="email" required>
                                        </div>
                                    </div>

                                    <div class="small_area">
                                        <div class="text_code">
                                            <h3>This is your payment_code</h3>
                                            <p>This code will be sent to your email</p>
                                            <input type="hidden" name="code" value="${code}">
                                            <h2 style="color: red;">${code}</h2>
                                        </div>
                                        <div>
                                            <h3>Input your bill:</h3>
                                            <p>Carefully check your bill and reinput it</p>
                                            <input class="form_input base" type="text" name="money" required>
                                        </div>
                                    </div>
                                </div>
                                <table class="shop_table" style="margin-bottom: 10px">
                                    <thead>
                                        <tr>
                                            <th class="product-name">Product</th>
                                            <th class="product-total">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${cart.getItems()}">
                                            <tr class="cart_item">
                                                <td class="product-name">
                                                    ${item.getProduct().getName()} <strong class="product-quantity">× ${item.getQuantity()}</strong> </td>
                                                <td class="product-total">
                                                    <span class="amount">${item.getTotalCurrencyFormat()}</span> </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr class="cart-subtotal">
                                            <th>Cart Subtotal</th>
                                            <td><span class="amount">${cart.getTotalCurrencyFormat()}</span>
                                            </td>
                                        </tr>

                                        <tr class="shipping">
                                            <th>Shipping and Handling</th>
                                            <td>
                                                Free Shipping
                                            </td>
                                        </tr>
                                        <tr class="order-total">
                                            <th>Order Total</th>
                                            <td><strong><span class="amount">${cart.getTotalCurrencyFormat()}</span></strong> </td>
                                        </tr>

                                    </tfoot>
                                </table>
                                <div class="btn_area">
                                    <a href="checkout.jsp" class="btn button">return</a>
                                    <h2 style="color: green;">
                                        ${message}
                                    </h2>
                                    <input type="submit">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </body>

            </html>