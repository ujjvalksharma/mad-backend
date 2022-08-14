package com.mad.dto;

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
public class LeagueDetailsDTO {

	private int id;
	public String leagueName;
	public int userId;
	public String accessCode; 
	public String totalMembers;
	
}
