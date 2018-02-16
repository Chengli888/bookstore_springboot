package edu.monmouth.product.util;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    public  static boolean sendMail(String email,String emailMsg){
        String from = "18852951220@163.com";
        String to = email;
        final String username = "18852951220@163.com";
        final String password = "13401229976c";

        //set Properties' Object,set information of the environment
        Properties props = new Properties();

        //set address of the email service
        props .setProperty("mail.transport.protocol","smtp");
        props.setProperty("mail.smtp.host","smtp.163.com");
        props.setProperty("mail.smtp.auth","true");

        javax.mail.Session session = javax.mail.Session.getInstance(props);
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage((session));
            message.setFrom(new InternetAddress(from));
            message.setSubject("Active your account");
            message.setContent(emailMsg,"text/html;charset=utf-8");
            Transport transport = session.getTransport();
            //connect the email service
            transport.connect("smtp.163.com",25,username,password);
            //set email address of the receiver
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            transport.close();
            return true;
        } catch (AddressException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return  false;
        }
    }
}
