package org.example.exercisespringallabout.adapter.in.web;

import org.example.exercisespringallabout.application.LicenseService;
import org.example.exercisespringallabout.domain.user.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.exercisespringallabout.domain.user.UserContext;

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
