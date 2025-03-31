package org.example.exercisespringallabout.application;

import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    private final LicenseNotificationPort licenseNotificationPort;

    public LicenseService(LicenseNotificationPort licenseNotificationPort) {
        this.licenseNotificationPort = licenseNotificationPort;
    }

    public void issueLicense(String userName) {
        String licenseKey = "ABC-1234";
        System.out.println("🎫 라이선스 발급 완료: " + licenseKey);

        licenseNotificationPort.notify(userName + " 님의 라이선스가 발급되었습니다.");
    }
}
