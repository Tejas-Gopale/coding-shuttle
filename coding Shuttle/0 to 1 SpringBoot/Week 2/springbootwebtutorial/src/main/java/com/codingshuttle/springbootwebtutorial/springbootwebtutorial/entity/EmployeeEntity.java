package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Setter
@Getter
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "name is Required")
	@NotNull(message = "Name can't be null")
	private String name;
	
	@NotBlank(message = "Email is Reuired")
	@Email(message = "Email Must contains @gmail.com")
	private  String email;
	
	private String role;
	
	private Integer age;
	
	private Boolean isActive;
}
