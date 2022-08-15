package com.mad.scheduler.notifications;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mad.client.FireBaseNotificationClient;
import com.mad.dto.Notification;
import com.mad.dto.OuterNotification;
import com.mad.models.MADUser;
import com.mad.models.UserToken;
import com.mad.repository.MADUserRepository;
import com.mad.repository.UserTokenRepository;


@Service
@EnableFeignClients
public class SolveProblemsNotificationService {

	//SERVER KEY
	final String AUTHORIZATION="key=AAAAHd2rzEo:APA91bHWLlQRn2lgiKbQ59J3J-C_T2T5bn-51Sh_GcgAynQMN8yIn3mVGhSEJ96jO280LagU-zOtrpIQ2TKnyp6mWY5Tis_WUkWoC6zeued8SC80Gtz0LeIyJX2sjPkEcJox6wCVhIH0";
	
	final String contentType="application/json";
	@Autowired
	private FireBaseNotificationClient fireBaseNotificationClient;
	
	@Autowired
	MADUserRepository mADUserRepository;
	
	@Autowired
	UserTokenRepository userTokenRepository;
	
	@Scheduled(fixedRate = 4320000) // for every 43200->12 hours and 5000 for every 5 secs
	public void sendNotificationToSolveProblems() {
		
		System.out.println("notification scheduler is running");
		 int leetcodeProblemNumber = (int) (Math.random() * (1000 - 1)) + 1;
		List<MADUser> usersToBeReminded=mADUserRepository.findByIsReminderOn(1).get();
		
	for(int i=0;i<usersToBeReminded.size();i++) {
		MADUser madUser=usersToBeReminded.get(i);
	//	List<UserToken> userTokens=userTokenRepository.findByUserId(madUser.getId()).get();
	//	for(UserToken userToken: userTokens) {
		
		Runnable runnable = ()->{
			Notification innerNotificationObj=Notification
					.builder()
					.body("Solve leetcode problem "+leetcodeProblemNumber)
					.title("Stay ahead in the leagues!!")
					.build();
	
			OuterNotification outerNotification=OuterNotification
					.builder()
					.to(madUser.getToken())
					.notification(innerNotificationObj)
					.build();
        fireBaseNotificationClient.sendNotification(AUTHORIZATION, contentType, outerNotification);
		};
		new Thread(runnable).start();
        
	//	}
		
	}
	}

}
