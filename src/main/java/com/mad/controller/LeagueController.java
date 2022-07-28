package com.mad.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mad.models.LeagueDetails;
import com.mad.models.LeagueMemebers;
import com.mad.service.LeagueService;

@RestController
public class LeagueController {
	
	
	LeagueService leagueService;
	
	@PostMapping("/league")
	public ResponseEntity<LeagueDetails> saveLeague( @RequestBody LeagueDetails LeagueDetails){
		
		return  ResponseEntity.ok(leagueService.saveLeague(LeagueDetails).get());
	}
	
	@PostMapping("/league/{leagueId}/user/{userId}")
	public ResponseEntity<LeagueMemebers> saveMemberToLeague(@PathVariable int leagueId, @PathVariable int userId ){

		return  ResponseEntity.ok(leagueService.saveUserToLeagueMember(leagueId, userId).get());
	}
	
	
	@GetMapping("/league/{leagueId}/users")
	public ResponseEntity<List<LeagueMemebers>> getLeagueMemberDetails(@PathVariable int leagueId){
		
		return  ResponseEntity.ok(leagueService.findMembersOfLeague(leagueId).get());
	}
	
	@PostMapping("/league/accessCode/{accessCode}")
	public ResponseEntity<LeagueMemebers> addMemberToLeagueByAccessCode(@PathVariable String accessCode){
		
		return  ResponseEntity.ok(leagueService.addMemberToLeagueByAccessCode(accessCode).get());
	}
	

}
