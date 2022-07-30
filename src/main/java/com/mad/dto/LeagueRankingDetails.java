package com.mad.dto;

import com.mad.models.LeagueMemebers;

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
	
	private int leagueId;
	private LeagueMemebers leagueMemebers;
	private int points;
	private int rank;
	
	

}
