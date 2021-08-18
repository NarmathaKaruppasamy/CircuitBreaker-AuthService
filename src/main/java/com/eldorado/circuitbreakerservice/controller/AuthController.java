package com.eldorado.circuitbreakerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eldorado.circuitbreakerservice.dto.UserCredentialsDto;
import com.eldorado.circuitbreakerservice.client.AuthClient;

import io.vavr.collection.Map;

@EnableFeignClients
@RestController
public class AuthController {
	
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
	
	@Autowired
	AuthClient authClient;
	
	@PostMapping("api/token")
	public ResponseEntity<String> getAccessToken(@RequestBody UserCredentialsDto userCredentialsDto) {

		CircuitBreaker cb = circuitBreakerFactory.create("auth");
		
		return cb.run(() -> authClient.getAccessToken(userCredentialsDto), throwable -> ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Auth service is not available"));
	}
	
	
}
