package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.annotations.Collate;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository.EmployeeRepo;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Service
public class EmployeeService {

	private final EmployeeRepo employeeRepo;
	
	//model mapper -> Entity to DTO 
	private final ModelMapper modelMapper; 

	public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper) {
		super();
		this.employeeRepo = employeeRepo;
		this.modelMapper = modelMapper;
	}
	
	 
	
	public Optional<EmployeeDTO> getEmployeeById(Long id) {
	//Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(id);
//	return modelMapper.map(employeeEntity, EmployeeDTO.class);
	//return employeeEntity.map( employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
	
	return employeeRepo.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
	}

	public List<EmployeeDTO> getAllEmployee() {
		// TODO Auto-generated method stub
		List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
		
//		Streams 
	return	employeeEntities
			.stream()
			.map(entity -> modelMapper.map(entity, EmployeeDTO.class)).collect(Collectors.toList());
	}	

	
	public EmployeeEntity save(EmployeeEntity inputEmployee) {
		return employeeRepo.save(inputEmployee);
	}



	public EmployeeDTO createNewEmployee(EmployeeDTO newuser) {
		//to check if the user is admin or not 
		//log something
		EmployeeEntity toSaveEntity = modelMapper.map(newuser,EmployeeEntity.class);
		System.out.printf(toSaveEntity.getEmail(),toSaveEntity.getId());

		EmployeeEntity saveEmployeeEntity = employeeRepo.save(toSaveEntity);
		return modelMapper.map(saveEmployeeEntity, EmployeeDTO.class);	
	}



	public EmployeeDTO UpdateEmnployeeById(EmployeeDTO employeeDTO, Long employeeId) {
		 isExistBEmployeeId(employeeId);
		EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
		employeeEntity.setId(employeeId);
		EmployeeEntity savedEmployeeEntity = employeeRepo.save(employeeEntity);
		
		return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
	}



	public boolean DeleteEmployeeById(Long employeeId) {
		 isExistBEmployeeId(employeeId);
		employeeRepo.deleteById(employeeId);
		return true;
	}

//	 check the Emnployee is presend using the EmployeeId
	
	public boolean isExistBEmployeeId(Long employeeId) {
		boolean exits = employeeRepo.existsById(employeeId);
		if(!exits)	throw new ResourceNotFoundException("Employee not Found with the id :" + employeeId);
		return true;
	}

	
//  update the Information of the Admin using the Reflection  Concept 
	public EmployeeDTO updateParticalyEmployee(Long employeeId, Map<String, Object> updates) {
	isExistBEmployeeId(employeeId);
	EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).orElseThrow();
	updates.forEach((field, value)-> {
	Field fieldToBeUpdated =ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
	
	fieldToBeUpdated.setAccessible(true);
	ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
	});
	
	return modelMapper.map(employeeRepo.save(employeeEntity), EmployeeDTO.class);
	}
	
	
}
