package com.azsoft.skbiryani.serviceImpl;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService{

	@Override
	public String generateOtp(String mobileNumber) {
		String otp = getRandomOtp();
		return otp;
	}
	
	private String getRandomOtp() {
		SecureRandom secureRandom = new SecureRandom();
		return String.format("%04d", secureRandom.nextInt(10000));
	}

}
