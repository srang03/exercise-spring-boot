# 📘 주제 4: 로깅 시스템 및 로그 설정

## ✅ 파트 1. 로깅이란? 로그 레벨의 의미

---

## 1-1. 로깅이란?

**로깅(logging)**은 애플리케이션 실행 중 발생하는 다양한 사건(이벤트, 상태, 오류 등)을 기록하여,
개발자나 운영자가 이를 **모니터링, 분석, 디버깅**할 수 있도록 도와주는 핵심 기능입니다.

### 💡 로깅의 중요성

| 목적 | 설명 |
|------|------|
| 🔎 디버깅 | 개발 중 문제 추적 (입력, 흐름, 반환 값 등) |
| 🧯 장애 분석 | 예외 발생 시 로그를 통해 원인 분석 |
| 🩺 서비스 상태 파악 | 정상 작동 여부, 재시도 여부 등 확인 가능 |
| 🔐 보안 추적 | 접근 기록, 인증 이슈 등 감사 로그 용도 |
| 📈 서비스 분석 | 사용 패턴, 리퀘스트 빈도, 성능 추적 등 가능 |

---

## 1-2. 로그 레벨(Level) 종류

로그는 **중요도에 따라 5단계**로 구분됩니다:

| 로그 레벨 | 설명 | 사용 예시 |
|------------|--------|--------------|
| `TRACE` | 가장 상세한 디버깅 정보 | 반복문 내부, 내부 변수 추적 |
| `DEBUG` | 디버깅용 상세 정보 | 흐름, 조건 분기, 파라미터 추적 |
| `INFO` | 일반적인 시스템 흐름 정보 | 로그인 성공, 서비스 진입, 주문 처리 완료 |
| `WARN` | 경고. 이상 징후가 있지만 동작은 계속됨 | 응답 지연, fallback 처리, 재시도 경고 |
| `ERROR` | 오류 발생. 비정상 종료, 예외 발생 | 예외 로그, DB 연결 실패 등 |

> 🔸 운영 환경에서는 `INFO`, `WARN`, `ERROR` 중심으로 사용함
> 🔸 `TRACE`, `DEBUG`는 개발 환경에서만 활성화하는 것이 일반적

---

## 1-3. 로그 레벨 설정 예시 (Spring Boot)

Spring Boot에서는 `application.yml` 또는 `application.properties` 파일에서 로그 레벨을 지정할 수 있습니다.

```yaml
logging:
  level:
    root: info # 전체 기본 로그 레벨
    com.example.service: debug # 특정 패키지만 debug 활성화
```

---

## ✅ 파트 2. Spring Boot 기본 로깅 구조

---

## 2-1. 기본 로깅 구현체는 무엇인가요?

Spring Boot는 기본적으로 **SLF4J + Logback** 조합을 사용합니다.

| 구성 요소 | 설명 |
|------------|------|
| SLF4J (Simple Logging Facade for Java) | 로깅 인터페이스 (공통 API 역할) |
| Logback | 실제 로그를 출력하는 구현체 (default) |

> 즉, 코드는 `LoggerFactory.getLogger()` 등을 통해 SLF4J를 사용하지만,
> 실제 출력은 내부적으로 Logback이 담당합니다.


---

## 2-2. 로그 출력 기본 위치와 형태

- 기본 출력 대상: **콘솔 (STDOUT)**
- 기본 출력 포맷:
  ```
  yyyy-MM-dd HH:mm:ss.SSS [level] --- [thread] class.name : message
  ```

예:
```
2025-03-31 22:11:02.114  INFO 12345 --- [nio-8080-exec-1] c.e.demo.service.LicenseService : License created: ABC-1234
```

---

## 2-3. 로그 설정 파일 우선순위

Spring Boot는 다음 순서로 설정을 자동으로 탐지합니다:

1. `logback-spring.xml`
2. `logback.xml`
3. `log4j2-spring.xml`
4. `log4j2.xml`
5. `application.yml` 또는 `application.properties`

> 가장 권장되는 커스터마이징 방식은 **logback-spring.xml** 사용입니다.

---

## 2-4. Logger 사용 예시 (SLF4J 기반)

```java
@Slf4j // lombok 사용 시
@Service
public class LicenseService {

    public void createLicense(String name) {
        log.info("License created for user: {}", name);
        log.debug("상세 디버그 정보 출력");
    }
}
```

---

## ✅ 파트 3. 로그 레벨 및 출력 설정 실습

---

## 3-1. application.yml 기반 로그 설정 예시

```yaml
logging:
  level:
    root: info
    org.example: debug
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/app.log
```

| 설정 항목 | 설명 |
|------------|------|
| `level.root` | 전체 기본 로그 레벨 설정 |
| `level.org.example` | 특정 패키지에 대해 개별 로그 레벨 지정 |
| `pattern.console` | 콘솔 출력 포맷 지정 (Logback 형식) |
| `file.name` | 로그 파일 저장 위치 (기본 logback 로거 사용 시 적용됨) |

> 로그 패턴 커스터마이징은 반드시 **logback-spring.xml**과 함께 쓰는 것이 가장 유연합니다.

---

## 3-2. 실습 시나리오 예시

1. application.yml에 `root: warn`, `org.example.service: debug`로 설정
2. 로그 출력 확인:
    - info 로그는 service 클래스에서만 보이고
    - 전체 시스템 로그는 warn 이상만 출력됨

---