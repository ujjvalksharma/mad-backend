package com.mad.repository;

import org.springframework.stereotype.Repository;

import com.mad.models.LeagueDetails;
import com.mad.models.MADUser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MADUserRepository extends JpaRepository<MADUser, Integer>{

	public MADUser findByUsername(String userName);
	
	public Optional<List<MADUser>> findByNameOrUsernameContainingIgnoreCase(String name, String username);
	
	public Optional<List<MADUser>> findByIsReminderOn(int isReminderOn);
}
