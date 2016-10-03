package org.slevin.servlet;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.type.PhoneNumber;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;

public class TwilioUtil {

	
	
		public static final String ACCOUNT_SID = "ACf93a18291e93bfcdbbd744e44668cccc";//prod
		  public static final String AUTH_TOKEN = "80841bde7702dd3022658653b99c7ccf";
		  
//		  public static final String ACCOUNT_SID = "AC881f698acbedfab2a3eb5da6963788fa";//test
//		  public static final String AUTH_TOKEN = "0caa88fa70ae51eb061dab536f0689a2";

		  public static void main(String[] args) {
		    sendSms("+905345482946", "deneme");
		  }
	
		  public static String sendSms(String phoneNumber,String messageText){
			  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			    Message message = new MessageCreator(ACCOUNT_SID,
			      new PhoneNumber(phoneNumber), // TO number
			      new PhoneNumber("+12569297050"), // From Twilio number
//			      new PhoneNumber("+15005550006"),//test
			      messageText
			    ).execute();

			    
			    System.out.println(message.getSid());
			    return message.getSid();
		  }
}
