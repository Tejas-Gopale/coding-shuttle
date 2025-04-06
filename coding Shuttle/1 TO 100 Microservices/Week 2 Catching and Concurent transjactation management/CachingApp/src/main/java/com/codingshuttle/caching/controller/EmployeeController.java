package com.codingshuttle.caching.controller;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingshuttle.caching.dto.EmployeeDto;
import com.codingshuttle.caching.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
		EmployeeDto employeeDto = employeeService.getEmployeeByID(id);
		return ResponseEntity.ok(employeeDto);
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto createdEmployeeDto = employeeService.createNewEmployee(employeeDto);
		return new ResponseEntity<>(createdEmployeeDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> udateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
		EmployeeDto updatedEmployeeDto = employeeService.UpdateEmployeeById(employeeDto,id);
		return ResponseEntity.ok(updatedEmployeeDto);
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.noContent().build();
	}
	
}
