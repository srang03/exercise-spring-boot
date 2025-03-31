package org.example.exercisespringallabout.controller;

import org.example.exercisespringallabout.service.LicenseManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/licenses")
public class LicenseController {
    private final LicenseManager licenseManager;

    public LicenseController(LicenseManager licenseManager) {
        this.licenseManager = licenseManager;
    }

    @GetMapping("/{userName}")
    public ResponseEntity issue(@PathVariable String userName){
        licenseManager.issueLicense(userName);
        return ResponseEntity.ok().build();
    }
}
