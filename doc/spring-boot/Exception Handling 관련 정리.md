# 📘 주제 6: 예외 처리

---

## ✅ 1. 예외를 처리하지 않으면 서버가 종료되나요?

- 아니요. **Spring Boot는 요청 단위로 예외를 처리**하기 때문에, 예외가 발생해도 해당 요청만 실패합니다.
- 하지만 **예외를 무한 반복하거나 자원을 점유**하는 경우, 서버가 과부하로 인해 장애가 발생할 수 있습니다.
- 따라서 **예외는 반드시 명시적으로 처리**해 주어야 합니다.

---

## ✅ 2. 예외가 서비스에서 발생하면 컨트롤러는 계속 실행되나요?

- 아니요. `Service` 계층에서 예외를 `throw`하면 **컨트롤러의 후속 코드는 실행되지 않고 중단**됩니다.
- 예외는 즉시 `@RestControllerAdvice`로 전달되어, 응답 처리는 전역 핸들러가 담당합니다.

---

## ✅ 3. 커스텀 예외 응답을 만들고 싶은데 ResponseEntity를 꼭 써야 하나요?

- `ResponseEntity`를 사용하지 않으면, 응답 객체는 `200 OK`로 리턴됩니다.
- 커스텀 응답 객체(`ApiResponse`)만 사용하면, **HTTP 상태 코드를 직접 제어할 수 없습니다.**
- 실무에서는 `ResponseEntity<ApiResponse<?>>` 형태로 반환하여 **응답 바디 + 상태 코드**를 동시에 제어합니다.

---

## ✅ 4. `@ExceptionHandler`에서 예외 응답을 JSON으로 반환하려면?

- 반환 타입을 `ResponseEntity<ErrorResponse>` 형태로 지정하고,
- `body()`에 JSON 형태로 커스텀 DTO를 넣으면 됩니다.
```java
@ExceptionHandler(MyException.class)
public ResponseEntity<ErrorResponse> handle(MyException ex) {
    return ResponseEntity.status(400).body(new ErrorResponse("CODE", ex.getMessage(), now()));
}
```

---

## ✅ 5. `@ResponseStatus`를 커스텀 예외에 붙이면 무조건 코드가 설정되나요?

- 네. `@ResponseStatus(HttpStatus.NOT_FOUND)` 처럼 지정하면, 해당 예외가 발생할 때 **HTTP 상태 코드가 자동 적용**됩니다.
- 단점: **응답 바디를 커스터마이징할 수 없습니다.**
- 따라서 실무에서는 대부분 `@ExceptionHandler` 방식 선호

---

## ✅ 6. 도메인 예외와 서비스 예외는 왜 구분해야 하나요?

- 각 계층의 책임이 다르기 때문입니다:
    - **도메인 예외**: 객체의 상태, 값 자체의 유효성 (`InvalidEmailException`)
    - **서비스 예외**: 흐름, 비즈니스 조건 위반 (`LicenseAlreadyIssuedException`)
- 이렇게 분리하면 **디버깅, 유지보수, 책임 추적이 훨씬 쉬워집니다.**

---

## ✅ 7. ErrorResponse에 어떤 정보들을 포함해야 하나요?

| 필드 | 설명 |
|------|------|
| `code` | 비즈니스 코드 (`USER-001`, `LICENSE-002`) |
| `message` | 사용자에게 보여줄 에러 메시지 |
| `timestamp` | 예외 발생 시각 (서버 기준) |

→ 일관된 에러 포맷은 클라이언트의 예외 처리 로직을 단순화합니다.

---

## ✅ 8. `ResponseEntity` 없이 응답 코드를 제어할 수 있는 다른 방법은?

| 방법 | 설명 | 동적 제어 |
|------|------|-----------|
| `@ResponseStatus` | 정적 설정만 가능 | ❌ |
| `ResponseEntity` | 코드 + 바디 모두 제어 가능 | ✅ |
| 커스텀 객체 반환 | 상태코드는 200 고정 | ❌

→ 실무에서는 대부분 `ResponseEntity`를 사용합니다.

---

## ✅ 결론 요약

- 예외는 반드시 계층별로 책임을 분리하여 처리
- `ResponseEntity`로 상태 코드와 JSON 응답을 명확하게 제어
- 전역 핸들러에서 일관된 응답 포맷을 제공하여 프론트엔드 협업 효율을 높임