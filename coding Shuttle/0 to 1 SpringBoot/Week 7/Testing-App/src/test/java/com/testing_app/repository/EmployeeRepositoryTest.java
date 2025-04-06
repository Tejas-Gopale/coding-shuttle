package com.testing_app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.testing_app.entity.Employee;

@SpringBootTest

public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	
	@BeforeEach
	void setUp() {
		employee = Employee.builder().id(1L)
				.name("Tejas")
				.email("tejas@gmail.com")
				.salary(100L)
				.build();
	}
	
	@Test
	void testFindByEmail_whereEmailIsPresent_thenReturnEmployee() {
		
		// 1. Arrange or Given
		employeeRepository.save(employee);
		
		//2. Act or When 
		List<Employee> employeeList = employeeRepository.
				findByEmail(employee.getEmail());
	
		//3. Assert , Then
		assertThat(employeeList).isNotNull();
		assertThat(employeeList).isNotEmpty();
		assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
	}
	
	@Test
	void testFindByEmail_WhereEmailIsNotFound_thenReturnEmptyEmployeeList() {
	
		// 1. Arrange or Given 
			String email = "notPresent123@gmail.com";
			
		// Act
			List<Employee> employeList = employeeRepository.findByEmail(email); 
		
		// Then or asset 
			assertThat(employeList).isNotNull();
			assertThat(employeList).isEmpty();
	}
	
}
