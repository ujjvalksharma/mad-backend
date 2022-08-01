package com.mad.client;

import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mad.dto.Notification;

@FeignClient(url="https://fcm.googleapis.com",name="notification-feign-client")
public interface FireBaseNotificationClient {

   @PostMapping(path = "/fcm/send", consumes = "application/json", produces = "application/json")
	public void sendNotification(
			@RequestHeader(value ="Authorization") String authorization,
			@RequestHeader(value ="Content-Type") String contentType,
			@RequestBody Notification notification
			);
	
}