package org.example.review_module.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAop {
    @Before("execution(* org.example.review_module.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("----------------------------------------------------------");
        System.out.println("🔍 [Before] " + joinPoint.getSignature().getName() + " 호출 시작");
    }
    // 2. 정상 실행 후 로그
    @AfterReturning("execution(* org.example.review_module.service..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("✅ [AfterReturning] " + joinPoint.getSignature().getName() + " 정상 종료");
    }
    // 3. 예외 발생시 로그
    @AfterThrowing(pointcut = "execution(* org.example.review_module.service..*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("❌ [AfterThrowing] " + joinPoint.getSignature().getName() + " 예외 발생: " + ex.getMessage());
    }
}
