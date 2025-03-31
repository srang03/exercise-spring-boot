package org.example.exercisespringallabout.adapter.out.notification;

import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.springframework.stereotype.Service;

@Service("smsLicenseNotificationAdapter")
public class SmsLicenseNotificationAdapter implements LicenseNotificationPort {

    @Override
    public void notify(String message) {
        System.out.println("📱 SMS 전송: " + message);
    }
}
