# ✅ AOP 주제 이해도 확인 - 문제 및 정답 정리

# 🎯 이해도 확인 문제 (총 6문항)

### Q1. 생성자 주입이 권장되는 이유를 아래 보기 중 고르세요. (1점)

A. 순환 참조를 막기 위해
B. setter가 없으면 불변 객체가 되므로
C. 한 번만 주입되고, 외부에서 변경이 불가능해 안정적이기 때문
D. 필드가 private이면 접근할 수 없기 때문

---

### Q2. 다음 중 `@Around` 어노테이션의 설명으로 올바른 것은 무엇인가요? (1점)

A. 메서드 실행 전 로직만 실행된다\
B. 메서드 실행 후 로직만 실행된다\
C. 메서드 실행 전, 후, 예외, 결과 모두 제어할 수 있다\
D. JoinPoint를 사용할 수 없다

---

### Q3. AOP 적용 시, 동일한 타입의 Bean이 2개 이상일 경우 해결 방법이 아닌 것은? (1점)

A. `@Qualifier`를 사용하여 Bean을 명시적으로 주입한다\
B. `@Primary`로 기본 Bean을 지정한다\
C. `@Autowired(required = false)`로 강제하지 않는다\
D. `ApplicationContext.getBean(beanName)`으로 수동 조회한다

---

### Q4. Spring Security 없이 직접 AOP로 인가 처리를 구현하려면 필요한 구성요소는? (2점)

1. 사용자 역할을 보관하는 \_\_\_\_\_\_\_\_
2. 메서드 진입 시 역할을 확인하는 \_\_\_\_\_\_\_\_
3. 역할 요구사항을 정의하는 \_\_\_\_\_\_\_\_ 어노테이션

---

### Q5. ThreadLocal이 static으로 선언되었는데도 사용자별로 정보가 분리되는 이유는? (1점)

---

### Q6. 아래 코드의 실행 결과로 알맞은 것은? (1점)

```java
@RestController
public class UserController {
    @GetMapping("/me")
    public UserInfo getUserInfo() {
        return new UserInfo("홍길동", "010-1234-5678", "gildong@example.com");
    }
}
```

**조건**: UserContext의 Role이 USER일 때, `phone`, `email` 필드는 @MaskField 어노테이션이 붙어 있음

출력 결과:
A. 전화번호와 이메일 모두 그대로 노출됨\
B. 전화번호만 마스킹됨\
C. 이메일만 마스킹됨\
D. 전화번호와 이메일 모두 마스킹됨

---


## 🧾 제출한 정답

| 번호 | 문제 요약 | 제출한 답 | 정답 여부 |
|------|------------|------------|------------|
| 1 | 생성자 주입 이유 | C | ✅ 정답 |
| 2 | @Around 설명 | C | ✅ 정답 |
| 3 | 동일 타입 Bean이 2개 이상일 때 해결책이 아닌 것 | C | ❌ 오답 |
| 4-1 | 사용자 역할 저장소 | ThreadLocal | ✅ 정답 |
| 4-2 | 진입 시 역할 확인 방식 | AOP | ✅ 정답 |
| 4-3 | 어노테이션 이름 | @RequiresRoles | ✅ 정답 |
| 5 | ThreadLocal 값 분리 이유 | 별도 스레드 내에서 작동하므로 분리됨 | ✅ 정답 (핵심 포함) |
| 6 | 마스킹 결과 | D (전화번호/이메일 모두 마스킹) | ✅ 정답 |

---

## 🧠 해설 및 주의 포인트

### ❌ 문제 3 해설
- `@Autowired(required = false)`는 **빈이 없는 경우 예외를 막기 위한 것**이지,
  동일 타입의 빈이 2개 이상일 때 어느 것을 선택할지 지정하는 방식이 아님
- 해결 방법이 아닌 것 → **정답: C 맞음, 오답 이유는 이해 필요**

---

# 📘 보충 정리: 실무 질문 기반 내용 요약

## 1. 필터와 AOP의 역할 분리
- 필터: 요청의 최전방 (URL, 인증 토큰 기반 판단)
- AOP: 메서드 진입 시점에서 역할 기반 인가 처리 (더 세밀한 로직)

## 2. ThreadLocal vs static 혼동 정리
- `ThreadLocal`은 static으로 선언되지만,
  내부적으로는 각 스레드가 `ThreadLocalMap`이라는 독립된 저장소를 가짐
- 즉, static은 **접근 경로**일 뿐, **데이터는 스레드마다 격리되어 저장**됨

## 3. ThreadLocalMap 개념 시각화
```text
Thread-1:
  ThreadLocalMap: { userRole(ThreadLocal) => ADMIN }

Thread-2:
  ThreadLocalMap: { userRole(ThreadLocal) => USER }
```

## 4. 조건부 마스킹 실무 예
```java
if (!UserContext.getRole().equals(Role.ADMIN)) {
    field.set(obj, maskValue(...));
}
```

## 5. 리플렉션 예외 방지 처리
```java
try {
    field.setAccessible(true);
    field.set(obj, masked);
} catch (IllegalAccessException | IllegalArgumentException e) {
    log.warn("마스킹 오류: {}", e.getMessage());
}
```

## 6. 리스트/배열 마스킹 처리
```java
if (result instanceof Collection<?>) {
    for (Object item : (Collection<?>) result) {
        applyMaskingToObject(item);
    }
}
```
