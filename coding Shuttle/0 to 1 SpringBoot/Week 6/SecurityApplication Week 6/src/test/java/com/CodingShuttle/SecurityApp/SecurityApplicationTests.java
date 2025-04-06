package com.CodingShuttle.SecurityApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.CodingShuttle.SecurityApp.controller.service.JwtService;
import com.CodingShuttle.SecurityApp.entity.User;

@SpringBootTest
class SecurityApplicationTests {

//	0
	
	@Autowired
	private JwtService jwtService;
	
	@Test
	void createUser() {
		//creating the usert
		User user = new User(4L, "tejas@gmail.com","Tejas", "tejas@123");
			
		//creating the 
		String token = jwtService.generateAccessToken(user);
		
		System.out.println("JWT Tocken is " + token);
		
		Long id = jwtService.getUserIdFromToken(token);
		
		System.out.println("Jwt Tocken id "+id);
	}

}
