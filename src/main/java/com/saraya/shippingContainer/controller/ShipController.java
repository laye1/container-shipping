package com.saraya.shippingContainer.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saraya.shippingContainer.model.Shipping;
import com.saraya.shippingContainer.service.ShippingService;

@RestController
@RequestMapping("/api/ship")
@CrossOrigin("*")
public class ShipController {
	
	@Autowired
	ShippingService shippingService;
	
	@PostMapping("/contact")
	public void registerShip(@RequestBody Shipping shipping ) {
		
		shippingService.registerShip(shipping);
		
	}
	
	@GetMapping("/contacts")
	public List<Shipping> getShip( ) {
		
		return shippingService.getShip();
		
	}

	
}
