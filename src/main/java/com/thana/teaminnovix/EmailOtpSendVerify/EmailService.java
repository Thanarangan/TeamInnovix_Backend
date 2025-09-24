package com.thana.teaminnovix.EmailOtpSendVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    public void SendOtpEmail(String tomail, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("spidytingsense@gmail.com");
        message.setTo(tomail);
        message.setSubject(" Welcome! to the Innovix");
        message.setText("And your OTP was this: " + otp);
        mailSender.send(message);
    }
}
