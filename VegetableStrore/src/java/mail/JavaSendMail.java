package mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class JavaSendMail {

    public JavaSendMail() {
    }

    public void sendMail(String recepient, String header, String content) throws MessagingException {
        System.out.println("Prepared sending email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        //
        final String account = "hidden2792001@gmail.com";
        final String password = "bao2792001";
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, password);
            }
        });
        Message message = preparedMessage(session, account, recepient, header, content);
        Transport.send(message);
        System.out.println("Send email successfully");

    }

    private Message preparedMessage(Session session, String account, String recepient, String header, String content) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(account));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(header);
            message.setText(content);

            return message;
        } catch (Exception e) {
            System.out.println("Failed!!!");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws MessagingException {
        JavaSendMail j = new JavaSendMail();
        j.sendMail("hiddenwory@gmail.com", "Xac Nhan Don Hang", "Don hang #066534 se duoc giao vao thu 6");
    }
}
