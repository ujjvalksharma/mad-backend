package com.mad.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mad.client.FireBaseNotificationClient;
import com.mad.dto.Notification;

@RestController
@EnableFeignClients
public class NotificationController {
	
final String AUTHORIZATION="key=AAAASxTK1Zc:APA91bGYwZrw31HYrALDg1Z5o50EY6N4PbME-TbREE9kmTPsz-lxJ7lpVI6fo_bZUT0J5Zo0pa8c_cphzcS4W_EbueJcFUUkA4LP4NS1wuwbeeBw2PM-wxl2TqeROpFc84ClYZwdTjkI";
	
	final String contentType="application/json";
	
	@Autowired
	FireBaseNotificationClient fireBaseNotificationClient;
	
	@PostMapping("/notification")
	public void sendNotification(@RequestBody Notification notificationObj ) {
		
		JSONObject json = new JSONObject();
        json.put("to", notificationObj.getToken());
        JSONObject notification = new JSONObject();
        notification.put("title", notificationObj.getTitle());
        notification.put("body", notificationObj.getBody());
        json.put("notification", notification);
        fireBaseNotificationClient.sendNotification(AUTHORIZATION, contentType, json);
        
	}

}
