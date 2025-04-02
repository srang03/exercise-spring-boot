✅ 주제 5: 이해도 확인 문제 (파트 1~2)
총 6문항 (객관식 5 + 단답형 1)

1. 다음 중 @NotBlank에 대한 설명으로 올바른 것은?
   A. 모든 타입에 적용 가능하며 null만 검사한다
   B. 공백 문자와 null을 모두 허용한다
   C. String 타입에만 적용되며 null 또는 빈 문자열을 허용하지 않는다
   D. @NotBlank는 숫자 타입 검증에도 사용할 수 있다

2. 다음 중 Bean Validation이 적용될 수 없는 위치는?
   A. Controller에서 DTO를 받을 때
   B. Service에서 @Validated 붙인 클래스 내 메서드
   C. JPA Entity 내부 필드
   D. private static 메서드 매개변수

3. 다음 중 유효성 검증 실패 시 BindingResult의 역할로 옳은 것은?
   A. 검증 예외를 즉시 던져준다
   B. 모든 검증을 우회하고 결과를 무시한다
   C. 오류 메시지를 필드 단위로 수집할 수 있도록 한다
   D. 컨트롤러를 건너뛰고 Global ExceptionHandler로 바로 전달한다

4. 다음 중 @Valid 와 @Validated 의 차이점으로 알맞은 것은?
   A. @Valid는 스프링 전용, @Validated는 JSR-303 표준이다
   B. @Validated는 그룹 검증이 가능하다
   C. @Validated는 컨트롤러에서만 사용할 수 있다
   D. 둘은 완전히 동일한 기능이다

5. 다음 중 Service 계층에서 Validation을 적용하기 위한 전제 조건은?
   A. 메서드에만 @Valid 붙이면 된다
   B. 클래스에 @Validated가 선언되어야 한다
   C. DTO에 @NotNull만 붙이면 된다
   D. Validation은 Controller에서만 처리 가능하다

6. 단답형
   컨트롤러에서 @Valid로 유효성 검사를 할 때 예외를 막고 오류 메시지를 수집하려면
   함수 시그니처에 어떤 파라미터를 추가해야 하나요?