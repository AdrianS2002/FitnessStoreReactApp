package com.example.ProiectMagazinSuplimenteAlimentare.service;

import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import com.example.ProiectMagazinSuplimenteAlimentare.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * EmailCronjobService class is used to send emails to all users in the database.
 */
@Service
public class EmailCronjobService {

    @Autowired
    private UserService userService; // Autowired UserService to fetch users

    @Autowired
    private JavaMailSender mailSender; // Autowired JavaMailSender to send emails

    public EmailCronjobService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail() {
        List<User> users = userService.findAll(); // Fetch all users from the database
        if (users == null || users.isEmpty()) {
            System.out.println("No users found to send emails.");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mailtrap@demomailtrap.com");

        for (User user : users) {
            message.setTo(user.getUsername());
            message.setSubject("Daily Motivation");
            message.setText("Don't focus on your past, focus on your future!\n \n Work Harder! \n \n Use Gorilla store products to strive in life! \n" + new Date());
            mailSender.send(message);
            System.out.println("Email sent to: " + user.getUsername() + " at " + new Date());
        }
    }
}
