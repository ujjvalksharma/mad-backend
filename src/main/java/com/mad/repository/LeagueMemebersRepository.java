package com.mad.repository;

import org.springframework.stereotype.Repository;

import com.mad.models.LeagueDetails;
import com.mad.models.LeagueMemebers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LeagueMemebersRepository extends JpaRepository<LeagueMemebers, Integer>{

	Optional<List<LeagueMemebers>> findbyLeagueId(int leagueId);
	
	Optional<List<LeagueMemebers>> findbyUserId(int userId);
}
