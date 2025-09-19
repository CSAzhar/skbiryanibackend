package com.azsoft.skbiryani.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseAuthenticationMobilePassword {
	
	private String mobile;
	private String token;

}
