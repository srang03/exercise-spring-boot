package org.example.exercisespringallabout.adapter.in.web;

import org.example.exercisespringallabout.application.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/licenses")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{userName}")
    public ResponseEntity issue(@PathVariable String userName){
        licenseService.issueLicense(userName);
        return ResponseEntity.ok().build();
    }
}
