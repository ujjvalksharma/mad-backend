package com.mad.repository;

import org.springframework.stereotype.Repository;

import com.mad.models.MADUser;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MADUserRepository extends JpaRepository<MADUser, Integer>{

	public MADUser findByUsername(String userName);
}
