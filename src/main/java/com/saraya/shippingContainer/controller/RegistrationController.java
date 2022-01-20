package com.saraya.shippingContainer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.saraya.shippingContainer.registration.RegistrationService;
import com.saraya.shippingContainer.registration.RequestRegistration;

@RequestMapping("/api/ship/register")
@RestController
@CrossOrigin("*")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody RequestRegistration request) {
		
		String response = registrationService.register(request);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(path="confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
