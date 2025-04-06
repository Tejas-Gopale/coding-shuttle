package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.configs;

import org.hibernate.annotations.Bag;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper getModelMapper() {
	return	 new ModelMapper();
	}
}
