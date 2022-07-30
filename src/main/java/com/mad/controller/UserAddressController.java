package com.mad.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mad.models.UserAddress;
import com.mad.repository.UserAddressRepository;

@RestController
public class UserAddressController {
	
	UserAddressRepository userAddressRepository;
	@PostMapping("/userAddress")
	public UserAddress save(UserAddress userAddress) {
		return userAddressRepository.save(userAddress);
		
	}
	

}
