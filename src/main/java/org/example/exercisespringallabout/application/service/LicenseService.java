package org.example.exercisespringallabout.application.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.common.annotation.RequiresRoles;
import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.example.exercisespringallabout.domain.port.out.LicenseNotificationPort;
import org.example.exercisespringallabout.domain.port.out.LicenseRepositoryPort;
import org.example.exercisespringallabout.infrastructure.adapter.out.persistence.entity.LicenseEntity;
import org.example.exercisespringallabout.infrastructure.aop.Role;
import org.example.exercisespringallabout.infrastructure.config.NotificationProperties;
import org.example.exercisespringallabout.infrastructure.mapper.LicenseMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

import org.example.exercisespringallabout.application.port.in.IssueLicenseUseCase;
import org.example.exercisespringallabout.domain.model.License;

@Service
@Slf4j
@Validated
public class LicenseService implements IssueLicenseUseCase {
    private final LicenseNotificationPort licenseNotificationPort;
    private final NotificationProperties props;
    private final LicenseRepositoryPort licenseRepositoryPort;
    private final LicenseMapper licenseMapper;

    public LicenseService(LicenseMapper licenseMapper, LicenseNotificationPort licenseNotificationPort, NotificationProperties props, LicenseRepositoryPort licenseRepositoryPort) {
        this.licenseMapper = licenseMapper;
        this.licenseNotificationPort = licenseNotificationPort;
        this.props = props;
        this.licenseRepositoryPort = licenseRepositoryPort;
    }

    @Override
    public LicenseResponse issueLicense(@Valid LicenseRequest licenseRequest) {
        if (licenseRequest == null || licenseRequest.getEmail().isBlank() || licenseRequest.getEmail().contains("fail")) {
            throw new IllegalArgumentException("잘못된 사용자입니다.");
        }

        // LicenseRequest -> License 변환은 Mapper 사용
        License license = licenseMapper.toDomain(licenseRequest);

        // 라이센스 키 생성 및 설정 (이 부분은 수동으로 처리)
        String licenseKey = UUID.randomUUID().toString();
        license = license.withLicenseKey(licenseKey).withActive(true);

        // 라이센스 저장
        licenseRepositoryPort.save(licenseMapper.toEntity(license));

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
        licenseNotificationPort.notify(license.getEmail().getValue() + " 님의 라이선스가 발급되었습니다.");

        // License -> LicenseResponse 변환은 Mapper 사용
        LicenseResponse response = licenseMapper.toResponse(license);

        // Email은 별도로 설정 (Mapper에서 무시되므로)
        return LicenseResponse.builder()
                .id(response.getId())
                .licenseKey(response.getLicenseKey())
                .email(license.getEmail().getValue())
                .build();
    }

    @Override
    public String revoke(String userName) {
        return "구현 안됨";
    }

    @Override
    public LicenseResponse retrieveById(String id) {
        LicenseEntity licenseEntity = licenseRepositoryPort.findById(id);
        if (licenseEntity == null) {
            throw new IllegalArgumentException("라이선스를 찾을 수 없습니다.");
        }
        License license = licenseMapper.toDomain(licenseEntity);
        LicenseResponse response = licenseMapper.toResponse(license);

        // Email 필드 별도 설정
        return LicenseResponse.builder()
                .id(response.getId())
                .licenseKey(response.getLicenseKey())
                .email(license.getEmail().getValue())
                .build();
    }

    @RequiresRoles({Role.ADMIN})
    public void revokeLicense(String userName) {
        log.info("LicenseService.revokeLicense() called");
        System.out.println("🗑️ 라이선스 삭제 완료: " + userName);
    }
}
