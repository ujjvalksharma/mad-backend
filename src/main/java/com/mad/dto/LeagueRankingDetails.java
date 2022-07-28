package com.mad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeagueRankingDetails {
	
	private String leagueId;
	private int userId;
	private int streak;
	private int points;
	private int rank;
	
	

}
