
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
