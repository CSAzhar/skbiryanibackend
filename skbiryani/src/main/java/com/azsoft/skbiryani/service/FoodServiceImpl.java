package com.azsoft.skbiryani.service;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azsoft.skbiryani.config.AwsService;
import com.azsoft.skbiryani.entity.FoodEntity;
import com.azsoft.skbiryani.io.FoodRequest;
import com.azsoft.skbiryani.io.FoodResponse;
import com.azsoft.skbiryani.mapper.FoodMapper;
import com.azsoft.skbiryani.repository.FoodRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService{
	
	private AwsService awsService;
	private FoodRepository foodRepository;
	private FoodMapper foodMapper;

	@Override
	public FoodResponse addFood(FoodRequest foodRequest, MultipartFile file) {
		
		String imageUrl= awsService.uploadImage(file);
		
		FoodEntity foodEntity = foodMapper.foodRequestToFoodEntity(foodRequest);
		foodEntity.setImageUrl(imageUrl);
		
		FoodEntity savedFood = foodRepository.save(foodEntity);
		
		FoodResponse foodResponse = foodMapper.foodEntityToFoodResponse(savedFood);
		
		return foodResponse;
	}

}













