package com.CodingShuttle.SecurityApp.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.CodingShuttle.SecurityApp.controller.service.JwtService;
import com.CodingShuttle.SecurityApp.controller.service.UserService;
import com.CodingShuttle.SecurityApp.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Custom filter to  

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

	private final 	JwtService jwtService;
	private final UserService userService;
	
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver handlrExceptionResolver;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			System.out.println("Inside the dofiltert Internal Chaine");
		try {
			
			System.out.println("Inside the Try cathc block");
						final String requestTokenHeader= request.getHeader("Authorization");
						if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
							System.out.println("Insdie the if Statement");
							filterChain.doFilter(request, response);
							return;
					}else {
						System.out.println("else Part Running");
					}
				
					String token = requestTokenHeader.split("Bearer")[1];
					
					Long userId =	jwtService.getUserIdFromToken(token);
					
					System.out.println("outside of the first else part");
					if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
						
						System.out.println("If User id = null and security context == null");
						User user= userService.getUserById(userId);
						
						UsernamePasswordAuthenticationToken authenticationToken = 
									new UsernamePasswordAuthenticationToken(user, null , null);
						
						authenticationToken.setDetails(
								new WebAuthenticationDetailsSource().buildDetails(request)
								);
						
						//pass the user to security fielter chain 
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}
					
					filterChain.doFilter(request, response);
						//		we can do anything with the response 
					System.out.println("Custom Filter Response\t\t " + response.toString());
			}catch (Exception ex) {
				System.out.println("inside the catch block");
				handlrExceptionResolver.resolveException(request, response, null, ex);
				
			}
	}
}
