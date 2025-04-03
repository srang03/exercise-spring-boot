package org.example.exercisespringallabout.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicenseResponse {
    private String id;
    private String licenseKey;
    private String email;
}
