package com.azsoft.skbiryani.serviceImpl;

import com.azsoft.skbiryani.service.OtpSMSProvider;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;

public class TwilioSMSProvider implements OtpSMSProvider{

	@Override
	public String sendOtpSMS(String mobile, String otp) {
		 // Find your Account Sid and Token at twilio.com/console
		  final String ACCOUNT_SID = "AC843957377afe4f9187849f257f949efc";
		  final String AUTH_TOKEN = "ba3dc0adc7434a3c6c32540673c692f4";

		  
		    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		    Verification verification = Verification.creator(
		            "VAe9842c395d64238587b250aae7429c78",
		            "+918010905731",
		            "sms")
		        .create();

		    System.out.println(verification.getSid());
		    return "Message Sent";
	}
	

}
