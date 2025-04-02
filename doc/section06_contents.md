# 📘 주제 6: 예외 처리 (Exception Handling) 정리

---

## ✅ 파트 1. 자바 예외 구조 & 스프링 예외 추상화

### 📌 예외의 종류

| 유형 | 설명 | 예시 |
|------|------|------|
| Checked Exception | 컴파일 타임 강제 처리 | IOException, SQLException |
| Unchecked Exception | 런타임 발생, 선택 처리 | NullPointerException, IllegalArgumentException |

### 📌 스프링 추상화 예외 계층

| 계층 | 예외 |
|------|------|
| Persistence | `DataAccessException` |
| Transaction | `TransactionException` |
| MVC | `HandlerExceptionResolver`, `ServletException` |

> ✅ 스프링은 대부분 `RuntimeException` 기반으로 추상화하여 처리

---

## ✅ 파트 2. @ControllerAdvice 전역 예외 처리

### 📌 핵심 개념

- `@RestControllerAdvice` 또는 `@ControllerAdvice`로 전역 예외 핸들러 구성
- `@ExceptionHandler`를 통해 타입별 예외 처리
- 일관된 JSON 응답 포맷으로 반환

### 📌 예외 응답 예시

```json
{
  "code": "ERR-001",
  "message": "잘못된 요청입니다",
  "timestamp": "2025-04-01T22:30:00"
}
```

---

## ✅ 파트 3. 커스텀 예외 설계 전략

### 📌 도입 목적

- 도메인 의미를 반영한 예외 설계
- 계층별로 적절한 책임 분리
- 에러 코드 체계화 (`LICENSE-001`, `USER-002`, `COMMON-500`)
- 테스트 및 디버깅 용이

### 📌 예시 코드

```java
public class LicenseAlreadyIssuedException extends AbstractBusinessException {
    public LicenseAlreadyIssuedException(String userId) {
        super("이미 라이선스를 발급받은 사용자입니다: " + userId);
    }
    public String getErrorCode() { return "LICENSE-001"; }
}
```

---

## ✅ 파트 4. 예외 응답 구조 표준화

### 📌 공통 응답 DTO

```java
public record ErrorResponse(String code, String message, LocalDateTime timestamp) {
    public static ErrorResponse from(AbstractBusinessException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage(), LocalDateTime.now());
    }
}
```

### 📌 적용 방식

```java
@ExceptionHandler(AbstractBusinessException.class)
public ResponseEntity<ErrorResponse> handleBusiness(AbstractBusinessException ex) {
    return ResponseEntity.badRequest().body(ErrorResponse.from(ex));
}
```

### 📌 Validation 예외도 통합 처리

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    return ResponseEntity.badRequest().body(ErrorResponse.of("VALID-001", msg));
}
```

---

## ✅ 요약

- 예외는 `@RestControllerAdvice`로 통합 처리
- 예외마다 코드 + 메시지 + 시간 포함한 구조 설계
- 도메인, 서비스 계층에서 의미 있는 커스텀 예외 분리
- 응답은 항상 `ResponseEntity<ErrorResponse>`로 제어

---