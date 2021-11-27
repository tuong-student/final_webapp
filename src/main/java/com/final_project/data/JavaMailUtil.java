package com.final_project.data;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

    public static void sendEmail(String reception, int code) throws MessagingException {
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

        Message message = prepareMessage(session, myAccount, reception, code);
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccount, String reception, int code) {
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
}
