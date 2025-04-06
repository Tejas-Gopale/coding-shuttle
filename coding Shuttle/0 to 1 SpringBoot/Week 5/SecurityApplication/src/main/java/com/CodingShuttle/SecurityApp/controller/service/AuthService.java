package com.CodingShuttle.SecurityApp.controller.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CodingShuttle.SecurityApp.dto.LoginDto;
import com.CodingShuttle.SecurityApp.dto.LoginResponseDto;
import com.CodingShuttle.SecurityApp.dto.SignUpDto;
import com.CodingShuttle.SecurityApp.dto.UserDto;
import com.CodingShuttle.SecurityApp.entity.User;
import com.CodingShuttle.SecurityApp.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
		//for logging 
		Logger log =  LoggerFactory.getLogger(UserService.class);
		//authentication manager 
		private final AuthenticationManager authenticationManager;	
		//JWT Service
		private final JwtService jwtService;
		
		private final UserService userService;

		
		//Login 
		public LoginResponseDto login(LoginDto loginDto) {
			//log.info("Login in the User first Step \t: UserService");
		Authentication authentication=	authenticationManager.authenticate
			(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		User user = (User) authentication.getPrincipal();
		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		
		return new LoginResponseDto(user.getId(),accessToken,refreshToken);
		}

		public LoginResponseDto refreshToken(String refreshToken) {
		   
			Long userId = jwtService.getUserIdFromToken(refreshToken);
		    System.out.println(userId);
			User user = userService.getUserById(userId);

		    // Generate new access token
		    String accessToken = jwtService.generateAccessToken(user);
		    
		    // Optionally generate a new refresh token
		    String newRefreshToken = jwtService.generateRefreshToken(user);

		    return new LoginResponseDto(user.getId(), accessToken, newRefreshToken);
		}
}
