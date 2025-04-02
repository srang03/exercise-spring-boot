package org.example.exercisespringallabout.adapter.in.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.application.LicenseService;
import org.example.exercisespringallabout.aop.Role;
import org.example.exercisespringallabout.dto.LicenseRequest;
import org.example.exercisespringallabout.dto.LicenseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.exercisespringallabout.context.UserContext;


@Slf4j
@RestController
@RequestMapping("/licenses")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping("")
    public ResponseEntity<LicenseResponse> createLicense(@RequestBody @Valid LicenseRequest licenseRequest) {
        LicenseResponse licenseResponse = licenseService.issueLicense(licenseRequest);
        return ResponseEntity.ok().body(licenseResponse);
    }

    @DeleteMapping("/{userName}")
    public String revoke(@PathVariable String userName) {
        if(userName.equals("admin")) {
            UserContext.setRole(Role.ADMIN); // 관리자 사용자로 시도
        }else{
            UserContext.setRole(Role.USER); // 일반 사용자로 시도
        }
        licenseService.revokeLicense(userName);
        UserContext.clear();
        return "삭제 완료";
    }
}
