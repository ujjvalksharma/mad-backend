package com.mad.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mad.client.FireBaseNotificationClient;
import com.mad.dto.Notification;
import com.mad.dto.OuterNotification;
import com.mad.models.MADUser;
import com.mad.repository.MADUserRepository;

@RestController
@EnableFeignClients
public class NotificationController {
	
final String AUTHORIZATION="key=AAAASxTK1Zc:APA91bGYwZrw31HYrALDg1Z5o50EY6N4PbME-TbREE9kmTPsz-lxJ7lpVI6fo_bZUT0J5Zo0pa8c_cphzcS4W_EbueJcFUUkA4LP4NS1wuwbeeBw2PM-wxl2TqeROpFc84ClYZwdTjkI";
	
	final String contentType="application/json";
	
	@Autowired
	FireBaseNotificationClient fireBaseNotificationClient;
	
	@Autowired
	MADUserRepository mADUserRepository;
	
	@PostMapping("/notification")
	public ResponseEntity<String>  sendNotification(@RequestBody OuterNotification notificationObj ) {
		System.out.println("notificationObj: "+notificationObj);
        fireBaseNotificationClient.sendNotification(AUTHORIZATION, contentType, notificationObj);
        return ResponseEntity.ok("Success");
	}
	
	@PostMapping("/notification/{userId}")
	public ResponseEntity<String>  sendNotification(@PathVariable int userId, @RequestBody Notification notification ) {
		
		MADUser MadUser=mADUserRepository.findById(userId).get();
		OuterNotification outerNotification=OuterNotification.builder().to(MadUser.getToken()).notification(notification).build();
        fireBaseNotificationClient.sendNotification(AUTHORIZATION, contentType, outerNotification);
        return ResponseEntity.ok("Success");
	}

}
