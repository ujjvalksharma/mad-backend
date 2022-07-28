package com.mad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mad.models.LeagueMemebers;
import com.mad.models.MADUser;
import com.mad.repository.LeagueMemebersRepository;
import com.mad.service.MyUserDetailsService;


@RestController
public class UserController {
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	LeagueMemebersRepository leagueMemebersRepository;
	
	@PostMapping("/user")
	public ResponseEntity<MADUser> saveUser( @RequestBody MADUser madUser){
		
		return  ResponseEntity.ok(myUserDetailsService.save(madUser).get());
	}
	
	@GetMapping("/user/{userId}/League")
	public ResponseEntity<List<LeagueMemebers>> getLeagueUserisPartOf(@PathVariable int userId){
		
		return  ResponseEntity.ok(leagueMemebersRepository.findbyUserId(userId).get());
	}

}
