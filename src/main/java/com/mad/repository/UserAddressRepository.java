package com.mad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mad.models.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer>{
}