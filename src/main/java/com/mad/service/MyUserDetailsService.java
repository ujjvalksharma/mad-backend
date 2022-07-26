package com.mad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mad.models.MADUser;
import com.mad.repository.MADUserRepository;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	MADUserRepository MADUserRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
    	MADUser existingUser=MADUserRepository.findByUsername(userName);
    	if(existingUser==null) {
    		return new User(null, null,
                    new ArrayList<>());
    	}
        return new User(existingUser.getUsername(), existingUser.getPassword(),
                new ArrayList<>());
    }
    
    public Optional<MADUser> save(MADUser madUser) {
    	
    	MADUser existingUser=MADUserRepository.findByUsername(madUser.getUsername());
    	if(existingUser!=null) {
    		return Optional.of(existingUser);
    	}
    return Optional.of(MADUserRepository.save(madUser));
    
    }
}
