package com.azsoft.skbiryani.service;

public interface IOtpSMSProvider {
	
	public String sendOtpSMS(String mobile, String message);

}
