package com.CodingShuttle.SecurityApp.controller.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CodingShuttle.SecurityApp.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secretkey}")
	private String jwtSecretKey;
	
	//method to create the key 
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
// create the JWT Tocken 
//	public String generateToken(User user) {
//	    return Jwts.builder()
//	            .setSubject(user.getId().toString()) // Corrected method name
//	            .claim("email", user.getEmail())
//	            .claim("roles", Set.of("ADMIN", "USER"))
//	            .setIssuedAt(new Date()) // Corrected method name
//	            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) // Corrected method name
//	            .signWith(getSecretKey())
//	            .compact();
//	}
	

	
	
	//how to get something form the usertocken 
	public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        
        return Long.valueOf(claims.getSubject()); // Assuming the subject is the user ID
    }
	
	//Refresh token 6.1
	// Access Token 
		public String generateAccessToken(User user) {
		    return Jwts.builder()
		            .setSubject(user.getId().toString()) // Corrected method name
		            .claim("email", user.getEmail())
		            .claim("roles", Set.of("ADMIN", "USER"))
		            .setIssuedAt(new Date()) // Corrected method name
		            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) // method of the 10 Mins
		            .signWith(getSecretKey())
		            .compact();
		}
		
		//generate the ZZRefresh Token
		public String generateRefreshToken(User user) {
		    return Jwts.builder()
		            .setSubject(user.getId().toString()) // Corrected method name
		            .setIssuedAt(new Date()) // Corrected method name
		            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60*24*30*6)) // Six  Months for the Refreshtocken	
		            .signWith(getSecretKey())
		            .compact();
		}
		
}
