package com.mad.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mad.client.FireBaseNotificationClient;
import com.mad.dto.LeagueMemberDTO;
import com.mad.dto.Notification;
import com.mad.dto.OuterNotification;
import com.mad.models.MADUser;
import com.mad.repository.MADUserRepository;

@RestController
@EnableFeignClients
public class NotificationController {
	
final String AUTHORIZATION="key=AAAAHd2rzEo:APA91bHWLlQRn2lgiKbQ59J3J-C_T2T5bn-51Sh_GcgAynQMN8yIn3mVGhSEJ96jO280LagU-zOtrpIQ2TKnyp6mWY5Tis_WUkWoC6zeued8SC80Gtz0LeIyJX2sjPkEcJox6wCVhIH0";
	
	final String contentType="application/json";
	
	@Autowired
	FireBaseNotificationClient fireBaseNotificationClient;
	
	@Autowired
	MADUserRepository mADUserRepository;
	
	@Autowired
	LeagueController leagueController;
	
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
	
	@PostMapping("/notification/user/{userId}/league/{leagueId}")
	public ResponseEntity<String>  sendNotificationToLeague(@PathVariable int userId, @RequestBody Notification notification,@PathVariable int leagueId ) {
		
		List<LeagueMemberDTO> leagueMemberDtoList= leagueController.getLeagueMemberDetails(leagueId).getBody();
		
		for(LeagueMemberDTO member: leagueMemberDtoList) {
			
			Runnable runnable =()->{
			if(member.getMADUser().getToken()!=null && member.getMADUser().getId()!=userId) {
			OuterNotification outerNotification=OuterNotification.builder().to(member.getMADUser().getToken()).notification(notification).build();
	        fireBaseNotificationClient.sendNotification(AUTHORIZATION, contentType, outerNotification);
			}
		};
			new Thread(runnable).start();
			
		}
		 return ResponseEntity.ok("Success");
	}

}
