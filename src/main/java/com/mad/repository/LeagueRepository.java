package com.mad.repository;

import org.springframework.stereotype.Repository;

import com.mad.models.LeagueDetails;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueDetails, Integer>{

	public Optional<List<LeagueDetails>> findByLeagueNameContaining(String leagueName);
}
