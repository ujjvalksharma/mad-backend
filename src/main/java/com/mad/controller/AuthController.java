package com.mad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mad.models.AuthenticationRequest;
import com.mad.models.AuthenticationResponse;
import com.mad.models.MADUser;
import com.mad.repository.MADUserRepository;
import com.mad.service.MyUserDetailsService;
import com.mad.util.JwtUtil;

@RestController
class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	MADUserRepository MADUserRepository;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

	/*	try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
*/

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		MADUser mADUser = MADUserRepository.findByUsername(authenticationRequest.getUsername());
		
		if(userDetails==null || mADUser==null || !mADUser.getPassword().equals(userDetails.getPassword())) {
			return ResponseEntity.ok(null);
		}
		

	//	final String jwt = jwtTokenUtil.generateToken(userDetails);

		AuthenticationResponse authenticationResponse=AuthenticationResponse
				.builder()
				.jwt("jwt")
				.mADUser(mADUser)
				.build();
		return ResponseEntity.ok(authenticationResponse);
	}
	
	@GetMapping("/validate/username/{username}")
	public ResponseEntity<AuthenticationResponse> getUserDetailsByJwt(@PathVariable String username, @RequestHeader (name="Authorization") String token){
		
		if(!jwtTokenUtil.validateToken(token,username)) {
			return ResponseEntity.ok(null);
		}
		
		MADUser mADUser = MADUserRepository.findByUsername(username);
		
		AuthenticationResponse authenticationResponse=AuthenticationResponse
				.builder()
				.jwt(token)
				.mADUser(mADUser)
				.build();
		return ResponseEntity.ok(authenticationResponse);
	}

}
