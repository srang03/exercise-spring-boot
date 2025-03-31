package org.example.exercisespringallabout.service;

import org.springframework.stereotype.Service;

@Service("emailLicenseNotificationService")
public class EmailLicenseNotificationService implements LicenseNotificationService{

    @Override
    public void notify(String message) {
        System.out.println("📧 이메일 전송: " + message);
    }
}
