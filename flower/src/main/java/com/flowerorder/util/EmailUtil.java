package com.flowerorder.util;

import com.flowerorder.model.OrderItems;
import com.flowerorder.model.Orders;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USER = "flowerorder123@gmail.com"; // Your Gmail address
    private static final String EMAIL_PASSWORD = "cpfz ehfk xhvn ocvq"; // Your app-specific password

    public static void sendOrderConfirmationEmail(String recipientEmail, Orders order) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USER, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Order Confirmation - Order #" + order.getOrder_id());

            // Construct the email content
            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Dear Customer,\n\n");
            emailContent.append("Thank you for your order!\n\n");
            emailContent.append("Order Details:\n");
            emailContent.append("Order ID: ").append(order.getOrder_id()).append("\n");
            emailContent.append("Order Status: ").append(order.getOrder_status()).append("\n");
            emailContent.append("Total Price: $").append(String.format("%.2f", order.getTotal_price())).append("\n");
            emailContent.append("Shipping Address: ").append(order.getShipping_address()).append("\n");
            emailContent.append("Payment Method: ").append(order.getPayment_method()).append("\n");
            emailContent.append("Order Date: ").append(order.getCreated_at()).append("\n\n");

            emailContent.append("\nWe appreciate your business and hope you enjoy your purchase!\n");
            emailContent.append("If you have any questions or need further assistance, please don't hesitate to contact us.\n\n");
            emailContent.append("Best regards,\n");
            emailContent.append("Flower Shop Team");

            message.setText(emailContent.toString());

            Transport.send(message);

            System.out.println("Order confirmation email sent to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email to " + recipientEmail);
        }
    }
}
