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

/**
 * 라이센스 관련 웹 요청을 처리하는 컨트롤러
 * 인프라스트럭처 계층의 어댑터로서 HTTP 요청을 애플리케이션 계층의 유스케이스로 변환
 */
@Slf4j
@RestController
@RequestMapping("/licenses")
@RequiredArgsConstructor
public class LicenseController {
    private final IssueLicenseUseCase licenseService;

    /**
     * 라이센스 발급 요청 처리
     */
    @PostMapping("")
    public ResponseEntity<Object> createLicense(@RequestBody @Valid LicenseRequest licenseRequest,
                                               BindingResult bindingResult) {
        log.debug("라이센스 발급 요청: {}", licenseRequest.getId());
        
        // 입력값 검증
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("잘못된 요청입니다.", bindingResult);
        }

        // 유스케이스 실행
        LicenseResponse licenseResponse = licenseService.issueLicense(licenseRequest);
        log.info("라이센스 발급 완료: {}", licenseResponse.getId());
        
        return new ResponseEntity<>(licenseResponse, HttpStatus.CREATED);
    }

    /**
     * 라이센스 삭제 요청 처리
     */
    @DeleteMapping("/{userName}")
    public ResponseEntity<String> revoke(@PathVariable String userName) {
        log.debug("라이센스 삭제 요청: {}", userName);
        
        try {
            // 권한 설정 (예제 용도)
            if (userName.equals("admin")) {
                UserContext.setRole(Role.ADMIN);
            } else {
                UserContext.setRole(Role.USER);
            }
            
            // 유스케이스 실행
            String result = licenseService.revoke(userName);
            log.info("라이센스 삭제 완료: {}", userName);
            
            return ResponseEntity.ok(result);
        } finally {
            // 컨텍스트 정리
            UserContext.clear();
        }
    }

    /**
     * 라이센스 조회 요청 처리
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getLicenseById(@PathVariable String id) {
        log.debug("라이센스 조회 요청: {}", id);
        
        // 유스케이스 실행
        LicenseResponse licenseResponse = licenseService.retrieveById(id);
        log.info("라이센스 조회 완료: {}", id);
        
        return new ResponseEntity<>(licenseResponse, HttpStatus.OK);
    }
}
