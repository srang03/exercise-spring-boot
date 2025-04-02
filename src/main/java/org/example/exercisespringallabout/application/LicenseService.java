package org.example.exercisespringallabout.application;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.annotation.RequiresRoles;
import org.example.exercisespringallabout.config.NotificationProperties;
import org.example.exercisespringallabout.domain.License;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.example.exercisespringallabout.aop.Role;
import org.example.exercisespringallabout.dto.LicenseRequest;
import org.example.exercisespringallabout.dto.LicenseResponse;
import org.example.exercisespringallabout.vo.Email;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
public class LicenseService implements IssueLicenseUseCase {
    private final LicenseNotificationPort licenseNotificationPort;
    private final NotificationProperties props;

    public LicenseService(LicenseNotificationPort licenseNotificationPort, NotificationProperties props) {
        this.licenseNotificationPort = licenseNotificationPort;
        this.props = props;
    }

    @Override
    public LicenseResponse issueLicense(@Valid LicenseRequest licenseRequest) {
        if(licenseRequest == null || licenseRequest.getEmail().isBlank() || licenseRequest.getEmail().contains("fail")) {
            throw new IllegalArgumentException("잘못된 사용자입니다.");
        }

        String licenseKey = UUID.randomUUID().toString();
        License license = new License(licenseRequest.getId(), licenseRequest.getCount(), licenseKey, new Email(licenseRequest.getEmail()),true);
        System.out.println("🎫 라이선스 발급 완료: " + licenseKey);

        String type = props.getNotificationType();
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
        licenseNotificationPort.notify(licenseRequest.getEmail() + " 님의 라이선스가 발급되었습니다.");

        return LicenseResponse.builder().id(licenseRequest.getId()).licenseKey(licenseKey).build();
    }
    @RequiresRoles({Role.ADMIN})
    public void revokeLicense(String userName){
        log.info("LicenseService.revokeLicense() called");
        System.out.println("🗑️ 라이선스 삭제 완료: " + userName);
    }
}
