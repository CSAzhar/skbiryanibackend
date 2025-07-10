package com.azsoft.skbiryani.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azsoft.skbiryani.io.security.AuthenticationRequestEmail;
import com.azsoft.skbiryani.io.security.ResponseAuthenticationEmail;
import com.azsoft.skbiryani.service.AppUserDetailService;
import com.azsoft.skbiryani.utils.JwtUtil;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/skb/user/")
@AllArgsConstructor
@CrossOrigin
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final AppUserDetailService userDetailService;
	private final JwtUtil jwtUtil;
	
	@PostMapping("login-email")
	public ResponseEntity<ResponseAuthenticationEmail> loginMail(@RequestBody AuthenticationRequestEmail requestEmail){
		System.out.println("con -1");
		authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(requestEmail.getEmail(), requestEmail.getPassword()));
		System.out.println("con -2");
		final UserDetails userDetails = userDetailService.loadUserByUsername(requestEmail.getEmail());
		System.out.println("con -3");
		final String jwtToken = jwtUtil.generateJwtToken(userDetails);
		System.out.println("con -4");
		return ResponseEntity.ok().body(ResponseAuthenticationEmail
				.builder()
				.email(requestEmail.getEmail())
				.token(jwtToken)
				.build());
	}
	
	@PostMapping("login-getotp")
	public ResponseEntity<?> getMobileOtp(){
		return null;
	}
	
	@PostMapping("login-otp")
	public ResponseEntity<?> loginOtp(){
		return null;
	}

}
