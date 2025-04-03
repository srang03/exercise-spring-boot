package org.example.exercisespringallabout.application.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.application.port.in.IssueLicenseUseCase;
import org.example.exercisespringallabout.application.port.out.LicenseAssembler;
import org.example.exercisespringallabout.application.port.out.LicenseRepository;
import org.example.exercisespringallabout.application.port.out.NotificationService;
import org.example.exercisespringallabout.common.annotation.RequiresRoles;
import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.example.exercisespringallabout.domain.model.License;
import org.example.exercisespringallabout.infrastructure.aop.Role;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

/**
 * 라이센스 발급 유스케이스 구현
 * 애플리케이션 계층에서 비즈니스 흐름을 조정
 */
@Service
@Slf4j
@Validated
@RequiredArgsConstructor
public class LicenseService implements IssueLicenseUseCase {
    // 포트 인터페이스에 의존 (구현체는 Spring DI가 주입)
    private final LicenseRepository licenseRepository;
    private final NotificationService notificationService;
    private final LicenseAssembler licenseAssembler;

    @Override
    public LicenseResponse issueLicense(@Valid LicenseRequest licenseRequest) {
        // 1. 입력 검증
        validateLicenseRequest(licenseRequest);
        
        // 2. DTO → 도메인 모델 변환 (어셈블러 사용)
        License license = licenseAssembler.toEntity(licenseRequest);
        
        // 3. 비즈니스 로직 실행
        String licenseKey = generateLicenseKey();
        license = license.withLicenseKey(licenseKey).withActive(true);
        
        // 4. 저장 (포트 인터페이스 사용)
        licenseRepository.save(license);
        log.info("🎫 라이선스 발급 완료: {}", licenseKey);
        
        // 5. 알림 전송 (포트 인터페이스 사용)
        notificationService.notify(
            license.getEmail().getValue(), 
            "님의 라이선스가 발급되었습니다."
        );
        
        // 6. 응답 생성 (어셈블러 사용)
        return licenseAssembler.toResponse(license);
    }

    @Override
    @RequiresRoles({Role.ADMIN})
    public String revoke(String userName) {
        log.info("라이선스 삭제 요청: {}", userName);
        
        // 1. 라이센스 조회 (간단한 예제를 위해 userName을 ID로 간주)
        License license = licenseRepository.findById(userName);
        
        if (license == null) {
            log.warn("삭제할 라이선스를 찾을 수 없음: {}", userName);
            return "라이선스를 찾을 수 없습니다.";
        }
        
        // 2. 라이센스 삭제
        license = licenseRepository.deleteById(userName);
        
        // 3. 알림 전송
        boolean notified = notificationService.notify(
            license.getEmail().getValue(),
            "님의 라이선스가 취소되었습니다."
        );
        
        // 4. 결과 반환
        log.info("라이선스 삭제 완료: {}", userName);
        return "라이선스가 삭제되었습니다. 알림 전송 " + (notified ? "성공" : "실패");
    }

    @Override
    public LicenseResponse retrieveById(String id) {
        // 1. 라이센스 조회 (포트 인터페이스 사용)
        License license = licenseRepository.findById(id);
        
        // 2. 존재 여부 확인
        if (license == null) {
            throw new IllegalArgumentException("라이선스를 찾을 수 없습니다.");
        }
        
        // 3. 응답 생성 (어셈블러 사용)
        return licenseAssembler.toResponse(license);
    }
    
    // 헬퍼 메서드
    
    private void validateLicenseRequest(LicenseRequest request) {
        if (request == null || 
            request.getEmail() == null || 
            request.getEmail().isBlank() || 
            request.getEmail().contains("fail")) {
            throw new IllegalArgumentException("잘못된 사용자입니다.");
        }
    }
    
    private String generateLicenseKey() {
        return UUID.randomUUID().toString();
    }
}
