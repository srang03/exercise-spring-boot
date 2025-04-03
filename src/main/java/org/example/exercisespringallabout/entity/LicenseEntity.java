package org.example.exercisespringallabout.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class LicenseEntity {
    private String id;
    private String licenseKey;

    public String getId() {
        return id;
    }

    public String getLicenseKey() {
        return licenseKey;
    }
}
