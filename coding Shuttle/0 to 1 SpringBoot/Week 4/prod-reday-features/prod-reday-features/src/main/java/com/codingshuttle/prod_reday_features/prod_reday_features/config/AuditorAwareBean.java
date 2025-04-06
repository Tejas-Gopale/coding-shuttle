package com.codingshuttle.prod_reday_features.prod_reday_features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.codingshuttle.prod_reday_features.prod_reday_features.auth.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl" )
public class AuditorAwareBean {

	@Bean
	AuditorAware getAuditorAwareImpl() {
		return new AuditorAwareImpl();
	}
}
