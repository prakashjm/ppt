package com.xyz.mydiary.validation.UserDTO;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserEmailValidation.class)
@Documented
public @interface ValidUserEmail {
    String message () default "Your Email Already Registered";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}