package com.codingshuttle.prod_reday_features.prod_reday_features.clients.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.codingshuttle.prod_reday_features.prod_reday_features.advice.APIResponse;
import com.codingshuttle.prod_reday_features.prod_reday_features.clients.EmployeeClients;
import com.codingshuttle.prod_reday_features.prod_reday_features.dto.EmployeeDTO;
import com.codingshuttle.prod_reday_features.prod_reday_features.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeClientImplementation implements EmployeeClients{

	private final RestClient restClient;
	
	Logger log = LoggerFactory.getLogger(EmployeeClientImplementation.class);
	
	@Override
	public List<EmployeeDTO> getAllEmployees() {
		log.trace("Trying to retrive get all Employees");
	try {
		System.out.println("Inside of the GetAll Employees");
		APIResponse<List<EmployeeDTO>> employeeDtoList=	restClient
									.get()
									.uri("employees")
									.retrieve()
									.body(new ParameterizedTypeReference<>() {
										});
		 return employeeDtoList.getData();
	}catch (Exception e) {
		System.out.println(e);
		log.error("Exception Occured in getAllEmployee" + e);
		throw new RuntimeException(e);
	}
	}

	@Override
	public EmployeeDTO getEmployeeById(Long employeeId) {
	try {
		APIResponse<EmployeeDTO> emplApiResponse = restClient
					.get()
					.uri("/{employeeId}", employeeId)
					.retrieve()
					.body(new ParameterizedTypeReference<>() {
		});
		log.info("Successfully  retrive the Employees" );
		log.trace("Retrives Employees List in getAllEmployees: {} , {}"+ emplApiResponse.getData());
		return emplApiResponse.getData();
		
	}catch (Exception e) {
		throw new RuntimeException(e);
	}
	}

	@Override
	public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
		try {
			APIResponse<EmployeeDTO> employeeApiResponse = restClient
					.post()
					.uri("/createnewEmployee")
					.body(employeeDTO)
					.retrieve()
					.onStatus(HttpStatusCode::is4xxClientError, (req , res ) -> {
						System.out.print(new String (res.getBody().readAllBytes()));
						throw new ResourceNotFoundException("error Occured" + "Could not Create the Employee");
					})
					.body(new ParameterizedTypeReference<>() {
			});
			log.info("Successfully Createde the Employee"+ employeeApiResponse.getData());
			return employeeApiResponse.getData();
		}catch (Exception e) {
			log.error("Exception Occured in the create New Employee :{}"+ e);
			throw new RuntimeException(e);
		}
	}
}
