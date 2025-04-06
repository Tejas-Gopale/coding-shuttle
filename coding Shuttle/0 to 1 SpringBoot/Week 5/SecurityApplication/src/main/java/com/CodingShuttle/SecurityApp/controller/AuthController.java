package com.CodingShuttle.SecurityApp.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingShuttle.SecurityApp.controller.service.AuthService;
import com.CodingShuttle.SecurityApp.controller.service.UserService;
import com.CodingShuttle.SecurityApp.dto.LoginDto;
import com.CodingShuttle.SecurityApp.dto.LoginResponseDto;
import com.CodingShuttle.SecurityApp.dto.SignUpDto;
import com.CodingShuttle.SecurityApp.dto.UserDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

	
	private final UserService userService;
	
	private final AuthService authService;
	
	Logger log= LoggerFactory.getLogger(UserService.class);
	
	@Value("${deploy.env}")
	private String deployEnv;
	
	@PostMapping("/signUp")
	public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
		UserDto  userDto = userService.signUp(signUpDto);
		return ResponseEntity.ok(userDto);
	}
//login  controller	
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody LoginDto loginDto ){
//		String token = authService.login(loginDto);	
//		return ResponseEntity.ok(token);
//	}
	
//	 save the token into the cooke controller
	
	//6.1 
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto , HttpServletRequest request , HttpServletResponse response){
		
		LoginResponseDto loginResponseDto  = authService.login(loginDto);
		//System.out.println(loginResponseDto.getAccessTocken()+"\t" + loginResponseDto.getRefreshTocken());
		//System.out.println("Login response is ",loginResponseDto);
		Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
		cookie.setHttpOnly(true);
		cookie.setSecure("production".equals(deployEnv));
		response.addCookie(cookie);
		
		return ResponseEntity.ok(loginResponseDto);
	}
	
	@PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
		log.info("First Inside the Controller : \t"+1);
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));
       
		log.info("Refresh token getting and Inside the Controller : \t" + refreshToken + "\t : "+2);
		LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
		log.info("Controler Login Response DTo after everting is fine end of the controler : \t" + loginResponseDto);
        return ResponseEntity.ok(loginResponseDto);
    }
	
	
}
