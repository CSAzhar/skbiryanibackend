package com.azsoft.skbiryani.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azsoft.skbiryani.entity.Category;
import com.azsoft.skbiryani.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/skb/category/")
@AllArgsConstructor
public class CategoryController {
	
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory(){
		return ResponseEntity.ok().body(categoryService.getAllCategory());
	}
	
	@PostMapping
	public ResponseEntity<Category> saveCategory(
													@ModelAttribute Category category,
													@RequestPart("file") MultipartFile file){
		return ResponseEntity.ok().body(categoryService.saveCategory(category, file));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteCategoryById(@PathVariable Long id){
		return ResponseEntity.ok().body(categoryService.deleteCategoryById(id));
	}
	
	
	

}
