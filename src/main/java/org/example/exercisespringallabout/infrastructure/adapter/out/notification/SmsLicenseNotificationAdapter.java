package org.example.exercisespringallabout.infrastructure.adapter.out.notification;

import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.springframework.stereotype.Service;

@Slf4j
@Service("smsLicenseNotificationAdapter")
public class SmsLicenseNotificationAdapter implements LicenseNotificationPort {

    @Override
    public boolean notify(String email, String message) {
        try {
            log.info("📱 SMS 전송: {} -> {}", email, message);
            return true;
        } catch (Exception e) {
            log.error("SMS 전송 실패: {}", email, e);
            return false;
        }
    }
}
