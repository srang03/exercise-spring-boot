package org.example.exercisespringallabout.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {
    @Around("execution(* org.example.exercisespringallabout.application..*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            return joinPoint.proceed();
        }finally {
            long end = System.currentTimeMillis();
            String methodName = joinPoint.getSignature().toShortString();
            System.out.println("⏱️ 실행 시간 - " + methodName + " : " + (end - start) + "ms");
        }
    }
}
