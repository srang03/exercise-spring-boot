package org.example.exercisespringallabout.infrastructure.adapter.out.assembler;

import lombok.RequiredArgsConstructor;
import org.example.exercisespringallabout.application.port.out.LicenseAssembler;
import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.example.exercisespringallabout.domain.model.License;
import org.example.exercisespringallabout.domain.vo.Email;
import org.example.exercisespringallabout.infrastructure.mapper.LicenseMapper;
import org.springframework.stereotype.Component;

/**
 * LicenseAssembler 인터페이스의 구현체
 * 인프라스트럭처 계층에서 MapStruct와 같은 구체적인 기술을 사용하여 변환 로직 구현
 */
@Component
@RequiredArgsConstructor
public class LicenseAssemblerImpl implements LicenseAssembler {
    private final LicenseMapper licenseMapper;

    @Override
    public License toEntity(LicenseRequest request) {
        // MapStruct를 사용하여 변환 (비즈니스 로직은 포함하지 않음)
        return licenseMapper.toDomain(request);
    }

    @Override
    public LicenseResponse toResponse(License license) {
        // MapStruct로 기본 매핑 후 Email을 String으로 처리
        LicenseResponse response = licenseMapper.toResponse(license);
        
        // 완전한 응답 객체 생성
        return LicenseResponse.builder()
                .id(response.getId())
                .licenseKey(response.getLicenseKey())
                .email(license.getEmail().getValue())
                .build();
    }
} 