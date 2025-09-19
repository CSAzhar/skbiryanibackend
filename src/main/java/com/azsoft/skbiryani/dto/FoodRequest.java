package com.azsoft.skbiryani.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class FoodRequest {
	
	private String name;
	private String description;
	private double price;
	private Long categoryId;
	

}
