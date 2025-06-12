package com.azsoft.skbiryani.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import com.azsoft.skbiryani.config.AwsService;
import com.azsoft.skbiryani.entity.FoodEntity;
import com.azsoft.skbiryani.io.FoodRequest;
import com.azsoft.skbiryani.io.FoodResponse;
import com.azsoft.skbiryani.mapper.FoodMapper;
import com.azsoft.skbiryani.repository.FoodRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class FoodServiceImpl implements FoodService{

    private final S3Client s3Client;
	
	private AwsService awsService;
	private FoodRepository foodRepository;
	private FoodMapper foodMapper;

    FoodServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

	@Override
	public FoodResponse addFood(FoodRequest foodRequest, MultipartFile file) {
		
		String imageUrl= awsService.uploadImage(file);
		
		FoodEntity foodEntity = foodMapper.foodRequestToFoodEntity(foodRequest);
		foodEntity.setImageUrl(imageUrl);
		
		FoodEntity savedFood = foodRepository.save(foodEntity);
		
		FoodResponse foodResponse = foodMapper.foodEntityToFoodResponse(savedFood);
		
		return foodResponse;
	}

	@Override
	public List<FoodResponse> getAllFood() {
		return foodRepository.findAll().stream()
				.map(foodMapper::foodEntityToFoodResponse)
				.collect(Collectors.toList());
	}

	@Override
	public FoodResponse getFoodById(Long foodId) {
		return foodMapper.foodEntityToFoodResponse(foodRepository.findById(foodId).
								orElseThrow( ()-> new RuntimeException("Food not found for this id") ));
	}

	@Override
	public Boolean deleteFoodById(Long foodId) {
		FoodEntity foodEntity = foodRepository.findById(foodId).orElseThrow( () -> new RuntimeException("No food item found") );
		if(foodEntity != null) {
			String imageUrl = foodEntity.getImageUrl();
			Boolean isImageDeletedFromS3 = awsService.deleteImageFile( imageUrl.substring(imageUrl.lastIndexOf("/")+1) );
			if(isImageDeletedFromS3) {
				foodRepository.deleteById(foodId);
				return true;
			}
		}
		return false;
	}

	@Override
	public FoodResponse updateFood(FoodRequest foodRequest, MultipartFile file) {
		FoodEntity oldFood = foodRepository.findByName(foodRequest.getName());
		String newImageUrl;
		if(file != null && !file.isEmpty()) {
			newImageUrl = awsService.uploadImage(file);
			String oldImageUrl = oldFood.getImageUrl();
			Boolean isImageDeleted = awsService.deleteImageFile(oldImageUrl.substring(oldImageUrl.lastIndexOf("/")+1));
			if(isImageDeleted) oldFood.setImageUrl(newImageUrl);
			
		}
		oldFood.setName(foodRequest.getName());
		oldFood.setCategory(foodRequest.getCategory());
		oldFood.setDescription(foodRequest.getDescription());
		oldFood.setPrice(foodRequest.getPrice());
		
		FoodEntity newFood = foodRepository.save(oldFood);
		
		return foodMapper.foodEntityToFoodResponse(newFood);
		
	}
	
	

}













