package org.example.exercisespringallabout.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.exercisespringallabout.annotation.RequiresRoles;
import org.springframework.stereotype.Component;
import org.example.exercisespringallabout.domain.user.UserContext;
import java.util.Arrays;


@Aspect
@Component
public class RoleCheckAspect {
    @Around("@annotation(requiresRoles)")
    public Object checkRoles(ProceedingJoinPoint joinPoint, RequiresRoles requiresRoles) throws Throwable {
        Role current = UserContext.getRole();
        Role[] requiredRoles = requiresRoles.value();

        System.out.println("🔐 현재 사용자 역할: " + current);
        System.out.println("🔐 요구되는 역할: " + Arrays.toString(requiredRoles));

        boolean authorized = Arrays.asList(requiredRoles).contains(current);

        if (!authorized) {
            throw new SecurityException("접근 권한 없음. 필요한 역할: " + Arrays.toString(requiredRoles));
        }

        return joinPoint.proceed();
    }
}
