package com.azsoft.skbiryani.service;

import org.springframework.web.multipart.MultipartFile;

import com.azsoft.skbiryani.io.FoodRequest;
import com.azsoft.skbiryani.io.FoodResponse;

public interface FoodService {
	
//	String uploadFile(MultipartFile file);
	FoodResponse addFood(FoodRequest foodRequest, MultipartFile file);

}
