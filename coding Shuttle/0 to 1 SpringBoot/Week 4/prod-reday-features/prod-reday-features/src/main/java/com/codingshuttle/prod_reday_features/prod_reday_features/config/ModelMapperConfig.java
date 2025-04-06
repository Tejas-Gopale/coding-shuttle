package com.codingshuttle.prod_reday_features.prod_reday_features.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper(); 
	}
}
