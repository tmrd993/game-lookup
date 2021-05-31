package com.timucin.gamelookup.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = PasswordsMatchValidator.class)
public @interface PasswordsMatch {
	
    String message() default "Passwords don't match";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
