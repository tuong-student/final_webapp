package com.final_project.data;

import javax.servlet.http.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.final_project.model.*;

public class JavaMailUtil {

    public static void sendEmail(String reception, int code, HttpServletRequest request) throws MessagingException {
        String mail_action = request.getAttribute("mail_action").toString();
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String myAccount = "demoshop271@gmail.com";
        final String password = "demoshop271.com";

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, password);
            }
        };

        Session session = Session.getInstance(properties, auth);
        if (mail_action.equals("reset")) {
            Message message = prepareMessageReset(session, myAccount, reception, code);
            Transport.send(message);
        }
        if (mail_action.equals("checkout")) {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            String total = cart.getTotalCurrencyFormat();
            if (reception.equals(myAccount)) {
                Message message = prepareMessageCheckoutAdmin(session, myAccount, reception, total, request);
                Transport.send(message);
            } else {
                Message message = prepareMessageCheckout(session, myAccount, reception, code, total);
                Transport.send(message);
            }
        }
    }

    private static Message prepareMessageReset(Session session, String myAccount, String reception, int code) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reception));
            message.setSubject("Your Reset Code");
            String htmlCode = "<h2>This is your code</h2> </br> <h1>" + code + "<h1>";
            message.setContent(htmlCode, "text/html");
            System.out.println("Java mail success!!!");
            return message;
        } catch (Exception e) {
            System.out.println("Java mail fail!!!");
        }
        return null;
    }

    private static Message prepareMessageCheckout(Session session, String myAccount, String reception, int code,
            String total) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reception));
            message.setSubject("Your Checkout Code");
            String htmlCode = "<h2>This is your code</h2> </br> <h1>" + code
                    + "<h1></br><h2>This is the amound you have to pay</h2> </br> <h1>" + total + "</h1>";
            message.setContent(htmlCode, "text/html");
            System.out.println("Java mail success!!!");
            return message;
        } catch (Exception e) {
            System.out.println("Java mail fail!!!");
        }
        return null;
    }

    private static Message prepareMessageCheckoutAdmin(Session session, String myAccount, String reception,
            String total, HttpServletRequest request) {
        try {
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String payment_method = request.getParameter("payment_method");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reception));
            message.setSubject("Your Checkout Code");
            // message for admin
            String htmlCode = "<h1>Payment method: " + payment_method
                    + "</h1></br><h2>This is user code:</h2> </br> <h1>" + code
                    + "</h1></br><h2>This is the uers's bill:</h2> </br> <h1>" + total + "</h1>"
                    + "</br><h2>This is the uers's information</h2> </br>"
                    + "<p>name: </p>" + name
                    + "</br><p>email: </p>" + email;
            message.setContent(htmlCode, "text/html");
            System.out.println("Java mail success!!!");
            return message;
        } catch (Exception e) {
            System.out.println("Java mail fail!!!");
        }
        return null;
    }
}
