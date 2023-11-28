package com.innovaturelabs.training.examportal.service.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}") private String sender;
    
    public void SentEmail(String recipient,String subject,String body) {
        
        String sendername="EXAM PORTAL";
        try {
            MimeMessage mailMessage =javaMailSender.createMimeMessage();
            MimeMessageHelper helper= new MimeMessageHelper(mailMessage);
            MimeBodyPart messageBodyPart=new MimeBodyPart();
            helper.setFrom(sender,"EXAM PORTAL");
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(body);
            
            
            javaMailSender.send(mailMessage);
            System.out.println("Mail sent successfully");
        }
        
        catch(Exception e) {
            System.out.println("Error while sending message");
        }
    }
}
