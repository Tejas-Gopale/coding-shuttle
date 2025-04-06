package com.codingshuttle.caching.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.codingshuttle.caching.dto.EmployeeDto;
import com.codingshuttle.caching.entity.Employee;
import com.codingshuttle.caching.exception.ResourceNotFoundException;
import com.codingshuttle.caching.repository.EmployeeRepository;
import com.codingshuttle.caching.service.EmployeeService; 

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository employeeServiceImpl;
	private final ModelMapper modelMapper;
	private final String CACH_NAME = "employees";
	
	@Override
	@Cacheable(cacheNames = CACH_NAME ,key = "#id") // takes the response of cach and put into the cach
	public EmployeeDto getEmployeeByID(Long id) {
	log.info("fetching employee with id : {}", id);
	Employee employee = employeeServiceImpl.findById(id)
			.orElseThrow(  ()-> {
			log.error("Employee not found with id : {}  ",id);
			return new ResourceNotFoundException("Employee Not Found with id : "+id);
			});
	
	log.info("Successfully fetched employee with id : {} ", id);
	
		return modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	@CachePut(cacheNames = CACH_NAME, key ="#result.id")
	public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
		log.info("Creatting new Employee with id : {}" , employeeDto.getEmail());
		List<Employee> existingEmployees = employeeServiceImpl.findByEmail(employeeDto.getEmail());
		
		if(!existingEmployees.isEmpty()) {
			log.error("Employee already exist with email : {} ",employeeDto.getEmail());
			throw new RuntimeException("Employee already exist with the mail id: {} "+ employeeDto.getEmail());
		}
		
		Employee newEmployee = modelMapper.map(employeeDto, Employee.class);
		Employee saveEmployee = employeeServiceImpl.save(newEmployee);
		log.info("New Employee saved Sucessfully : {} "+ saveEmployee.toString());
		
		return modelMapper.map(saveEmployee, EmployeeDto.class);
	}

	@Override
	@CachePut(cacheNames =  CACH_NAME, key ="#id")
	public EmployeeDto UpdateEmployeeById(EmployeeDto employeeDto, Long id) {
		log.info("Updating Employee with id : {} ", id);
		Employee employee = employeeServiceImpl.findById(id).orElseThrow( () -> {
			log.error("Employee Not found with id : {} ",id);
			throw new ResourceNotFoundException("Employee Not found with id : {} "+ id);
		});
		
		if(!employee.getEmail().equals(employeeDto.getEmail())) {
			log.error("Attempted to update email for employee with id: {} ",id);
			throw new RuntimeException("The Email of Employee cannot be Updated");
		}
		modelMapper.map(employeeDto, employee);
		employee.setId(id);
		
		Employee savedEmployee = employeeServiceImpl.save(employee);
		log.info("Successfully  updated employee with id : {} ",id);
		
		return modelMapper.map(savedEmployee, EmployeeDto.class);
	}

	@Override
	@CacheEvict(cacheNames = CACH_NAME , key ="#id")
	public void deleteEmployeeById(Long id) {
		log.info("Deleting Employee with id: {} ",id);
		boolean isExit = employeeServiceImpl.existsById(id);
		
		if(!isExit) {
			log.error("Employee Not Found with id : {} ",id);
			throw new ResourceNotFoundException("Employee not found with id: " + id);
		}
		employeeServiceImpl.deleteById(id);
		log.info("SuccessFuly deleted employee with id : {} ",id);
	}

}
