package com.codingshuttle.caching.service;

import com.codingshuttle.caching.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto getEmployeeByID(Long id);
	EmployeeDto createNewEmployee(EmployeeDto employeeDto);
	EmployeeDto UpdateEmployeeById(EmployeeDto employeeDto, Long id);
	void deleteEmployeeById(Long id);
}
