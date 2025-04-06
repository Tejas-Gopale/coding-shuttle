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

	//6.1 
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto , HttpServletRequest request , HttpServletResponse response){
		
		LoginResponseDto loginResponseDto  = authService.login(loginDto);
		
		Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
		cookie.setHttpOnly(true);
		cookie.setSecure("production".equals(deployEnv));
		response.addCookie(cookie);
		
		return ResponseEntity.ok(loginResponseDto);
	}
	
	@PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));
        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);
    }
	
	
}
