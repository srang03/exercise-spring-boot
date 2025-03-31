package org.example.exercisespringallabout.service;

import org.springframework.stereotype.Service;

@Service("smsLicenseNotificationService")
public class SmsLicenseNotificationService implements LicenseNotificationService {

    @Override
    public void notify(String message) {
        System.out.println("📱 SMS 전송: " + message);
    }
}
