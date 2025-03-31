package org.example.exercisespringallabout.annotation;

import org.example.exercisespringallabout.aop.MaskingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskField {
    MaskingType type() default MaskingType.GENERIC;
}


