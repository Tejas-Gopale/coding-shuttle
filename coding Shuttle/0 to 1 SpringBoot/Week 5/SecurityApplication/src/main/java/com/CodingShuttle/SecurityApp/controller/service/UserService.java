package com.CodingShuttle.SecurityApp.controller.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CodingShuttle.SecurityApp.dto.LoginDto;
import com.CodingShuttle.SecurityApp.dto.SignUpDto;
import com.CodingShuttle.SecurityApp.dto.UserDto;
import com.CodingShuttle.SecurityApp.entity.User;
import com.CodingShuttle.SecurityApp.exceptions.ResourceNotFoundException;
import com.CodingShuttle.SecurityApp.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	//for saving
	private final UserRepo userRepo;
	
	//for logging 
	Logger log =  LoggerFactory.getLogger(UserService.class);
	
	//for converting 
	private final ModelMapper modelMapper;
	
	private final PasswordEncoder passwordEncoder;
		
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("1");
		return userRepo.findByEmail(username)
				.orElseThrow(()-> new BadCredentialsException("User with the email " + username + "not found"));
	}
	
	
	public User getUserById(Long userId) {
		return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Preset corresponding to this id"+ userId));
	}
	
	public UserDto signUp(SignUpDto signUpDto) {
		Optional<User> user=	userRepo.findByEmail(signUpDto.getEmail());
		if(user.isPresent()) {
				log.info("user is Alreday Present with the Email id "+ signUpDto.getEmail());
				throw new BadCredentialsException("User With the Email is Alreday Presnet in the Database \t" + signUpDto.getEmail());
		}
		//map th esignupDto with the User
		User toBeCreate = modelMapper.map(signUpDto  , User.class);
		toBeCreate.setPassword(passwordEncoder.encode(toBeCreate.getPassword()));
		User saveUser =  userRepo.save(toBeCreate);
		log.info("user is Saved to the database" + signUpDto.toString());
			return modelMapper.map(saveUser, UserDto.class);
		}
	
	
	
}
