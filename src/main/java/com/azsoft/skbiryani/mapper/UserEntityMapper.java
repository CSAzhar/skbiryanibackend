package com.azsoft.skbiryani.mapper;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.azsoft.skbiryani.entity.UserEntity;
import com.azsoft.skbiryani.io.security.ResponseUserCreate;
import com.azsoft.skbiryani.io.security.UserCreateRequest;
import com.azsoft.skbiryani.repository.UserRepository;

@Component
public class UserEntityMapper {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserEntity requestUserEntityToUserEntity(UserCreateRequest request) {
		Long userId;
		do {
			userId = generateRandomUserId();
		} while (userRepository.existsById(userId));
		
		 return UserEntity.builder()
		.userId(userId)
		.name(request.getName())
		.email(request.getEmail())
		.mobile(request.getMobile())
		.password(passwordEncoder.encode(request.getPassword()))
		.build();
		
	}
	
	
	
	private Long generateRandomUserId() {
		return (long) (1000 + new Random().nextInt(999000));
	}



	public ResponseUserCreate userEntityToResponseUserCreate(UserEntity savedUser) {
		return ResponseUserCreate.builder()
				.userId(savedUser.getUserId())
				.name(savedUser.getName())
				.email(savedUser.getEmail())
				.mobile(savedUser.getMobile())
				.build();
				
	}

}
