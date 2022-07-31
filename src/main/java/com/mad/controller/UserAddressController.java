package com.mad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mad.models.UserAddress;
import com.mad.repository.UserAddressRepository;

@RestController
public class UserAddressController {
	
	@Autowired
	UserAddressRepository userAddressRepository;
	
	@PostMapping("/userAddress")
	public UserAddress save(@RequestBody UserAddress userAddress) {
		return userAddressRepository.save(userAddress);
		
	}
	

}
