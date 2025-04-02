package org.example.exercisespringallabout.adapter.in.web;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.example.exercisespringallabout.application.LicenseService;
import org.example.exercisespringallabout.aop.Role;
import org.example.exercisespringallabout.dto.LicenseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.exercisespringallabout.domain.user.UserContext;



@Slf4j
@RestController
@RequestMapping("/licenses")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }


    @PostMapping("")

    public ResponseEntity createLicense(@RequestBody @Valid LicenseRequest licenseRequest) {
        licenseService.createLicense(licenseRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userName}")
    public ResponseEntity issue(@PathVariable String userName){
        licenseService.issueLicense(userName);
        log.info("라이선스 발급 요청 - 사용자: {}", userName);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{userName}")
    public String revoke(@PathVariable String userName) {
        if(userName.equals("admin")) {
            UserContext.setRole(Role.ADMIN); // 관리자 사용자로 시도
        }else{
            UserContext.setRole(Role.USER); // 일반 사용자로 시도
        }
        licenseService.revokeLicense(userName);
        return "삭제 완료";
    }
}
