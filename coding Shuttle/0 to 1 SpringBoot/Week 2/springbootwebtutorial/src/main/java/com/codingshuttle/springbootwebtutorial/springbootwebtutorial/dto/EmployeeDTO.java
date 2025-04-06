package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.customannotations.EmployeeRoleValidationAnnotations;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeDTO {


	Long id;
	
	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name is Required")
	@Size(max = 10, min = 2)
	String name;
	
	@NotBlank(message = "Emial is Required")
	@Email(message = "Email Must be Valid")
	String email;
	
	@Max(value = 80)
	@Min(value = 18)
	Integer age;
	
	//@Pattern(regexp = "^(ADMIN|USERS)$", message = "Roles of Employee can be USER or ADMIN")
	@EmployeeRoleValidationAnnotations
	private String role; //Admin or user 
	
	private Boolean isActive;
}
