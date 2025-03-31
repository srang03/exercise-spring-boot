# 📘 주제 3: 설정 시스템 및 자동 구성 - 1단계 정리

## ✅ 1단계 목표
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

✅ 다음 학습 단계: `@Value` vs `@ConfigurationProperties` 비교 및 실습

