package org.example.exercisespringallabout.adapter.in.web;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.exercisespringallabout.application.LicenseService;
import org.example.exercisespringallabout.aop.Role;
import org.example.exercisespringallabout.dto.LicenseRequest;
import org.example.exercisespringallabout.dto.LicenseResponse;
import org.example.exercisespringallabout.dto.ApiResponse;
import org.example.exercisespringallabout.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.example.exercisespringallabout.context.UserContext;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/licenses")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping("")
    public ApiResponse<Object> createLicense(@RequestBody @Valid LicenseRequest licenseRequest,
                                                      BindingResult bindingResult,
                                             HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());

            List<ValidationErrorResponse.FieldError> errors = bindingResult.getFieldErrors().stream()
                    .map(err -> ValidationErrorResponse.FieldError.builder()
                            .field(err.getField())
                            .reason(err.getDefaultMessage())
                            .build())
                    .toList();

            return new ApiResponse<>(
                    400,
                    "Bad Request",
                    ValidationErrorResponse.builder().fieldErrors(errors).build()
            );
        }

        LicenseResponse licenseResponse = licenseService.issueLicense(licenseRequest);
        return new ApiResponse<>(200, "OK", licenseResponse);
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
