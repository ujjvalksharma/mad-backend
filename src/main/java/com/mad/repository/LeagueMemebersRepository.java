package com.mad.repository;

import org.springframework.stereotype.Repository;

import com.mad.models.LeagueDetails;
import com.mad.models.LeagueMemebers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface LeagueMemebersRepository extends JpaRepository<LeagueMemebers, Integer>{

	@Query(value ="Select * from league_memebers lm where  league_id=%?1%", nativeQuery = true)
	Optional<List<LeagueMemebers>> findbyLeagueId(int leagueId);
	
	@Query(value ="Select * from league_memebers lm where  user_id=%?1%", nativeQuery = true)
	Optional<List<LeagueMemebers>> findbyUserId(int userId);
}
