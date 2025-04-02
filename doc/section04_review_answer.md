# 🧠 주제 4: 로깅 시스템 - 파트 1~3 이해도 확인 문제

총 6문항 (객관식 5 + 서술형 1)

---

## ✅ Q1. 다음 중 로그 레벨의 우선순위가 **높은 것부터 낮은 것** 순서로 올바르게 나열된 것은? (1점)

A. ERROR > INFO > DEBUG > TRACE > WARN  
**B. ERROR > WARN > INFO > DEBUG > TRACE ✅ (정답)**  
C. TRACE > DEBUG > INFO > WARN > ERROR  
D. DEBUG > TRACE > WARN > INFO > ERROR

---

## ✅ Q2. Spring Boot의 기본 로깅 조합으로 올바른 것은? (1점)

A. log4j + SLF4J  
B. java.util.logging + SLF4J  
**C. Logback + SLF4J ✅ (정답)**  
D. Logback + JUL

---

## ✅ Q3. 로그 설정 파일 우선순위가 가장 높은 것은? (1점)

A. logback.xml  
B. log4j2-spring.xml  
C. application.yml  
**D. logback-spring.xml ✅ (정답)**

---

## ✅ Q4. 로그 출력 대상과 로그 레벨을 다르게 적용하고 싶을 때 어떤 설정 파일을 써야 하는가? (1점)

A. application.yml  
B. logback.xml  
**C. logback-spring.xml ✅ (정답)**  
D. log4j.properties

---

## ✅ Q5. 다음 중 application.yml에서 지정 가능한 로깅 설정이 아닌 것은? (1점)

A. 로그 파일 경로 지정  
B. 특정 패키지의 로그 레벨 지정  
**C. 콘솔 색상 커스터마이징 ✅ (정답)**  
D. 로그 출력 패턴 지정

---

## ✅ Q6. 서술형 (2점)

> logback-spring.xml을 사용하는 이유와, application.yml 설정만으로는 해결하기 어려운 예시를 하나 들어 설명해주세요.

**정답 예시:**
Spring Boot의 application.yml은 단일 로그 레벨과 단일 출력 대상에 대한 간단한 설정만 지원한다. 하지만 logback-spring.xml을 사용하면 로그 레벨별로 Appender를 분리하거나 콘솔과 파일 로그를 따로 설정할 수 있다. 예를 들어 콘솔은 INFO 이상, 파일은 WARN 이상만 출력되도록 구성하려면 logback-spring.xml이 필요하다.

---

정답을 확인하셨으면, 다음 주제 또는 실습으로 이동하실 수 있습니다! 🎯

