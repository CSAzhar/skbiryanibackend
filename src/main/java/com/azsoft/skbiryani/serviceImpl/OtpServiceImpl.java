package com.azsoft.skbiryani.serviceImpl;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.dto.VerifyOtpRequestDto;
import com.azsoft.skbiryani.dto.VerifyOtpResponseDto;
import com.azsoft.skbiryani.entity.OtpVerification;
import com.azsoft.skbiryani.repository.OtpVerificationRepo;
import com.azsoft.skbiryani.service.IOtpSMSProvider;
import com.azsoft.skbiryani.service.IOtpService;

@Service
public class OtpServiceImpl implements IOtpService{
	@Autowired
	private IOtpSMSProvider otpSMSProvider;
	@Autowired
	private OtpVerificationRepo otpVerificationRepo;

	@Override
	public String generateOtp(String mobileNumber) {
		//deleting old otp
		mobileNumber = "+91"+mobileNumber;
		otpVerificationRepo.deleteByMobile(mobileNumber);
		String otp = getRandomOtp();
		String message = "OTP to login to SK Biryani is- "+otp+" . Valid for 2 mimutes";
		String otpStatus = otpSMSProvider.sendOtpSMS(mobileNumber, message);
		if(otpStatus.equals("sent")) {
			OtpVerification otpVerification = OtpVerification
													.builder()
													.mobile(mobileNumber)
													.otp(otp)
													.expiryTIme(LocalDateTime.now().plusMinutes(1))
													.used(false)
													.build();
			otpVerificationRepo.save(otpVerification);
		}
		return otp;
	}
	
	public VerifyOtpResponseDto verifyOtp(VerifyOtpRequestDto requestDto) {
		requestDto.setMobile("+91" + requestDto.getMobile());
		OtpVerification dbOtp = otpVerificationRepo
											.findByMobile(requestDto.getMobile())
											.orElseThrow(() -> new RuntimeException("No Otp found"));
		if(dbOtp.getOtp().equals(requestDto.getOtp()) && dbOtp.getExpiryTIme().isAfter(LocalDateTime.now())) {
			return VerifyOtpResponseDto
					.builder()
					.mobile(dbOtp.getMobile())
					.token("tokenGivne")
					.build();
		}
		return VerifyOtpResponseDto
				.builder()
				.mobile(dbOtp.getMobile())
				.token("invalidOTP")
				.build();
	}
	
	
	private String getRandomOtp() {
		SecureRandom secureRandom = new SecureRandom();
		return String.format("%04d", secureRandom.nextInt(10000));
	}

}











