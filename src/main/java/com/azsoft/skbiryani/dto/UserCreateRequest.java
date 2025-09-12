package com.azsoft.skbiryani.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
	
	private String name;
	private String email;
	private String mobile;
	private String password;

}
