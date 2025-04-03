package org.example.exercisespringallabout.infrastructure.mapper;

import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.example.exercisespringallabout.domain.model.License;
import org.example.exercisespringallabout.domain.vo.Email;
import org.example.exercisespringallabout.infrastructure.adapter.out.persistence.entity.LicenseEntity;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring", 
    imports = {Email.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    builder = @Builder(disableBuilder = false)
)
public interface LicenseMapper {

    @Mappings({
        @Mapping(target = "licenseKey", ignore = true),
        @Mapping(target = "email", expression = "java(stringToEmail(licenseRequest.getEmail()))"),
        @Mapping(target = "active", constant = "false")
    })
    License toDomain(LicenseRequest licenseRequest);
    
    @Mappings({
        @Mapping(target = "email", expression = "java(emailToString(license.getEmail()))")
    })
    LicenseResponse toResponse(License license);
    
    @Mappings({
        @Mapping(target = "email", expression = "java(emailToString(license.getEmail()))"),
    })
    LicenseEntity toEntity(License license);
    
    @Mappings({
        @Mapping(target = "email", expression = "java(stringToEmail(licenseEntity.getEmail()))"),
    })
    License toDomain(LicenseEntity licenseEntity);
    
    // 편의 메서드 추가
    default String emailToString(Email email) {
        return email != null ? email.getValue() : null;
    }
    
    default Email stringToEmail(String email) {
        return email != null ? new Email(email) : null;
    }
}
