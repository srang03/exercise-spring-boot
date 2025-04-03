package org.example.exercisespringallabout.application.port.out;

import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.example.exercisespringallabout.domain.model.License;

/**
 * 애플리케이션 계층에서 정의하는 어셈블러 인터페이스
 * DTO와 도메인 모델 간의 변환을 담당
 */
public interface LicenseAssembler {
    /**
     * LicenseRequest DTO를 도메인 모델로 변환
     */
    License toEntity(LicenseRequest request);
    
    /**
     * 도메인 모델을 LicenseResponse DTO로 변환
     */
    LicenseResponse toResponse(License license);
} 