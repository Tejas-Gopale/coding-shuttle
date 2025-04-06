package com.CodingShuttle.SecurityApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.CodingShuttle.SecurityApp.filters.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // we are telling to springboot you wnats to enable the security 
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtAuthFilter authFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// mostly we not working with login --> 
		//when fron end is anyother and backend is any other use jwt  and cross error handling
		httpSecurity
			.authorizeHttpRequests(auth -> 
					auth
					.requestMatchers("/post", "/error", "/auth/**").permitAll()// anyone can access the get url
					.requestMatchers("/auth/refresh").permitAll()
					//.requestMatchers("/post/**").hasAnyRole("ADMIN")
//					.requestMatchers("/post/**").authenticated()
					.anyRequest().authenticated())
					.csrf(csrfconfig -> csrfconfig.disable())// desable the csrf config
					.sessionManagement(sessionConfig  -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);					
		return httpSecurity.build();
	}
	
	@Bean
	AuthenticationManager authentactionManager (AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
