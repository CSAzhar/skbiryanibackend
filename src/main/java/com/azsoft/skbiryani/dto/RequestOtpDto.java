package com.azsoft.skbiryani.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RequestOtpDto {
	
	@NotNull(message = "Mobile number is required")
	@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	private String mobile;
}
