package com.azsoft.skbiryani.io;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodResponse {
	
	private Long id;
	private String name;
	private String description;
	private String imageUrl;
	private double price;
	private String category;

}
