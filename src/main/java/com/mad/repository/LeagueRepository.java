package com.mad.repository;

import org.springframework.stereotype.Repository;

import com.mad.models.LeagueDetails;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueDetails, Integer>{

}
