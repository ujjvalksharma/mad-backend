package com.mad.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="LeagueMemebers")
@Table(name="LeagueMemebers")
public class LeagueMemebers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private int leagueId;
	private String joiningDate;
	@JsonProperty(value = "easySolved")
	private int easySolvedOnDateOfJoiningLeague;
	@JsonProperty(value = "mediumSolved")
	private int mediumSolvedOnDateOfJoiningLeague;
	@JsonProperty(value = "hardSolved")
	private int hardSolvedOnDateOfJoiningLeague;
	@JsonProperty(value = "totalSolved")
	private int totalSolvedOnDateOfJoiningLeague;
	
	//https://leetcode-stats-api.herokuapp.com/<YOUR_USERNAME>
	
	
}
