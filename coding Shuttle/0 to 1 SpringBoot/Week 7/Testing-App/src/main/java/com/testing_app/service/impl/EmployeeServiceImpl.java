package com.testing_app.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing_app.dto.EmployeeDto;
import com.testing_app.entity.Employee;
import com.testing_app.exception.ResourceNotFoundException;
import com.testing_app.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		log.info("Fetching Employee with id : {}", id);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee With id {} not found :" + id));
		log.info("Successfully fetched employee with id: {}", id);
		return modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
		log.info("Creating new Employee With email: {}", employeeDto.getEmail());
		List<Employee> employee = employeeRepository.findByEmail(employeeDto.getEmail());

		if (!employee.isEmpty()) {
			log.info("Employee alreday exist with the email : {}" + employeeDto.getEmail());
			throw new ResourceNotFoundException("Employee Already exist with same email : " + employeeDto.getEmail());
		}
		Employee newEmployee = modelMapper.map(employee, Employee.class);
		Employee saveEmployee = employeeRepository.save(newEmployee);
		log.info("SuccessFully Created New Employee with id : {} ", saveEmployee.getId());
		return modelMapper.map(saveEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
		log.info("updating employee with id : {}", id);
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
			log.error("Employee Not Found with id: {} " + id);
			throw new ResourceNotFoundException("Employee Not Found with id : {}" + id);
		});

		if (!employee.getEmail().equals(employeeDto.getEmail())) {
			log.error("Attempted to update email for employee with id :{}", id);
			throw new RuntimeException("The Email of Employee cannot be updated");
		}

		employeeDto.setId(null);
		modelMapper.map(employeeDto, employee);

		Employee saveEmployee = employeeRepository.save(employee);
		log.info("Successfully updated employee with id : {}", id);

		return modelMapper.map(saveEmployee, EmployeeDto.class);
	}

	@Override
	public void deletEmployee(Long id) {
		log.info("Deleting employee with id: {}", id);
		boolean exits = employeeRepository.existsById(id);
		if (!exits) {
			log.info("Employee not found with id : {}", id);
			throw new ResourceNotFoundException("Employee Not Found with id : " + id);
		}

		employeeRepository.deleteById(id);
		log.info("SuccessFully delete employee with id : {}", id);
	}

}
