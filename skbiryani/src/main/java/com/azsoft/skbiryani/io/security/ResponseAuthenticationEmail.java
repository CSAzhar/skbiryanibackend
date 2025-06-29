package com.azsoft.skbiryani.io.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseAuthenticationEmail {
	
	private String email;
	private String token;

}
