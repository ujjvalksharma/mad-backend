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

import com.mad.dto.LeagueMemberDTO;
import com.mad.models.LeagueMemebers;
import com.mad.models.MADUser;
import com.mad.models.UserToken;
import com.mad.repository.LeagueMemebersRepository;
import com.mad.repository.MADUserRepository;
import com.mad.repository.UserTokenRepository;
import com.mad.service.LeagueMemberToLeagueMemberDto;
import com.mad.service.MyUserDetailsService;


@RestController
public class UserController {
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	LeagueMemebersRepository leagueMemebersRepository;
	
	@Autowired
	UserTokenRepository userTokenRepository;
	
	@Autowired
	MADUserRepository mADUserRepository;
	
	@Autowired
	LeagueMemberToLeagueMemberDto leagueMemberToLeagueMemberDto;
	
	@PostMapping("/user")
	public ResponseEntity<MADUser> saveUser( @RequestBody MADUser madUser){
		
		return  ResponseEntity.ok(myUserDetailsService.save(madUser).get());
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<MADUser> saveUser(@PathVariable int userId){
		
		return  ResponseEntity.ok(mADUserRepository.findById(userId).get());
	}
	
	
	
	@PutMapping("/user")
	public ResponseEntity<MADUser> updateUser( @RequestBody MADUser madUser){
		
		return  ResponseEntity.ok(myUserDetailsService.updateUser(madUser).get());
	}
	
	@PostMapping("/usertoken")
	public ResponseEntity<UserToken> saveUserToken( @RequestBody UserToken userToken){
		
		return  ResponseEntity.ok(userTokenRepository.save(userToken));
	}
	
	@GetMapping("/user/{userId}/League")
	public ResponseEntity<List<LeagueMemberDTO>> getLeagueUserisPartOf(@PathVariable int userId){
		//to do: incorrect results
		return  ResponseEntity.ok(leagueMemberToLeagueMemberDto.convert(leagueMemebersRepository.findbyUserId(userId).get()));
	}
	
	@PutMapping("/user/{userId}/increment/{num}")
	public ResponseEntity<MADUser> saveUser(@PathVariable int userId,@PathVariable int num){
		
		return  ResponseEntity.ok(myUserDetailsService.incrementCoins(userId,num).get());
	}
	
	@GetMapping("/findAllUser")
	public ResponseEntity<List<MADUser>>  findAllUser(){
		//to do: incorrect results
		return  ResponseEntity.ok(mADUserRepository.findAll());
	}

}
