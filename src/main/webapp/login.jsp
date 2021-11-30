<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/main.css">
    <script>
        window.dataLayer = window.dataLayer || [];
    </script>
</head>

<body>
    <div class="wrapper">
        <div class="container">
            <div class="header">
                <h2 class="header__title">Login Form</h2>
                <p class="header__description">Login with your email and password.</p>
            </div>
            <div class="alert show error">
                <p class="alert__text">${message}</p>
            </div>
            <form action="LoginController" class="form" method="post">
                <input type="hidden" name="action" value="login">
                <div class="form__input">
                    <label for="email" class="form__label">Email</label>
                    <i class="form__icon"><img src="assets/email.svg" alt=""></i>
                    <input id="email" type="email" name="email" placeholder="Enter Your E-Mail" autocomplete="off" required spellcheck="false" class="field">
                </div>
                <div class="form__input">
                    <label for="password" class="form__label">Password</label>
                    <i class="form__icon"><img src="assets/lock.svg" alt=""></i>
                    <input id="password" type="password" name="password" placeholder="Password" autocomplete="off" required class="field password">
                    <i class="form__icon eye"><img src="assets/eye-off.svg" alt=""></i>
                </div>
                <a class="link forgot" href="forgot_password.html">Forgot password?</a>
                <button class="btn" type="submit">Login</button>
                <p class="form__text">Not yet a member? <a class="link" href="signup.html">Signup now</a></p>
            </form>
        </div>
    </div>

    <!-- <script src="js/login.js"></script> -->

</body>

</html>