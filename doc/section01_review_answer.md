
# ✅ Spring Boot 커리큘럼 - 주제 1 이해도 확인 정답 정리

## 📘 주제: 의존성 주입(DI)과 Spring 핵심 구조

---

## 📝 Q1. 생성자 주입이 권장되는 이유는?

**정답:**
- 어떤 의존성이 필요한지 명확하게 표현할 수 있음
- 생성 시점에 딱 한 번 주입되어 불변성 보장 (final 필드 사용 가능)
- 테스트 시 Mock 객체 주입이 용이함
- 필드/세터 주입은 외부에서 변경 가능 → 캡슐화 약함

---

## 📝 Q2. 자동으로 Bean으로 등록되지 않는 어노테이션은?

**정답: d) @Bean**

- `@Component`, `@Service`, `@Repository`는 컴포넌트 스캔 대상
- `@Bean`은 개발자가 수동으로 직접 등록할 때 사용하는 어노테이션

---

## 📝 Q3. 설정 기반 DI가 정상 동작하기 위한 조건은?

**정답:**
- 설정값(`application.yml`)에 등록된 이름이 실제 Bean 이름과 일치해야 함
- 해당 Bean이 `@Service("이름")` 또는 `@Bean(name = "이름")`으로 등록되어 있어야 함

---

## 📝 Q4. Spring 6 이상에서 생성자 파라미터 이름 오류 방지를 위한 컴파일러 설정은?

**정답: `-parameters`**

### Maven 설정 예시:
```xml
<plugin>
  <artifactId>maven-compiler-plugin</artifactId>
  <configuration>
    <compilerArgs>
      <arg>-parameters</arg>
    </compilerArgs>
  </configuration>
</plugin>
```

---

## 🧪 Q5. 동일 타입 Bean이 여러 개일 때 발생하는 주입 충돌 문제

**정답:**
- `LicenseNotificationService` 구현체가 2개 이상 등록된 경우 Spring이 어떤 걸 주입할지 몰라 오류 발생
- `NoUniqueBeanDefinitionException`

### 해결 방법:
- `@Qualifier("beanName")`를 사용하여 명시적으로 주입
- 또는 `application.yml` 설정 + `ApplicationContext.getBean(name)`으로 수동 등록
- 또는 `@Primary`를 활용하여 기본 Bean 지정

---

## 💡 Q6. 설정 기반 DI의 장점

**정답:**
- application.yml 파일만 바꾸면 구현체 변경이 가능 (빌드 없이 유연한 전략 전환)
- 운영/개발/테스트 환경에 따라 Bean 구성을 다르게 할 수 있음
- 구현체가 늘어나도 DIP 원칙을 유지하며 확장 가능
- 플러그인 구조, 전략 패턴 구현에 용이

---

## ✅ 전체 요약

| 항목 | 학습 완료 포인트 |
|------|------------------|
| DI 기본 | 생성자 주입, @Qualifier, @Bean 이해 |
| 충돌 해결 | NoUniqueBeanDefinitionException 해결법 이해 |
| 설정 기반 DI | application.yml 기반 전략 주입 실습 완료 |
| 아키텍처 적용 | Hexagonal 구조 전환 및 Port-Adapter 개념 적용 |
