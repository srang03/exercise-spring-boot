package org.example.exercisespringallabout.application.port.in;


import jakarta.validation.Valid;
import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface IssueLicenseUseCase {
    LicenseResponse issueLicense(@Valid LicenseRequest licenseRequest);
    String revoke(@PathVariable String userName);
    LicenseResponse retrieveById(@Valid String id);
}
