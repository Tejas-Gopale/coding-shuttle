package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.customannotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class EmployeeRoldeValidation implements ConstraintValidator<EmployeeRoleValidationAnnotations , String> {

	@Override
	public boolean isValid(String inputRole, ConstraintValidatorContext context) {
	List<String> roles = List.of("USER","ADMIN");
		return roles.contains(inputRole);
	}

}
