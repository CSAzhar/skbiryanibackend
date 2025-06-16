package com.azsoft.skbiryani.io;

import com.azsoft.skbiryani.entity.Category;

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
	private Category category;
	private Boolean isAvailable;
	
	private int statusCode;
    private String message;

}
