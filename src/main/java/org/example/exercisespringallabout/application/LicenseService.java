package org.example.exercisespringallabout.application;

import org.example.exercisespringallabout.annotation.RequiresRoles;
import org.example.exercisespringallabout.config.NotificationProperties;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.example.exercisespringallabout.aop.Role;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    private final LicenseNotificationPort licenseNotificationPort;
    private final NotificationProperties properties;

    public LicenseService(LicenseNotificationPort licenseNotificationPort, NotificationProperties properties) {
        this.licenseNotificationPort = licenseNotificationPort;
        this.properties = properties;
    }

    public void issueLicense(String userName) {
        try{
            Thread.sleep(500);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if(userName == null || userName.isBlank() || userName.contains("fail")) {
            throw new IllegalArgumentException("잘못된 사용자입니다.");
        }

        String licenseKey = "ABC-1234";
        System.out.println("🎫 라이선스 발급 완료: " + licenseKey);
        System.out.println("📤 알림 방식: " + properties.getType());
        System.out.println("👤 발신자 이름: " + properties.getSenderName());
        System.out.println("🔁 재시도 횟수: " + properties.getRetryCount());

        licenseNotificationPort.notify(userName + " 님의 라이선스가 발급되었습니다.");
    }

    @RequiresRoles({Role.ADMIN})
    public void revokeLicense(String userName){
        System.out.println("🗑️ 라이선스 삭제 완료: " + userName);
    }
}
