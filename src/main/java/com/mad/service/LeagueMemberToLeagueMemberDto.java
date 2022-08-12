package com.mad.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mad.models.LeagueMemebers;
import com.mad.repository.LeagueRepository;
import com.mad.repository.MADUserRepository;
import com.mad.dto.LeagueMemberDTO;

@Service
public class LeagueMemberToLeagueMemberDto {
	
	@Autowired
	MADUserRepository mADUserRepository;
	
	@Autowired
	LeagueRepository leagueRepository;
	
	public List<LeagueMemberDTO> convert(List<LeagueMemebers> leagueMemebers){
	return	leagueMemebers
			.stream()
			.map((leagueMemeber)-> convert(leagueMemeber))
			.collect(Collectors.toList());
		
	}
	
	public LeagueMemberDTO convert(LeagueMemebers leagueMemeber){
		
	return	LeagueMemberDTO
		.builder()
		.easySolved(leagueMemeber.getEasySolvedOnDateOfJoiningLeague())
		.hardSolved(leagueMemeber.getHardSolvedOnDateOfJoiningLeague())
		.mediumSolved(leagueMemeber.getMediumSolvedOnDateOfJoiningLeague())
		.totalSolved(leagueMemeber.getTotalSolvedOnDateOfJoiningLeague())
		.id(leagueMemeber.getId())
		.joiningDate(leagueMemeber.getJoiningDate())
		.leagueDetails(leagueRepository.findById(leagueMemeber.getLeagueId()).get())
		.mADUser(mADUserRepository.findById(leagueMemeber.getUserId()).get())
		.build();
		
	}

}
