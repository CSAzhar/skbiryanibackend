package com.azsoft.skbiryani.io.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestMobileOTPLogin {
	
	private String mobile;

}
