package com.azsoft.skbiryani.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.azsoft.skbiryani.dto.AuthenticationRequestMobile;
import com.azsoft.skbiryani.dto.RequestOtpDto;
import com.azsoft.skbiryani.dto.ResponseAuthenticationMobilePassword;
import com.azsoft.skbiryani.dto.VerifyOtpRequestDto;
import com.azsoft.skbiryani.dto.VerifyOtpResponseDto;
import com.azsoft.skbiryani.service.IOtpService;
import com.azsoft.skbiryani.serviceImpl.AppUserDetailService;
import com.azsoft.skbiryani.utils.JwtUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/skb/user/")
@AllArgsConstructor
@CrossOrigin
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final AppUserDetailService userDetailService;
	private final IOtpService otpService;
	private final JwtUtil jwtUtil;
	
	@PostMapping("login-mob-pass")
	public ResponseEntity<ResponseAuthenticationMobilePassword> loginMail(@RequestBody AuthenticationRequestMobile requestMobile){
		
		authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(requestMobile.getMobile(), requestMobile.getPassword()));
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(requestMobile.getMobile());

		final String jwtToken = jwtUtil.generateJwtToken(userDetails);
		
		return ResponseEntity.ok().body(ResponseAuthenticationMobilePassword
				.builder()
				.mobile(requestMobile.getMobile())
				.token(jwtToken)
				.build());
	}
	
	
	@PostMapping("login-getotp")
	public ResponseEntity<String> getMobileOtp(@RequestBody @Valid RequestOtpDto requestOtpDto){
		return ResponseEntity.ok(otpService.generateOtp(requestOtpDto.getMobile()));
	}
	
	
	@PostMapping("verify-otp")
	public ResponseEntity<VerifyOtpResponseDto> verifyOtp(@RequestBody VerifyOtpRequestDto verifyOtpRequestDto){
		return ResponseEntity.ok(otpService.verifyOtp(verifyOtpRequestDto));
	}

}
