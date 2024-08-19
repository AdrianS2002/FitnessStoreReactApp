package com.example.ProiectMagazinSuplimenteAlimentare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ScheduledTasks class is used to send emails at a specific time.
 */
@Component
public class ScheduledTasks {
    @Autowired
    private EmailCronjobService emailCronjobService;

    @Scheduled(cron = "* * * 1 * *")
    public void sendEmail() {
        System.out.println("Sending email...");
        emailCronjobService.sendEmail();
    }
}
