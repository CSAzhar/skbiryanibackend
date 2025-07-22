package com.azsoft.skbiryani.io.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUserCreate {
	
	private Long userId;
	private String name;
	private String email;
	private String mobile;

}
