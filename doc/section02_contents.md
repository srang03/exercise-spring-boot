# ✅ 주제 2: AOP - 개념 정리 및 이해도 확인 문제

## 🧠 AOP 핵심 개념 정리

| 항목                               | 설명                                                              |
| -------------------------------- | --------------------------------------------------------------- |
| AOP(Aspect Oriented Programming) | 공통 관심사를 핵심 로직과 분리하여 모듈화하는 프로그래밍 패러다임                            |
| Aspect                           | 횡단 관심사를 모듈화한 단위, 보통 `@Aspect` 클래스로 정의                           |
| Advice                           | 언제 어떤 코드를 실행할지 정의하는 로직 (ex: @Before, @Around 등)                 |
| JoinPoint                        | Advice가 실행될 수 있는 지점 (메서드 호출, 예외 발생 등)                           |
| Pointcut                         | 어떤 JoinPoint에 Advice를 적용할지 결정하는 조건식                             |
| @Around                          | 메서드 실행 전/후/예외/결과 모두 제어 가능한 Advice, 가장 강력함                       |
| ProceedingJoinPoint              | `@Around` Advice에서 원래 메서드 호출을 진행시키는 객체                          |
| 실무 적용 위치                         | 주로 Service 레이어 중심으로 AOP 적용, Controller는 Filter나 Interceptor로 분리 |

---

## ✅ 실무 활용 예 요약

| AOP 적용 목적 | 적용 위치               | 설명                               |
| --------- | ------------------- | -------------------------------- |
| 실행 시간 측정  | Service             | 메서드 전후 시간 측정으로 성능 추적             |
| 로깅        | Service, Controller | 호출 흐름, 파라미터, 결과 로그 등             |
| 권한 검사     | Controller, Service | `@RequiresRoles` 등으로 역할 기반 진입 제한 |
| 데이터 마스킹   | Controller 응답 DTO   | 민감 정보 보호, 조건부 마스킹                |

---

## ✅ ThreadLocal 핵심 요약

| 개념                       | 설명                                                    |
| ------------------------ | ----------------------------------------------------- |
| static ThreadLocal       | 어디서든 접근 가능하지만, 값은 스레드마다 별도로 저장됨                       |
| ThreadLocalMap           | 각 스레드 내부에서만 접근 가능한 저장소 구조                             |
| UserContext.setRole() 위치 | 실무에서는 Filter 또는 Security Filter에서 설정, Controller에서는 ❌ |
| 반드시 clear() 호출           | 스레드 풀 재사용 환경에서 메모리 누수 방지 목적                           |

