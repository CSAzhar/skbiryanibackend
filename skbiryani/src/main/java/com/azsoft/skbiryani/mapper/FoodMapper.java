package com.azsoft.skbiryani.mapper;

import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.entity.FoodEntity;
import com.azsoft.skbiryani.io.FoodRequest;
import com.azsoft.skbiryani.io.FoodResponse;

@Service
public class FoodMapper {
	
	public FoodEntity foodRequestToFoodEntity(FoodRequest foodRequest) {
		return  FoodEntity.builder()
				.name(foodRequest.getName())
				.description(foodRequest.getDescription())
				.price(foodRequest.getPrice())
				.category(foodRequest.getCategory())
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
				.build();
	}

}
