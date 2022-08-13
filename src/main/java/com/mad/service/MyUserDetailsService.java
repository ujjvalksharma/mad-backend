package com.mad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mad.controller.EmailController;
import com.mad.dto.Email;
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
	
	@Autowired
	EmailController emailController;
	
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
    		throw new IllegalArgumentException("This username is not a leetcode username");
    	}
    	MADUser existingUser=MADUserRepository.findByUsername(madUser.getUsername());
    	if(existingUser!=null) {
    		
    		throw new IllegalArgumentException("You are already a registered user");
    	}
    	
    
    	emailController.sendEmail(Email.builder()
    			.reciverEmail(madUser.getEmail())
    			.subject("Registration successful at Leetground")
    			.body("Hi "+madUser.getName()
                        +"\n"
                        +"Welcome you have registered successfully! For any technical and non technical contact: +1 (6172)-259-8936 "
                        +"\n"
                        +"Thanks & Regards,"
                        +"\n"
                        +"Team LeetGround")
    			.build());
  
    	
    	madUser.setBillingAddress("Northeastern University, Boston, MA");
    return Optional.of(MADUserRepository.save(madUser));
    
    }
    
  public Optional<MADUser> updateUser(MADUser madUser) {
    	
    	MADUser existingUser=MADUserRepository.findByUsername(madUser.getUsername());
    	if(existingUser!=null) {
    		madUser.setId(existingUser.getId());
    	}else {
    	throw new IllegalArgumentException("User doesn't exisit!");
    	}
    return Optional.of(MADUserRepository.save(madUser));
    
    }

	public Optional<MADUser> incrementCoins(int userId, int num) {
		MADUser user=MADUserRepository.findById(userId).get();
		user.setCoins(user.getCoins()+num);
		return Optional.of(MADUserRepository.save(user));
	}
	
}
