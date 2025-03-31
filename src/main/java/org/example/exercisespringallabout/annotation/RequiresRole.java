package org.example.exercisespringallabout.annotation;

import org.example.exercisespringallabout.domain.user.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    Role value(); // 요구되는 역할
}
