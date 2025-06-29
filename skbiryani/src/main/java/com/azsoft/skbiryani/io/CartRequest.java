package com.azsoft.skbiryani.io;

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
public class CartRequest {
	
	private Long userId;
	private Map<String, Integer> items = new HashMap<>();
	

}
