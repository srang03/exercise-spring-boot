# 📘 주제 3: 설정 시스템 및 자동 구성

## ✅ 파트 1 목표
- Spring Boot 설정 구조 이해
- 설정 우선순위 파악
- `@Value`의 실무 활용 시점 파악

---

## 🧱 설정 파일 종류

| 설정 파일 | 설명 |
|------------|------|
| `application.properties` | 기본 key-value 형태 설정 파일 |
| `application.yml` | 계층 구조 표현이 가능한 설정 파일 (실무 선호) |
| `application-{profile}.yml` | 프로파일 환경별 설정 파일 (예: dev, prod 등) |

---

## 🔄 설정 우선순위 (높음 → 낮음)

1. **Command Line Arguments** (`--server.port=8081`)
2. `application.yml` / `application.properties` (기본 리소스 경로)
3. `@PropertySource` 명시된 외부 파일
4. 환경변수 (`$SERVER_PORT`)
5. JVM 옵션 (`-Dserver.port=8083`)
6. Spring Boot 내장 기본값

> 📌 높은 우선순위가 낮은 설정을 덮어씀

---

## 🌍 프로파일(profile) 기반 설정 구성

### ✅ 파일 구조 예
```
src/main/resources
├── application.yml
├── application-dev.yml
├── application-prod.yml
```

### ✅ application.yml
```yaml
spring:
  profiles:
    active: dev
```

→ `spring.profiles.active=dev`이면 `application-dev.yml` 자동 병합 로딩

---

## 🔁 on-profile 블록과 파일 분리 비교

| 방식 | 용도 | 설명 |
|-------|------|------|
| `application-{profile}.yml` | 파일 기반 분리 | 자동 로딩 방식으로 가장 많이 사용 |
| `on-profile` | 한 파일 내부 블록 나눔 | 여러 환경을 한 yml에 구성할 때 사용 |

예시:
```yaml
spring:
  config:
    activate:
      on-profile: dev
```

---

## 🧪 `@Value` 실무 활용 사례

| 사용 상황 | 예시 | 설명 |
|------------|------|------|
| 단일 설정 주입 | `@Value("${server.port}")` | 기본 포트 설정 |
| 시스템 변수 | `@Value("${JAVA_HOME}")` | OS 환경 변수 읽기 |
| API Key 주입 | `@Value("${external.api.key}")` | 민감 설정 파일 외부화 |
| 기본값 포함 | `@Value("${timeout:5000}")` | 값 없을 경우 기본값 사용 |

---

## ⛔ @Value 사용의 한계

| 상황 | 대안 |
|-------|------|
| 구조화된 설정 (중첩 값) | `@ConfigurationProperties` 사용 |
| 여러 값 주입 시 | DTO/빈에 매핑하는 방식 선호 |
| 명시적 타입 변환 필요 | `@ConfigurationProperties`가 더 유연함 |

---

## 📌 정리

| 항목 | 요약 |
|------|------|
| 설정 파일 종류 | application.yml, application-{profile}.yml 등 존재 |
| 설정 우선순위 | 명령줄 > yml > 환경변수 > JVM 옵션 순 |
| 프로파일 구성 | `spring.profiles.active` 또는 `on-profile`로 활성화 |
| @Value 적절한 사용 | 단일 값, 문자열, 간단한 주입에 적합 |
| 구조적 설정 | 다음 단계에서 다룰 `@ConfigurationProperties`로 해결 |

---

## ✅ 파트 2 목표
- `@ConfigurationProperties`를 활용해 구조화된 설정을 객체로 바인딩
- 중첩 설정 클래스 구성 방법 학습
- `@Validated`를 통한 설정값 유효성 검사 적용
- 설정값에 따라 서비스에서 조건 분기 처리

---

## 1️⃣ `@ConfigurationProperties` 개념 및 장점

| 항목 | 설명 |
|------|------|
| 사용 목적 | yml/properties 설정을 POJO에 바인딩 |
| 자동 바인딩 | 타입 변환, 계층적 구조 자동 매핑 지원 |
| 유지보수성 | 설정 관련 값을 한 클래스로 관리 가능 |
| 문서화 가능 | 설정 클래스만 보면 전체 구조 파악 가능 |

---

## 2️⃣ application.yml 설정 예시

```yaml
license:
  notification-type: emailLicenseNotificationAdapter
  email:
    sender-name: Reboot
    retry-count: 3
  sms:
    sender-number: "010-1234-5678"
    retry-count: 2
```

---

## 3️⃣ 중첩 클래스 구성 예시

```java
@Component
@ConfigurationProperties(prefix = "license")
@Getter @Setter
public class NotificationProperties {
    private String notificationType; // ex: emailLicenseNotificationAdapter
    private Email email;
    private Sms sms;

    @Getter @Setter
    public static class Email {
        private String senderName;
        private int retryCount;
    }

    @Getter @Setter
    public static class Sms {
        private String senderNumber;
        private int retryCount;
    }
}
```

> ✅ setter 가 반드시 있어야 바인딩 가능 (Spring Boot는 setter 기반 바인딩)

---

## 4️⃣ 설정값을 기준으로 서비스 내부 조건 분기

```java
@Service
public class LicenseService {
    private final LicenseNotificationPort licenseNotificationPort;
    private final NotificationProperties props;

    public LicenseService(LicenseNotificationPort licenseNotificationPort, NotificationProperties props) {
        this.licenseNotificationPort = licenseNotificationPort;
        this.props = props;
    }

    public void issueLicense(String userName) {
        String type = props.getNotificationType();

        if (type.startsWith("email")) {
            var email = props.getEmail();
            // email 발신 처리
        } else if (type.startsWith("sms")) {
            var sms = props.getSms();
            // sms 발신 처리
        }

        licenseNotificationPort.notify(userName + " 님의 라이선스가 발급되었습니다.");
    }
}
```

