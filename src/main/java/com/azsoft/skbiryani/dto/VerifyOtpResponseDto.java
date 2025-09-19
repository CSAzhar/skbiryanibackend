package com.azsoft.skbiryani.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyOtpResponseDto {
	private String mobile;
	private String token;
}
