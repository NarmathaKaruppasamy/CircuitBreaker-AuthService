package com.eldorado.circuitbreakerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eldorado.circuitbreakerservice.dto.UserCredentialsDto;

//import com.eldorado.authservice.dto.UserCredentialsDto;

import io.vavr.collection.Map;

@FeignClient(url="http://localhost:8084",name="authclient")
public interface AuthClient {
	
	@PostMapping("/api/auth/getaccesstoken")
	public ResponseEntity<String> getAccessToken(@RequestBody UserCredentialsDto userCredentialsDto);
}
