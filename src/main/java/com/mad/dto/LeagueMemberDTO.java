package com.mad.dto;

import com.mad.models.LeagueDetails;
import com.mad.models.MADUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class LeagueMemberDTO {

	
	private int id;
	private int userId;
	private LeagueDetails leagueDetails;
	private String joiningDate;
	private int easySolved;
	private int mediumSolved;
	private int hardSolved;
	private int totalSolved;
	private MADUser mADUser;
	private int totalMembersALeague;
	
	
}
