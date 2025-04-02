# ✅ 주제 5: Bean Validation

---

## 📘 공통 개요

Spring Boot에서 Bean Validation은 `hibernate-validator`를 기반으로 동작하며, DTO/Request 객체, 도메인 객체 등 다양한 계층에서 유효성 검사를 수행할 수 있습니다.

---

### ✅ 1. `@Valid`만 붙이면 왜 서비스에서 검증이 동작하지 않나요?

- `@Valid`는 단독으로 작동하지 않으며, **AOP 기반의 트리거**가 필요합니다.
- Service 클래스에는 `@Validated`를 꼭 붙여야 메서드 파라미터 검증이 작동합니다.

```java
@Service
@Validated // ← 이게 꼭 필요함!
public class LicenseService {
    public void issueLicense(@Valid LicenseRequest request) { ... }
}
```

---

### ✅ 2. 컨트롤러에서 검증했는데 서비스에서 또 검증해야 하나요?

- 일반적으로 컨트롤러에서만 검증해도 충분하지만,
- 다음과 같은 경우는 서비스에서도 검증 필요:
    - Batch, Scheduler, Event 등 **직접 서비스 호출** 시
    - 테스트 코드에서 검증 확인 필요할 때
    - 비즈니스 로직 자체가 검증 대상일 경우

---

### ✅ 3. 도메인 객체 생성자 vs 커스텀 Validator 차이?

| 구분 | 생성자 검증 | 커스텀 Validator |
|------|-------------|------------------|
| 위치 | 도메인 객체 내부 | Validator 클래스 외부화 |
| 대상 | Email, Money, 등 불변 VO | 사용자 ID, 패스워드 등 입력 필드 |
| 처리 | 생성 시점에 예외 발생 | 유효성 실패 시 메시지 반환 |
| 장점 | 불변성, 단일 책임 | 재사용성, 메시지 커스터마이징 용이 |
| 단점 | 메시지 처리 어려움 | 도메인 내부 보장 어려움 |

---

### ✅ 4. `message()`는 어떻게 메시지 파일과 연결되나요?

- `message() = "ValidId"` 와 같이 키 형태로 작성하면
- `resources/ValidationMessages.properties`에서 메시지를 찾습니다.

```java
@Constraint(validatedBy = ...)
public @interface ValidUserId {
    String message() default "ValidUserId";
}
```

```properties
# ValidationMessages.properties
ValidUserId=ID는 'fail'이라는 단어를 포함할 수 없습니다.
```

```yaml
# application.yml
spring:
  messages:
    basename: ValidationMessages
    encoding: UTF-8
```

---

### ✅ 5. `groups()`와 `payload()`의 의미는?

| 속성 | 설명 | 사용 예 |
|------|------|---------|
| `groups()` | 조건부 유효성 검사 분류 | `@Validated(Create.class)` |
| `payload()` | 메타데이터 전달용 (중요도 구분 등) | 거의 사용 안 함 |

```java
String message() default "ValidId";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
```

---

### ✅ 6. ValidationMessages.properties 설정했는데 메시지 키 그대로 출력되는 이유?

**확인 사항 체크리스트:**

- [x] `ValidationMessages.properties` 경로가 `resources/` 루트에 있음
- [x] `message()` 값이 정확히 키 형태 (예: `"ValidId"`)
- [x] `application.yml`에 `spring.messages.basename: ValidationMessages` 포함
- [x] 파일 인코딩: UTF-8
- [x] 프로젝트 Clean + Rebuild 했는지 확인

---

### ✅ 7. 커스텀 Validator 작성 시 필수 요소는?

1. `@Constraint(validatedBy = Validator.class)`
2. `String message() default`
3. `Class<?>[] groups() default`
4. `Class<? extends Payload>[] payload() default`

---
