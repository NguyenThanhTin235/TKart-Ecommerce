package com.tkart.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tkart-support@ecommerce.com");
        message.setTo(to);
        message.setSubject("TKart - Your Verification Code");
        message.setText("Your OTP code is: " + otp + ". This code will expire in 5 minutes.");
        mailSender.send(message);
    }
}
