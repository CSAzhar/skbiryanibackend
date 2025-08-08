package com.azsoft.skbiryani.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/ping")
public class TestApiController {
	
	@GetMapping
	public ResponseEntity<String> testApiForAws(){
		System.out.println("=======Checked health from ping=======");
		return ResponseEntity.status(200).body("pong");
	}

}
