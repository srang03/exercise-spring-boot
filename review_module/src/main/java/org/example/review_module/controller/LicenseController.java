package org.example.review_module.controller;

import org.example.review_module.annotation.NameFilter;
import org.example.review_module.service.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/licenses")
public class LicenseController {
    LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{userName}")

    public ResponseEntity issue(@PathVariable String userName) {
        licenseService.createLicense(userName);
        return ResponseEntity.ok().build();
    }
}
