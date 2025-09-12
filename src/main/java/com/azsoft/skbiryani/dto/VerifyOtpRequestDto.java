package com.azsoft.skbiryani.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VerifyOtpRequestDto {
	@NotNull(message = "Mobile cannot be null")
	@Pattern(regexp="\\d{10}", message = "Mobile should be 10 digit")
	private String mobile;
	private String otp;
}
