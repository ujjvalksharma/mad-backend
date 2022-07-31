package com.mad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mad.dto.LeagueRankingDetails;
import com.mad.models.LeagueDetails;
import com.mad.models.LeagueMemebers;
import com.mad.models.MADUser;
import com.mad.service.LeagueService;

@RestController
public class LeagueController {
	
	@Autowired
	LeagueService leagueService;
	
	@PostMapping("/league")
	public ResponseEntity<LeagueDetails> saveLeague(@RequestBody LeagueDetails leagueDetails){
		
		return  ResponseEntity.ok(leagueService.saveLeague(leagueDetails).get());
	}
	
	@PostMapping("/league/{leagueId}/user/{userId}")
	public ResponseEntity<LeagueMemebers> saveMemberToLeague(@PathVariable int leagueId, @PathVariable int userId ){

		return  ResponseEntity.ok(leagueService.saveUserToLeagueMember(leagueId, userId).get());
	}
	
	
	@GetMapping("/league/{leagueId}/users")
	public ResponseEntity<List<LeagueMemebers>> getLeagueMemberDetails(@PathVariable int leagueId){
		
		return  ResponseEntity.ok(leagueService.findMembersOfLeague(leagueId).get());
	}
	
	@PostMapping("/league/accesscode/{accessCode}/user/{userId}")
	public ResponseEntity<LeagueMemebers> addMemberToLeagueByAccessCode(@PathVariable String accessCode
			,@PathVariable int userId){
		
		return  ResponseEntity.ok(leagueService.addMemberToLeagueByAccessCode(accessCode,userId).get());
	}
	
	@GetMapping("/league/search/{leagueName}") //search by league name
	public ResponseEntity<List<LeagueDetails>> searchLeague(@PathVariable String leagueName){
		
		return  ResponseEntity.ok(leagueService.searchLeague(leagueName).get());
	}
	
	@GetMapping("/league/search/{leagueId}/users/{key}") //key can be username or name
	public ResponseEntity<List<MADUser>> searchUserInALeague(@PathVariable int leagueId, @PathVariable String key){
		
		return  ResponseEntity.ok(leagueService.searchUserInALeague(leagueId,key).get());
	}
	
	@PutMapping("league/rank/memebers")
	public  ResponseEntity<List<LeagueRankingDetails>> rankLeagueMembers(@RequestBody List<LeagueMemebers> leagueMembers){
		return ResponseEntity.ok(leagueService.rankMembers(leagueMembers).get());
		
	}
	
	@GetMapping("league/{leagueId}/rank/memebers") 
	public  ResponseEntity<List<LeagueRankingDetails>> rankLeagueMembersByLeague(@PathVariable int leagueId){
		//to do: buggy, same user is saved twice in league members
		//to do: ranking is not correct
		return ResponseEntity.ok(leagueService.rankMembers(leagueService.findMembersOfLeague(leagueId).get()).get());
		
	}

}
