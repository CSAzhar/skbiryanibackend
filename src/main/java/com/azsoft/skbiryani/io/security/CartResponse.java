package com.azsoft.skbiryani.io.security;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
	
	private Long id;
	private Long userId;
	private Map<Long, Integer> items = new HashMap<>();
	
	private Integer statusCode;
	private String message;
	

}
