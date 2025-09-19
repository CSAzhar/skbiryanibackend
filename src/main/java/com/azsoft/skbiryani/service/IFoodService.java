package com.azsoft.skbiryani.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.azsoft.skbiryani.dto.FoodRequest;
import com.azsoft.skbiryani.dto.FoodResponse;

public interface IFoodService {
	
//	String uploadFile(MultipartFile file);
	FoodResponse addFood(FoodRequest foodRequest, MultipartFile file, Long categoryId);
	List<FoodResponse> getAllFood();
	FoodResponse getFoodById(Long foodId);
	Boolean deleteFoodById(Long foodId);
	FoodResponse updateFood(Long foodId, FoodRequest foodRequest, MultipartFile file, Long categoryId);
	List<FoodResponse> getAllActiveFoods();
	Boolean toggleActiveFood(Long foodId);

}
