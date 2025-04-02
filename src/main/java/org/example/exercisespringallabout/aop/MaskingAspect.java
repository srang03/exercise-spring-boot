package org.example.exercisespringallabout.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.exercisespringallabout.annotation.MaskField;
import org.example.exercisespringallabout.context.UserContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Aspect
@Component
public class MaskingAspect {

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object applyMaskingToAllControllers(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result == null) return null;
        if (result instanceof Collection<?>) {
            for (Object item : (Collection<?>) result) {
                applyMaskingToObject(item);
            }
        } else if (result.getClass().isArray()) {
            Object[] array = (Object[]) result;
            for (Object item : array) {
                applyMaskingToObject(item);
            }
        } else {
            applyMaskingToObject(result);
        }
        return result;
    }

    private void applyMaskingToObject(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(MaskField.class) && field.getType() == String.class) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(obj);
                    Role currentRole = UserContext.getRole(); // 예: ADMIN, USER

                    if (!Role.ADMIN.equals(currentRole)) {
                        field.set(obj, maskValue(field.getName(), value));
                    }
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    System.err.println("⚠️ 마스킹 처리 중 오류 발생: " + e.getMessage());
                }
            }
        }
    }

    private String maskValue(String fieldName, String value) {
        // 전화번호 마스킹 (010-1234-5678 -> 010-****-5678)
        if (fieldName.contains("phone")) {
            if (value.matches("\\d{3}-\\d{3,4}-\\d{4}")) {
                return value.replaceAll("(\\d{3})-(\\d{3,4})-(\\d{4})", "$1-****-$3");
            }
        }

        // 이메일 마스킹 (gildong@example.com -> g******@example.com)
        if (fieldName.contains("email")) {
            int atIndex = value.indexOf("@");
            if (atIndex > 1) {
                return value.charAt(0) + "*".repeat(atIndex - 1) + value.substring(atIndex);
            }
        }

        // 기본 마스킹
        if (value.length() > 2) {
            return value.substring(0, 1) + "*".repeat(value.length() - 2) + value.charAt(value.length() - 1);
        }

        return "*".repeat(value.length()); // 매우 짧은 문자열
    }
}
