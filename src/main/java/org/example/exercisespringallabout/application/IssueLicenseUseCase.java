package org.example.exercisespringallabout.application;

import jakarta.validation.Valid;
import org.example.exercisespringallabout.dto.LicenseRequest;
import org.example.exercisespringallabout.dto.LicenseResponse;

public interface IssueLicenseUseCase {
    LicenseResponse issueLicense(@Valid LicenseRequest licenseRequest);
}
