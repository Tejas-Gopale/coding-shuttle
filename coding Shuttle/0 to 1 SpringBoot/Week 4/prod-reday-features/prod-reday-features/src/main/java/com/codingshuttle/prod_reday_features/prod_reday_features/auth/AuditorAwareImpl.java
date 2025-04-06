package com.codingshuttle.prod_reday_features.prod_reday_features.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		
		//get the security context 
		//get the auntications
		//get the principles
		//get the username
		return Optional.of("Tejas Gopale");
	}

}
