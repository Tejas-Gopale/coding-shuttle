package com.testing_app.service.impl;

import com.testing_app.dto.EmployeeDto;
import com.testing_app.entity.Employee;

public interface EmployeeService {

	EmployeeDto getEmployeeById(Long id);
	EmployeeDto createNewEmployee(EmployeeDto employeeDto);
	EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
	void deletEmployee(Long id);
}
