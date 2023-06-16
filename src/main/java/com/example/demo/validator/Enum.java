package com.example.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface Enum {
    
    Class<? extends java.lang.Enum<?>> enumClass();

    String message() default "許可されていない値です";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
