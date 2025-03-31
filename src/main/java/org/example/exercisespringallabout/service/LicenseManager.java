package org.example.exercisespringallabout.service;

import org.springframework.stereotype.Service;

@Service
public class LicenseManager {
    private final LicenseNotificationService licenseNotificationService;

    public LicenseManager(LicenseNotificationService licenseNotificationService) {
        this.licenseNotificationService = licenseNotificationService;
    }

    public void issueLicense(String userName) {
        String licenseKey = "ABC-1234";
        System.out.println("🎫 라이선스 발급 완료: " + licenseKey);

        licenseNotificationService.notify(userName + " 님의 라이선스가 발급되었습니다.");
    }
}
