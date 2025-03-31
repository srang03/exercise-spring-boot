package org.example.exercisespringallabout.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect{
    // 1. 메서드 실행 전 로그
    @Before("execution(* org.example.exercisespringallabout.application..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("----------------------------------------------------------");
        System.out.println("🔍 [Before] " + joinPoint.getSignature().getName() + " 호출 시작");
    }
    // 2. 정상 실행 후 로그
    @AfterReturning("execution(* org.example.exercisespringallabout.application..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("✅ [AfterReturning] " + joinPoint.getSignature().getName() + " 정상 종료");
    }
    // 3. 예외 발생시 로그
    @AfterThrowing(pointcut = "execution(* org.example.exercisespringallabout.application..*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("❌ [AfterThrowing] " + joinPoint.getSignature().getName() + " 예외 발생: " + ex.getMessage());
    }
}
