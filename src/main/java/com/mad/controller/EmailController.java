package com.mad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mad.dto.Email;

@RestController
public class EmailController {
	
    @Autowired
    private JavaMailSender mailSender;
    
	@PostMapping("/email")
	public ResponseEntity<String> sendEmail(@RequestBody Email email){
		
		Runnable  runnable =()->{
			
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ujjvalksharma@gmail.com");
        message.setTo(email.getReciverEmail());
        message.setText(email.getBody());
        message.setSubject(email.getSubject());
        mailSender.send(message);
        
	};
        
        new Thread(runnable).start();
		return ResponseEntity.ok("success");
	}

}
