package com.mad.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class OuterNotification implements Serializable{
	
	
	private String to;
	Notification notification;
}
/*
{

"notification":{
	"title": "demo title",
	"body": "demo body"
},
"to":"es7R8B0_RHacxdqn6Dga0B:APA91bGzovFTHQdeMeCzND6voRopnaTvBBWMwbStXTYs96m6RkInRw8ODeiBCUcnA17T7uCA566IhhnwaJGyoj88nMYRbYp67diMVqc1nr1xqIFNIkrfbfe1i95r2-tdXeiIWNoDGsXb"

}

*/