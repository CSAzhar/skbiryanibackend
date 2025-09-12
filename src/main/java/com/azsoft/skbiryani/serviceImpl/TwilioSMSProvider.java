package com.azsoft.skbiryani.serviceImpl;

import org.springframework.stereotype.Service;


import com.azsoft.skbiryani.service.IOtpSMSProvider;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSMSProvider implements IOtpSMSProvider{

	public static final String ACCOUNT_SID = "AC843957377afe4f9187849f257f949efc";
	public static final String AUTH_TOKEN = "ba3dc0adc7434a3c6c32540673c692f4";
	
	@Override
	public String sendOtpSMS(String mobile, String message) {
//		sendMessageByTwilio(mobile, message);
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










