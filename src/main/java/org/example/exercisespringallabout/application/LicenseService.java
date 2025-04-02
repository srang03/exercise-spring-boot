package org.example.exercisespringallabout.application;

import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.annotation.RequiresRoles;
import org.example.exercisespringallabout.config.NotificationProperties;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.example.exercisespringallabout.aop.Role;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LicenseService {
    private final LicenseNotificationPort licenseNotificationPort;
    private final NotificationProperties props;

    public LicenseService(LicenseNotificationPort licenseNotificationPort, NotificationProperties props) {
        this.licenseNotificationPort = licenseNotificationPort;
        this.props = props;
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
        String type = props.getNotificationType();

        System.out.println("🎫 라이선스 발급 완료: " + licenseKey);

        if (type.startsWith("email")) {
            var email = props.getEmail();
            System.out.println("📧 이메일 발신자: " + email.getSenderName());
            System.out.println("🔁 재시도 횟수: " + email.getRetryCount());

        } else if (type.startsWith("sms")) {
            var sms = props.getSms();
            System.out.println("📱 SMS 발신 번호: " + sms.getSenderNumber());
            System.out.println("🔁 재시도 횟수: " + sms.getRetryCount());
        }
        log.info("LicenseService.issueLicense() called");
        licenseNotificationPort.notify(userName + " 님의 라이선스가 발급되었습니다.");
    }

    @RequiresRoles({Role.ADMIN})
    public void revokeLicense(String userName){
        log.info("LicenseService.revokeLicense() called");
        System.out.println("🗑️ 라이선스 삭제 완료: " + userName);
    }
}
