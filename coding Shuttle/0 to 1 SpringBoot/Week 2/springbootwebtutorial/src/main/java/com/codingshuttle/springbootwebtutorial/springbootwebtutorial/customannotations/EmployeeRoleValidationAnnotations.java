package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.customannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD , ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoldeValidation.class})
public @interface EmployeeRoleValidationAnnotations {

	String message() default "Role of Employee can either be USER or ADMIN";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
