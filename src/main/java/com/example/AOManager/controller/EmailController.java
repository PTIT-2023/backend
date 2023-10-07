package com.example.AOManager.controller;

import com.example.AOManager.email.EmailDetail;
import com.example.AOManager.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @GetMapping("/sendMail")
    public String sendMail(String to, String subject, String text)
    {
        EmailDetail details = new EmailDetail();
        details.setRecipient(to);
        details.setMsgBody(text);
        details.setSubject(subject);
        String status = emailService.sendSimpleMail(details);
        return status;
    }
}
