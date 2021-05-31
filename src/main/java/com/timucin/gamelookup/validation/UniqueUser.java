package com.timucin.gamelookup.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueUserValidator.class)
public @interface UniqueUser {
	
    String message() default "This username is already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
