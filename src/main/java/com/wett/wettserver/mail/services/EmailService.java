package com.wett.wettserver.mail.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huutrungg02@gmail.com");
        message.setTo("huutrungg02@gmail.com");
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}

