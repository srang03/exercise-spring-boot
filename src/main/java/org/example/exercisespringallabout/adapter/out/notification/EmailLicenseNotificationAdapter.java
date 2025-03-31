package org.example.exercisespringallabout.adapter.out.notification;

import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.springframework.stereotype.Service;

@Service("emailLicenseNotificationService")
public class EmailLicenseNotificationAdapter implements LicenseNotificationPort {

    @Override
    public void notify(String message) {
        System.out.println("📧 이메일 전송: " + message);
    }
}
