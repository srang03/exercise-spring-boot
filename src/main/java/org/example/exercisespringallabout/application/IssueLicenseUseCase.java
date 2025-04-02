package org.example.exercisespringallabout.application;

import org.example.exercisespringallabout.domain.license.License;
import org.example.exercisespringallabout.dto.LicenseRequest;
import org.example.exercisespringallabout.dto.LicenseResponse;

public interface IssueLicenseUseCase {
    LicenseResponse issueLicense(LicenseRequest licenseRequest);
}
