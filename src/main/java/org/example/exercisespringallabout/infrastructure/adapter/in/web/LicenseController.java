package org.example.exercisespringallabout.infrastructure.adapter.in.web;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.application.port.in.IssueLicenseUseCase;
import org.example.exercisespringallabout.common.dto.LicenseRequest;
import org.example.exercisespringallabout.common.dto.LicenseResponse;
import org.example.exercisespringallabout.infrastructure.aop.Role;
import org.example.exercisespringallabout.infrastructure.context.UserContext;
import org.example.exercisespringallabout.infrastructure.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/licenses")
@RequiredArgsConstructor
public class LicenseController {
    private final IssueLicenseUseCase licenseService;

    @PostMapping("")
    public ResponseEntity<Object> createLicense(@RequestBody @Valid LicenseRequest licenseRequest,
                                                BindingResult bindingResult,
                                                HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("잘못된 요청입니다.", bindingResult);
        }

        LicenseResponse licenseResponse = licenseService.issueLicense(licenseRequest);
        return new ResponseEntity<>(licenseResponse, HttpStatus.CREATED);
    }


    @DeleteMapping("/{userName}")
    public String revoke(@PathVariable String userName) {
        if (userName.equals("admin")) {
            UserContext.setRole(Role.ADMIN); // 관리자 사용자로 시도
        } else {
            UserContext.setRole(Role.USER); // 일반 사용자로 시도
        }
        licenseService.revoke(userName);
        UserContext.clear();
        return "삭제 완료";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLicenseById(@PathVariable String id) {
        LicenseResponse licenseResponse = licenseService.retrieveById(id);
        return new ResponseEntity<>(licenseResponse, HttpStatus.OK);
    }
}
