package com.saraya.shippingContainer.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraya.shippingContainer.model.Shipping;
import com.saraya.shippingContainer.repository.ShippingRepository;

@Service("shippingService")
public class ShippingService {
	
	@Autowired
	ShippingRepository shippingRepository;
	
	public void registerShip(Shipping ship) {
		
		shippingRepository.save(ship);
		
		}
	
     public List<Shipping> getShip() {
		
		 return shippingRepository.findAll();
		
		}

}
