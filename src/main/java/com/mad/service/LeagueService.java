package com.mad.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mad.models.MADUser;
import com.mad.dto.LeagueRankingDetails;
import com.mad.dto.LeetcodeUserDetails;
import com.mad.models.LeagueDetails;
import com.mad.models.LeagueMemebers;
import com.mad.repository.LeagueMemebersRepository;
import com.mad.repository.LeagueRepository;
import com.mad.repository.MADUserRepository;
import com.mad.service.leetcode.LeetcodeService;

@Service
public class LeagueService {

	@Autowired
	LeagueRepository leagueRepository;
	
	@Autowired
	LeetcodeService leetcodeService;
	
	@Autowired
	MADUserRepository mADUserRepository;
	
	@Autowired
	LeagueMemebersRepository leagueMemebersRepository;

	public Optional<LeagueDetails> saveLeague(LeagueDetails leagueDetails) {

		if(leagueRepository.findById(leagueDetails.getId()).isPresent()) {
			throw new IllegalArgumentException("League Id is already created");
		}
			
		leagueDetails.setAccessCode("");
		leagueDetails=leagueRepository.save(leagueDetails);
		saveUserToLeagueMember(leagueDetails.getId(),leagueDetails.getUserId());
		
		String accessCodeStr=leagueDetails.getId()+"-"+leagueDetails.getLeagueName()+"-"+leagueDetails.getUserId();
		byte[] accessCodeByte = accessCodeStr.getBytes(StandardCharsets.UTF_8);
		String encodedAccessCode = Base64.getEncoder().encodeToString(accessCodeByte);
		
		leagueDetails.setAccessCode(encodedAccessCode);
		System.out.println("save league details: "+leagueDetails);
		return Optional.of(leagueRepository.save(leagueDetails));
	}

	public Optional<LeagueMemebers> saveUserToLeagueMember(int leagueId, int userId) {
		
		
		Optional<List<LeagueMemebers>> listOfLeagueIdUserIsPartOf=leagueMemebersRepository
				.findbyUserIdAndLeagueId(userId, leagueId);
		
		if(listOfLeagueIdUserIsPartOf.isPresent()&&listOfLeagueIdUserIsPartOf.get().size()>0) {
			 throw new IllegalArgumentException("User is already part of the league");
		}
		
		
		// todo: check if a user exisit in a league
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    
		LeetcodeUserDetails leetcodeUserDetails=leetcodeService.getLeetcodeUserDetails(mADUserRepository.findById(userId).get().getUsername());
		LeagueMemebers leagueMemebers=LeagueMemebers
				                           .builder()
				                           .easySolvedOnDateOfJoiningLeague(leetcodeUserDetails.getEasySolved())
				                           .mediumSolvedOnDateOfJoiningLeague(leetcodeUserDetails.getMediumSolved())
				                           .hardSolvedOnDateOfJoiningLeague(leetcodeUserDetails.getHardSolved())
				                           .totalSolvedOnDateOfJoiningLeague(leetcodeUserDetails.getTotalSolved())
				                           .leagueId(leagueId)
				                           .userId(userId)
				                           .joiningDate(formatter.format(date))
				                           .build();
		return Optional.of(leagueMemebersRepository.save(leagueMemebers));
	}
	
	public Optional<List<LeagueMemebers>> findMembersOfLeague(int leagueId){
		return leagueMemebersRepository.findbyLeagueId(leagueId);
	}

	public Optional<LeagueMemebers> addMemberToLeagueByAccessCode(String accessCode, int userId) {
		
		byte[] decoded = Base64.getDecoder().decode(accessCode);
		String decodedAccessCode=new String(decoded, StandardCharsets.UTF_8);
		String[] splittedAccessCode=decodedAccessCode.split("-");
		System.out.println("splittedAccessCode: "+Arrays.toString(splittedAccessCode));
		return saveUserToLeagueMember(Integer.parseInt(splittedAccessCode[0])
				,userId);
	}


	public Optional<List<LeagueDetails>> searchLeague(String leagueName) {
		return leagueRepository.findByLeagueNameContaining(leagueName);
	}

	public Optional<List<MADUser>> searchUserInALeague(int leagueId, String key) {
		List<LeagueMemebers> leagueMemebers = leagueMemebersRepository.findbyLeagueId(leagueId).get();
		Set<Integer> userIdInLeague=new HashSet<Integer>();
		for(LeagueMemebers leagueMemeber: leagueMemebers) {
			userIdInLeague.add(leagueMemeber.getUserId());
		}
		List<MADUser>  MADUsers=mADUserRepository.findByNameOrUsernameContainingIgnoreCase(key,key).get();
		
		return Optional.of(MADUsers
				.stream()
				.filter((user)->userIdInLeague.contains(user.getId()))
				.collect(Collectors.toList()));
	}

	public Optional<List<LeagueRankingDetails>> rankMembers(List<LeagueMemebers> leagueMembers) {
		
		System.out.println("leagueMembers: "+leagueMembers);
		List<LeagueRankingDetails>  ListOfleagueRankingDetails=new ArrayList<LeagueRankingDetails>();
		
		for(LeagueMemebers leagueMember: leagueMembers) {
			
		LeetcodeUserDetails leetcodeUserDetails=
				leetcodeService.getLeetcodeUserDetails(mADUserRepository.findById(leagueMember
						.getUserId()).get().getUsername());
		
		int newHardProblemSolved=leetcodeUserDetails.getHardSolved()-leagueMember.getHardSolvedOnDateOfJoiningLeague();
		int newEasyProblemSolved=leetcodeUserDetails.getEasySolved()-leagueMember.getEasySolvedOnDateOfJoiningLeague();
		int newMediumProblemSolved=leetcodeUserDetails.getMediumSolved()-leagueMember.getMediumSolvedOnDateOfJoiningLeague();
		int leaguePoints= newEasyProblemSolved+ 2*newMediumProblemSolved+3*newHardProblemSolved;
		
		
		System.out.println("leagueMember: "+leagueMember+" leetcodeUserDetails: "+leetcodeUserDetails);
		LeagueRankingDetails leagueRankingDetails=LeagueRankingDetails
				                                  .builder()
				                                  .points(leaguePoints)
				                                  .leagueId(leagueMember.getLeagueId())
				                                  .leagueMemebers(leagueMember)
				                                  .rank(0)
				                                  .build();
		
		ListOfleagueRankingDetails.add(leagueRankingDetails);
		
		}
		
		Collections.sort(ListOfleagueRankingDetails,(l1,l2)->l2.getPoints()-l1.getPoints());
		
		for(int i=0;i<ListOfleagueRankingDetails.size();i++) {
			ListOfleagueRankingDetails.get(i).setRank(i+1);
		}
		
		return Optional.of(ListOfleagueRankingDetails);
		
	}
	

}
