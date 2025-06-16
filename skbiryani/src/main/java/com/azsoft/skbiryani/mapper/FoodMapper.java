package com.azsoft.skbiryani.mapper;

import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.entity.Category;
import com.azsoft.skbiryani.entity.FoodEntity;
import com.azsoft.skbiryani.io.FoodRequest;
import com.azsoft.skbiryani.io.FoodResponse;

@Service
public class FoodMapper {
	
	public FoodEntity foodRequestToFoodEntity(FoodRequest foodRequest, Category category) {
		return  FoodEntity.builder()
				.name(foodRequest.getName())
				.description(foodRequest.getDescription())
				.price(foodRequest.getPrice())
				.category(category)
				.isAvailable(true)
				.build();
	}
	
	public FoodResponse foodEntityToFoodResponse(FoodEntity foodEntity) {
		return FoodResponse.builder()
				.id(foodEntity.getId())
				.name(foodEntity.getName())
				.description(foodEntity.getDescription())
				.price(foodEntity.getPrice())
				.category(foodEntity.getCategory())
				.imageUrl(foodEntity.getImageUrl())
				.isAvailable(foodEntity.isAvailable())
				.build();
	}

}
