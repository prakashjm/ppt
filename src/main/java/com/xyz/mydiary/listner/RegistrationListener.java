package com.xyz.mydiary.listner;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.xyz.mydiary.event.OnRegistrationCompleteEvent;
import com.xyz.mydiary.model.UserDAO;
import com.xyz.mydiary.service.UserService;

@Component

public class RegistrationListener implements 
  ApplicationListener<OnRegistrationCompleteEvent> {
 
    
 @Autowired
 private UserService service;
    @Autowired
    private MessageSource messages;
 
    @Autowired
    private JavaMailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserDAO user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);
        
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        String message = "SUCESSSS";//messages.getMessage("message.regSucc", null, event.getLocale());
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
