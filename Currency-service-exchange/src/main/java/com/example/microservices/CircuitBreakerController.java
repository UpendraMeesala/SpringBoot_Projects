package com.example.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class); 
	
	@GetMapping("/sample-api")
	@Retry(name="sample-api",fallbackMethod="fallbackResponse")	
	public String getSampleResponse() {
		logger.info("default retry calledddd");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		return forEntity.getBody();
	}
	
	public String fallbackResponse(Exception ex) {
		return "fallback-response";
	}
}
