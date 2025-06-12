package com.azsoft.skbiryani.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azsoft.skbiryani.io.FoodRequest;
import com.azsoft.skbiryani.io.FoodResponse;
import com.azsoft.skbiryani.service.FoodServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/skb/food/")
@CrossOrigin
@AllArgsConstructor
public class FoodController {
	
	private FoodServiceImpl foodService;
	
	@PostMapping("save-food")
	public ResponseEntity<FoodResponse> saveFood(@ModelAttribute FoodRequest foodRequest, @RequestPart("file") MultipartFile file){
		return ResponseEntity.ok().body(foodService.addFood(foodRequest, file));
	}
	
	@GetMapping
	public ResponseEntity<List<FoodResponse>> getAllFoods(){
		return ResponseEntity.ok().body(foodService.getAllFood());
	}
	
	@GetMapping("{foodId}")
	public ResponseEntity<FoodResponse> getFoodById(@PathVariable("foodId") Long id){
		return ResponseEntity.ok().body(foodService.getFoodById(id));
	}
	
	@DeleteMapping("{foodId}")
	public ResponseEntity<Boolean> deleteFoodById(@PathVariable("foodId") Long foodId){
		return ResponseEntity.ok().body(foodService.deleteFoodById(foodId));
	}
	
	@PutMapping
	public ResponseEntity<FoodResponse> updateFoodById(@ModelAttribute FoodRequest foodRequest, @RequestPart("file") MultipartFile file){
		return ResponseEntity.ok().body(foodService.updateFood(foodRequest, file));
	}

}
