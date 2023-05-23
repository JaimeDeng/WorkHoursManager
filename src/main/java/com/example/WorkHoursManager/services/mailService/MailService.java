package com.example.WorkHoursManager.services.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Qualifier("mailService")
public class MailService {
 
    private JavaMailSender mailSender;
 
    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    public void prepareAndSend(String recipient, String subject, String message) {
       MimeMessagePreparator messagePreparator = mimeMessage -> {
             MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
             messageHelper.setFrom("ravenhexas@gmail.com");
             messageHelper.setTo(recipient);
             messageHelper.setSubject(subject);
             messageHelper.setText(message);
         };
         try {
             mailSender.send(messagePreparator);
             System.out.println("sent");
         } catch (MailException e) {
             System.out.println(e);
         }
    }
 
}
