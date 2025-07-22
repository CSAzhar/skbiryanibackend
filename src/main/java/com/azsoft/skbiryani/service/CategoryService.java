package com.azsoft.skbiryani.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azsoft.skbiryani.config.AwsService;
import com.azsoft.skbiryani.entity.Category;
import com.azsoft.skbiryani.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final AwsService awsService;
	
	public Category saveCategory(Category category, MultipartFile file) {
		String imageUrl = awsService.uploadImage(file);
		category.setImageUrl(imageUrl);
		return categoryRepository.save(category);
	}
	
	public boolean deleteCategoryById(Long id) {
		Boolean response;
		try {
			Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("food not available"));
			String imageUrl = category.getImageUrl();
			String awsImageUrl = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
			Boolean isImageDeleted = awsService.deleteImageFile(awsImageUrl);
			if(isImageDeleted) categoryRepository.deleteById(id);
			response = true;
		} catch (Exception e) {
			response = false;
		}
		return response;
		
	}
	
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}

}
