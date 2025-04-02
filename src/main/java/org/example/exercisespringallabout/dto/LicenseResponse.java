package org.example.exercisespringallabout.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LicenseResponse {
    private String id;
    private String licenseKey;
}
