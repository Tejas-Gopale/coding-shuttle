package com.codingshuttle.prod_reday_features.prod_reday_features.clients;

import java.util.List;

import com.codingshuttle.prod_reday_features.prod_reday_features.dto.EmployeeDTO;

public interface EmployeeClients {

	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO getEmployeeById(Long employeeId);
	
	EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
