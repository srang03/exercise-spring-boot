package org.example.review_module.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.review_module.annotation.NameFilter;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NameFilterAop {

    @Before("@annotation(NameFilter)")
    public void CheckName(JoinPoint joinPoint, NameFilter NameFilter) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String name = (String) args[0];

        if (name == null || name.isEmpty() || name.contains("fail")){
            throw new IllegalArgumentException("이름은 null 이거나 비어있을 수 없습니다.");
        }
        if(name.contains(NameFilter.name())){
            System.out.println("Admin Login Success");
        }
    }
}
