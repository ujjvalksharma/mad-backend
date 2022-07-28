package com.mad.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

		leagueDetails=leagueRepository.save(leagueDetails);
		saveUserToLeagueMember(leagueDetails.getId(),leagueDetails.getUserId());
		
		String accessCodeStr=leagueDetails.getId()+"-"+leagueDetails.getLeagueName()+"-"+leagueDetails.getUserId();
		byte[] accessCodeByte = accessCodeStr.getBytes(StandardCharsets.UTF_8);
		String encodedAccessCode = Base64.getEncoder().encodeToString(accessCodeByte);
		
		leagueDetails.setAccessCode(encodedAccessCode);
		
		return Optional.of(leagueRepository.save(leagueDetails));
	}

	public Optional<LeagueMemebers> saveUserToLeagueMember(int leagueId, int userId) {
		//
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

	public Optional<LeagueMemebers> addMemberToLeagueByAccessCode(String accessCode) {
		
		byte[] decoded = Base64.getDecoder().decode(accessCode);
		String decodedAccessCode=new String(decoded, StandardCharsets.UTF_8);
		String[] splittedAccessCode=decodedAccessCode.split("-");
		
		return saveUserToLeagueMember(Integer.parseInt(splittedAccessCode[0])
				,Integer.parseInt(splittedAccessCode[2]));
	}
	

}
