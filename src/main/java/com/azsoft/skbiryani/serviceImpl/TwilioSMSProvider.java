package com.azsoft.skbiryani.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.azsoft.skbiryani.service.IOtpSMSProvider;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSMSProvider implements IOtpSMSProvider{

	@Value("${twilio_account_sid}")
	public String ACCOUNT_SID;
	@Value("${twilio_auth_token}")
	public String AUTH_TOKEN;
	
	@Override
	public String sendOtpSMS(String mobile, String message) {
		sendMessageByTwilio(mobile, message);
		return "sent";
	}
	
	
	
	public void sendMessageByTwilio(String mobile, String inputMessage) {
		PhoneNumber to = new PhoneNumber(mobile);
		PhoneNumber from = new PhoneNumber("+14434023159");
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	    Message message = Message.creator(to, from, inputMessage).create();
	    
	    System.out.println(message.getSid());
	  }
	

}










