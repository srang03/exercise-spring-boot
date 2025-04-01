# ✅ 주제 3: 설정 시스템 및 자동 구성 (파트 1~2) - 이해도 확인 문제 및 정답

총 6문항 (객관식 5 + 서술형 1)

---

## 🧠 문제 및 정답 정리

### Q1. Spring Boot 설정값 우선순위가 높은 순으로 나열한 것을 고르세요.
> **정답: C**
> - 명령줄 인자 > application.yml > 환경변수 > JVM 옵션

✅ 설명: Spring Boot는 CLI 인자를 최우선으로 하며, 설정파일 → 환경변수 → JVM 순

---

### Q2. 아래 설정 파일을 기준으로 올바른 `@ConfigurationProperties` prefix는?
```yaml
license:
  notification-type: email
  email:
    sender-name: Reboot
    retry-count: 3
```
> **정답: C** (`license`)

✅ 설명: 설정의 루트가 `license`이므로 prefix는 `license`가 되어야 바인딩됨

---

### Q3. `@ConfigurationProperties` 바인딩 오류가 아닌 것은?
> **정답: C** ❌ (오답)

✅ 해설: Spring Boot는 `kebab-case`(`retry-count`)와 `camelCase`(`retryCount`)를 자동 매핑함
→ 오답으로 처리됨

---

### Q4. `@Value`와 `@ConfigurationProperties` 차이 중 옳지 않은 것은?
> **정답: C** ❌ (오답)

✅ 해설: `@Value`는 유효성 검사(`@Validated`)를 직접 지원하지 않음 → 잘못된 설명

---

### Q5. 중첩 클래스 구성 설명 중 옳은 것은?
> **정답: A**
> - 내부 클래스는 static으로 선언해야 바인딩 가능

✅ 해설: 내부 클래스가 static이 아니면 인스턴스 없이 바인딩 불가능

---

### Q6. 설정값 기반 분기 처리 방식의 장점과 개선 방향 (서술형)
```java
String type = props.getNotificationType();
if (type.startsWith("email")) { ... } else if (type.startsWith("sms")) { ... }
```
> **정답 요약:**
> - 장점: 간단하고 빠른 로직 분기 가능, 설정 기반 유연성 확보
> - 단점: 조건 분기 추가 시 코드 복잡도 증가, 문자열 기반은 오타 위험 있음
> - 개선: `Enum` 타입으로 설정 값 제한, 설정값 → Bean 이름 매핑 도입, 전략 패턴 활용 추천

✅ 해설: 실무에서는 Enum, 전략 맵핑, @Conditional 또는 BeanFactory 등 활용하여 구조화함

---

## 🧾 결과 요약

| 문제 | 정답 여부 | 설명 |
|------|------------|------|
| Q1 | ✅ 정답 | 우선순위 정확히 이해함 |
| Q2 | ✅ 정답 | prefix 매핑 이해함 |
| Q3 | ❌ 오답 | kebab-case는 자동 매핑됨 |
| Q4 | ❌ 오답 | `@Value`는 유효성 검사 미지원 |
| Q5 | ✅ 정답 | static 중첩 클래스 필수 이해함 |
| Q6 | ✅ 정답 | 실무적 고려와 개선방안 완벽히 제시 |

---