package com.mad.client;

import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url="https://fcm.googleapis.com",name="USER-CLIENT")
public interface FireBaseNotificationClient {

	@PostMapping("/fcm/send")
	public void sendNotification(
			@RequestHeader("Authorization") String authorization,
			@RequestHeader("Content-Type") String contentType,
			@RequestBody JSONObject json
			);
	
}