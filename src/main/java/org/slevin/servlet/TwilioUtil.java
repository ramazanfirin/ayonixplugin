package org.slevin.servlet;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.type.PhoneNumber;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;

public class TwilioUtil {

	
	
		public static final String ACCOUNT_SID = "ACf93a18291e93bfcdbbd744e44668cccc";
		  public static final String AUTH_TOKEN = "80841bde7702dd3022658653b99c7ccf";

		  public static void main(String[] args) {
		    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    Message message = new MessageCreator(ACCOUNT_SID,
		      new PhoneNumber("+905345482946"), // TO number
		      new PhoneNumber("+12569297050"), // From Twilio number
		      "Hello from Java"
		    ).execute();

		    System.out.println(message.getSid());
		  }
	
}
