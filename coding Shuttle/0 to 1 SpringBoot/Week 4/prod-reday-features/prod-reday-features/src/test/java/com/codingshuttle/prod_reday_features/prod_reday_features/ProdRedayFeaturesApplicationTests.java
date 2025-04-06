package com.codingshuttle.prod_reday_features.prod_reday_features;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import com.codingshuttle.prod_reday_features.prod_reday_features.clients.EmployeeClients;
import com.codingshuttle.prod_reday_features.prod_reday_features.dto.EmployeeDTO;

//need this anotation to run the all the testcases in order
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
class ProdRedayFeaturesApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	EmployeeClients employeeClients;
	
	@Test
	@Order(2) // to change the order of the test cases execuatin
	void getAllEmployee() {
		System.out.println("get All Employee Test");
		List<EmployeeDTO> employeeDTOList = employeeClients.getAllEmployees();
		System.out.println("GEt All Employee Run Successfully");
		System.out.println(employeeDTOList);
	}

	@Test
	void getEmployeeById() {
		EmployeeDTO employeeDTO = employeeClients.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}
	
//	create the new Employee
	@Test
	void createNewEmployee() {
		EmployeeDTO savedEmployeeDto = new EmployeeDTO(null ,"Tejas", "tejasgopale111@gmail.com", 20 , "ADMIN" , true);
		EmployeeDTO employeeDTO = employeeClients.createNewEmployee(savedEmployeeDto);
	}
}