> ✅ Bean 이름에 직접 의존하지 않고 설정값을 활용한 분기 처리 방식

---

## 5️⃣ 발생했던 문제 및 해결

| 문제 | 원인 | 해결 방법 |
|------|------|-------------|
| 설정값이 null | prefix 불일치 (`license.notification-type` vs `license.notification`) | prefix를 `license`로 수정하여 전체 경로 포함 |
| 바인딩 실패 | setter 없음 | 중첩 클래스에도 `@Setter` 추가 |

---

## ✅ 파트 2 요약

| 항목 | 정리 |
|------|------|
| `@ConfigurationProperties` | 계층형 설정 구조를 객체에 바인딩 |
| 중첩 클래스 | email, sms 등 설정을 명확히 분리 가능 |
| 유효성 검사 | `@Validated` + 제약 어노테이션으로 설정값 검증 가능 |
| 실무 활용 | 설정 기반 조건 분기, Bean 주입보다 유연하고 명확하게 사용 가능 |

---

## ✅ 파트 3 목표
- `@Configuration`, `@Bean`의 의미와 차이 이해
- Spring Boot의 자동 설정(Auto Configuration) 원리 파악
- `@ConditionalOn...` 시리즈 어노테이션 학습
- 수동 설정 방식 vs 자동 조건 설정 방식의 차이 비교 및 실습

---

## 1️⃣ @Configuration & @Bean

| 어노테이션 | 설명 |
|------------|------|
| `@Configuration` | 해당 클래스가 스프링 설정 클래스임을 명시 |
| `@Bean` | 메서드의 반환 객체를 직접 스프링 빈으로 등록 |

예시:
```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

---

## 2️⃣ 자동 설정이란?
Spring Boot는 의존성 존재 여부, 클래스패스 탐색, 프로퍼티 값 등을 기반으로
수많은 기본 컴포넌트를 자동 등록해준다.

자동 설정 클래스에는 아래와 같은 조합이 붙음:
```java
@Configuration
@ConditionalOnClass(...)
@ConditionalOnProperty(...)
```

> ✅ 대표 예: Spring Data JPA, Spring Web 등에서 내부적으로 자동 설정 적용

---

## 3️⃣ 주요 Conditional 어노테이션

| 어노테이션 | 설명 |
|-------------|------|
| `@ConditionalOnClass` | 특정 클래스가 classpath에 있을 경우 설정 적용 |
| `@ConditionalOnMissingBean` | 특정 타입의 빈이 없을 때 설정 적용 |
| `@ConditionalOnProperty` | 지정된 설정값이 존재하거나 특정 값을 가질 때 설정 적용 |
| `@ConditionalOnExpression` | SpEL 표현식이 true일 때 설정 적용 |

---

## 4️⃣ 실습: notificationType에 따라 조건부 빈 주입하기

```java
@Configuration
public class NotificationConfig {
    @Bean
    @ConditionalOnProperty(name = "license.notification-type", havingValue = "email")
    public LicenseNotificationPort emailAdapter() {
        return new EmailAdapter();
    }

    @Bean
    @ConditionalOnProperty(name = "license.notification-type", havingValue = "sms")
    public LicenseNotificationPort smsAdapter() {
        return new SmsAdapter();
    }
}
```

> 📌 조건에 맞는 Bean만 등록되어, 나머지는 컨테이너에 포함되지 않음

---

## 5️⃣ 수동 설정 방식 (ApplicationContext 기반)

```java
@Configuration
public class LicenseNotificationConfig {
    @Value("${license.notification-type}")
    private String notificationBeanName;

    private final ApplicationContext context;

    public LicenseNotificationConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    @Primary
    public LicenseNotificationPort licenseNotificationService() {
        return (LicenseNotificationPort) context.getBean(notificationBeanName);
    }
}
```

✅ 설정 기반으로 동적 선택 가능 / ✅ 다양한 방식으로 확장 가능  
❌ 실수 방지에 취약 / ❌ Bean 이름 관리가 복잡해질 수 있음

---

## 6️⃣ 충돌 문제 사례 및 해결

오류:
```
Parameter 0 of constructor in LicenseService required a single bean, but 2 were found:
 - emailLicenseNotificationAdapter
 - smsLicenseNotificationAdapter
```

원인:
- 기존 `@Service` Bean 등록이 그대로 유지되고 있음
- 동시에 `@Bean` 수동 등록 → **같은 타입의 Bean 중복 등록** 오류

해결:
- 기존 `@Service` 제거하거나, `@ConditionalOnProperty` 만 유지

---

## ✅ 파트 3 요약

| 항목 | 요약 |
|------|------|
| `@Configuration` | 설정 클래스임을 명시 |
| `@Bean` | 수동 빈 등록 방식, 명시적 제어 가능 |
| 자동 설정 | 클래스 존재/설정 값 기반 자동 구성 (@ConditionalOn...) |
| 조건부 설정 | `@ConditionalOnProperty` 등으로 설정값에 따라 자동 분기 |
| 실무 적용 | 설정 기반 주입 시 기존 `@Service` 충돌 주의 필요 |

---

🎯 다음: 주제 4 - 로깅 시스템 및 로그 설정


