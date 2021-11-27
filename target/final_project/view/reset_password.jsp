<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <link rel="stylesheet" href="../css/main.css">
    <script>
        window.dataLayer = window.dataLayer || [];
    </script>
</head>

<body>
    <div class="wrapper">
        <div class="container">
            <div class="header">
                <h2 class="header__title">Reset Password</h2>
                <p class="header__description">It's quick and easy!</p>
            </div>
            <div class="alert show error">
                <p class="alert__text">${message}</p>
            </div>
            <form action="LoginController" class="form">
                <input type="hidden" name="action" value="reset_password">
                <div class="form__input">
                    <label for="email" class="form__label">Email</label>
                    <i class="form__icon"><img src="assets/email.svg" alt=""></i>
                    <input id="email" type="email" name="email" placeholder="Enter Your E-Mail" required autocomplete="off" required spellcheck="false" class="field">
                </div>
                <div class="form__input">
                    <label for="password" class="form__label">Password</label>
                    <i class="form__icon"><img src="assets/lock.svg" alt=""></i>
                    <input id="password" type="password" name="password" placeholder="Password" required autocomplete="off" required class="field password">
                    <i class="form__icon eye"><img src="assets/eye-off.svg" alt=""></i>
                </div>
                <div class="form__input">
                    <label for="re_password" class="form__label">Repeat Password</label>
                    <input id="re_password" type="password" name="re_password" placeholder="Repeat Password" autocomplete="off" class="field">
                    <p class="form__error">Required input</p>
                </div>
                <button class="btn" type="submit" formmethod="post">Submit</button>
                <p class="form__text">Already a member? <a class="link" href="login.html">Login here</a></p>
            </form>
        </div>
    </div>

</body>

</html>