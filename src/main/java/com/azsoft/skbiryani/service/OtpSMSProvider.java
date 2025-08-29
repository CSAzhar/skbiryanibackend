package com.azsoft.skbiryani.service;

public interface OtpSMSProvider {
	
	public String sendOtpSMS(String mobile, String otp);

}
