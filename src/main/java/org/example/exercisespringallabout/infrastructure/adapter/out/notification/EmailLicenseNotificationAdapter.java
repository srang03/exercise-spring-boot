package org.example.exercisespringallabout.infrastructure.adapter.out.notification;

import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.springframework.stereotype.Service;

@Slf4j
@Service("emailLicenseNotificationAdapter")
public class EmailLicenseNotificationAdapter implements LicenseNotificationPort {

    @Override
    public boolean notify(String email, String message) {
        try {
            log.info("📧 이메일 전송: {} -> {}", email, message);
            return true;
        } catch (Exception e) {
            log.error("이메일 전송 실패: {}", email, e);
            return false;
        }
    }
}
