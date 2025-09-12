package com.azsoft.skbiryani.service;

import com.azsoft.skbiryani.dto.VerifyOtpRequestDto;
import com.azsoft.skbiryani.dto.VerifyOtpResponseDto;
import com.azsoft.skbiryani.entity.OtpVerification;

public interface IOtpService {
	
	public String generateOtp(String mobileNumber);
	public VerifyOtpResponseDto verifyOtp(VerifyOtpRequestDto requestDto);
	

}
