package com.mad.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mad.models.UserToken;



@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer>{

	public Optional<List<UserToken>> findByUserId(int userId);
	
}
