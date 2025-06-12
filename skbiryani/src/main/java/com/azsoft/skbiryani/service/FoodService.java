package com.azsoft.skbiryani.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.azsoft.skbiryani.io.FoodRequest;
import com.azsoft.skbiryani.io.FoodResponse;

public interface FoodService {
	
//	String uploadFile(MultipartFile file);
	FoodResponse addFood(FoodRequest foodRequest, MultipartFile file);
	List<FoodResponse> getAllFood();
	FoodResponse getFoodById(Long foodId);
	Boolean deleteFoodById(Long foodId);
	FoodResponse updateFood(Long foodId, FoodRequest foodRequest, MultipartFile file);

}
