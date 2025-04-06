package com.codingshuttle.caching.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmployeeDto {

	private Long id;
	private String name;
	private String email;
	private Long salary;
}
