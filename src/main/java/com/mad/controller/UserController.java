package com.mad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mad.models.MADUser;
import com.mad.service.MyUserDetailsService;


@RestController
public class UserController {
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@PostMapping("/user")
	public ResponseEntity<MADUser> saveUser( @RequestBody MADUser madUser){
		
		return  ResponseEntity.ok(myUserDetailsService.save(madUser).get());
	}
	

}
