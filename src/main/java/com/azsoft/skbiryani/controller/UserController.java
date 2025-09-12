package com.azsoft.skbiryani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azsoft.skbiryani.dto.ResponseUserCreate;
import com.azsoft.skbiryani.dto.UserCreateRequest;
import com.azsoft.skbiryani.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("skb/user/")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("create-user")
	public ResponseEntity<ResponseUserCreate> createUser(@RequestBody UserCreateRequest createRequest){
		return ResponseEntity.status(200).body(userServiceImpl.registerUser(createRequest));
	}

}
