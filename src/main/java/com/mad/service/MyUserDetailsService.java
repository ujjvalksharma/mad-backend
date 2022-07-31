package com.mad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mad.dto.LeetcodeUserDetails;
import com.mad.models.MADUser;
import com.mad.repository.MADUserRepository;
import com.mad.service.leetcode.LeetcodeService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	MADUserRepository MADUserRepository;
	
	@Autowired
	LeetcodeService leetcodeService;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
    	MADUser existingUser=MADUserRepository.findByUsername(userName);
    	if(existingUser==null) {
    		return new User("", "",
                    new ArrayList<>());
    	}
        return new User(existingUser.getUsername(), existingUser.getPassword(),
                new ArrayList<>());
    }
    
    public Optional<MADUser> save(MADUser madUser) {
    	
    	LeetcodeUserDetails leetcodeUserDetails = leetcodeService.getLeetcodeUserDetails(madUser.getUsername());
    	if(leetcodeUserDetails==null) {
    		madUser.setId(-1);
    		return Optional.of(madUser);
    	}
    	MADUser existingUser=MADUserRepository.findByUsername(madUser.getUsername());
    	if(existingUser!=null) {
    		madUser.setId(existingUser.getId());
    	}else {
    	madUser.setIsReminderOn(1);
    	}
    return Optional.of(MADUserRepository.save(madUser));
    
    }

	public Optional<MADUser> incrementCoins(int userId, int num) {
		MADUser user=MADUserRepository.findById(userId).get();
		user.setCoins(user.getCoins()+num);
		return Optional.of(MADUserRepository.save(user));
	}
	
}
