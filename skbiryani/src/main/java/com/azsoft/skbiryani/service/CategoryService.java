package com.azsoft.skbiryani.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.entity.Category;
import com.azsoft.skbiryani.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public boolean deleteCategoryById(Long id) {
		Boolean response;
		try {
			categoryRepository.deleteById(id);
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
