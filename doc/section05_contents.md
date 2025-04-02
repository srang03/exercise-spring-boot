# ✅ 주제 5: Bean Validation & 계층별 유효성 검사 정리

---

## 🧩 파트 1. Bean Validation 기본 개념

### 📌 Bean Validation 이란?

- JSR-303 / JSR-380 표준에 따른 **Java Bean 기반 유효성 검사**
- DTO나 VO 클래스에 제약 조건을 선언하고, 실행 시점에 자동 검증
- Spring Boot는 `hibernate-validator` 기반으로 기본 제공

---

### 📌 주요 어노테이션

| 어노테이션 | 설명 |
|------------|------|
| `@NotNull` | null 금지 |
| `@NotBlank` | null + 빈 문자열 금지 (`String` 전용) |
| `@NotEmpty` | null + 빈 값 금지 (컬렉션 등) |
| `@Email` | 이메일 형식 검사 |
| `@Min`, `@Max` | 숫자 최소/최대값 검사 |
| `@Pattern` | 정규 표현식 검사 |

---

### 📌 적용 대상

- 컨트롤러 `@RequestBody`, `@ModelAttribute`
- 서비스 메서드 파라미터 (`@Validated` 필요)
- 도메인 생성자나 팩토리 메서드 내부

---

## 🧩 파트 2. 계층별 Validation 책임 분리

### ✅ 1. Controller 계층 - `@Valid`, `BindingResult`

```java
@PostMapping
public ResponseEntity<?> issue(@RequestBody @Valid LicenseRequest request,
                                BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    licenseService.issueLicense(request);
}
```

| 구성 요소 | 설명 |
|-----------|------|
| `@Valid` | DTO 필드 유효성 검사 수행 |
| `BindingResult` | 검사 실패 시 예외를 던지지 않고 오류 수집 가능 |

---

### ✅ 2. Service 계층 - `@Validated` + `@Valid`

```java
@Service
@Validated
public class LicenseService {
    public void issueLicense(@Valid LicenseRequest request) {
        // 유효성 검사 수행됨 (AOP 기반)
    }
}
```

| 조건 | 설명 |
|------|------|
| `@Validated` | AOP 기반 검증 프록시를 활성화 |
| `@Valid` | 메서드 파라미터에서 실제 유효성 검사 수행 |

- 컨트롤러를 통하지 않는 **배치, 이벤트, 테스트 등에서의 유효성 검사 보장** 목적

---

### ✅ 3. Domain 계층 - 생성자 검증 (불변, 객체 자체 유효성)

```java
public class Email {
    private final String value;

    public Email(String value) {
        if (!value.matches(".+@.+\..+")) {
            throw new IllegalArgumentException("이메일 형식 오류");
        }
        this.value = value;
    }
}
```

- 비즈니스 객체의 **불변성과 유효 상태 보장**
- 외부 유효성에 의존하지 않음

---

## 🧠 예외 처리 흐름

| 계층 | 예외 클래스 | 설명 |
|------|-------------|------|
| Controller | `MethodArgumentNotValidException` | `@RequestBody @Valid` 실패 |
| Service | `ConstraintViolationException` | `@Validated` 클래스 내부 `@Valid` 실패 |
| Model Binding | `BindException` | `@ModelAttribute` 바인딩 실패 |

---

## 🎯 요약

| 계층 | 검증 위치 | 유효성 도구 | 특징 |
|------|------------|-------------|------|
| Controller | 입력값 검증 | `@Valid` + `BindingResult` | 빠른 오류 응답 |
| Service | 비즈니스 검증 | `@Validated` + `@Valid` | AOP 기반, 재사용성 보장 |
| Domain | 본질적 유효성 | 생성자 수동 검증 | 불변, 엔티티 안전성 확보 |

---

필요 시 `@ControllerAdvice`를 통해 예외 메시지 일관화 가능.