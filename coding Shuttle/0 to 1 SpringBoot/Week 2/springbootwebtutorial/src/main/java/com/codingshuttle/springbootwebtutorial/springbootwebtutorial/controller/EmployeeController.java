package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository.EmployeeRepo;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

//  Lecture 2.2 
//	@GetMapping(path = "/")
//	public String getMySupperSecreatMessage() {
//		return "Secrate Message : asdfghjkl@mnbv";
//	}
//	
////	PathVaraible
//	@GetMapping(path = "/{employeeId}")
//	public EmployeeDTO  getEmployeeById(@PathVariable Long employeeId) {
//			return new EmployeeDTO(employeeId , "Tejas", "tejasgopale111@gmail.com" , 24,  true);
//	}
//	
//	@GetMapping(path = "/employees")
//	public String getAllEmployee(@RequestParam(required = false) Integer age , @RequestParam(required = false) String sortBy){
//		return "Hii age" + age + " " + sortBy;
//	}
//	
//	@PostMapping
//	public String createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
//			inputEmployee.setAge(21);
//		return inputEmployee.getEmail()+inputEmployee.getName()+inputEmployee.getAge();
//	}
//	
//	@PutMapping
//	public String updateEmployeeById() {
//		return "Hii form putMallping";
//	}
//	

//	 2.3 The Persistance layaer and JPA Repository 

//	private final EmployeeRepo employeeRepo;
//
//
//	public EmployeeController(EmployeeRepo employeeRepo) {
//		super();
//		this.employeeRepo = employeeRepo;
//	}
//	
//	
//	@GetMapping(path = "/{employeeId}")
//	public Optional<EmployeeEntity>  getEmployeeById(@PathVariable Long employeeId) {
//			
//		return employeeRepo.findById(employeeId);
//	}
//	
//	@GetMapping(path = "/employees")
//	public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false) Integer age , @RequestParam(required = false) String sortBy){
//		return employeeRepo.findAll();
//	}
//	
//	@PostMapping
//	public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
//			
//		return employeeRepo.save(inputEmployee);
//	}
//	
//	@PutMapping
//	public String updateEmployeeById() {
//		return "Hii form putMallping";
//	}
//	

//	2.3
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
	super();
	this.employeeService = employeeService;
}

//	@GetMapping(path = "/{employeeId}")
//	public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
//		return employeeService.getEmployeeById(employeeId);
//		
//	}

//	 set the status code with it., 
	@GetMapping(path = "/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@Valid @PathVariable Long employeeId) {
		Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);
	return	employeeDTO
			.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
			.orElseThrow(()->new  ResourceNotFoundException("Employee Not Found with id :"+ employeeId));
		
	}

	
	@GetMapping(path = "/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String sortBy) {
		return ResponseEntity.ok(employeeService.getAllEmployee());
	}

	@PostMapping(path = "/createnewEmployee")
	public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO newuser) {
		EmployeeDTO saveEmployee= employeeService.createNewEmployee(newuser);
				return  new ResponseEntity<>(saveEmployee,HttpStatus.CREATED);
	}
	
//	@ExceptionHandler(NoSuchElementException.class)
//	public ResponseEntity<String> handelEmployeeNotFound(NoSuchElementException exception) {
//		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
//	}
	
	@PutMapping(path = "/{employeeId}")
	public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO , @PathVariable Long employeeId ) {
		return ResponseEntity.ok(employeeService.UpdateEmnployeeById(employeeDTO, employeeId));
		}
	
	@DeleteMapping(path = "/{employeId}")
	public ResponseEntity<Boolean>  DeleteEmployeeById(@PathVariable(name = "employeId") Long employeeId ) {
//		return ResponseEntity.ok(employeeService.DeleteEmployeeById( employeeId));
		boolean gotDeleted = employeeService.DeleteEmployeeById(employeeId);
		if(gotDeleted) return ResponseEntity.ok(true);
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> updateParticalyEmployee(@RequestBody Map<String, Object> updates,
				@PathVariable Long employeeId) {
		EmployeeDTO employeeDTO= employeeService.updateParticalyEmployee(employeeId, updates);
	
		if(employeeDTO== null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(employeeDTO);
	}


}
