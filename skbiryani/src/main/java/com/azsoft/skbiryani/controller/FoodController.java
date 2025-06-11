package com.azsoft.skbiryani.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/save-food")
	public ResponseEntity<FoodResponse> saveFood(@ModelAttribute FoodRequest foodRequest, @RequestPart("file") MultipartFile file){
		return ResponseEntity.ok().body(foodService.addFood(foodRequest, file));
	}

}
