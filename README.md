
## Spring Boot 백엔드 실전 커리큘럼

### 도메인: 사용자, 제품 라이선스 생성 및 관리

### 전체 커리큘럼 개요 (1~15번 순차 진행)


| 번호 | 주제 | 핵심 학습 항목 요약 |
|------|------|------------------|
| 1 | 의존성 주입과 Spring 구조 | @Component, @Service, @Repository, 생성자 주입, DI 원칙 |
| 2 | AOP와 공통 처리 | @Aspect, Filter vs Interceptor, 트랜잭션/로깅 AOP |
| 3 | 설정 및 자동 설정 | application.yml, @Value, @ConfigurationProperties, 자동 설정 원리 |
| 4 | 로깅 및 예외 처리 | SLF4J, Logback, @ControllerAdvice, 예외 추상화 |
| 5 | Validation 처리 | @Valid, Validator 분리, FieldError, Bean Validation |
| 6 | 테스트 전략 | 단위 테스트, @MockBean, 통합 테스트, TestContainer |
| 7 | 데이터 접근 | JPA 기본, QueryDSL, JDBC Template, 영속성 컨텍스트 |
| 8 | 트랜잭션과 DDD | @Transactional, 도메인 모델, 엔티티/서비스 분리 |
| 9 | RESTful API & 문서화 | Swagger, REST Docs, 상태 코드, URL 설계 원칙 |
| 10 | 인증/인가 | Spring Security, JWT 인증 흐름, 필터 시큐리티 구성 |
| 11 | 비동기 & 스케줄링 | @Async, @Scheduled, 이벤트 발행/구독 구조 |
| 12 | 운영 환경 구성 | 프로파일 설정, 운영/로컬 분리, 환경 변수 |
| 13 | 아키텍처 설계 | 클린 아키텍처, 계층 분리, 인터페이스 추상화 |
| 14 | 성능 최적화 | N+1, Fetch Join, Pageable, 캐싱 |
| 15 | 모니터링과 로그 | Actuator, Prometheus, 로그 설계, 헬스체크 |


---


## ✅ 진행 방식

- 각 주제는 다음의 3단계로 진행합니다:
    1. **이론 및 개념 학습**
    2. **예제 코드 학습**
    3. **이해도 확인 문제 & 실습 과제 제공**


- 실습 도메인: "사용자, 제품 라이선스 생성 및 관리"
- 설정 기반 의존성 주입(`application.yml` + `ApplicationContext`) 방식을 기반으로 통일


---



## ✅ 주제 1: 의존성 주입(DI)과 Spring 핵심 구조

### 🎯 학습 목표
- Spring의 의존성 주입 원리를 이해하고 실습할 수 있다.
- 설정 파일(application.yml)을 기반으로 구현체를 선택적으로 주입하는 구조를 구현할 수 있다.
- 3계층 아키텍처와 Hexagonal Architecture의 차이와 적용법을 이해한다.

---

## 1️⃣ 이론 및 핵심 개념

### 🔹 의존성 주입(DI)의 방식
| 방식 | 특징 |
|------|------|
| 생성자 주입 | 가장 추천되는 방식, final 필드 사용 가능 |
| 필드 주입 | 테스트/리팩토링 어려움, 비권장 |
| 세터 주입 | 선택적 의존성 주입 시 사용 |

### 🔹 주요 어노테이션
| 어노테이션 | 설명 |
|------------|------|
| `@Component`, `@Service`, `@Repository` | Bean 등록을 위한 어노테이션 |
| `@Autowired` | 의존성 자동 주입 (생성자 1개면 생략 가능) |
| `@Bean` | 개발자가 직접 Bean을 등록 |
| `@Qualifier` | 동일 타입 Bean이 여러 개일 때 선택 |

---

## 2️⃣ 실습 구조 요약

### 🧩 도메인: 사용자, 제품 라이선스 생성 및 관리

### 구성 요약:
- `LicenseNotificationService` (인터페이스)
- `EmailNotificationService`, `SmsNotificationService` (구현체)
- `LicenseManager` (비즈니스 로직을 담당하는 서비스)
- `LicenseNotificationConfig` (설정값으로 Bean 결정)
- `application.yml` 설정값으로 구현체 선택

### 설정 기반 DI 핵심 코드
```yaml
license:
  notification-type: emailLicenseNotificationService
```

```java
@Bean
public LicenseNotificationService licenseNotificationService() {
    return (LicenseNotificationService) context.getBean(notificationBeanName);
}
```

```java
public LicenseManager(@Qualifier("licenseNotificationService") LicenseNotificationService service) { ... }
```

---

## 3️⃣ 주요 질문 및 답변 요약

###  Q. 인터페이스로 DI를 하는데, `@Qualifier("클래스명")`을 쓰면 구현체에 의존하는 게 아닌가요?
- 네, 어느 정도 의존이 생깁니다. 그래서 **설정 파일 기반 DI**를 도입하여 **구현체를 외부에서 선택**하도록 설계합니다.

###  Q. 그러면 `application.yml` 설정만으로 DI가 완전히 해결되나요?
- 아니요. 자동 등록(`@Service`)과 설정 기반 수동 등록(`@Bean`)이 **혼용되면 충돌**이 발생합니다.
- 설정 기반으로 완전히 하려면 **`@Service`를 제거하고 `@Bean`으로만 등록**해야 합니다.

###  Q. `LicenseManager`에서 오류가 발생하는 이유는?
- `LicenseNotificationService` 구현체가 여러 개여서 `NoUniqueBeanDefinitionException` 발생.
- 해결: 수동 등록한 Bean을 `@Qualifier`로 명시하거나, 구현체를 수동 등록만 하도록 구조 조정.

###  Q. 생성자에 `@Autowired`를 생략해도 되나요?
- 생성자가 하나뿐이라면 생략 가능(Spring 4.3 이상). 관례적으로 생략합니다.

###  Q. Hexagonal Architecture로도 구성 가능한가요?
- 네! 이번 구조는 Application → Port → Adapter 구조로 전환 가능하며, 설정 기반 DI 방식과도 잘 어울립니다.

---

## ✅ 최종 구조: Hexagonal Architecture (구현체 설정 기반)
```
[ Inbound Adapter (Controller) ]
        ↓
[ Application Layer (LicenseManager) ]
        ↓
[ Port (LicenseNotificationService interface) ]
        ↓
[ Outbound Adapter (Email/SMS Adapter) ]
```

---

## 마무리

- 설정 파일 기반으로 구현체를 DI 하는 구조를 실습함
- Spring의 Bean 등록 원리와 충돌 상황을 이해함
- Hexagonal Architecture 적용까지 성공적으로 완료함